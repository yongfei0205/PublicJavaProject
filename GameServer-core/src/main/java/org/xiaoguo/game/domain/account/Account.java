package org.xiaoguo.game.domain.account;

import io.netty.channel.Channel;

import org.xiaoguo.game.net.NetBuffer;

public class Account {
	private int id;
	private String username;
	private String password;
	private Channel channel;
	
	/**
	 * 是否在线
	 * @return
	 */
	public boolean isOnline(){
		return channel!=null && channel.isOpen();
	}
	
	public void sendMsg(byte[] msg){
		if(isOnline()){
			channel.write(new NetBuffer(msg));
		}
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
