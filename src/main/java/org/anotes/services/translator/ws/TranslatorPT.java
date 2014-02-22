package org.anotes.services.translator.ws;

import org.anotes.services.translator.ws.schema.TranslateRequest;
import org.anotes.services.translator.ws.schema.TranslateResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * User: gmc
 * Date: 08/02/14
 */
@WebService
public interface TranslatorPT {

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebMethod(action = "http://anotes.org/services/translator/ws/translate")
    @WebResult(name = "TranslateResponse", targetNamespace = "http://anotes.org/services/translator/ws/schema", partName = "part")
    @ResponseWrapper(targetNamespace = "http://anotes.org/services/translator/ws/schema", className = "org.anotes.services.translator.ws.schema.TranslateResponse")
    @RequestWrapper(targetNamespace = "http://anotes.org/services/translator/ws/schema", className = "org.anotes.services.translator.ws.schema.TranslateRequest")
    public TranslateResponse translate(@WebParam(name = "translateRequest",
            partName = "part",
            targetNamespace = "http://anotes.org/services/translator/ws/schema")
                                       TranslateRequest request);

}
