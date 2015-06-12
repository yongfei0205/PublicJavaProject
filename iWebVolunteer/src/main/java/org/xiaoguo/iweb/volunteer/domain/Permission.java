package org.xiaoguo.iweb.volunteer.domain;

public enum Permission {
	
	root(9,"管理员"),
	head(7,"负责人"),
	leader(5,"组长"),
	deputyLeader(3,"副组长"),
	user(1,"成员");
	
	private int id;
	private String name;
	Permission(int _id,String _name){
		this.id=_id;
		this.name=_name;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
