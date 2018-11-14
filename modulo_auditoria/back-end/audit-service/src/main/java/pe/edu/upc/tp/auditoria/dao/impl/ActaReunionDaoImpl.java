package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Service;

import pe.edu.upc.tp.auditoria.dao.ActaReunionDao;
import pe.edu.upc.tp.auditoria.model.ActareunionModel;

@Service
public class ActaReunionDaoImpl implements ActaReunionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ActareunionModel getActareunionByPeriodo(String periodo) {
		ActareunionModel actareunionModel = null;
		try {
			Query q = entityManager
					.createQuery("SELECT p FROM ActareunionModel p WHERE p.periodo=:periodo ", ActareunionModel.class)
					.setParameter("periodo", periodo);
			actareunionModel = (ActareunionModel) q.getSingleResult();
		} catch (NoResultException e) {
			 e.printStackTrace();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return actareunionModel;
	}

	@Override
	public List<ActareunionModel> getActasreunion() {
		Query q = entityManager.createQuery("SELECT p FROM ActareunionModel p");
		return (List<ActareunionModel>) q.getResultList();
	}

	@Override
	public int delete(int id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("INDRASD_ACTAREUNION");
		storedProcedure.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("id", id);
		storedProcedure.execute();
		return 1;
	}

}
