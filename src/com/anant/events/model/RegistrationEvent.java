package com.anant.events.model;

public interface RegistrationEvent {
	User getUser();
	String getAppUrl();
}
