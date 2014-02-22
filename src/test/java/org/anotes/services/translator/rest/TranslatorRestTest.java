package org.anotes.services.translator.rest;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


/**
 * User: gmc
 * Date: 11/02/14
 */
public class TranslatorRestTest {
    private final static String ENDPOINT_ADDRESS = "http://localhost:8062/apache-cxf-example/translator";
    public static final String ENCODING_UTF_8 = "UTF-8";

    @Test
    public void translateTest() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client.target(ENDPOINT_ADDRESS + "/translate/es/en/prueba").request("application/json").get();
        String responseStr = getResponseAsStr(response);
        System.out.println(responseStr);
    }

    private String getResponseAsStr(Response response) throws IOException {
        InputStream instream = (InputStream) response.getEntity();
        String responseStr = new Scanner(instream, ENCODING_UTF_8).useDelimiter("\\A").next();
        instream.close();
        return responseStr;
    }

}