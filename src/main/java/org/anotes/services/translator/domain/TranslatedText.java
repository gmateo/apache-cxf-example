package org.anotes.services.translator.domain;

/**
 * User: gmc
 * Date: 10/02/14
 */
public class TranslatedText {
    private String from;
    private String to;
    private String googleTranslation;
    private String microsoftTranslation;
    private String systranTranslation;

    public String getGoogleTranslation() {
        return googleTranslation;
    }

    public void setGoogleTranslation(String googleTranslation) {
        this.googleTranslation = googleTranslation;
    }

    public String getMicrosoftTranslation() {
        return microsoftTranslation;
    }

    public void setMicrosoftTranslation(String microsoftTranslation) {
        this.microsoftTranslation = microsoftTranslation;
    }

    public String getSystranTranslation() {
        return systranTranslation;
    }

    public void setSystranTranslation(String systranTranslation) {
        this.systranTranslation = systranTranslation;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TranslatedText{");
        sb.append("from='").append(from).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append(", googleTranslation='").append(googleTranslation).append('\'');
        sb.append(", microsoftTranslation='").append(microsoftTranslation).append('\'');
        sb.append(", systranTranslation='").append(systranTranslation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
