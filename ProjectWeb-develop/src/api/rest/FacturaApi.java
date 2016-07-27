package api.rest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import modelo.DetalleFactura;
import modelo.Factura;
import servicios.ServicioCliente;
import servicios.ServicioDetalleFactura;
import servicios.ServicioFactura;
import servicios.ServicioUsuario;

@Path(value="Facturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class FacturaApi {
	
	private ServicioFactura servicioFactura = new ServicioFactura();
	private ServicioCliente servicioCliente = new ServicioCliente();
	private ServicioUsuario servicioUsuario = new ServicioUsuario();
	private ServicioDetalleFactura servicioDetalleFactura = new ServicioDetalleFactura();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@GET //http://localhost:8080/ProjectWeb/api/Facturas
	public Response buscarFacturas(){
		
		List<Factura> listaFacturas = servicioFactura.findAll();
		
		if(listaFacturas != null){
			return Response.status(Status.OK).entity(listaFacturas.toArray()).build();
			//return Response.ok(listaFacturas).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	
	@GET //http://localhost:8080/ProjectWeb/api/Facturas/3
	@Path("{id}")  
	public Response buscarFacturaPorId(@PathParam("id") int id){
		
		Factura Factura = servicioFactura.findByIdFactura(id);
		
		if(Factura != null){
			return Response.status(Status.OK).entity(Factura).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST //http://localhost:8080/ProjectWeb/api/Facturas
	public Response crearFactura(@FormParam("fecha") String fecha, 
								@FormParam("iva") BigDecimal iva, 
								@FormParam("numero") int numero,
								@FormParam("subtotal") BigDecimal subtotal,
								@FormParam("total") BigDecimal total,
								@FormParam("idCliente") int idCliente,
								@FormParam("idUsuario") int idUsuario) throws ParseException{
		
		Factura nuevoFactura = new Factura();
		Date inputDate = dateFormat.parse(fecha);
		nuevoFactura.setFacFecha(inputDate);
		nuevoFactura.setFacIva(iva);
		nuevoFactura.setFacNumero(numero);
		nuevoFactura.setFacSubtotal(subtotal);
		nuevoFactura.setFacTotal(total);
		nuevoFactura.setCliente(servicioCliente.findByIdCliente(idCliente));
		nuevoFactura.setUsuario(servicioUsuario.findByIdUsuario(idUsuario));
		//System.out.println("NUEVO Factura "+nuevoFactura.toString());
		
		if(servicioFactura.crear(nuevoFactura)){
			return Response.status(Status.CREATED).entity(nuevoFactura).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT //http://localhost:8080/ProjectWeb/api/Facturas/3
	@Path("{id}")
	public Response editarFactura(@FormParam("fecha") String fecha, 
									@FormParam("iva") BigDecimal iva, 
									@FormParam("numero") int numero,
									@FormParam("subtotal") BigDecimal subtotal,
									@FormParam("total") BigDecimal total,
									@FormParam("idCliente") int idCliente,
									@FormParam("idUsuario") int idUsuario,
									@PathParam("id") int id) throws ParseException{

		Factura facturaEditar = servicioFactura.findByIdFactura(id);
		Date inputDate = dateFormat.parse(fecha);
		facturaEditar.setFacFecha(inputDate);		
		facturaEditar.setFacIva(iva);
		facturaEditar.setFacNumero(numero);
		facturaEditar.setFacSubtotal(subtotal);
		facturaEditar.setFacTotal(total);
		facturaEditar.setCliente(servicioCliente.findByIdCliente(idCliente));
		facturaEditar.setUsuario(servicioUsuario.findByIdUsuario(idUsuario));
			
		//System.out.println("EDITAR Factura "+FacturaEditar.toString()); //StackOverflow por toString()
		
		if(servicioFactura.modificar(facturaEditar)){
			return Response.status(Status.OK).entity(facturaEditar).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE //http://localhost:8080/ProjectWeb/api/Facturas/3
	@Path("{id}")
	public Response eliminarFactura(@PathParam("id") int id){
		
		if(servicioFactura.eliminar(servicioFactura.findByIdFactura(id))){
			return Response.status(Status.NO_CONTENT).build();
		}
		else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	// Metodos personalizados
	@GET //http://localhost:8080/ProjectWeb/api/Facturas/3/Detalles
	@Path("{id}/Detalles")
	public Response buscarDetallesFactura(@PathParam("id") int idFactura){
		
		Factura factura = servicioFactura.findByIdFactura(idFactura);
		
		if(factura != null){
			
			List<DetalleFactura> listaDetalles = servicioDetalleFactura.findByFactura(factura);
			return Response.status(Status.OK).entity(listaDetalles.toArray()).build();
			//return Response.ok(listaFacturas).build();
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
}
