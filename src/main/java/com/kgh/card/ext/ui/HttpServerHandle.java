package com.kgh.card.ext.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * 核心服务处理器
 */
public class HttpServerHandle extends SimpleChannelInboundHandler<FullHttpRequest> {

	private static final Logger logger = LoggerFactory.getLogger(HttpServerHandle.class);
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

		if (!request.decoderResult().isSuccess()) {
			Throwable t = request.decoderResult().cause();
			if(t != null) {
				logger.error("解析请求报文出错！", t);
			}
			response.setStatus(HttpResponseStatus.BAD_REQUEST);
		}

		// 限制请求方法
		HttpMethod httpMethod = request.method();
		if (httpMethod.compareTo(HttpMethod.POST) != 0) {
			response.content().writeBytes("METHOD NOT ALLOWED".getBytes());
			response.setStatus(HttpResponseStatus.METHOD_NOT_ALLOWED);
		}

		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
