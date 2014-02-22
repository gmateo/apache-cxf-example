package org.anotes.services.translator.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * User: gmc
 * Date: 09/02/14
 */
@Component
public class ExceptionHandler implements ExceptionMapper {

    public javax.ws.rs.core.Response toResponse(Throwable throwable) {
        return Response.serverError().entity(throwable.getMessage()).build();
    }
}
