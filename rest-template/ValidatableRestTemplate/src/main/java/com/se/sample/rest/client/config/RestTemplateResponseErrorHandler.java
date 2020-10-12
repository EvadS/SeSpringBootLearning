package com.se.sample.rest.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.rest.client.exception.NotFoundException;
import com.se.sample.rest.client.model.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @PostConstruct
    private  void  init(){
        logger.info("** PostConstruct  RestTemplateResponseErrorHandler");
    }
    private final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

//    @Override
//    public boolean hasError(ClientHttpResponse httpResponse)
//            throws IOException {
//        logger.info("HERE ");
//
//        return (
//                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
//                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
//    }

    @Override
    public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {

        if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
            logger.info("Status code: " + clienthttpresponse.getStatusCode());
            logger.info("Response" + clienthttpresponse.getStatusText());
            logger.info(""+clienthttpresponse.getBody());

            if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                logger.info("Call returned a error 403 forbidden resposne ");
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {
        logger.info("HERE 22 ");
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            int a = 0;
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR

        }
            int aaa = 0;

            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("хз");
            }
        }
    }
