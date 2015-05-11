package org.xiaoguo.game.net.action;

import org.xiaoguo.game.domain.account.Account;

public abstract class AccountAction<T> extends AbstractAction<Account, T> {

	protected AccountAction(int msgCode) {
		super(msgCode);
	}

	@Override
	public abstract void execute(Account account, T message);
}
