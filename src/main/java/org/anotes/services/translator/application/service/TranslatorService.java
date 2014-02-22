package org.anotes.services.translator.application.service;

import org.anotes.services.translator.domain.TranslatedText;

/**
 * User: gmc
 * Date: 08/02/14
 */
public interface TranslatorService {

    TranslatedText translate(String langFrom, String langTo, String text);
}
