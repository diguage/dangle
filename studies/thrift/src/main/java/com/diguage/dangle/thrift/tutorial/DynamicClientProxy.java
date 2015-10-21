package com.diguage.dangle.thrift.tutorial;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考:[Thrift 个人实战--Thrift 的序列化机制](http://www.cnblogs.com/mumuxinfei/p/3876075.html)
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-20 18:31
 */
public class DynamicClientProxy<T> implements InvocationHandler {

    private List<ServerNode> serverNodes;

    public Object createProxy(Class<T> ts, RpcServerConfiguration configuration) {
        return Proxy.newProxyInstance(ts.getClassLoader(), ts.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (ServerNode serverNode : serverNodes) {
            TSocket tSocket = new TSocket(serverNode.getHost(), serverNode.getPort());
            tSocket.setTimeout(1000);

            TProtocol protocol = new TBinaryProtocol(tSocket);

            Class[] argsClass = new Class[]{TProtocol.class};
            Constructor<T> constructor = null; // (Constructor<T>) ts.getConstructor(argsClass); TODO 这个地方改如何生成?
            T client = constructor.newInstance(protocol);
            tSocket.open();

            return method.invoke(client, args);
        }
    }
}

class ServerNode {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

class RpcServerConfiguration {
    private List<ServerNode> serverNodes;

    public RpcServerConfiguration() {
        this.serverNodes = new ArrayList<ServerNode>();
    }

    public List<ServerNode> getServerNodes() {
        return serverNodes;
    }
}
