/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Producto;

/**
 *
 * @author gato
 */
public class ServicioProducto {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean crear(Producto producto) {
    	
    	boolean productoCreado = false;
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            productoCreado = true;
        } catch (Exception e) {
            System.out.println("Error en insertar Producto");
        } finally {
            em.close();
        }
        return productoCreado;
    }

    public boolean eliminar(Producto producto) {
    	
    	boolean productoEliminado = false;
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(producto));
            em.getTransaction().commit();
            productoEliminado = true;

        } catch (Exception e) {
            System.out.println("Error en eliminar  Producto" + e);
        } finally {
            em.close();
        }
        return productoEliminado;
    }

    public boolean modificar(Producto producto) {
    	
    	boolean productoEditado = false;
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
            productoEditado = true;
        } catch (Exception e) {
            System.out.println("Error en insertar Producto");
        } finally {
            em.close();
        }
        return productoEditado;
    }

    public List<Producto> findAll() {

        List<Producto> listaProductos = null;//new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Producto.findAll", Producto.class);
//            query.setParameter("perCedula", cedula);
            listaProductos = (List<Producto>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Producto" +e.getMessage());
        } finally {
            em.close();
        }

        return listaProductos;
    }

    public Producto findByIdProducto(Integer valor) {

        Producto listaProductos = null;//new Producto();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Producto.findByIdProducto", Producto.class);
            query.setParameter("idProducto", valor);
            listaProductos = (Producto) query.getSingleResult();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Producto");
        } finally {
            em.close();
        }

        return listaProductos;
    }

    

    public List<Producto> findByProdNombre(String valor) {

        List<Producto> listaProductos = null;//new ArrayList<Producto>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Producto.findByProdNombre", Producto.class);
            query.setParameter("prodNombre", "%"+valor+"%");
            listaProductos = (List<Producto>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Producto");
        } finally {
            em.close();
        }

        return listaProductos;
    }

}
