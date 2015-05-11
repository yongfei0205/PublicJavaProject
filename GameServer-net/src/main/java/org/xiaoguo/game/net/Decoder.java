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
		// ���ȳ���Ҫ����10
		if (in.readableBytes() < 10) {
			return;
		}

		in.markReaderIndex();
		// ���magic header.
		short magicHeader = in.readShort();
		if (magicHeader != NetConstants.MAGIC_HEADER) {
			in.resetReaderIndex();
			throw new CorruptedFrameException("��Ч����Ϣͷ: " + magicHeader);
		}

		// ���ϵļ�⣬֪�����ݶ��Ѿ���ȡ��
		int dataLength = in.readInt();
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;
		}

		// �����ܵ�������ת����X2Request����.
		byte[] decoded = new byte[dataLength];
		in.readBytes(decoded);
		out.add(new NetBuffer(decoded));// ָ��+�û���+����
	}

}
