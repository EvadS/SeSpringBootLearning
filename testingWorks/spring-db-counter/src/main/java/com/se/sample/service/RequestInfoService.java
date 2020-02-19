package com.se.sample.service;

import com.se.sample.entity.RequestInfo;
import com.se.sample.repository.RequestInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestInfoService {

    private RequestInfoRepository requestInfoRepository;

    public RequestInfoService(@Autowired RequestInfoRepository requestInfoRepository) {
        this.requestInfoRepository = requestInfoRepository;
    }

    public Page<RequestInfo> getRequestInfos(Pageable pageable) {
        return requestInfoRepository.findAll(pageable);
    }
}
