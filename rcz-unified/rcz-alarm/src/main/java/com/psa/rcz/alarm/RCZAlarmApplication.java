package com.psa.rcz.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	    "com.psa.rcz.alarm",
	    "com.inetpsa.rcz.externalapihandler"   // IF you want to load external module beans
	})
public class RCZAlarmApplication {
	
	public static void main(String[] args) {
	    SpringApplication.run(com.psa.rcz.alarm.RCZAlarmApplication.class, args);
	  }

}






