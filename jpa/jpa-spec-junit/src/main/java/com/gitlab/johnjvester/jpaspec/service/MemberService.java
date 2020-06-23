package com.gitlab.johnjvester.jpaspec.service;

import com.gitlab.johnjvester.jpaspec.domain.Member;
import com.gitlab.johnjvester.jpaspec.repository.MemberRepository;
import com.gitlab.johnjvester.jpaspec.specification.MemberSpecification;
import com.gitlab.johnjvester.jpaspec.web.model.FilterRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private MemberSpecification memberSpecification;

    public MemberService(@Lazy MemberRepository memberRepository, @Lazy MemberSpecification memberSpecification) {
        this.memberRepository = memberRepository;
        this.memberSpecification = memberSpecification;
    }

    public List<Member> getMembers(FilterRequest filter, String searchString) {
        return memberRepository.findAll(Specification.where(memberSpecification.hasString(searchString)
                .or(memberSpecification.hasClasses(searchString)))
                .and(memberSpecification.getFilter(filter)));
    }
}
