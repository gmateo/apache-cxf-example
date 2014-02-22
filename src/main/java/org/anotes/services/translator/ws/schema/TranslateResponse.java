package org.anotes.services.translator.ws.schema;

/**
 * User: gmc
 * Date: 08/02/14
 */
public class TranslateResponse {
    private ResultEnum resultCode = ResultEnum.OK;
    private String errorMsg;
    private String googleTranslation;
    private String microsoftTranslation;
    private String systranTranslation;


    public ResultEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultEnum resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TranslateResponse{");
        sb.append("resultCode=").append(resultCode);
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append(", googleTranslation='").append(googleTranslation).append('\'');
        sb.append(", microsoftTranslation='").append(microsoftTranslation).append('\'');
        sb.append(", systranTranslation='").append(systranTranslation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
