package org.xiaoguo.game.net;


public class NetBuffer {
	
	
	private byte[] messages;

	public NetBuffer(byte[] bytes) {
		this.messages = bytes;
	}

	public byte[] getMessages() {
		return messages;
	}
}
