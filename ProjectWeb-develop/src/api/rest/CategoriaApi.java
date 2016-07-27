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

import modelo.Categoria;
import servicios.ServicioCategoria;

@Path(value="Categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CategoriaApi {
	private ServicioCategoria servicioCategoria = new ServicioCategoria();
	
	@GET //http://localhost:8080/ProjectWeb/api/Categorias
	public Response buscarCategorias(){
		
		List<Categoria> listaCategorias = servicioCategoria.findAll();
		
		if(listaCategorias != null){
			return Response.status(Status.OK).entity(listaCategorias.toArray()).build();
			//return Response.ok(listaCategorias).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	
	@GET //http://localhost:8080/ProjectWeb/api/Categorias/3
	@Path("{id}")  
	public Response buscarCategoriaPorId(@PathParam("id") int id){
		
		Categoria categoria = servicioCategoria.findByIdCategoria(id);
		
		if(categoria != null){
			return Response.status(Status.OK).entity(categoria).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST //http://localhost:8080/ProjectWeb/api/Categorias
	public Response crearCategoria(@FormParam("nombre") String nombre){
		
		Categoria nuevoCategoria = new Categoria();
		nuevoCategoria.setCatNombre(nombre);
		
		//System.out.println("NUEVO Categoria "+nuevoCategoria.toString());
		
		if(servicioCategoria.crear(nuevoCategoria)){
			return Response.status(Status.CREATED).entity(nuevoCategoria).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT //http://localhost:8080/ProjectWeb/api/Categorias/3
	@Path("{id}")
	public Response editarCategoria(@FormParam("nombre") String nombre,
									@PathParam("id") int id){

		Categoria categoriaEditar = servicioCategoria.findByIdCategoria(id);
		categoriaEditar.setCatNombre(nombre);
			
		//System.out.println("EDITAR Categoria "+CategoriaEditar.toString());
		
		if(servicioCategoria.modificar(categoriaEditar)){
			return Response.status(Status.OK).entity(categoriaEditar).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE //http://localhost:8080/ProjectWeb/api/Categorias/3
	@Path("{id}")
	public Response eliminarCategoria(@PathParam("id") int id){
		
		if(servicioCategoria.eliminar(servicioCategoria.findByIdCategoria(id))){
			return Response.status(Status.NO_CONTENT).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
