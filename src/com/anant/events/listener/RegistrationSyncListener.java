package com.anant.events.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.anant.events.model.OnRegistrationCompleteSyncEvent;

@Component
public class RegistrationSyncListener implements ApplicationListener<OnRegistrationCompleteSyncEvent> {

	@Override
	public void onApplicationEvent(OnRegistrationCompleteSyncEvent event) {
		confirmRegistration(event);

	}

	private void confirmRegistration(OnRegistrationCompleteSyncEvent event) {
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.sendRegistrationEmail(event);
	}

	
}