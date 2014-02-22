package org.anotes.services.translator.ws;

import org.anotes.services.translator.ws.schema.TranslateRequest;
import org.anotes.services.translator.ws.schema.TranslateResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: gmc
 * Date: 26/01/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/spring-context-test.xml"})
public class TranslatorPTTest {

    @Autowired
    private TranslatorPT wsClient;

    @Test
    public void translateTest() {
        TranslateRequest request = new TranslateRequest();
        request.setLangFrom("es");
        request.setLangTo("en");
        request.setText("Esta es una prueba de JAXWS");
        TranslateResponse response = wsClient.translate(request);
        System.out.println(response);
    }

}
