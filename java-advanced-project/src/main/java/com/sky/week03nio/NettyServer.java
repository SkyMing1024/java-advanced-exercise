package com.sky.week03nio;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGoup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.group(boosGroup,workGoup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                                  @Override
                                  public void initChannel(SocketChannel ch) throws Exception {
                                      // 编码
                                      ch.pipeline().addLast(new HttpResponseEncoder());
                                      // 解码
                                      ch.pipeline().addLast(new HttpRequestDecoder());
                                      ch.pipeline().addLast(new HttpServerInboundHandler());
                                  }
                              });
        ChannelFuture channelFuture = bootstrap.bind(8000).sync();
        channelFuture.channel().closeFuture().sync();

        workGoup.shutdownGracefully();
        boosGroup.shutdownGracefully();
    }
}
