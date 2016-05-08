package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.ComensalDAO;
import unlp.comedor.Comensal;

@Repository
@Transactional
public class ComensalDAOimpl implements ComensalDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Comensal createComensal(Comensal comensal) {
		return entityManager.merge(comensal);
	}

	@Override
	public Comensal updateComensal(Comensal comensal) {
		entityManager.merge(comensal);
		return comensal;
	}

	@Override
	public void deleteComensal(long id) {
		//entityManager.remove(entityManager.find(Comensal.class, id));
		Query query = entityManager.createQuery("UPDATE Comensal set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Comensal> getAllComensales() {
		Query query = entityManager.createQuery("SELECT e FROM Comensal e where activo=1");
		return (List<Comensal>)query.getResultList();
	}

	@Override
	public Comensal getComensal(long id) {
		return (Comensal) entityManager.find(Comensal.class, id);
	}

	@Override
	public Comensal getComensal(Integer documento, String password) {
		Query query=entityManager.createQuery("from Comensal where dni=:dni and password=:password and activo=1");
		query.setParameter("dni", documento);
		query.setParameter("password", password);
		
		if(query.getResultList().size() == 0){
			return null;
		}
		else{
			return (Comensal)query.getResultList().get(0);
		}
	}

}
