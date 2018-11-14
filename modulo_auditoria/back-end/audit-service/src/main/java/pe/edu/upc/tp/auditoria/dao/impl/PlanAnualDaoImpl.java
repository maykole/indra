package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.PlanAnualDao;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;

@Repository
public class PlanAnualDaoImpl implements PlanAnualDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PlanauditoriaanualModel getPlanAuditoriaAnualById(int id) {
		PlanauditoriaanualModel planauditoriaanual = entityManager.find(PlanauditoriaanualModel.class, id);
		return planauditoriaanual;
	}

	@Override
	public List<PlanauditoriaanualModel> getPlananualByActarenionId(int id) {
		List<PlanauditoriaanualModel> planauditoriaanual = null;
		try {
			Query q = entityManager.createQuery("SELECT p FROM PlanauditoriaanualModel p WHERE p.actareunionId=:actareunionId ", PlanauditoriaanualModel.class)
					.setParameter("actareunionId", id);
			planauditoriaanual = (List<PlanauditoriaanualModel>) q.getResultList();
		} catch (NoResultException e) {
//			e.printStackTrace();
		}catch (Exception e) {
//			e.printStackTrace();
		}
		return planauditoriaanual;
	}

	@Override
	public PlanauditoriaanualModel getPlanAuditoriaAnualByPeriodo(String periodo) {
		PlanauditoriaanualModel planauditoriaanual = null;
		try {
			Query q = entityManager.createQuery("SELECT p FROM PlanauditoriaanualModel p WHERE p.periodo=:periodo ")
					.setParameter("periodo", periodo);
			planauditoriaanual = (PlanauditoriaanualModel) q.getSingleResult();
		} catch (NoResultException e) {
//			e.printStackTrace();
		}catch (Exception e) {
//			e.printStackTrace();
		}
		return planauditoriaanual;
	}

	@Override
	public int getCantidadPlanAnualByPeriodo(String periodo) {
		Query q = entityManager
				.createQuery("SELECT count(p) FROM PlanauditoriaanualModel p WHERE p.periodo=:periodo ", Long.class)
				.setParameter("periodo", periodo);
		Long result = (Long) q.getSingleResult();
		return result.intValue();
	}

	@Override
	public int delete(int id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("INDRASD_PLANANUAL");
		storedProcedure.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("id", id);
		storedProcedure.execute();
		return 1;
	}

	@Override
	public List<PlanauditoriaanualModel> getPlanesAnuales() {
		Query q = entityManager.createQuery("SELECT p FROM PlanauditoriaanualModel p", PlanauditoriaanualModel.class);
		@SuppressWarnings("unchecked")
		List<PlanauditoriaanualModel> planesAnual = (List<PlanauditoriaanualModel>) q.getResultList();
		return planesAnual;
	}

}
