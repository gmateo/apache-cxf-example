package org.anotes.services.translator.application.service;

import org.anotes.services.translator.domain.Language;
import org.anotes.services.translator.domain.LanguageSourceTarget;
import org.anotes.services.translator.domain.TranslatedText;
import org.anotes.services.translator.domain.Translator;
import org.anotes.services.translator.exception.TranslatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * User: gmc
 * Date: 08/02/14
 */
@Service
public class TranslatorServiceImpl implements TranslatorService {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    Translator googleTranslator;
    @Autowired
    Translator microsoftTranslator;
    @Autowired
    Translator systranTranslator;

    public TranslatedText translate(String langFrom, String langTo, String text) {
        LanguageSourceTarget languageSourceTarget = new LanguageSourceTarget(Language.fromString(langFrom), Language.fromString(langTo));
        if (languageSourceTarget.sourceAndTargeAreEquals()) {
            throw new TranslatorException("The languages from and to must be differents.");
        }
        Future<String> googleResult = googleTranslator.translate(languageSourceTarget, text);
        Future<String> systranResult = systranTranslator.translate(languageSourceTarget, text);
        Future<String> microsoftResult = microsoftTranslator.translate(languageSourceTarget, text);
        TranslatedText response = new TranslatedText();
        response.setFrom(languageSourceTarget.getSourceAsStr());
        response.setTo(languageSourceTarget.getTargetAsStr());
        response.setMicrosoftTranslation(getTranslation(microsoftResult));
        response.setGoogleTranslation(getTranslation(googleResult));
        response.setSystranTranslation(getTranslation(systranResult));
        return response;
    }

    private String getTranslation(Future<String> futureResult) {
        try {
            return futureResult.get();
        } catch (Throwable e) {
            LOG.error("Problems getting the translation", e);
            return "Error:" + e.getMessage();
        }
    }
}
