package org.xiaoguo.game.net;

@SuppressWarnings("rawtypes")
public interface ActionManager {

	
	public BaseAction getActionById(short id);

	public void addAction(BaseAction action);

	public void dispath(int cmd, byte[] message);

}
