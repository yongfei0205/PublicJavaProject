package org.xiaoguo.iweb.volunteer.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.thymeleaf.expression.Lists;

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
	
	public Permission getValue(int id){		
		return Arrays.asList(values()).stream().filter(p->p.id==id).findFirst().get();
	}
}
