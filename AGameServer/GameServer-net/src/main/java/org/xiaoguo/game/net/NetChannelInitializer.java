package org.xiaoguo.game.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NetChannelInitializer extends ChannelInitializer<NioSocketChannel> {

	
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast("decoder", new Decoder());
		p.addLast("encoder", new Encoder());
		p.addLast("handler", new ChannelHandler());
	}

	@Override
	protected void initChannel(NioSocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast("decoder", new Decoder());
		p.addLast("encoder", new Encoder());
		p.addLast("handler", new ChannelHandler());
		
	}

}
