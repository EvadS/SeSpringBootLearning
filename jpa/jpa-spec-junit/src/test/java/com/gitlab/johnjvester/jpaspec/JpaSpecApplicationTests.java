package com.gitlab.johnjvester.jpaspec;

import com.gitlab.johnjvester.jpaspec.domain.Class;
import com.gitlab.johnjvester.jpaspec.domain.Member;
import com.gitlab.johnjvester.jpaspec.repository.ClassRepository;
import com.gitlab.johnjvester.jpaspec.repository.MemberRepository;
import com.gitlab.johnjvester.jpaspec.specification.MemberSpecification;
import com.gitlab.johnjvester.jpaspec.web.model.FilterRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.main.banner-mode=off")
@Transactional
public class JpaSpecApplicationTests {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberSpecification memberSpecification;

    @Before
    public void init() {
        memberRepository.deleteAll();
        classRepository.deleteAll();

        Class classWaterPolo = new Class();
        classWaterPolo.setName("Water Polo");
        classRepository.save(classWaterPolo);

        Class classSwimming = new Class();
        classSwimming.setName("Swimming");
        classRepository.save(classSwimming);

        Class classLifting = new Class();
        classLifting.setName("Lifting");
        classRepository.save(classLifting);

        Class classPilates = new Class();
        classPilates.setName("Pilates");
        classRepository.save(classPilates);

        Class classZumba = new Class();
        classZumba.setName("Zumba");
        classRepository.save(classZumba);

        Set<Class> gregSet = new HashSet<>();
        gregSet.add(classWaterPolo);
        gregSet.add(classLifting);

        Member memberGreg = new Member();
        memberGreg.setActive(true);
        memberGreg.setFirstName("Greg");
        memberGreg.setLastName("Brady");
        memberGreg.setInterests("I love to cycle and swim");
        memberGreg.setZipCode("90210");
        memberGreg.setClasses(gregSet);
        memberRepository.save(memberGreg);

        Set<Class> marshaSet = new HashSet<>();
        marshaSet.add(classSwimming);
        marshaSet.add(classZumba);

        Member memberMarsha = new Member();
        memberMarsha.setActive(true);
        memberMarsha.setFirstName("Marsha");
        memberMarsha.setLastName("Brady");
        memberMarsha.setInterests("I love to do zumba and pilates");
        memberMarsha.setZipCode("90211");
        memberMarsha.setClasses(marshaSet);
        memberRepository.save(memberMarsha);

        Set<Class> aliceSet = new HashSet<>();
        aliceSet.add(classSwimming);

        Member memberAlice = new Member();
        memberAlice.setActive(false);
        memberAlice.setFirstName("Alice");
        memberAlice.setLastName("Nelson");
        memberAlice.setInterests("I used to love that belt machine-y thing");
        memberAlice.setZipCode("90201");
        memberAlice.setClasses(aliceSet);
        memberRepository.save(memberAlice);
    }

    @Test
    public void testMembersActive() {
        FilterRequest filter = new FilterRequest();
        filter.setActive(true);

        List<Member> memberList = memberRepository.findAll(memberSpecification.getFilter(filter));

        assertEquals(2, memberList.size());
    }

    @Test
    public void testMembersInZip902() {
        FilterRequest filter = new FilterRequest();
        filter.setZipFilter("902");

        List<Member> memberList = memberRepository.findAll(memberSpecification.getFilter(filter));

        assertEquals(3, memberList.size());
    }

    @Test
    public void testMembersWithSwimClassOrInterest() {
        String searchString = "sWIM";

        List<Member> memberList = memberRepository.findAll(Specification.where(memberSpecification.hasString(searchString)
                .or(memberSpecification.hasClasses(searchString))));

        assertEquals(3, memberList.size());
    }

    @Test
    public void testMembersActiveInZip902WithSwimClassOrInterest() {
        FilterRequest filter = new FilterRequest();
        filter.setActive(true);
        filter.setZipFilter("902");
        String searchString = "sWIM";

        List<Member> memberList = memberRepository.findAll(Specification.where(memberSpecification.hasString(searchString)
                .or(memberSpecification.hasClasses(searchString)))
                .and(memberSpecification.getFilter(filter)));

        assertEquals(2, memberList.size());
    }

}
