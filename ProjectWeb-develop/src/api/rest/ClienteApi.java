package api.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import modelo.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import servicios.ServicioCliente;
import utilitario.MailerClass;

@Path(value="Clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class ClienteApi {
	
	private ServicioCliente servicioCliente = new ServicioCliente();
	
	@GET //http://localhost:8080/ProjectWeb/api/Clientes
	public Response buscarClientes(){
		
		List<Cliente> listaClientes = servicioCliente.findAll();
		
		if(listaClientes != null){
			return Response.status(Status.OK).entity(listaClientes.toArray()).build();
			//return Response.ok(listaClientes).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	
	@GET //http://localhost:8080/ProjectWeb/api/Clientes/3
	@Path("{id}")  
	public Response buscarClientePorId(@PathParam("id") int id){
		
		Cliente cliente = servicioCliente.findByIdCliente(id);
		
		if(cliente != null){
			return Response.status(Status.OK).entity(cliente).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST //http://localhost:8080/ProjectWeb/api/Clientes
	public Response crearCliente(@FormParam("cedula") int cedula, 
							@FormParam("correo") String correo, 
							@FormParam("direccion") String direccion,
							@FormParam("movil") String movil,
							@FormParam("nombre") String nombre,
							@FormParam("razonSocial") String razonSocial,
							@FormParam("telefono") String telefono){
		
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setCliCedula(String.valueOf(cedula));
		nuevoCliente.setCliCorreo(correo);
		nuevoCliente.setCliDireccion(direccion);
		nuevoCliente.setCliMovil(movil);
		nuevoCliente.setCliNombre(nombre);
		nuevoCliente.setCliRazonSocial(razonSocial);
		nuevoCliente.setCliTelefono(telefono);
		
		//System.out.println("NUEVO CLIENTE "+nuevoCliente.toString());
		
		if(servicioCliente.crear(nuevoCliente)){
			return Response.status(Status.CREATED).entity(nuevoCliente).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT //http://localhost:8080/ProjectWeb/api/Clientes/3
	@Path("{id}")
	public Response editarCliente(@FormParam("cedula") int cedula, 
								@FormParam("correo") String correo, 
								@FormParam("direccion") String direccion,
								@FormParam("movil") String movil,
								@FormParam("nombre") String nombre,
								@FormParam("razonSocial") String razonSocial,
								@FormParam("telefono") String telefono,
								@PathParam("id") int id){

		Cliente clienteEditar = servicioCliente.findByIdCliente(id);
		
		clienteEditar.setCliCedula(String.valueOf(cedula));
		clienteEditar.setCliCorreo(correo);
		clienteEditar.setCliDireccion(direccion);
		clienteEditar.setCliMovil(movil);
		clienteEditar.setCliNombre(nombre);
		clienteEditar.setCliRazonSocial(razonSocial);
		clienteEditar.setCliTelefono(telefono);
			
		//System.out.println("EDITAR CLIENTE "+clienteEditar.toString());
		
		if(servicioCliente.modificar(clienteEditar)){
			return Response.status(Status.OK).entity(clienteEditar).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE //http://localhost:8080/ProjectWeb/api/Clientes/3
	@Path("{id}")
	public Response eliminarCliente(@PathParam("id") int id){
		
		if(servicioCliente.eliminar(servicioCliente.findByIdCliente(id))){
			return Response.status(Status.NO_CONTENT).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	//Metodos personalizados
	@GET
	@Path("buscar/nombre/{nombreCliente}")
	public Response buscarClientePorNombre(@PathParam("nombreCliente") String nombreCliente){
		
		List<Cliente> listaClientes = servicioCliente.findByCliNombre(nombreCliente);
		
		if(listaClientes != null){
			return Response.status(Status.OK).entity(listaClientes.toArray()).build();
			//return Response.ok(listaClientes).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("reporte")
	public Response generarReporteClientes(){
		
		ResponseBuilder responseBuilder = servicioCliente.generarReporte();
			
		if(responseBuilder != null){
			return responseBuilder.build();			
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
