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

import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Factura;

/**
 *
 * @author gato
 */
public class ServicioDetalleFactura {

	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public boolean crear(DetalleFactura detalleFactura) {
		
		boolean detalleCreado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.persist(detalleFactura);
			em.getTransaction().commit();
			detalleCreado = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Factura");
		} finally {
			em.close();
		}
		return detalleCreado;
	}

	public boolean eliminar(DetalleFactura detalleFactura) {
		
		boolean detalleEliminado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.remove(em.merge(detalleFactura));
			em.getTransaction().commit();
			detalleEliminado = true;
		} catch (Exception e) {
			System.out.println("Error en eliminar  Factura" + e);
		} finally {
			em.close();
		}
		return detalleEliminado;
	}

	public boolean modificar(DetalleFactura detalleFactura) {
		
		boolean detalleEditado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.merge(detalleFactura);
			em.getTransaction().commit();
			detalleEditado = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Factura");
		} finally {
			em.close();
		}
		return detalleEditado;
	}

	/*public List<DetalleFactura> findAll() {

		List<DetalleFactura> listaDetalleFactura = new ArrayList<DetalleFactura>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("DetalleFactura.findAll",
					DetalleFactura.class);
			// query.setParameter("perCedula", cedula);
			listaDetalleFactura = (List<DetalleFactura>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out
					.println("Error en lsa consulta Factura" + e.getMessage());
		} finally {
			em.close();
		}

		return listaDetalleFactura;
	}*/

	public DetalleFactura findByIdDetalle(Integer valor) {

		DetalleFactura listaDetalleFactura = null;//new ArrayList<DetalleFactura>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("DetalleFactura.findByIdDetalle",
					DetalleFactura.class);
			query.setParameter("idDetalle", valor);
			listaDetalleFactura = (DetalleFactura) query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Factura");
		} finally {
			em.close();
		}

		return listaDetalleFactura;
	}

	public List<DetalleFactura> findByFactura(Factura valor) {

		List<DetalleFactura> listaFacturas = null;//new ArrayList<DetalleFactura>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("DetalleFactura.findByFactura",DetalleFactura.class);
			query.setParameter("factura", valor);
			listaFacturas = (List<DetalleFactura>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Factura");
		} finally {
			em.close();
		}

		return listaFacturas;
	}

}
