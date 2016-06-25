package com.diguage.dangle.studies.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

import java.awt.print.Book;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by diguage on 15/11/9.
 */
public class JoinerDemo {
    @Test
    public void testJoiner() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        String sql = String.format("select * from sku where id in (%s)", Joiner.on(",").skipNulls().join(idList));
        System.out.println(sql);
    }

    public String buildString(List<String> stringList, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (String s : stringList) {
            if (s != null) {
                builder.append(s).append(delimiter);
            }
        }
        builder.setLength(builder.length() - delimiter.length());
        return builder.toString();
    }

    @Test
    public void test() throws IOException {
Splitter splitter = Splitter.on('|').trimResults();//.omitEmptyStrings();
//Next call returns a new instance, does not
// modify the original!
//splitter.trimResults().omitEmptyStrings();
//Result would still contain empty elements
Iterable<String> parts = splitter.split("1|2|3|||");
System.out.println(parts);
    }
//
//public String toString() {
//    return Objects.toStringHelper(this)
//            .omitNullValues()
//            .add("title", title)
//            .add("author", author)
//            .add("publisher", publisher)
//            .add("price", price)
//            .add("isbn", isbn).toString();
//}
//
//public int hashCode() {
//    return Objects.hashCode(title, author, publisher, isbn);
//}
//
//public int compareTo(Book o) {
//    int result = this.title.compareTo(o.getTitle());
//    if (result != 0) {
//        return result;
//    }
//    result = this.author.compareTo(o.getAuthor());
//    if (result != 0) {
//        return result;
//    }
//    result = this.publisher.compareTo(o.getPublisher());
//    if (result != 0) {
//        return result;
//    }
//    return this.isbn.compareTo(o.getIsbn());
//}
//
//public int compareTo(Book o) {
//    return ComparisonChain.start()
//            .compare(this.title, o.getTitle())
//            .compare(this.author, o.getAuthor())
//            .compare(this.publisher, o.getPublisher())
//            .compare(this.isbn, o.getIsbn())
//            .compare(this.price, o.getPrice())
//            .result();
//}

}
