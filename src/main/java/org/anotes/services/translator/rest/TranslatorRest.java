package org.anotes.services.translator.rest;

import org.anotes.services.translator.domain.TranslatedText;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: gmc
 * Date: 08/02/14
 */
@Path("/")
public interface TranslatorRest {

    @GET
    @Path("translate/{from}/{to}/{text}")
    @Produces({MediaType.APPLICATION_JSON})
    public TranslatedText translate(@PathParam("from") String from, @PathParam("to") String to, @PathParam("text") String text);
}
