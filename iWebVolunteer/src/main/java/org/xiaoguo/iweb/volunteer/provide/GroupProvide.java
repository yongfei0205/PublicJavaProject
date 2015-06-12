package org.xiaoguo.iweb.volunteer.provide;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.xiaoguo.iweb.volunteer.domain.Group;

@Component
public class GroupProvide {
	
	private List<Group> groups=new  ArrayList<Group>();
	@PostConstruct
	private void init(){
		groups.add(new Group(0,-1, "无分组"));
		groups.add(new Group(1,1, "搬运"));
		groups.add(new Group(2, -1,"摄影"));
		groups.add(new Group(3, 1,"网络"));
		groups.add(new Group(4, -1,"微博"));
		groups.add(new Group(5,0, "签到"));
		groups.add(new Group(6,0, "入场"));
		groups.add(new Group(7, 0,"引导"));
		groups.add(new Group(8, -1,"表演"));
		groups.add(new Group(9, 0, "礼仪"));
	}
}
