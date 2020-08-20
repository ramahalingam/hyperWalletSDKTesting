package com.paypal.sdkSpringBootProject;

import com.hyperwallet.clientsdk.Hyperwallet;
import com.hyperwallet.clientsdk.HyperwalletException;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@RestController
@RequestMapping("rest/v4/")
public class UploadDocumentRef {

    //Upload Doc in hw2 repo:
    //    @PUT
    //    @RolesAllowed({Roles.PUT_USERS_RESOURCE, Roles.SDK_PUT_USERS_RESOURCE})
    //    @Path("{userToken}")
    //    @Consumes(MediaType.MULTIPART_FORM_DATA)
    //    @Produces(MediaType.APPLICATION_JSON)
    //    public Response uploadDocuments(@UserToken @PathParam("userToken") String userToken, final FormDataMultiPart multiPart,
    //            @Context UriInfo uriInfo)
    //            throws Exception {
    //        Wallet loggedInWallet = getWalletForToken(userToken);
    //        UserRepresentation userRepresentationResponse = getUserResourceService().createOrUpdateDocument(loggedInWallet, multiPart, uriInfo);
    //        return buildOkResponse(userRepresentationResponse);
    //    }


    @RequestMapping(value = "users/{userToken}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public void uploadDocument(@PathVariable String userToken, final FormDataMultiPart multiPart)
            throws NoSuchAlgorithmException, KeyManagementException {

        SSLIssue.resolveSSLIssue();

        System.out.println("Hi this is Ravi in uploadDocument");

        try {

            Hyperwallet client = new Hyperwallet("selrestuser@330068",
                    "Password1!", "prg-eedaf875-01f1-4524-8b94-d4936af78",
                    "https://localhost:8181");

//            FormDataMultiPart part = null;
            client.uploadDocuments(userToken, multiPart);
        } catch (HyperwalletException e) {
            System.out.println("Inside HyperwalletException getMessage:::" + e.getMessage());
            System.out.println("Inside HyperwalletException getCause:::" + e.getCause());
            System.out.println("Inside HyperwalletException getErrorMessage:::" + e.getErrorMessage());

        }
    }
}
