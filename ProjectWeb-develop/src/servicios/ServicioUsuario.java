/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

/**
 *
 * @author gato
 */
public class ServicioUsuario {

	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public boolean crear(Usuario usuario) {
		boolean usuarioCreado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			usuarioCreado = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Usuario");
		} finally {
			em.close();
		}
		return usuarioCreado;
	}

	public boolean eliminar(Usuario usuario) {
		boolean usuarioEliminado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.remove(em.merge(usuario));
			em.getTransaction().commit();
			usuarioEliminado = true;
		} catch (Exception e) {
			System.out.println("Error en eliminar  Usuario" + e);
		} finally {
			em.close();
		}
		return  usuarioEliminado;
	}

	public boolean modificar(Usuario usuario) {
		boolean usuarioEditado = false;
		try {
			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
			usuarioEditado = true;
		} catch (Exception e) {
			System.out.println("Error en insertar Usuario");
		} finally {
			em.close();
		}
		return usuarioEditado;
	}

	public List<Usuario> findAll() {

		List<Usuario> listaUsuarios = null;//new ArrayList<Usuario>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Usuario.findAll", Usuario.class);
			// query.setParameter("perCedula", cedula);
			listaUsuarios = (List<Usuario>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out
					.println("Error en lsa consulta Usuario" + e.getMessage());
		} finally {
			em.close();
		}

		return listaUsuarios;
	}

	public Usuario findByIdUsuario(Integer valor) {

		Usuario listaUsuarios = null;//new ArrayList<Usuario>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Usuario.findByIdUsuario",
					Usuario.class);
			query.setParameter("idUsuario", valor);
			listaUsuarios = (Usuario) query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Usuario");
		} finally {
			em.close();
		}

		return listaUsuarios;
	}

	public List<Usuario> findByUsuNombre(String valor) {

		List<Usuario> listaUsuarios = null;//new ArrayList<Usuario>();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Usuario.findByUsuNombre",
					Usuario.class);
			query.setParameter("usuNombre", valor);
			listaUsuarios = (List<Usuario>) query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Usuario");
		} finally {
			em.close();
		}

		return listaUsuarios;
	}

	public Usuario validarUsuario(String username, String password) {

		Usuario usuario = null;//new Usuario();
		try {
			// Connection connection = em.unwrap(Connection.class);

			em = HelperPersistencia.getEMF();
			em.getTransaction().begin();
			Query query = em.createNamedQuery("Usuario.findByValidarUsuario",
					Usuario.class);
			query.setParameter("usuLogin", username);
			query.setParameter("usuPassword", password);

			usuario = (Usuario) query.getSingleResult();
			System.out.println("longitud de la lista de usu "+usuario);
			if (usuario != null) {
				System.out.println("existe el usuario");
			} else {
				System.out.println("no encuentra en la base al usuari");

			}

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en lsa consulta Usuario");
		} finally {
			em.close();
		}

		return usuario;
	}
}
