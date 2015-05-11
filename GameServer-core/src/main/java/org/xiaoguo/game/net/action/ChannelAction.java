package org.xiaoguo.game.net.action;

import io.netty.channel.Channel;

public abstract class ChannelAction<T> extends AbstractAction<Channel, T> {

	protected ChannelAction(int msgCode) {
		super(msgCode);
	}

	@Override
	public abstract void execute(Channel channel, T message);

}
