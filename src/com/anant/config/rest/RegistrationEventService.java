package com.anant.config.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.anant.config.bean.ResponseBean;
import com.anant.events.model.OnRegistrationCompleteAsyncEvent;
import com.anant.events.model.OnRegistrationCompleteSyncEvent;
import com.anant.events.model.User;

//https://www.keyup.eu/en/blog/101-synchronous-and-asynchronous-spring-events-in-one-application

@RestController
@RequestMapping("/userRegistration")
public class RegistrationEventService {

	@Autowired
	ApplicationEventPublisher appEventPublisher;

	@RequestMapping("/asyncRegister")
	public ResponseBean asyncRegister(@RequestBody User user, WebRequest req) {
		ResponseBean bean = new ResponseBean();
		System.out.println("Asynchronous request started for user registration @ " + new Date());

		if (user.getEmailId() == null || user.getName() == null) {
			bean.add("message", "Incorrect input details");
		} else {
			// Register user

			// Send Confirmation URL
			initiateAsyncEvent(req, user);

			bean.add("message", "Registration process status : INITIATED");
			System.out.println("Asynchronous request completed for user registration @ " + new Date());
			System.out.println("-------------------------------------------------------");
		}
		return bean;
	}

	//curl -i -X POST -H "Content-Type:application/json" -d '{"name":"Anant", "emailId":"anana@anan.com"}' "localhost:8888/restCall/userRegistration/syncRegister"
	@RequestMapping("/syncRegister")
	public ResponseBean syncRegister(@RequestBody User user, WebRequest req) {
		ResponseBean bean = new ResponseBean();
		System.out.println("Synchronous request started for user registration @ " + new Date());
		// Register user

		// Send Confirmation URL
		initiateSyncEvent(req, user);
		user.setEmailId(null);
		bean.add("message", "Registration process status : COMPLETED.");
		bean.add("user", user);
		System.out.println("Synchronous request completed for user registration @ " + new Date());
		System.out.println("-------------------------------------------------------");
		return bean;
	}

	private void initiateAsyncEvent(WebRequest req, User user) {
		try {
			String appUrl = req.getContextPath();
			appEventPublisher.publishEvent(new OnRegistrationCompleteAsyncEvent(user, appUrl));
		} catch (Exception me) {
			me.printStackTrace();
		}
	}

	private void initiateSyncEvent(WebRequest req, User user) {
		try {
			String appUrl = req.getContextPath();
			appEventPublisher.publishEvent(new OnRegistrationCompleteSyncEvent(user, appUrl));
		} catch (Exception me) {
			me.printStackTrace();
		}
	}
}