package org.xiaoguo.game.net.action.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xiaoguo.game.domain.account.Account;
import org.xiaoguo.game.domain.role.Role;
import org.xiaoguo.game.net.OutputHandlerManager;
import org.xiaoguo.game.net.action.AccountAction;
@Component
public class CreateRole extends AccountAction<Byte[]> {

	@Autowired
	private OutputHandlerManager handlerManager;
	protected CreateRole( ) {
		super(2);
	}

	@Override
	public void execute(Account account, Byte[] message) {
		Role role=new Role();
		role.setAccount(account);
		role.setId(account.getId());
		handlerManager.register(account.getChannel(), role);
	}

}
