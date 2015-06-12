package org.xiaoguo.game.net;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoguo.game.GameMain;
import org.xiaoguo.game.net.action.AccountAction;
import org.xiaoguo.game.net.action.ChannelAction;
import org.xiaoguo.game.net.action.RoleAction;

@SuppressWarnings("rawtypes")
@Service
public class ActionManagerImpl implements ActionManager {
	private static Logger logger = Logger.getLogger(ActionManagerImpl.class);

	private Map<Integer, BaseAction> actionMap = new HashMap<Integer, BaseAction>();
	@Autowired
	private OutputHandlerManager outputHandlerManager;

	@PostConstruct
	public void init() {
		NetServer.setActionManager(this);
	}

	@Override
	public BaseAction getActionById(int id) {
		return actionMap.get(id);
	}

	@Override
	public void addAction(BaseAction action) {
		actionMap.put(action.getMsgCode(), action);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void dispath(Channel channel,int cmd, byte[] message) {
		BaseAction action = getActionById(cmd);
		Object p = null;

		if (RoleAction.class.isInstance(action))
			p = outputHandlerManager.get(channel);
		else if (AccountAction.class.isInstance(action))
			p = outputHandlerManager.getAccount(channel);
		else if (ChannelAction.class.isInstance(action))
			p = channel;
		else {
			logger.error("[channelId(" + channel.hashCode() + ")] [" + cmd
					+ "对应的Action(" + action.getClass().getSimpleName()
					+ ")] [excute方法的第一个参数错误]");
			return;
		}
		try 
		{			
			action.execute(p, message);
		} 
		catch (Exception e2) 
		{
			logger.error("发生异常，异常信息：", e2);
			return;
		}
	}

}
