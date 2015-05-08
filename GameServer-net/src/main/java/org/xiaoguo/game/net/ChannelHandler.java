package org.xiaoguo.game.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

public class ChannelHandler extends SimpleChannelInboundHandler<Request> {
	private static Logger logger = Logger.getLogger(ChannelHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request req)
			throws Exception {
		logger.info("cmd:"+req.getCmd());
	}

}
