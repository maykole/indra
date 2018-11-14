package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.PlanEspecificoDao;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;

@Repository
public class PlanEspecificaDaoImpl implements PlanEspecificoDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanauditoriaModel> getPlanesEspecificosByPlanAnual(int id) {
		Query q = entityManager.createQuery("SELECT p FROM PlanauditoriaModel p WHERE p.planauditoriaanualId =:planAnualId ")
				.setParameter("planAnualId", id);
		return (List<PlanauditoriaModel>) q.getResultList();
	}

	@Override
	public int delete(int id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("INDRASD_PLANAUDITORIA");
		storedProcedure.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("id", id);
		storedProcedure.execute();
		return 1;
	}

	@Override
	public PlanauditoriaModel getPlanAuditoriaByPlanAnualIdAndProgramaId(int planAnualId, int procesoId) {
		Query q = entityManager.createQuery("SELECT p FROM PlanauditoriaModel p WHERE p.planauditoriaanualId =:planAnualId and p.proceso.procesoId =:procesoId", PlanauditoriaModel.class)
				.setParameter("planAnualId", planAnualId)
				.setParameter("procesoId", procesoId);
		return (PlanauditoriaModel)q.getSingleResult();
	}

	/*@Override
	public int eliminarActividadesByProcedimiento(int procedimientoId) {
		Query q = entityManager.createQuery("DELETE FROM PlanactividadModel p WHERE p.planauditoriaanualId =:planAnualId and p.proceso.procesoId =:procesoId", PlanauditoriaModel.class)
				.setParameter("procesoId", procesoId);
		return (PlanauditoriaModel)q.getSingleResult();
		return 1;
	}*/


}
