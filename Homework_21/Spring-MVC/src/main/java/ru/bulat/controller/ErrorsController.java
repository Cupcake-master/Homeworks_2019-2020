package ru.bulat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController {

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView code= new ModelAndView("code");
        String message = "";
        int httpCode = getErrorCode(httpRequest);

        switch (httpCode) {
            case 400: {
                message = "Http  Code: 400. Bad Request";
                break;
            }
            case 200: {
                message = "Http  Code: 200. OK)";
                break;
            }
            case 404: {
                message = "Http  Code: 404. Resource not found";
                break;
            }

        }
        code.addObject("errorMsg", message);
        return code;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status__code");
    }
}
