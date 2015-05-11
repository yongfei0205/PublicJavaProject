package org.xiaoguo.game.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.apache.log4j.Logger;

public class ChannelHandler extends SimpleChannelInboundHandler<NetBuffer> {
	private static Logger logger = Logger.getLogger(ChannelHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NetBuffer buf)
			throws Exception {
		ActionManager actionManager = NetServer.getActionManager();

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
				buf.getMessages()));
		int cmd = dis.readInt();
		byte[] message = new byte[dis.available()];
		dis.read(message);
		 actionManager.dispath(cmd,message);
	}
}
