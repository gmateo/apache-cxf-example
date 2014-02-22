package org.anotes.services.translator.infrastructure;

import org.anotes.services.translator.infrastructure.support.SimpleHtmlParser;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

/**
 * User: gmc
 * Date: 17/05/11
 */
@Component("systranTranslator")
public class SystranTranslator extends TranslatorImpl {

    @Override
    protected HttpRequestBase getHttpRequest(String from, String to, String text, String encodedText) {
        String lpStr = from + "_" + to;
        String urlStr = "http://www.systranet.com/sai?gui=text&lp=" + lpStr + "&sessionid=13071170317011544&service=urlmarkuptranslate";
        text = "<html><body>" + text + "<br></body></html>";
        StringEntity entityStr = new StringEntity(text, ENCODING_UTF_8);
        HttpPost httpPost = new HttpPost(urlStr);
        httpPost.setEntity(entityStr);
        return httpPost;
    }

    @Override
    protected String getTranslationFrom(String responseAsStr) {
        String classResult = "<html>";
        int idxBegin = responseAsStr.indexOf(classResult);
        idxBegin = responseAsStr.indexOf(classResult, idxBegin + 1);
        int idxEnd = responseAsStr.length() - 1;
        String htmlResult = responseAsStr.substring(idxBegin, idxEnd);
        String result = SimpleHtmlParser.getInnerText(htmlResult);
        return result != null ? result.trim() : "";
    }


}
