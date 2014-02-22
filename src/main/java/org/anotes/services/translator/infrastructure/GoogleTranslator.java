package org.anotes.services.translator.infrastructure;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.stereotype.Component;

/**
 * User: gmc
 * Date: 17/05/11
 */
@Component("googleTranslator")
public class GoogleTranslator extends TranslatorImpl {

    @Override
    protected HttpRequestBase getHttpRequest(String from, String to, String text, String encodedText) {
        HttpGet httpGet = new HttpGet("http://translate.google.com/translate_a/t?client=t&text=" + encodedText + "&hl=" + from + "&sl=" + from + "&tl=" + to + "&multires=1&otf=2&ssel=0&tsel=0&sc=1&ie=" + ENCODING_UTF_8);
        return httpGet;
    }

    protected String getTranslationFrom(String responseAsStr) {
        StringBuilder sb = new StringBuilder();
        if (responseAsStr.length() > 4) {
            int idxEnd = responseAsStr.indexOf("\"", 4);
            sb.append(responseAsStr.substring(4, idxEnd));
        }
        return sb.toString();
    }

}
