package com.diguage.dangle.thrift;

import com.diguage.dangle.thrift.gen.Pair;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Thrift序列化协议的研究
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-19 20:52
 */
public class ThriftSerializableDemo {
    private static final Logger log = LoggerFactory.getLogger(ThriftSerializableDemo.class);

    private static final String dataFilePath = "/Users/diguage/develop/java/Projects/dangle/studies/thrift/pair.data";

    /**
     * 将使用Thrift协议压缩的数据,写入到文件中.
     *
     * @throws IOException
     * @throws TException
     */
    @Test
    public void testWriteDataToFile() throws IOException, TException {
        Pair pair = new Pair();
        pair.setKey("D瓜哥");
        pair.setValue("http://www.diguage.com/");
        FileOutputStream fos = new FileOutputStream(dataFilePath);
        pair.write(new TBinaryProtocol(new TIOStreamTransport(fos)));
        fos.close();
    }


    /**
     * 将数据从文件中读取出来.
     *
     * @throws IOException
     * @throws TException
     */
    @Test
    public void testReadDataFromFile() throws IOException, TException {
        FileInputStream fis = new FileInputStream(new File(dataFilePath));
        Pair pair = new Pair();
        pair.read(new TBinaryProtocol(new TIOStreamTransport(fis)));

        log.debug("Key: {},  Value: {}", pair.getKey(), pair.getValue());
        fis.close();
    }

    /**
     * 研究Thrift,将多个对象的数据写入到文件中.
     */
    @Test
    public void testWriteMultiDataToFile() {
        // TODO 如何将多个对象的数据写入到文件中?或者说如何将由容器包装的对象的数据写入到文件中?
    }

    /**
     * 研究Thrift,从文件中读取多个对象的数据
     */
    @Test
    public void testReadMultiDataFromFile() {
        // TODO 如何从文件中读取多个对象的数据?或者说如何将由容器包装的对象的数据写入到文件中?
    }

    /**
     * 研究Thrift的格式,从字节层面手动转换数据.
     */
    @Test
    public void manualData() {
        // TODO 研究Thrift的数据格式
        // 可以参考:[Thrift 个人实战--Thrift 的序列化机制](http://www.cnblogs.com/mumuxinfei/p/3876075.html)
    }
}
