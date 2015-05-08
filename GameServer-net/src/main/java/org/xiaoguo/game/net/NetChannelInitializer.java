package org.xiaoguo.game.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NetChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast("decoder", new Decoder());
		p.addLast("encoder", new Encoder());
		p.addLast("handler", new ChannelHandler());
	}

}
