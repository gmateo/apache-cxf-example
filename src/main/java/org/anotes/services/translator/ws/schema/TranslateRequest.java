package org.anotes.services.translator.ws.schema;

/**
 * User: gmc
 * Date: 08/02/14
 */
public class TranslateRequest {
    private String langFrom;
    private String langTo;
    private String text;

    public String getLangFrom() {
        return langFrom;
    }

    public void setLangFrom(String langFrom) {
        this.langFrom = langFrom;
    }

    public String getLangTo() {
        return langTo;
    }

    public void setLangTo(String langTo) {
        this.langTo = langTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
