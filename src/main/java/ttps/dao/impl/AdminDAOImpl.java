package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.AdministradorDAO;
import unlp.comedor.Administrador;



@Repository
@Transactional
public class AdminDAOImpl implements AdministradorDAO{

	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Administrador createAdministrador(Administrador admin) {
		// TODO Auto-generated method stub
		return entityManager.merge(admin);
	}

	@Override
	public Administrador updateAdministrador(Administrador admin) {
		entityManager.merge(admin);
		return admin;
	}

	@Override
	public void deleteAdministrador(long id) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.find(Administrador.class, id));
		
	}

	@Override
	public List<Administrador> getAllAdministradores() {
		Query query = entityManager.createQuery("SELECT e FROM Administrador e");
		return (List<Administrador>)query.getResultList();
	}

	@Override
	public Administrador getAdministrador(long id) {
		return (Administrador) entityManager.find(Administrador.class, id);
	}

	@Override
	public Administrador getAdministrador(Integer documento, String password) {
		Query query=entityManager.createQuery("from Administrador where dni=:dni and password=:password");
		query.setParameter("dni", documento);
		query.setParameter("password", password);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Administrador)query.getResultList().get(0);
		}
	}
}
