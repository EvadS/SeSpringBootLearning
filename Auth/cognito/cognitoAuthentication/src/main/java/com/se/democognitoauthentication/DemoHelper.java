package com.se.democognitoauthentication;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.se.democognitoauthentication.config.CognitoConfig;
import com.se.democognitoauthentication.model.UserSignUpRequest;

public class DemoHelper {

    private CognitoConfig cognitoConfig = new CognitoConfig();


    public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider =
                new ClasspathPropertiesFileCredentialsProvider();

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(propertiesFileCredentialsProvider)
                .withRegion(cognitoConfig.getRegion())
                .build();

    }

    public UserType signUp(UserSignUpRequest signUpRequest) {
        try {
            AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

            AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
                    .withUserPoolId(cognitoConfig.getUserPoolId())
                    .withUsername(signUpRequest.getUsername())
                    .withUserAttributes(
                            new AttributeType()
                                    .withName("email")
                                    .withValue(signUpRequest.getEmail()),
                            new AttributeType()
                                    .withName("name")
                                    .withValue(signUpRequest.getName()),
                            new AttributeType()
                                    .withName("family_name")
                                    .withValue(signUpRequest.getLastname()),
                            new AttributeType()
                                    .withName("phone_number")
                                    .withValue(signUpRequest.getPhoneNumber()),
//                            new AttributeType()
//                                    .withName("custom:brokerID")
//                                    .withValue(signUpRequest.getBrokerID()),
//                            new AttributeType()
//                                    .withName("custom:companyPosition")
//                                    .withValue(signUpRequest.getCompanyPosition()),
                            new AttributeType()
                                    .withName("email_verified")
                                    .withValue("true"))
                    .withTemporaryPassword("QuhxmE472.")
                    .withMessageAction("SUPPRESS")
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                    .withForceAliasCreation(Boolean.FALSE);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(cognitoRequest);
            UserType cognitoUser = createUserResult.getUser();

            return cognitoUser;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            int a = 1;
            e.printStackTrace();

        } catch (Exception e) {
            int a = 1;
            e.printStackTrace();
        }
return null;
    }

}
