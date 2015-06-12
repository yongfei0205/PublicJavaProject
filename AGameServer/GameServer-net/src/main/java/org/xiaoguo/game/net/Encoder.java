package org.xiaoguo.game.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.apache.log4j.Logger;

public class Encoder extends MessageToByteEncoder<NetBuffer> {
	private static Logger logger = Logger.getLogger(Encoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, NetBuffer buf, ByteBuf out)
			throws Exception {
		out = ctx.alloc().directBuffer();
		byte[] data = buf.getMessages();
		int dataLength = data.length;
		logger.debug("=====length:[\t" + dataLength + "\t]=======");
		// д��Ϣ
		out.writeShort(NetConstants.MAGIC_HEADER);// Matgic Header
		out.writeInt(dataLength);// ��Ҫ����ָ����
		out.writeBytes(data);// ����protobuf����
		ctx.write(out);
		ctx.flush();
	}

}
