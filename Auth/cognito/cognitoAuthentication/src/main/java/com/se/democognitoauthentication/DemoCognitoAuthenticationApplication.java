package com.se.democognitoauthentication;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.se.democognitoauthentication.model.UserSignUpRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCognitoAuthenticationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoCognitoAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            DemoHelper demoHelper = new DemoHelper();


            AWSCognitoIdentityProvider awsCognitoIdentityProvider = demoHelper.getAmazonCognitoIdentityClient();


            UserSignUpRequest signUpRequest = new UserSignUpRequest();
            signUpRequest.setUsername("evad");
            signUpRequest.setAgreementFlag("");
            signUpRequest.setEmail("mail@mail.com");
            signUpRequest.setLastname("Last name ");
            signUpRequest.setPassword("123456");
            signUpRequest.setName("NAME ");
            signUpRequest.setAgreementFlag("aggreement flag");
            signUpRequest.setPhoneNumber("+380999999999");
            signUpRequest.setCompanyName("CompanyName");
            signUpRequest.setBrokerID("brokerID");
            signUpRequest.setCompanyPosition("CompanyPosition");

            int bb = 10;

            ListUsersRequest listUsersRequest = new ListUsersRequest();

            demoHelper.signUp(signUpRequest);
        }
        catch (Exception ex) {
         ex.printStackTrace();
            int a = 10;
        }
    }
}
