package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.ProgramaDao;
import pe.edu.upc.tp.auditoria.model.ProgramaModel;

@Repository
public class ProgramaDaoImpl implements ProgramaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaModel> getProgramasByPlanAnualAndFechas(int idPlanAnual, Date currentDate) {
		Query q = entityManager
				.createQuery(
						"SELECT p FROM ProgramaModel p WHERE p.planauditoriaanualId=:planAnualId AND :currentDate BETWEEN p.fechaInicioLimite AND p.fechaFinLimite")
				.setParameter("planAnualId", idPlanAnual).setParameter("currentDate", currentDate);
		return (List<ProgramaModel>) q.getResultList();
	}

}
