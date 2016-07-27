package api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import modelo.Usuario;
import servicios.ServicioUsuario;

@Path(value="Autenticacion")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class AutenticacionApi {

	private ServicioUsuario servicioUsuario = new ServicioUsuario();
	@Context private HttpServletRequest request;
	
	@POST
	@Path("login")	
	public Response login(@FormParam("username") String username,
						  @FormParam("password") String password){
		System.out.println("username "+username);
		System.out.println("password "+password);
		Usuario usuario = servicioUsuario.validarUsuario(username, password);
		
		if(usuario != null){
			HttpSession session = request.getSession(true);
			session.setAttribute("usuario", usuario);
			return Response.status(Status.OK).entity(usuario).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST
	@Path("logout")
	public Response logout(){
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("usuario");
		
		return Response.status(Status.OK).build();
		
	}
}
