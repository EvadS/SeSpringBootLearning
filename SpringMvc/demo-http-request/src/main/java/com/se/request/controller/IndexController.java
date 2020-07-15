package com.se.request.controller;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final String USER_AGENT_KEY = "mobile-user-agent";


    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/agent")
    public String newOrder(@RequestHeader(value = "User-Agent") String userAgent,
                           RedirectAttributes redirectAttributes
            , HttpServletRequest request) {
        String requestAgent = request.getHeader("User-Agent");
        logger.info("** userAgent {}", userAgent);
        logger.info("request.getHeader(User-Agent): {}", requestAgent);


        return "client: " + requestAgent;
    }

    @GetMapping("/user-agent")
    public String userAgent2(@RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest request) {
        //...

        String requestAgent = request.getHeader(USER_AGENT_KEY);
        if (!StringUtils.isEmpty(requestAgent)) {
            logger.info("** client {}", requestAgent);
            return "client: " + requestAgent;
        }

//        // получить инфу стандартными средствами
//        requestAgent = request.getHeader("User-Agent");
//        if (!StringUtils.isEmpty(requestAgent)) {
//            logger.info("** userAgent {}", requestAgent);
//            return "userAgent: " + requestAgent;
//        }

        // получить инфу o браузере посредством user Agent
        UserAgent userAgent2 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        if (!StringUtils.isEmpty(userAgent2) && userAgent2.getBrowser() != Browser.UNKNOWN) {
            logger.info("userAgent2.getBrowser().getName(): {}", userAgent2.getBrowser().getName());
            logger.info("userAgent2.getBrowserVersion(): {}", userAgent2.getBrowserVersion());
            return "userAgent: " + userAgent2.getBrowser().getGroup() + ":" + userAgent2.getBrowserVersion();
        }

        requestAgent = request.getHeader("User-Agent");
        logger.info("** userAgent {}", requestAgent);
        return "userAgent: " + requestAgent;
    }
}
