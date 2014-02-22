package org.anotes.services.translator.domain;

import java.util.concurrent.Future;

/**
 * User: gmc
 * Date: 08/02/14
 */
public interface Translator {

    public Future<String> translate(LanguageSourceTarget languageSourceTarget, String text);

    public String detectLanguage(String text);
}
