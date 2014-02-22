package org.anotes.services.translator.infrastructure;

import org.anotes.services.translator.exception.TranslatorException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;

/**
 * User: gmc
 * Date: 09/02/14
 */
@Component("microsoftTranslator")
public class MicrosoftTranslator extends TranslatorImpl {
    private static String API_KEY = "YOUR_API_KEY";

    @Override
    protected HttpRequestBase getHttpRequest(String from, String to, String text, String encodedText) {
        String urlStr = "http://api.microsofttranslator.com/v2/Http.svc/Translate";
        String PARAMETERS = "appId=" + API_KEY + "&text=" + encodedText + "&from=" + from + "&to=" + to + "";
        HttpGet httpget = new HttpGet(urlStr + "?" + PARAMETERS);
        return httpget;
    }

    @Override
    protected String getTranslationFrom(String responseAsStr) {
        return getResultFromResponseStr(responseAsStr);
    }

    public String detectLanguage(String text) {
        try {
            String encodedText = URLEncoder.encode(text, ENCODING_UTF_8);
            String urlStr = "http://api.microsofttranslator.com/v2/Http.svc/Detect";
            String parameters = "appId=" + API_KEY + "&text=" + encodedText;
            HttpGet httpget = new HttpGet(urlStr + "?" + parameters);
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseStr = transformToString(entity);
            return getResultFromResponseStr(responseStr);
        } catch (Throwable e) {
            LOG.error("Problems detecting language:" + e.getMessage(), e);
            throw new TranslatorException("Problems detecting language:" + e.getMessage(), e);
        }
    }

    private static String getResultFromResponseStr(String responseAsStr) {
        if (!StringUtils.hasText(responseAsStr)) {
            return "";
        }
        int idxBegin = responseAsStr.indexOf(">");
        int idxEnd = responseAsStr.indexOf("<", idxBegin + 1);
        return responseAsStr.substring(idxBegin + 1, idxEnd);
    }


}
