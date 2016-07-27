package api.rest;

import java.math.BigDecimal;
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

import modelo.Producto;
import servicios.ServicioCategoria;
import servicios.ServicioProducto;

@Path(value="Productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class ProductoApi {
	
	private ServicioProducto servicioProducto = new ServicioProducto();
	private ServicioCategoria servicioCategoria = new ServicioCategoria();
	
	@GET //http://localhost:8080/ProjectWeb/api/Productos
	public Response buscarProductos(){
		
		List<Producto> listaProductos = servicioProducto.findAll();
		
		if(listaProductos != null){
			return Response.status(Status.OK).entity(listaProductos.toArray()).build();
			//return Response.ok(listaProductos).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	
	@GET //http://localhost:8080/ProjectWeb/api/Productos/3
	@Path("{id}")  
	public Response buscarProductoPorId(@PathParam("id") int id){
		
		Producto Producto = servicioProducto.findByIdProducto(id);
		
		if(Producto != null){
			return Response.status(Status.OK).entity(Producto).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST //http://localhost:8080/ProjectWeb/api/Productos
	public Response crearProducto(@FormParam("costoVentaFinal") BigDecimal costoVentaFinal, 
								@FormParam("costoVentaReferencial") BigDecimal costoVentaReferencial, 
								@FormParam("codigo") String codigo,
								@FormParam("estado") int estado,
								@FormParam("nombre") String nombre,
								@FormParam("idCategoria") int idCategoria){
		
		Producto nuevoProducto = new Producto();
		nuevoProducto.setPordCostoVentaFinal(costoVentaFinal);
		nuevoProducto.setPordCostoVentaRef(costoVentaReferencial);
		nuevoProducto.setProdCodigo(codigo);
		nuevoProducto.setProdEstado(estado);
		nuevoProducto.setProdNombre(nombre);
		nuevoProducto.setCategoria(servicioCategoria.findByIdCategoria(idCategoria));
		
		//System.out.println("NUEVO Producto "+nuevoProducto.toString());
		
		if(servicioProducto.crear(nuevoProducto)){
			return Response.status(Status.CREATED).entity(nuevoProducto).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT //http://localhost:8080/ProjectWeb/api/Productos/3
	@Path("{id}")
	public Response editarProducto(@FormParam("costoVentaFinal") BigDecimal costoVentaFinal, 
								@FormParam("costoVentaReferencial") BigDecimal costoVentaReferencial, 
								@FormParam("codigo") String codigo,
								@FormParam("estado") int estado,
								@FormParam("nombre") String nombre,
								@FormParam("idCategoria") int idCategoria,
								@PathParam("id") int id){

		Producto productoEditar = servicioProducto.findByIdProducto(id);
		productoEditar.setPordCostoVentaFinal(costoVentaFinal);
		productoEditar.setPordCostoVentaRef(costoVentaReferencial);
		productoEditar.setProdCodigo(codigo);
		productoEditar.setProdEstado(estado);
		productoEditar.setProdNombre(nombre);
		productoEditar.setCategoria(servicioCategoria.findByIdCategoria(idCategoria));
			
		//System.out.println("EDITAR Producto "+productoEditar.toString()); //StackOverflow por toString()
		
		if(servicioProducto.modificar(productoEditar)){
			return Response.status(Status.OK).entity(productoEditar).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE //http://localhost:8080/ProjectWeb/api/Productos/3
	@Path("{id}")
	public Response eliminarProducto(@PathParam("id") int id){
		
		if(servicioProducto.eliminar(servicioProducto.findByIdProducto(id))){
			return Response.status(Status.NO_CONTENT).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
