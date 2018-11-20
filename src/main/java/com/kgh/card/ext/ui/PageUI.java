package com.kgh.card.ext.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kgh.card.core.ui.UI;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class PageUI implements UI {

	private static final Logger logger = LoggerFactory.getLogger(PageUI.class);

	public PageUI() {
		init();
	}

	private int port = 9999;

	private void init() {
		try {
			EventLoopGroup boosGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup();

			ServerBootstrap b = new ServerBootstrap();
			b.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
							ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(999999999));
							ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
							ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
							ch.pipeline().addLast("API-Server", new HttpServerHandle());
						}
					});

			ChannelFuture future = b.bind(port);
			logger.info("服务器启动成功，监听端口：" + port);
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
