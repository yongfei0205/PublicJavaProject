package org.xiaoguo.game.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.apache.log4j.Logger;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		startNetService(8888);
	}
	/**
	 * 启动网络服务
	 */
	public static void startNetService(final int port)
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();

		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new NetChannelInitializer());
			ChannelFuture f = b.bind(port).sync();
			logger.info("网络服务启动完毕");
			f.channel().closeFuture().sync();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

}
