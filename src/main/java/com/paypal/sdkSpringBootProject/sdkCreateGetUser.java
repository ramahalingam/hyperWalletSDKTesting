package com.paypal.sdkSpringBootProject;

import com.hyperwallet.clientsdk.Hyperwallet;
import com.hyperwallet.clientsdk.HyperwalletException;
import com.hyperwallet.clientsdk.model.HyperwalletUser;
import com.hyperwallet.clientsdk.model.HyperwalletUser.Gender;
import com.hyperwallet.clientsdk.util.HyperwalletJsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

@RestController
@RequestMapping("rest1/v4/")
public class sdkCreateGetUser {

    @RequestMapping(value = "create")
    public void createGetUser() throws NoSuchAlgorithmException, KeyManagementException {
        SSLIssue.resolveSSLIssue();

        try {

            Hyperwallet client = new Hyperwallet("selrestuser@330068",
                    "Password1!", "prg-eedaf875-01f1-4524-8b94-d4936af78",
                    "https://localhost:8181");

            HyperwalletUser user = new HyperwalletUser();
            user
                    .clientUserId("test-client29")
                    .profileType(HyperwalletUser.ProfileType.INDIVIDUAL)
                    .firstName("Ravikumar")
                    .lastName("Mahalingam")
                    .email("ravikumarmahalingam29@yahoo.com")
                    .addressLine1("No: 19/54, ThiruNagar")
                    .addressLine1("Line2")
                    .gender(Gender.MALE)
                    .city("Chennaiiiiiiiii")
                    .stateProvince("TN")
                    .country("IN")
                    .postalCode("600049")
                    .language("en")
                    .mobileNumber("9884757644")
                    .programToken("prg-0438cadc-614c-11e5-af23-0faa28ca7c0f")
                    .governmentId("444444444")
                    .driversLicenseId("")
                    .dateOfBirth(new Date(13 - 10 - 1984))
                    .timeZone("GMT")
                    .passportId("");

            HyperwalletUser createdUser = client.createUser(user);

            String newlyCreatedUser = HyperwalletJsonUtil.toJson(createdUser);
            System.out.println("Value of the created user:::" + newlyCreatedUser);

            System.out.println("Value of city of the created user:::" + createdUser.getCity());

            HyperwalletUser user1 = client.getUser("usr-287c93c1-5dfa-44af-a8c3-0289238e34a6");
            System.out.println("Value of the city :::" + user1.getCity());
        } catch (HyperwalletException e) {
            System.out.println("ErrorCode::" + e.getErrorCode());
            System.out.println("ErrorCode::" + e.getCause());
            System.out.println("ErrorCode::" + e.getMessage());
        }
    }
}
