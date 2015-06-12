package org.xiaoguo.game.net.action.login;

import io.netty.channel.Channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xiaoguo.game.domain.account.Account;
import org.xiaoguo.game.net.OutputHandlerManager;
import org.xiaoguo.game.net.action.ChannelAction;

@Component
public class LoginAccountAction extends ChannelAction<byte[]> {

	@Autowired
	private OutputHandlerManager handlerManager;
	protected LoginAccountAction() {
		super(1);
	}

	@Override
	public void execute(Channel channel, byte[] message) {
		Account account=new Account();
		account.setChannel(channel);
		account.sendMsg(new byte[]{1});
		handlerManager.registerAccout(channel, account);
	}

}
