package org.anotes.services.translator.exception;

/**
 * User: gmc
 * Date: 08/02/14
 */
public class TranslatorException extends RuntimeException {
    public TranslatorException(String message) {
        super(message);
    }

    public TranslatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
