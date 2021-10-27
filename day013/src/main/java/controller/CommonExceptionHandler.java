package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("controller")
public class CommonExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(Exception e) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("excep", e);
		mav.setViewName("excep/error.jsp");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("excep", e);
		mav.setViewName("excep/error.jsp");
		return mav;
	}
}
