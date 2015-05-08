package org.xiaoguo.game.net;

public class Response {
	/**
	 * ÷∏¡Ó±‡∫≈
	 */
	private int cmd;
	
	/**
	 * protobuf∂‘œÛ
	 */
	private byte[] bytes;

	public int getCmd()
	{
		return cmd;
	}

	public void setCmd(int cmd)
	{
		this.cmd = cmd;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
