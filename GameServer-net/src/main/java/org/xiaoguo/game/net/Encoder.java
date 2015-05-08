package org.xiaoguo.game.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.apache.log4j.Logger;

public class Encoder extends MessageToByteEncoder<Response> {
	private static Logger logger = Logger.getLogger(Encoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Response res, ByteBuf out)
			throws Exception {
		out=ctx.alloc().directBuffer();
		byte[] data = res.getBytes();
	    int dataLength = data.length;
	    logger.debug("=====length:[\t"+dataLength+"\t]=======");
	    //写消息
	    out.writeShort(NetConstants.MAGIC_HEADER);//Matgic Header
	    out.writeInt(dataLength+4);// 需要加上指令编号
	    out.writeInt(res.getCmd());
	    out.writeBytes(data);//发送protobuf对象
		ctx.write(out);
		ctx.flush();
	}

}
