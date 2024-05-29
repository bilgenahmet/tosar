package com.tosar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tosar.SecureUser;
import com.tosar.dao.MessageDao;
import com.tosar.dao.UserDao;
import com.tosar.model.User;

@Controller
public class UserDetailsControler {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MessageDao messageDao;

	/**
	 * Show user information and user's messages with user name.
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/u/{username}", method = RequestMethod.GET)
	public String userDetailByUsername(@PathVariable(value = "username") String username, ModelMap model) {
		if(userDao.isUsernameExist(username)){
			User user = this.userDao.getUserByUserName(username);
			model.addAttribute("user", user);
			model.addAttribute("messages", messageDao.getMessageByUserId(user.getId()));

			return "userdetail";
		}else {
			return "redirect:/messages";
		}
	}
	
	/**
	 * Edit user information with username.
	 * @param username
	 * @param model
	 * @param secureUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/u/{username}/edit", method = RequestMethod.GET)
	public String userEdit(@PathVariable String username, ModelMap model, @AuthenticationPrincipal SecureUser secureUser)
			throws Exception {
		User user = this.userDao.getUserByUserName(username);
		model.addAttribute("user", user);
		model.addAttribute("username", username);

		if (user.equals(secureUser.getUser())) {
			return "edit-user";
		} else {
			throw new Exception("No kidding, this is not you!!!");
		}
	}
	
	/**
	 * Edit user information with username.
	 * @param username
	 * @param user
	 * @param secureUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/u/{username}/edit", method = RequestMethod.POST)
	public String userEdit(@PathVariable String username, @ModelAttribute User user,
			@AuthenticationPrincipal SecureUser secureUser) throws Exception {
		User edituser = this.userDao.getUserByUserName(username);
		if (user.getId() != 0) {
			if (edituser.equals(secureUser)) {
				BCryptPasswordEncoder enc = new BCryptPasswordEncoder(12);
				user.setPassword(enc.encode(user.getPassword()));
				userDao.save(user);

			} else {
				throw new Exception("Some thing is wrong");
			}
		} else {
			throw new Exception("Some thing is wrong");
		}

		return "redirect:/u/" + username;
	}

	/**
	 * Check the given username is already used
	 * 
	 * @param username
	 */
	@RequestMapping(value = "/user/available/{username}", method = RequestMethod.GET)
	@ResponseBody
	public String getAvailability(@PathVariable String username) {
		if (!userDao.isUsernameExist(username)) return "{\"available\":true}";
		else return "{\"available\":false}";
	}
}
