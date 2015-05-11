package org.xiaoguo.game.net.action;

import org.xiaoguo.game.domain.role.Role;

public abstract class RoleAction<T> extends AbstractAction<Role, T> {

	protected RoleAction(int msgCode) {
		super(msgCode);
	}

	public abstract void execute(Role role, T message);

}
