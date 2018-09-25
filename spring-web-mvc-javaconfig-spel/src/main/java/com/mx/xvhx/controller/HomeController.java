package com.mx.xvhx.controller;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.xvhx.spelutils.SpelExpressionUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("homeController")
public class HomeController {

	private @Getter Random random = new Random();

	// value is evaluated just once
	@Value("#{@homeController.random.nextInt()}")
	private int value;

	@Autowired
	private SpelExpressionUtils spelExpressionUtils;

	@ResponseBody
	@RequestMapping(value = "/nextInt", method = RequestMethod.GET)
	public int nextInt(Locale locale, Model model) {

		log.info("calling spelExpressionUtils.eval bean method");

		// Dependency must be inyected in current bean
		return spelExpressionUtils.eval("@homeController.random.nextInt()", Integer.class);
	}

	@ResponseBody
	@RequestMapping(value = "/static/nextInt", method = RequestMethod.GET)
	public int staticNextInt(Locale locale, Model model) {

		log.info("calling SpelExpressionUtils.evalate static method");

		// No dependency required, just call static method
		return SpelExpressionUtils.evalate("@homeController.random.nextInt()", Integer.class);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {

		return "home";
	}

}
