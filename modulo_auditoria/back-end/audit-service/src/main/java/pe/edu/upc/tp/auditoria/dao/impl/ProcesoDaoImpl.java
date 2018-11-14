package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.ProcesoDao;
import pe.edu.upc.tp.auditoria.model.ProcesoModel;

@Repository
public class ProcesoDaoImpl implements ProcesoDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ProcesoModel> getProcesos() {
		Query q = entityManager.createQuery("SELECT p FROM ProcesoModel p");
		@SuppressWarnings("unchecked")
		List<ProcesoModel> procesos = (List<ProcesoModel>)q.getResultList();
		return procesos;
	}

}
