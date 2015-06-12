package org.xiaoguo.game.test.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService2 {
	@Autowired
	private TestService service;

	@PostConstruct
	public void init() {
		service.test();
	}
}
