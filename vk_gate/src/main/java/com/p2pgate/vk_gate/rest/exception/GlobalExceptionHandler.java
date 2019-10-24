package com.p2pgate.vk_gate.rest.exception;

import com.p2pgate.vk_gate.config.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.servlet.http.HttpServletResponse;



/**
 *
 * Created by OAKutsenko on 09.10.2017.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Value("${page.error}")
    private String errorURL;

    @Autowired
    Messages messages;

    @ExceptionHandler(PayApiException.class)
    public String payHandler(HttpServletResponse httpServletResponse, PayApiException ex, final Model model) {

        httpServletResponse.setStatus(ex.getErrCode());
        model.addAttribute("errorMsg", ex.getReason());
        return "error";
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception(final Throwable ex, final Model model) {

        model.addAttribute("errorMsg", ex.getMessage());
        return "error";
    }
}
