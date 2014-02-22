package org.anotes.services.translator.rest;

import org.anotes.services.translator.domain.Translator;
import org.anotes.services.translator.exception.TranslatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@Provider
@PreMatching
public class LanguageSetterFilter implements ContainerRequestFilter {
    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_LANG_TO = "es";
    private static final String PATH_TRANSLATE = "/translate/";
    @Autowired
    Translator microsoftTranslator;

    public void filter(ContainerRequestContext ctx) {
        UriInfo uriInfo = ctx.getUriInfo();
        String uriStr = uriInfo.getRequestUri().toString();
        int idx = uriStr.indexOf(PATH_TRANSLATE);
        boolean isTranslatePath = idx != -1;
        if (isTranslatePath) {
            String parameters = uriStr.substring(idx + PATH_TRANSLATE.length());
            boolean existLanguages = parameters.indexOf("/") != -1;
            if (!existLanguages) {
                String headerLanguage = getHeaderLanguage(ctx);
                ctx.setRequestUri(getNewUri(uriStr, idx, parameters, headerLanguage));
            }
        }
    }

    private String getHeaderLanguage(ContainerRequestContext ctx) {
        String headerString = ctx.getHeaderString("accept-language");
        return headerString != null && headerString.length() > 2 ? headerString.substring(0, 2) : DEFAULT_LANG_TO;
    }

    private URI getNewUri(String uriStr, int idx, String parameters, String headerLanguage) {
        String langFrom = microsoftTranslator.detectLanguage(parameters);
        String langTo = headerLanguage;
        String newUri = uriStr.substring(0, idx + PATH_TRANSLATE.length()) + langFrom + "/" + langTo + "/" + parameters;
        try {
            return new URI(newUri);
        } catch (URISyntaxException e) {
            LOG.error("Getting new uri:" + newUri, e);
            throw new TranslatorException("Problems Getting new uri:" + newUri, e);
        }
    }
}