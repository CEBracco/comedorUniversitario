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
		//entityManager.remove(entityManager.find(Administrador.class, id));
		Query query = entityManager.createQuery("UPDATE Administrador set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Administrador> getAllAdministradores() {
		Query query = entityManager.createQuery("SELECT e FROM Administrador e WHERE tipo='Administrador' and activo=1");
		return (List<Administrador>)query.getResultList();
	}

	@Override
	public Administrador getAdministrador(long id) {
		return (Administrador) entityManager.find(Administrador.class, id);
	}

	@Override
	public Administrador getAdministrador(Integer documento, String password) {
		Query query=entityManager.createQuery("from Administrador where dni=:dni and password=:password and activo=1");
		query.setParameter("dni", documento);
		query.setParameter("password", password);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Administrador)query.getResultList().get(0);
		}
	}
	
	@Override
	public Administrador getAdministrador(int documento) {
		Query query=entityManager.createQuery("from Administrador where dni=:dni");
		query.setParameter("dni", documento);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Administrador)query.getResultList().get(0);
		}
	}
}
