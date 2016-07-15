package com.mycompany.accountservice.client;


/**
 *
 * @author fabry
 */
public class Test {
    public static void main(final String[] args) throws Exception {
        AccountServiceClient client = new AccountServiceClient("localhost",9080);
        String resp = client.getServiceInfo();
        System.out.println("########### "+resp);
    }
}
