package com.anant.config.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class EventsConfig {

	@Bean
	public DistributiveEventMulticaster applicationEventMulticaster() {

		ApplicationEventMulticaster sync = new SimpleApplicationEventMulticaster();
		ApplicationEventMulticaster async = new SimpleApplicationEventMulticaster();
		ThreadPoolTaskExecutor tpte = new MyThreadPoolTaskExecutor();
		tpte.initialize();
		((SimpleApplicationEventMulticaster) async).setTaskExecutor(tpte);

		return new DistributiveEventMulticaster(sync, async);
	}
}