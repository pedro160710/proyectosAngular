/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import modelo.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import utilitario.MailerClass;

/**
 *
 * @author gato
 */
public class ServicioCliente {

	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public boolean crear(Cliente cliente) {
		
		boolean clienteCreado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
			clienteCreado = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Cliente");
		} finally {
			em.close();
		}
		return clienteCreado;
	}

	public boolean eliminar(Cliente cliente) {

		boolean clienteEliminado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.remove(em.merge(cliente));
			em.getTransaction().commit();
			clienteEliminado = true;
		} catch (Exception e) {
			System.out.println("Error en eliminar  Cliente" + e);
		} finally {
			em.close();
		}
		
		return clienteEliminado;
	}

	public boolean modificar(Cliente cliente) {
		
		boolean clienteEditado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.merge(cliente);
			em.getTransaction().commit();
			clienteEditado = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Cliente");
		} finally {
			em.close();
		}
		
		return clienteEditado;
	}

	public List<Cliente> findAll() {

		List<Cliente> listaClientes = null;//new ArrayList<Cliente>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Cliente.findAll", Cliente.class);
			// query.setParameter("perCedula", cedula);
			listaClientes = (List<Cliente>) query.getResultList();

			em.getTransaction().commit();
			System.out.println("CONSULTANDO TODOS LOS CLIENTES...");

		} catch (Exception e) {
			System.out.println("Error en lsa consulta Cliente" + e.getMessage());
		} finally {
			em.close();
		}

		return listaClientes;
	}

	public Cliente findByIdCliente(Integer valor) {

		Cliente listaClientes = null;//new Cliente();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Cliente.findByIdCliente",
					Cliente.class);
			query.setParameter("idCliente", valor);
			listaClientes = (Cliente) query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Cliente");
		} finally {
			em.close();
		}

		return listaClientes;
	}

	public List<Cliente> findByCliCedula(String valor) {

		List<Cliente> listaClientes = null;//new ArrayList<Cliente>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Cliente.findByCliCedula",
					Cliente.class);
			query.setParameter("cliCedula", "%" + valor + "%");
			listaClientes = (List<Cliente>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Cliente");
		} finally {
			em.close();
		}

		return listaClientes;
	}
	
	public Cliente findByCedula(String valor) {

		Cliente listaClientes = null;//new Cliente();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Cliente.findByCliCedula",
					Cliente.class);
			query.setParameter("cliCedula", "%" + valor + "%");
			listaClientes = (Cliente) query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Cliente");
		} finally {
			em.close();
		}

		return listaClientes;
	}
	public List<Cliente> findByCliNombre(String valor) {

		List<Cliente> listaClientes = null;//new ArrayList<Cliente>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Cliente.findByCliNombre",
					Cliente.class);
			query.setParameter("cliNombre", "%" + valor + "%");
			listaClientes = (List<Cliente>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Cliente");
		} finally {
			em.close();
		}

		return listaClientes;
	}
	
	public ResponseBuilder generarReporte(){
		
		byte[] bytes=null;
		ResponseBuilder responseBuilder = null;
		
		try {

			Connection con = null;
			String inputStream = null;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			inputStream = getClass().getClassLoader().getResource(File.separator + "reportes" + File.separator + "cliente_epn.jasper").getPath();
			parametros.put("cliente", "%%");

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventarioepn","root", "root");

			if (con != null) {
				System.out.println("Conexi�n Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				// return con;
			} else {
				System.out.println("error en la conexion");
			}
			
			FileInputStream fileInputStream = new FileInputStream(inputStream);
			bytes = JasperRunManager.runReportToPdf(fileInputStream, parametros, con);

			responseBuilder = Response.ok(bytes);
		    responseBuilder.type("application/pdf");
		    responseBuilder.header("Content-Disposition", "filename=clientes.pdf");
		
		} catch (FileNotFoundException | JRException | SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// si es diferente, significa que si se genero el pdf
		if(responseBuilder != null){
			// DESCOMENTAR ESTO ANTES DE PRESENTAR EL PROYECTO
			//enviarEmail();
		}
		return responseBuilder;
		
		
	}
	
	private void enviarEmail(){
		
		boolean estadoEnvio;
				
		try {
			
			//ByteToPdf.byteArrayToFile(bytes);
			MailerClass mailerClass = new MailerClass();
			List<String> listaEnvio = new ArrayList<String>();
			listaEnvio.add("darwinvinicio14_11@hotmail.com");
			estadoEnvio = mailerClass.sendMail(listaEnvio,
					"Prueba de mail de aplicaciones web 2016, se ha generado un reporte", "",
					"Presentacion del proyecto", "nombreArchivo.jpg");
			
			if(estadoEnvio){
				System.out.println("enviado con exito");
			}else{
				System.out.println("no se enviooooooo");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
