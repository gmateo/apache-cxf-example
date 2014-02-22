package org.anotes.services.translator.ws;

import org.anotes.services.translator.application.service.TranslatorService;
import org.anotes.services.translator.domain.TranslatedText;
import org.anotes.services.translator.ws.schema.TranslateRequest;
import org.anotes.services.translator.ws.schema.TranslateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * User: gmc
 * Date: 08/02/14
 */
@Service
@WebService(portName = "translatorPort",
        serviceName = "translatorService",
        targetNamespace = "http://anotes.org/services/translator/ws",
        endpointInterface = "org.anotes.services.translator.ws.TranslatorPT")
public class TranslatorPTImpl implements TranslatorPT {

    @Autowired
    TranslatorService translatorService;

    @Autowired
    ConversionService conversionService;

    public TranslateResponse translate(TranslateRequest request) {
        TranslatedText translatedText = translatorService.translate(request.getLangFrom(), request.getLangTo(), request.getText());
        TranslateResponse response = conversionService.convert(translatedText, TranslateResponse.class);
        return response;
    }
}
