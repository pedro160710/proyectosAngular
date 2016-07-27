package api.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import modelo.Usuario;
import servicios.ServicioUsuario;

@Path(value="Usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class UsuarioApi {
	private ServicioUsuario servicioUsuario = new ServicioUsuario();
	
	@GET //http://localhost:8080/ProjectWeb/api/Usuarios
	public Response buscarUsuarios(){
		
		List<Usuario> listaUsuarios = servicioUsuario.findAll();
		
		if(listaUsuarios != null){
			return Response.status(Status.OK).entity(listaUsuarios.toArray()).build();
			//return Response.ok(listaUsuarios).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	
	@GET //http://localhost:8080/ProjectWeb/api/Usuarios/3
	@Path("{id}")  
	public Response buscarUsuarioPorId(@PathParam("id") int id){
		
		Usuario usuario = servicioUsuario.findByIdUsuario(id);
		
		if(usuario != null){
			return Response.status(Status.OK).entity(usuario).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST //http://localhost:8080/ProjectWeb/api/Usuarios
	public Response crearUsuario(@FormParam("correo") String correo, 
								@FormParam("login") String login,
								@FormParam("nivel") int nivel,
								@FormParam("nombre") String nombre,
								@FormParam("password") String password){
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setUsuCorreo(correo);
		nuevoUsuario.setUsuLogin(login);
		nuevoUsuario.setUsuNivel(nivel);
		nuevoUsuario.setUsuNombre(nombre);
		nuevoUsuario.setUsuPassword(password);
		
		System.out.println("NUEVO Usuario "+nuevoUsuario.toString());
		
		if(servicioUsuario.crear(nuevoUsuario)){
			return Response.status(Status.CREATED).entity(nuevoUsuario).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT //http://localhost:8080/ProjectWeb/api/Usuarios/3
	@Path("{id}")
	public Response editarUsuario(@FormParam("correo") String correo, 
								@FormParam("login") String login,
								@FormParam("nivel") int nivel,
								@FormParam("nombre") String nombre,
								@FormParam("password") String password,
								@PathParam("id") int id){

		Usuario usuarioEditar = servicioUsuario.findByIdUsuario(id);
		usuarioEditar.setUsuCorreo(correo);
		usuarioEditar.setUsuLogin(login);
		usuarioEditar.setUsuNivel(nivel);
		usuarioEditar.setUsuNombre(nombre);
		usuarioEditar.setUsuPassword(password);
			
		System.out.println("EDITAR Usuario "+usuarioEditar.toString());
		
		if(servicioUsuario.modificar(usuarioEditar)){
			return Response.status(Status.OK).entity(usuarioEditar).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE //http://localhost:8080/ProjectWeb/api/Usuarios/3
	@Path("{id}")
	public Response eliminarUsuario(@PathParam("id") int id){
		
		if(servicioUsuario.eliminar(servicioUsuario.findByIdUsuario(id))){
			return Response.status(Status.NO_CONTENT).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
