package org.xiaoguo.game.net;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.xiaoguo.game.domain.account.Account;
import org.xiaoguo.game.domain.role.Role;

@Service
public class OutputHandlerManager {
	private Map<Channel, Role> outputHanderMap = new ConcurrentHashMap<Channel, Role>();

	private Map<Channel, Account> channelAccountMap = new ConcurrentHashMap<Channel, Account>();
	
	public void register(Channel channel, Role role)
	{
		outputHanderMap.put(channel, role);
	}

	public Role get(Channel channel)
	{
		return outputHanderMap.get(channel);
	}
	
	public void registerAccout(Channel channel, Account account)
	{
		channelAccountMap.put(channel, account);
		account.setChannel(channel);
	}

	public Account getAccount(Channel channel)
	{
		return channelAccountMap.get(channel);
	}

}
