package com.diguage.dangle.cl1024;

import com.google.common.collect.Queues;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.NamedRunnable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 草榴专用爬虫
 *
 * @author diguage
 * @since 2016/5/30.
 */
public class Crawler {
    private static final Logger log = LoggerFactory.getLogger(Crawler.class);
    private final OkHttpClient client;
    private final Set<HttpUrl> fetchedUrls = Collections.synchronizedSet(new LinkedHashSet<HttpUrl>());
    private final BlockingQueue<HttpUrl> queue = Queues.newLinkedBlockingQueue();
    private final SecureRandom random = new SecureRandom();

    public final String webHost = "http://cl.fffkkk.me";
    public final String indexPage = webHost + "/index.php";
    public final String firstPage = webHost + "/htm_data/20/1605/1918022.html";
    public final String urlFormater = webHost + "/read.php?tid=1918022&fpage=0&toread=&page=%d";
    private final Pattern pattern = Pattern.compile("page=(\\d+)");

    public Crawler(OkHttpClient client) {
        this.client = client;
    }

    private void parallelDrainQueue(int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new NamedRunnable("Crawler %s", i) {
                @Override
                protected void execute() {
                    try {
                        drainQueue();
                        TimeUnit.SECONDS.sleep(random.nextInt(20));
                    } catch (Exception e) {
                        log.error("something error", e);
                    }
                }
            });
        }
    }

    private void drainQueue() throws InterruptedException {
        for (HttpUrl httpUrl; (httpUrl = queue.take()) != null; ) {
            if (!fetchedUrls.add(httpUrl)) {
                continue;
            }
            try {
                fetch(httpUrl);
            } catch (Exception e) {
                log.error("fetch context error, {}", httpUrl, e);
            }
        }
    }

    private void fetch(HttpUrl httpUrl) throws IOException {
//        RequestBody body = RequestBody.create(MediaType.parse("text/html; charset=gb2312"), "");
//        Request request = new Request.Builder()
//                .url(httpUrl)
//                .post(body)
//                .build();
//        final Response response = new Response.Builder()
//                .request(request)
//                .protocol(Protocol.HTTP_1_1)
//                .code(200)
//                .body(ResponseBody.create(MediaType.parse("text/html; charset=gb2312"), "abc"))
//                .build();
//        client.interceptors().add(new Interceptor() {
//            public Response intercept(Chain chain) throws IOException {
//                return response;
//            }
//        });
        // TODO 如何优雅解决乱码问题?
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = client.newCall(request).execute();
        String responseSource = response.networkResponse() != null
                ? ("(network: " + response.networkResponse().code() + " over " + response.protocol() + ")")
                : "(cache)";
        int responseCode = response.code();
        log.info("{}: {} {}", responseCode, httpUrl, responseSource);

        String contentType = response.header("Content-Type");
        if (responseCode != 200 || contentType == null) {
            response.body().close();
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(response.body().byteStream(), "GB2312"));

        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[8192];
        int read;
        while ((read = br.read(buffer, 0, buffer.length)) > 0) {
            builder.append(buffer, 0, read);
        }
        String full = builder.toString();

        Document document = Jsoup.parse(full, httpUrl.toString());
        if (firstPage.equals(httpUrl.toString())) {
            parsePaging(document);
        }

        Elements contentElements = document.select("#main div.t.t2");
        for (Element ce : contentElements) {
            Elements authorElements = ce.select("table tbody tr th b");
            for (Element ae : authorElements) {
                System.out.print(ae.html() + "\t");
            }
            // 评论中: table > tbody > tr.tr1.do_not_catch > th:nth-child(2) > table > tbody > tr > td > div.tpc_content
            // 评论中: table > tbody > tr:nth-child(1) > th:nth-child(2) > div.tpc_content
            Elements articleElements = ce.select("table tbody tr th div.tpc_content");
            for (Element ae : articleElements) {
                System.out.println(ae.html().length());
            }
        }
    }

    private void parsePaging(Document document) {
        Elements pageElements = document.select("#main .pages");
        Element pageElement = pageElements.first();
        int minPage = Integer.MAX_VALUE;
        int maxPage = 0;
        for (Element e : pageElement.select("a")) {
            String href = e.attr("href");
            Matcher pageMatcher = pattern.matcher(href);
            while (pageMatcher.find()) {
                int page = Integer.parseInt(pageMatcher.group(1));
                if (page < minPage) {
                    minPage = page;
                }
                if (page > maxPage) {
                    maxPage = page;
                }
            }
        }
        for (int i = minPage; i <= maxPage; i++) {
            if (i > 10) {
                break;
                // TODO 测试,不需要抓太多数据
            }
            queue.add(HttpUrl.parse(String.format(urlFormater, i)));
        }
    }

    private void addUrl(HttpUrl httpUrl) {
        this.queue.add(httpUrl);
    }

    public static void main(String[] args) {
        int threadCount = 2;
        long cacheByteCount = 1024L * 1024L * 100L;

        Cache cache = new Cache(new File("./cache.txt"), cacheByteCount);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();

        Crawler crawler = new Crawler(client);
        crawler.addUrl(HttpUrl.parse(crawler.firstPage));
//        crawler.addUrl(HttpUrl.parse(String.format(crawler.urlFormater, 5)));
        crawler.parallelDrainQueue(threadCount);
    }
}
