package com.paypal.sdkSpringBootProject;

import com.hyperwallet.clientsdk.Hyperwallet;
import com.hyperwallet.clientsdk.HyperwalletException;
import com.hyperwallet.clientsdk.model.HyperwalletTransferRefund;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class RefundTest {


    public void refundTransfer() throws NoSuchAlgorithmException, KeyManagementException {
        try {
            SSLIssue.resolveSSLIssue();
            Hyperwallet client = new Hyperwallet("selrestuser@330068",
                    "Password1!", "prg-0438cadc-614c-11e5-af23-0faa28ca7c0f",
                    "https://localhost:8181");

            HyperwalletTransferRefund transferRefund = new HyperwalletTransferRefund()
                    .clientRefundId("clientRefundId")
                    .notes("Merchant Payment return to Wallet Balance")
                    .memo("TransferReturn123456");

            client.createTransferRefund("prg-0438cadc-614c-11e5-af23-0faa28ca7c0f", transferRefund);
            System.out.println("Transfer refund initiated successfullytrf-6f55c059-18df-46e0-9eae-463b973ac90e\", transferRefund);");
        } catch (HyperwalletException e) {
            System.out.println("Value of the exception:::" + e.getErrorMessage());
            System.out.println("Value of the exception:::" + e.getCause());
        }
    }
}
