package org.xiaoguo.game.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class Request {
	/**
	 * ÷∏¡Ó±‡∫≈
	 */
	private int cmd;

	/**
	 * protobuf∂‘œÛ
	 */
	private byte[] bytes;
	
	public Request(byte[] bytes)
	{
		try
		{
			DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));
			cmd=dis.readInt();
			bytes=new byte[dis.available()];
			dis.read(bytes);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
