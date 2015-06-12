package org.xiaoguo.game.net;

import io.netty.channel.Channel;

@SuppressWarnings("rawtypes")
public interface ActionManager {

	
	public BaseAction getActionById(int id);

	public void addAction(BaseAction action);

	public void dispath(Channel channel,int cmd, byte[] message);

}
