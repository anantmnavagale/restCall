package com.anant.events.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.anant.config.events.AsyncListener;
import com.anant.events.model.OnRegistrationCompleteAsyncEvent;

@Component
@AsyncListener
public class RegistrationAsyncListener implements ApplicationListener<OnRegistrationCompleteAsyncEvent> {

	@Override
	public void onApplicationEvent(OnRegistrationCompleteAsyncEvent event) {
		confirmRegistration(event);

	}

	private void confirmRegistration(OnRegistrationCompleteAsyncEvent event) {
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.sendRegistrationEmail(event);
	}

}