/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Categoria;

/**
 *
 * @author gato
 */
public class ServicioCategoria {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean crear(Categoria Categoria) {
    	
    	boolean categoriaCreada = false;
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(Categoria);
            em.getTransaction().commit();
            categoriaCreada = true;
        } catch (Exception e) {
            System.out.println("Error en insertar Categoria");
        } finally {
            em.close();
        }
        return categoriaCreada;
    }

    public boolean eliminar(Categoria Categoria) {
    	
    	boolean categoriaEliminada = false;
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(Categoria));
            em.getTransaction().commit();
            categoriaEliminada = true;


        } catch (Exception e) {
            System.out.println("Error en eliminar  Categoria" + e);
        } finally {
            em.close();
        }
        return categoriaEliminada;
    }

    public boolean modificar(Categoria Categoria) {
    	
    	boolean categoriaEditada = false;
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(Categoria);
            em.getTransaction().commit();
            categoriaEditada = true;
        } catch (Exception e) {
            System.out.println("Error en insertar Categoria");
        } finally {
            em.close();
        }
        return categoriaEditada;
    }

    public List<Categoria> findAll() {

        List<Categoria> listaCategorias = null;//new ArrayList<Categoria>();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Categoria.findAll", Categoria.class);
//            query.setParameter("perCedula", cedula);
            listaCategorias = (List<Categoria>) query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Categoria" +e.getMessage());
        } finally {
            em.close();
        }

        return listaCategorias;
    }

    public Categoria findByIdCategoria(Integer id) {

        Categoria listaCategorias = null;//new Categoria();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Categoria.findByIdCategoria", Categoria.class);
            query.setParameter("idCategoria", id);
            listaCategorias = (Categoria) query.getSingleResult();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta por id Categoria");
        } finally {
            em.close();
        }

        return listaCategorias;
    }

    public Categoria findByProvCedula(String valor) {

        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        Categoria Categoria = new Categoria();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Categoria.findByProvCedula", Categoria.class);
            query.setParameter("provCedula", valor);
            listaCategorias = (List<Categoria>) query.getResultList();
            if (listaCategorias.size() > 0) {
                Categoria = listaCategorias.get(0);
            } else {
                Categoria = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Categoria");
        } finally {
            em.close();
        }

        return Categoria;
    }
    public Categoria findByProvNombre(String valor) {

        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        Categoria Categoria = new Categoria();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Categoria.findByProvNombre", Categoria.class);
            query.setParameter("provNombre", valor);
            listaCategorias = (List<Categoria>) query.getResultList();
            if (listaCategorias.size() > 0) {
                Categoria = listaCategorias.get(0);
            } else {
                Categoria = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Categoria");
        } finally {
            em.close();
        }

        return Categoria;
    }
   
}
