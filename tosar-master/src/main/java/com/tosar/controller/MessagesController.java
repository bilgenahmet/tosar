package com.tosar.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tosar.SecureUser;
import com.tosar.dao.MessageDao;
import com.tosar.model.Comment;
import com.tosar.model.Message;
import com.tosar.model.User;

@Controller
@RequestMapping("/messages")
public class MessagesController {
	@Autowired
	private MessageDao messageDao;

	/**
	 * Show all messages.
	 * 
	 * @return Tamamlandı
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getMessages(ModelMap model) {
		model.addAttribute("messages", messageDao.findAll());

		return "messages";
	}

	/**
	 * Show one message with the given id.
	 * 
	 * @param id
	 * @return Tamamlandı
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getMessage(@PathVariable int id, ModelMap model) {
		model.addAttribute("message", messageDao.findOne(id));
		model.addAttribute("newComment", new Comment());

		return "message";
	}

	/**
	 * Edit the messages with the given id.
	 * 
	 * @param id
	 * @return Tamamlandı
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editMessage(@PathVariable int id, Model model,
			@AuthenticationPrincipal SecureUser secureUser) throws Exception {
		Message message = this.messageDao.findOne(id);
		model.addAttribute("message", message);

		if (message.getOwner().equals(secureUser.getUser())) {
			return "new-message";
		} else {
			throw new Exception("No kidding, this not your message!!!");
		}
	}

	/**
	 * Create new message.
	 * 
	 * @param model
	 * @return Tamamlandı
	 */
	@RequestMapping("/new")
	public String newMessage(ModelMap model) {
		model.addAttribute("message", new Message());

		return "new-message";
	}

	/**
	 * Create new message.
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newMessage(@ModelAttribute Message message,
			@AuthenticationPrincipal SecureUser secureUser) throws Exception {
		User user = secureUser.getUser();

		if (message.getId() == 0) {
			message.setDate(new Date());
			message.setCategory("gülme");
			message.setOwner(user);
			message.setVote(0);
		} else {
			Message original = messageDao.findOne(message.getId());

			if (!original.getOwner().equals(user))
				throw new Exception("No kidding, this not your message!!!");

			original.setTitle(message.getTitle());
			original.setMessage(message.getMessage());
			original.setDate(new Date());
		}

		this.messageDao.saveOrUpdate(message);

		return "redirect:/messages";
	}

	/**
	 * Vote the message with the given id. Every user vote the messages only one
	 * time.
	 * 
	 * @param id
	 * @return Tamamlandı
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/vote", method = RequestMethod.GET)
	public String addVote(@PathVariable int id,
			@AuthenticationPrincipal SecureUser secureUser) throws Exception {
		Message message = this.messageDao.findOne(id);
		User user = secureUser.getUser();

		if (!message.getOwner().equals(user)) {
			message.setVote(message.getVote() + 1);

		} else {
			throw new Exception("No kidding, this is your message!!!");
		}

		return "redirect:/messages/" + id;
	}

	/**
	 * Delete the messages with the given id.
	 * 
	 * @param id
	 * @return Tamamlandı
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/delete")
	public String deleteMessage(@PathVariable int id,
			@AuthenticationPrincipal SecureUser secureUser) throws Exception {
		Message message = this.messageDao.findOne(id);
		if (message.getOwner().equals(secureUser.getUser())) {
			messageDao.deleteById(id);
		} else {
			throw new Exception("No kidding, this not your message!!!");
		}
		return "redirect:/messages";
	}
}
