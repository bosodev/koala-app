package com.koala.rest.exception;

import java.io.FileNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FileException implements ExceptionMapper<Throwable> {

	private static final String ERROR_FILE_NO_FOUND = "Arquivo nao encontrado em nossos servidores";

	@Override
	public Response toResponse(Throwable exception) {
		RestExceptionObject restError = null;
		if (exception instanceof FileNotFoundException)
			restError = new RestExceptionObject(1, ERROR_FILE_NO_FOUND);
		else
			restError = new RestExceptionObject(2, exception.getMessage());
		return Response.ok(restError, MediaType.APPLICATION_JSON).status(500).build();
	}
}
