/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.facelets.Facelet;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Factura;
import modelo.Factura;

/**
 *
 * @author gato
 */
public class ServicioFactura {

	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public boolean crear(Factura factura) {
		
		boolean facturaCreada = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.persist(factura);
			em.getTransaction().commit();
			facturaCreada = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Factura");
		} finally {
			em.close();
		}
		return facturaCreada;
	}

	public boolean eliminar(Factura factura) {
		
		boolean facturaEliminada = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.remove(em.merge(factura));
			em.getTransaction().commit();
			facturaEliminada = true;

		} catch (Exception e) {
			System.out.println("Error en eliminar  Factura" + e);
		} finally {
			em.close();
		}
		return facturaEliminada;
	}

	public boolean modificar(Factura factura) {
		
		boolean facturaEditada = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.merge(factura);
			em.getTransaction().commit();
			facturaEditada = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Factura");
		} finally {
			em.close();
		}
		return facturaEditada;
	}

	public List<Factura> findAll() {

		List<Factura> listaFacturas = null;//new ArrayList<Factura>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Factura.findAll", Factura.class);
			// query.setParameter("perCedula", cedula);
			listaFacturas = (List<Factura>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out
					.println("Error en lsa consulta Factura" + e.getMessage());
		} finally {
			em.close();
		}

		return listaFacturas;
	}

	public Factura findByIdFactura(Integer valor) {

		Factura listaFacturas = null;new ArrayList<Factura>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Factura.findByIdFactura",Factura.class);
			query.setParameter("idFactura", valor);
			listaFacturas = (Factura) query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Factura");
		} finally {
			em.close();
		}

		return listaFacturas;
	}
	

	public List<Factura> findByFacNumero(Integer valor) {

		List<Factura> listaFacturas = null;//new ArrayList<Factura>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Factura.findByFacNumero",Factura.class);
			query.setParameter("facNumero", valor);
			listaFacturas = (List<Factura>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Factura");
		} finally {
			em.close();
		}

		return listaFacturas;
	}

}
