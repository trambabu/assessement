package org.services.ecommerse.bridge.outside;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.services.ecommerce.business.service.DetailsService;
import org.services.ecommerce.business.service.impl.DetailsServiceImpl;
import org.services.ecommerce.entity.Details;

import sun.misc.BASE64Decoder;

@Path("/userservice")
public class UserService {

	@GET
	@Path("/echo")
	public Response getResponse() {
		String output = "Welcome   : ";
		return Response.status(200).entity(output).build();
	}


	@GET
	@Path("/allUserDetails")
	@Produces({ "application/json", "application/xml" })
	public Response getAllDetails(@HeaderParam("authorization") String authString) {
		DetailsService service = new DetailsServiceImpl();
		
		System.out.println("UserService.getAllDetails()"+authString);
		if(authString!=null) {
			
		}else {
			System.out.println("Auth String null ");
		}
		if(!isUserAuthenticated(authString)){
			return Response.noContent().status(200).entity("Authorisation failed").build();	
        }
		List<Details> listOfAllUser = service.getAllDetails();
		if(!listOfAllUser.isEmpty()) {
			return Response.ok().status(200).entity(listOfAllUser).build();
		}
		return Response.noContent().status(200).entity("users List is empty!").build();	
	}
	
	 private boolean isUserAuthenticated(String authString){
         
		 System.out.println("UserService.isUserAuthenticated()");
		 System.out.println("UserService.isUserAuthenticated()"+authString);
	        String decodedAuth = "";
	        // Header is in the format "Basic 5tyc0uiDat4"
	        // We need to extract data before decoding it back to original string
	        String[] authParts = authString.split("\\s+");
	        String authInfo = authParts[1];
	        // Decode the data back to original string
	        byte[] bytes = null;
	        bytes = Base64.getDecoder().decode(authInfo);
	        try {
				decodedAuth = new String(bytes, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println(decodedAuth);
	         
	        /**
	         * here you include your logic to validate user authentication.
	         * it can be using ldap, or token exchange mechanism or your 
	         * custom authentication mechanism.
	         */
	        // your validation code goes here....
	        StringTokenizer st = new StringTokenizer(decodedAuth, ":");
	        System.out.println(" deocded token >>>>"+st.toString());
	        String username = st.nextToken();
	        String password = st.nextToken();
	        if((username.equals("rambabu")) && (password.equals("southafrica"))) {
	        	return true;
	 		}
	 		return false;
	    }

	@GET
	@Path("/findDetailsByUsername/{username}")
	@Produces({ "application/json", "application/xml" })
	public Response getDetailsByUsername(@PathParam("username")  String username) {
		System.out.println("HelloWorldService.findDetails()");
		DetailsService service = new DetailsServiceImpl();
		Details detail = service.readUserByUsername(username);
		if(detail!=null) {
			return Response.ok().status(200).entity(detail).build();
		}
		return Response.noContent().status(200).entity("user not found!").build();	
	}

	@POST
	@Path("/addDetails/{username}/{password}")
	@Produces({ "application/json" })
	@Consumes({"application/json","application/x-www-form-urlencoded"})
	public Response insertDetails(@PathParam("username")  String username, @PathParam("password")  String password) {
		System.out.println("insertDetails >>>>>>>>>>>>>>>>>>>>>"+username+"**********************"+password);
		if(username.isEmpty()||password.isEmpty()) {
			return Response.status(400).entity("Please add username or password !!").build();
		}
			DetailsService service = new DetailsServiceImpl();
			boolean status = service.createUser(username, password);
			if(status) {
				return Response.ok(username).status(201).build();
			}
			return Response.noContent().status(201).entity("unable to add user:please try again!").build();
	}

	@PUT
	@Path("/updateDetails/{username}/{password}")
	@Produces({ "application/json", "application/xml" })
	@Consumes({"application/json", "application/x-www-form-urlencoded"})
	public Response changeDetails(@PathParam("username") String username, @PathParam("password") String password) {
		System.out.println("UserService.changeDetails()"+username+"<<****************>>"+password);
		if(username.isEmpty()||password.isEmpty()) {
			return Response.status(400).entity("Please add username or password !!").build();
		}
		DetailsService service = new DetailsServiceImpl();
		service.updateUser(username, password);
		return Response.ok().build();
	}

	@DELETE
	@Path("/deleteDetails/{username}")
	@Produces({ "application/json", "application/xml" })
	@Consumes({"application/json", "application/x-www-form-urlencoded"})
	public Response removeDetails(@PathParam("username") String username) {
		System.out.println("UserService.removeDetails()"+username);
		if(username.isEmpty()) {
			return Response.noContent().build();
		}
		DetailsService service = new DetailsServiceImpl();
		 service.deleteUser(username);
		 return Response.status(202).entity("UseDetails deleted successfully !!").build();
	}
}
