package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.ProcedimientoDao;
import pe.edu.upc.tp.auditoria.model.ProcedimientoModel;

@Repository
public class ProcedimientoDaoImpl implements ProcedimientoDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcedimientoModel> getProcedimientos() {
		Query q = entityManager.createQuery("SELECT p FROM ProcedimientoModel p");
		return (List<ProcedimientoModel>)q.getResultList();
	}

	@Override
	public List<ProcedimientoModel> getProcedimientosByProceso(int idProceso) {
		Query q = entityManager.createQuery("SELECT p FROM ProcedimientoModel p WHERE p.procesoId=:procesoId", ProcedimientoModel.class)
				.setParameter("procesoId", idProceso);
		return (List<ProcedimientoModel>)q.getResultList();
	}

}
