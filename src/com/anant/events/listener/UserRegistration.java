package com.anant.events.listener;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import com.anant.config.EmailSender;
import com.anant.events.model.RegistrationEvent;
import com.anant.events.model.User;

public class UserRegistration {

	public void sendRegistrationEmail(RegistrationEvent registrationEvent) {

		User user = registrationEvent.getUser();
		System.out.println("Sending email for " + user.getName() + " started @ " + new Date());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String token = UUID.randomUUID().toString();
		String recipientAddress = user.getEmailId();
		String subject = "Registration Confirmation";
		String confirmationUrl = registrationEvent.getAppUrl() + "/confirmRegistration/" + token;
		EmailSender emailSender = new EmailSender();
		emailSender.sendEmail(recipientAddress, subject, "http://" + getIpAddress() + ":8081" + confirmationUrl);
		System.out.println("Sending email for " + user.getName() + " completed @ " + new Date());

	}

	private String getIpAddress() {
		InetAddress ip = null;
		String ipAddress = "localhost";
		try {

			Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
			while (b.hasMoreElements()) {
				NetworkInterface e = b.nextElement();
				if (e.getDisplayName().equalsIgnoreCase("eth0")) {
					for (InterfaceAddress f : e.getInterfaceAddresses())
						if (f.getAddress().isSiteLocalAddress()) {
							ip = f.getAddress();
							ipAddress = ip.getHostAddress();
							break;
						}
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}

		return ipAddress;
	}

}
