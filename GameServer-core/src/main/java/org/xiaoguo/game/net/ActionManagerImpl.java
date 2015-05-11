package org.xiaoguo.game.net;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xiaoguo.game.net.action.AbstractAction;

@Service
public class ActionManagerImpl implements ActionManager{

	private Map<Short, BaseAction> actionMap=new HashMap<Short, BaseAction>();
	@Override
	public BaseAction getActionById(short id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAction(BaseAction action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispath(int cmd, byte[] message) {
		// TODO Auto-generated method stub
		
	}

}
