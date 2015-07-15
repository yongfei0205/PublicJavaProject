package org.xiaoguo.iweb.volunteer.domain;

import java.io.Serializable;

public class LoginUser implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -467447247961600931L;
	public String username;
	public String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}