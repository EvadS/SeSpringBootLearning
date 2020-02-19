package com.se.sample.controller;

import com.se.sample.entity.RequestInfo;
import com.se.sample.service.RequestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/request-info")
public class RequestInfoController {


    private RequestInfoService requestInfoService;


    public RequestInfoController(@Autowired RequestInfoService requestInfoService) {
        this.requestInfoService = requestInfoService;
    }

    public ResponseEntity getAll(){

        return null;

    }

    @GetMapping("/requests")
    public Page<RequestInfo> getAllCommentsByPostId(Pageable pageable) {
        return requestInfoService.getRequestInfos( pageable);
    }
}
