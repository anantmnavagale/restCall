package com.anant.config.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anant.config.bean.ResponseBean;

@RestController
@RequestMapping("/confirmRegistration")
public class ConfirmRegistrationService {

	@RequestMapping("/{token}")
	public ResponseBean syncRegister(@PathVariable String token) {
		ResponseBean bean = new ResponseBean();

		bean.add("message", "Thank you for confirmation");
		return bean;
	}
}
