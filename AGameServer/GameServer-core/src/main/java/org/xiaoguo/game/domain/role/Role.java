package org.xiaoguo.game.domain.role;

import org.xiaoguo.game.domain.account.Account;

public class Role {
	private int id;
	private Account account;
	public int getAccountId(){
		return account.getId();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
