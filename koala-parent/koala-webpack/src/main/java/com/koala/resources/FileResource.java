package com.koala.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.koala.file.FileService;

@Stateless
@Path("/file")
public class FileResource {
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	private static final String ATTACHMENT_FILENAME_PROJECT = "attachment; filename=\"project.xlxs\"";
	
	@EJB
	private FileService fileService;

	@GET
	@Path("/download")
	@Produces("application/xls")
	public Response download() throws IOException, ParseException {
		InputStream file = fileService.downloadProjectFile();
		ResponseBuilder response = Response.ok((Object) file);
		response.header(CONTENT_DISPOSITION, ATTACHMENT_FILENAME_PROJECT);
		return response.build();
	}
	
	@GET
	@Path("/download/error")
	@Produces("application/xls")
	public Response downloadError() throws FileNotFoundException {
		InputStream file = fileService.downloadError();
		ResponseBuilder response = Response.ok((Object) file);
		response.header(CONTENT_DISPOSITION, ATTACHMENT_FILENAME_PROJECT);
		return response.build();
	}

}
