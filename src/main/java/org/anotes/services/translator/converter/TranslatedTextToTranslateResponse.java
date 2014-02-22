package org.anotes.services.translator.converter;


import org.anotes.services.translator.converter.support.TypeConverter;
import org.anotes.services.translator.domain.TranslatedText;
import org.anotes.services.translator.ws.schema.TranslateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * User: gmc
 * Date: 11/02/14
 */
@TypeConverter
public class TranslatedTextToTranslateResponse implements Converter<TranslatedText, TranslateResponse> {

    public TranslateResponse convert(TranslatedText translatedText) {
        TranslateResponse translateResponse = new TranslateResponse();
        BeanUtils.copyProperties(translatedText, translateResponse);
        return translateResponse;
    }
}
