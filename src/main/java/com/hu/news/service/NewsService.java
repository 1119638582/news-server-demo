package com.hu.news.service;

import com.hu.news.proto.NewsProto;
import com.hu.news.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Date;

public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {
    @Override
    public void list(NewsProto.NewsRequest request, StreamObserver<NewsProto.NewsRespose> responseObserver) {
        String data = request.getData();
        NewsProto.NewsRespose newList = null;
        try {
            NewsProto.NewsRespose.Builder newBuilder = NewsProto.NewsRespose.newBuilder();
            for (int i = 0; i < 100; i++) {
                NewsProto.News news = NewsProto.News.newBuilder().setId(i)
                        .setTotal("新闻标题" + i)
                        .setContent(data + "当日新闻 内容" + i)
                        .setCreateTime(new Date().getTime())
                        .build();
                newBuilder.addNews(news);
            }
            newList = newBuilder.build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(newList);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void hello(NewsProto.StringRequest request, StreamObserver<NewsProto.StringResponse> responseObserver) {
        String name = request.getName();
        NewsProto.StringResponse response = NewsProto.StringResponse.newBuilder().setResult("hi! " + name).build();
        //响应回传
        responseObserver.onNext(response);
        //结束
        responseObserver.onCompleted();
    }
}
