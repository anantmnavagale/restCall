package com.anant.config.events;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
	
	@Override
	public void initialize() {	
		super.initialize();
		System.out.println("ThreadPoolTaskExecutor initialized");
	}

}
