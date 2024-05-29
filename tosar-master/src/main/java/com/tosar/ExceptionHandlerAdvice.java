package com.tosar;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice({ "com.tosar.controller" })
public class ExceptionHandlerAdvice{
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception e) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("exceptionclass", e.getClass().getSimpleName());
		model.addObject("exceptionmessage", e.getMessage());
		return model;
	}
}
