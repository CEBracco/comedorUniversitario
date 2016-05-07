package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.UserDAO;
import unlp.comedor.Usuario;
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Usuario createUser(Usuario user) {
		return entityManager.merge(user);
	}

	@Override
	public Usuario updateUser(Usuario user) {
		entityManager.merge(user);
		return user;
	}

	@Override
	public void deleteUser(long id) {
		//entityManager.remove(entityManager.find(Usuario.class, id));
		Query query = entityManager.createQuery("UPDATE Usuario set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Usuario> getAllUsers() {
		Query query = entityManager.createQuery("SELECT e FROM Usuario e where e.activo=1");
		return (List<Usuario>)query.getResultList();
	}

	@Override
	public Usuario getUser(long id) {
		return (Usuario) entityManager.find(Usuario.class, id);
	}

	@Override
	public Usuario getUser(Integer documento, String password){
		Query query=entityManager.createQuery("from Usuario where dni=:dni and password=:password and activo=1");
		query.setParameter("dni", documento);
		query.setParameter("password", password);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Usuario)query.getResultList().get(0);
		}
	}
}
