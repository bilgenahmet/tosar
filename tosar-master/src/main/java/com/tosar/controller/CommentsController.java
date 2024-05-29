package com.tosar.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tosar.SecureUser;
import com.tosar.dao.CommentDao;
import com.tosar.dao.MessageDao;
import com.tosar.model.Comment;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	@Autowired
	private MessageDao messageDao;

	@Autowired
	private CommentDao commentDao;

	/**
	 * Add new comment with the messageID.
	 * 
	 * @param id
	 * @return Tamamlandı
	 */
	@RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
	public String newComments(@PathVariable int id, ModelMap model) {
		model.addAttribute("comment", new Comment());
		model.addAttribute("messageId", id);

		return "new-comment";
	}

	/**
	 * Add new comment with the messageID.
	 * 
	 * @param id
	 * @return Tamamlandı
	 */
	@RequestMapping(value = "/new/{id}", method = RequestMethod.POST)
	public String newComments(@PathVariable int id, @ModelAttribute Comment comment,
			@AuthenticationPrincipal SecureUser secureUser) {
		comment.setUser(secureUser.getUser());
		comment.setDate(new Date());
		comment.setMessage(messageDao.findOne(id));
		commentDao.save(comment);

		return "redirect:/messages/" + id;

	}

	/**
	 * Delete comment with the id.
	 * 
	 * @param id
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/delete")
	public String deleteComment(@PathVariable int id, @AuthenticationPrincipal SecureUser secureUser) throws Exception {
		Comment comment = commentDao.findOne(id);
		int messageid = comment.getMessage().getId();
		if (comment.getUser().equals(secureUser.getUser())) {
			commentDao.deleteById(id);
		} else {
			throw new Exception("No kidding, this is not your comment!!!");
		}
		return "redirect:/messages/" + messageid;
	}
}
