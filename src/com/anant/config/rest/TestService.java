package com.anant.config.rest;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anant.config.bean.ResponseBean;
import com.anant.config.bean.TestDate;
import com.anant.events.model.User;

@RestController
@RequestMapping("/myNewTest")
public class TestService {

	// curl -i -X GET "localhost:8081/restCall/myNewTest/test"
	@RequestMapping("/test")
	public ResponseBean t1() {
		ResponseBean bean = new ResponseBean();
		bean.add("currentDate", new Date());
		return bean;
	}

	// curl -i -X GET "localhost:8081/restCall/myNewTest/"
	@RequestMapping("/")
	public ResponseBean t2() {
		ResponseBean bean = new ResponseBean();
		bean.add("Today's Date", new Date());
		return bean;
	}

	// curl -i -X GET "localhost:8081/restCall/myNewTest/"
	@RequestMapping("/testDate")
	public ResponseBean testDate(TestDate date) {
		ResponseBean bean = new ResponseBean();
		bean.add("myDate", date);
		return bean;
	}
	


}
