package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.ResponsableDAO;
import unlp.comedor.Responsable;

@Repository
@Transactional
public class ResponsableDAOImpl implements ResponsableDAO{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Responsable createResponsable(Responsable responsable) {
		return entityManager.merge(responsable);
	}

	@Override
	public Responsable updateResponsable(Responsable responsable) {
		entityManager.merge(responsable);
		return responsable;
	}

	@Override
	public void deleteResponsable(long id) {
		//entityManager.remove(entityManager.find(Administrador.class, id));
		Query query = entityManager.createQuery("UPDATE Responsable set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Responsable> getAllResponsables() {
		Query query = entityManager.createQuery("SELECT e FROM Responsable e WHERE tipo='Responsable'");
		return (List<Responsable>)query.getResultList();
	}

	@Override
	public Responsable getResponsable(long id) {
		return (Responsable) entityManager.find(Responsable.class, id);
	}

	@Override
	public Responsable getResponsable(Integer documento, String password) {
		Query query=entityManager.createQuery("from Responsable where dni=:dni and password=:password and tipo='Responsable' and activo=1");
		query.setParameter("dni", documento);
		query.setParameter("password", password);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Responsable)query.getResultList().get(0);
		}
	}
	
	@Override
	public Responsable getResponsable(int documento) {
		Query query=entityManager.createQuery("from Responsable where dni=:dni and tipo='Responsable'");
		query.setParameter("dni", documento);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Responsable)query.getResultList().get(0);
		}
	}
	
	@Override
	public Responsable toResponsable(Responsable responsable){
		Query query = entityManager.createQuery("UPDATE Responsable SET tipo='Responsable' WHERE id=" + responsable.getId());
		System.out.println("lalalalala" + query.executeUpdate());
		return responsable;
	}
	
	@Override
	public Responsable toAdministrador(Responsable responsable){
		Query query = entityManager.createQuery("UPDATE Responsable SET tipo='Administrador' WHERE id=" + responsable.getId());
		query.executeUpdate();
		return responsable;
	}
}
