package org.xiaoguo.game.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// 首先长度要大于10
		if (in.readableBytes() < 10) {
			return;
		}

		in.markReaderIndex();
		// 检测magic header.
		short magicHeader = in.readShort();
		if (magicHeader != NetConstants.MAGIC_HEADER) {
			in.resetReaderIndex();
			throw new CorruptedFrameException("无效的消息头: " + magicHeader);
		}

		// 不断的检测，知道数据都已经读取了
		int dataLength = in.readInt();
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;
		}

		// 将接受到的数据转换成X2Request对象.
		byte[] decoded = new byte[dataLength];
		in.readBytes(decoded);
		out.add(new Request(decoded));// 指令+用户名+数组
	}

}
