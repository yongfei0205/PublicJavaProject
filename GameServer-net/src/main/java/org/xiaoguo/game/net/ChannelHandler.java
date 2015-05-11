package org.xiaoguo.game.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import org.apache.log4j.Logger;

public class ChannelHandler extends SimpleChannelInboundHandler<Request> {
	private static Logger logger = Logger.getLogger(ChannelHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request req)
			throws Exception {
		logger.info("cmd:" + req.getCmd());
		AttributeKey<Integer> a = new AttributeKey<Integer>("ssid");
		if (ctx.attr(a).get() != null) {
			logger.debug("ssid:" + ctx.attr(a).get());
		} else {
			ctx.attr(a).set(999);
			logger.debug("ssid: is null!");
		}

	}

}
