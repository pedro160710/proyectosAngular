package api.rest;

import java.math.BigDecimal;

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

import modelo.DetalleFactura;
import servicios.ServicioDetalleFactura;
import servicios.ServicioFactura;
import servicios.ServicioProducto;

@Path(value="Detalles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class DetalleFacturaApi {
	
	private ServicioDetalleFactura servicioDetalleFactura = new ServicioDetalleFactura();
	private ServicioFactura servicioFactura = new ServicioFactura();
	private ServicioProducto servicioProducto = new ServicioProducto();
	
	/*@GET //http://localhost:8080/ProjectWeb/api/DetalleFacturas
	 * No aplica por la funcionalidad
	public Response buscarDetalleFacturas(){
		
		List<DetalleFactura> listaDetalleFacturas = servicioDetalleFactura.findAll();
		
		if(listaDetalleFacturas != null){
			return Response.status(Status.OK).entity(listaDetalleFacturas.toArray()).build();
			//return Response.ok(listaDetalleFacturas).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}*/
	
	
	@GET //http://localhost:8080/ProjectWeb/api/DetalleFacturas/3
	@Path("{id}")  
	public Response buscarDetalleFacturaPorId(@PathParam("id") int id){
		
		DetalleFactura DetalleFactura = servicioDetalleFactura.findByIdDetalle(id);
		
		if(DetalleFactura != null){
			return Response.status(Status.OK).entity(DetalleFactura).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST //http://localhost:8080/ProjectWeb/api/DetalleFacturas
	public Response crearDetalleFactura(@FormParam("cantidad") BigDecimal cantidad, 
								@FormParam("descripcion") String descripcion, 
								@FormParam("subtotal") BigDecimal subtotal,
								@FormParam("total") BigDecimal total,
								@FormParam("idFactura") int idFactura,
								@FormParam("idProducto") int idProducto){
		
		DetalleFactura nuevoDetalleFactura = new DetalleFactura();
		nuevoDetalleFactura.setDetCantidad(cantidad);
		nuevoDetalleFactura.setDetDescripcion(descripcion);
		nuevoDetalleFactura.setDetSubtotal(subtotal);
		nuevoDetalleFactura.setDetTotal(total);
		nuevoDetalleFactura.setFactura(servicioFactura.findByIdFactura(idFactura));
		nuevoDetalleFactura.setProducto(servicioProducto.findByIdProducto(idProducto));
		
		//System.out.println("NUEVO DetalleFactura "+nuevoDetalleFactura.toString());
		
		if(servicioDetalleFactura.crear(nuevoDetalleFactura)){
			return Response.status(Status.CREATED).entity(nuevoDetalleFactura).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT //http://localhost:8080/ProjectWeb/api/DetalleFacturas/3
	@Path("{id}")
	public Response editarDetalleFactura(@FormParam("cantidad") BigDecimal cantidad, 
											@FormParam("descripcion") String descripcion, 
											@FormParam("subtotal") BigDecimal subtotal,
											@FormParam("total") BigDecimal total,
											@FormParam("idFactura") int idFactura,
											@FormParam("idProducto") int idProducto,
											@PathParam("id") int id){

		DetalleFactura detalleFacturaEditar = servicioDetalleFactura.findByIdDetalle(id);
		detalleFacturaEditar.setDetCantidad(cantidad);
		detalleFacturaEditar.setDetDescripcion(descripcion);
		detalleFacturaEditar.setDetSubtotal(subtotal);
		detalleFacturaEditar.setDetTotal(total);
		detalleFacturaEditar.setFactura(servicioFactura.findByIdFactura(idFactura));
		detalleFacturaEditar.setProducto(servicioProducto.findByIdProducto(idProducto));
			
		//System.out.println("EDITAR DetalleFactura "+DetalleFacturaEditar.toString()); //StackOverflow por toString()
		
		if(servicioDetalleFactura.modificar(detalleFacturaEditar)){
			return Response.status(Status.OK).entity(detalleFacturaEditar).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE //http://localhost:8080/ProjectWeb/api/DetalleFacturas/3
	@Path("{id}")
	public Response eliminarDetalleFactura(@PathParam("id") int id){
		
		if(servicioDetalleFactura.eliminar(servicioDetalleFactura.findByIdDetalle(id))){
			return Response.status(Status.NO_CONTENT).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
