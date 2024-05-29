package com.tosar.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tosar.dao.UserDao;
import com.tosar.model.User;

@Controller
public class MainController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
	private String root(ModelMap model) {
		return "redirect:/messages";
	}

	@RequestMapping("/hello")
	private String hello(ModelMap model) {
		return "redirect:/messages";
	}
	
	@RequestMapping("/index")
	private String index(ModelMap model) {
		return "redirect:/messages";
	}

	@RequestMapping("/login")
	public String login(ModelMap m) {
		m.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newUser(ModelMap m) {
		m.addAttribute("user", new User());
		return "new-user";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newUser(@ModelAttribute User user) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder(12);
		user.setPassword(enc.encode(user.getPassword()));
		user.setRegDate(new Date());
		userDao.save(user);

		return "redirect:/login";
	}
}
