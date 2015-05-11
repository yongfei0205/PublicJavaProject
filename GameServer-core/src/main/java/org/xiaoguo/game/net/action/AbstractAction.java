package org.xiaoguo.game.net.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.xiaoguo.game.net.ActionManager;
import org.xiaoguo.game.net.BaseAction;

public abstract class AbstractAction<P, T> implements BaseAction<P, T> {
	@Autowired
	private ActionManager actionManager;
	private int msgCode;

	protected AbstractAction(int msgCode) {
		this.msgCode = msgCode;
		actionManager.addAction(this);
	}

	@Override
	public abstract void execute(P p, T message);

}
