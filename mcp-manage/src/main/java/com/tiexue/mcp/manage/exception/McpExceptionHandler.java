package com.tiexue.mcp.manage.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tiexue.mcp.manage.controller.McpAdminController;

@ControllerAdvice // To Handle Exceptions
public class McpExceptionHandler {

	private static Logger logger = Logger.getLogger(McpAdminController.class);

	/**
	 * 错误信息
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		/*
		 * Map<String, String> exception = new HashMap<String, String>();

		 * logger.error("unauthorized Access to the API: " + e.getMessage(), e);
		 * exception.put("code", "401"); exception.put("reason",
		 * e.getMessage()); return exception;
		 */
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("mcpAdmin/unauthorized");
		return mv;

	}
}
