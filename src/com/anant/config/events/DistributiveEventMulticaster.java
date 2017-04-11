package com.anant.config.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;

public class DistributiveEventMulticaster implements ApplicationEventMulticaster {

	private ApplicationEventMulticaster syncEventMulticaster;
	private ApplicationEventMulticaster asyncEventMulticaster;

	public DistributiveEventMulticaster(ApplicationEventMulticaster syncEventMulticaster, ApplicationEventMulticaster asyncEventMulticaster) {
		super();
		this.syncEventMulticaster = syncEventMulticaster;
		this.asyncEventMulticaster = asyncEventMulticaster;
	}

	@Override
	public void addApplicationListener(ApplicationListener listener) {
		// choose multicaster by annotation
		if (listener.getClass().getAnnotation(AsyncListener.class) != null) {
			asyncEventMulticaster.addApplicationListener(listener);
			System.out.println("Adding " + listener.getClass() + " to Async Multicaster");
		} else {
			syncEventMulticaster.addApplicationListener(listener);
			System.out.println("Adding " + listener.getClass() + " to Sync Multicaster");
		}
	}

	@Override
	public void addApplicationListenerBean(String listenerBeanName) {
		// do nothing
	}

	@Override
	public void removeApplicationListener(ApplicationListener listener) {
		asyncEventMulticaster.removeApplicationListener(listener);
		syncEventMulticaster.removeApplicationListener(listener);
	}

	@Override
	public void removeApplicationListenerBean(String listenerBeanName) {
		// do nothing
	}

	@Override
	public void removeAllListeners() {
		syncEventMulticaster.removeAllListeners();
		asyncEventMulticaster.removeAllListeners();
	}

	@Override
	public void multicastEvent(ApplicationEvent event) {
		syncEventMulticaster.multicastEvent(event);
		asyncEventMulticaster.multicastEvent(event);
	}

	// ******************** SETTERS ********************

	public void setAsyncEventMulticaster(ApplicationEventMulticaster asyncEventMulticaster) {
		this.asyncEventMulticaster = asyncEventMulticaster;
	}

	public void setSyncEventMulticaster(ApplicationEventMulticaster syncEventMulticaster) {
		this.syncEventMulticaster = syncEventMulticaster;
	}
}