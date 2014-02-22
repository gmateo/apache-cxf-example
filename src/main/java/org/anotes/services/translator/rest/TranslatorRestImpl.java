package org.anotes.services.translator.rest;

import org.anotes.services.translator.application.service.TranslatorService;
import org.anotes.services.translator.domain.TranslatedText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: gmc
 * Date: 08/02/14
 */
@Service
public class TranslatorRestImpl implements TranslatorRest {

    @Autowired
    TranslatorService translatorService;

    public TranslatedText translate(String from, String to, String text) {
        TranslatedText translatedText = translatorService.translate(from, to, text);
        return translatedText;
    }
}
