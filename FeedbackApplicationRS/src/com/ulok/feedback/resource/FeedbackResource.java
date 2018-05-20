package com.ulok.feedback.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.ulok.feedback.service.FeedbackService;
import com.ulok.feedback.vo.FeedbackVO;

@Path("/feedback")
public class FeedbackResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	FeedbackService feedbackService = new FeedbackService();
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String postFeedback(@FormParam("name") String name, 
			@FormParam("comment") String comment,
			@Context HttpServletResponse servletResponse) throws IOException 
	{
//		@FormParam("star") int star,
		System.out.println("inside resource class, name:"+name + ", comments:"+comment);
		String message= null;
		FeedbackVO feedback= new FeedbackVO(name, comment, 4);
		int result= feedbackService.postFeedback(feedback);
		if(result>0)
		{
			message = "Feedback posted successfully";
		}
		else
		{
			message = "Feedback could not be posted";
		}
		
		return message;
	}
	
	@Path("/getfeedback")
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public FeedbackVO getFeedback() {
		return new FeedbackVO("Deepak Sharma", "Good try", 4);
	}

}
