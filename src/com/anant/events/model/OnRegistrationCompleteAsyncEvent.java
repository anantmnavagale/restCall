package com.anant.events.model;

import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteAsyncEvent extends ApplicationEvent implements RegistrationEvent{

	private static final long serialVersionUID = 1L;
	private final User user;
	private String appUrl;
	public OnRegistrationCompleteAsyncEvent(User user,String appUrl ) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}
	
	public User getUser() {
		return user;
	}
	public String getAppUrl() {
		return appUrl;
	}
	
	
	
	

}
