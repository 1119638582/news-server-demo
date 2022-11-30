package com.hu.news;

import com.google.api.ServiceOrBuilder;
import com.hu.news.service.NewsService;
import io.grpc.ServerBuilder;

import javax.xml.ws.Service;
import java.io.IOException;

public class GrpcService {
    private static final int port = 9999;
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(port).addService(new NewsService()).build().start();
        System.out.println(String.format("Grpc服务启动成功，端口号：%d.",port));
        server.awaitTermination();
    }
}
