package com.p2pgate.bank_gate.rest.exception;

import com.p2pgate.bank_gate.config.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 * Created by OAKutsenko on 09.10.2017.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @Value("${page.error}")
    private String errorURL;

    @Autowired
    Messages messages;

    private final Logger log = LoggerFactory.getLogger(getClass());


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(MethodArgumentNotValidException ex, final Model model) {

        model.addAttribute("errorMsg", "Bad request value.");
        return "error";
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden(final HttpServletRequest request, final Throwable throwable, final Model model){
        model.addAttribute("errorMsg", throwable.getMessage());
        return "error";
    }

    @ExceptionHandler(PayApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String payHandler(final HttpServletRequest request, PayApiException ex, final Model model) {

        Map<String,String> parameters = new HashMap<>();
        final String errMsg = (ex.getErrMsg() != null)?ex.getErrMsg():"Bad request parameters.";
        parameters.put("path", errorURL+"?payid=" + ex.getPayId() +"&error=" + errMsg+"&reason="+ex.getReason());
        model.addAllAttributes(parameters);
        return "error_pg";
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception(final Throwable ex, final Model model) {

        log.error(ex+"");
        model.addAttribute("errorMsg", ex.getMessage());
        return "error";
    }

    private Map error(Object message) {
        return Collections.singletonMap("error", message);
    }

}
