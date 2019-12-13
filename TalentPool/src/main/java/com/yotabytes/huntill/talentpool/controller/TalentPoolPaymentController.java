package com.yotabytes.huntill.talentpool.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v2/")
public class TalentPoolPaymentController {
	
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public @ResponseBody String  home(Map<String, Object> model, HttpSession session) {

		return "New Controller";
	}
}
