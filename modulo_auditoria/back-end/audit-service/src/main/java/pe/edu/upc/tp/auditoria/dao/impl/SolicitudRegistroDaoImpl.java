package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.SolicitudRegistroDao;
import pe.edu.upc.tp.auditoria.model.ActareunionModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.model.ProcedimientoModel;
import pe.edu.upc.tp.auditoria.model.SolicitudregistroModel;

@Repository
public class SolicitudRegistroDaoImpl implements SolicitudRegistroDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SolicitudregistroModel> getSolicitudesregistro() {
        Query q = entityManager.createQuery("SELECT p FROM SolicitudregistroModel p");
        return (List<SolicitudregistroModel>)q.getResultList();
    }

	@Override
	public SolicitudregistroModel getSolicitudregistroPendiente(String periodo) {
		SolicitudregistroModel solicitudregistroModel = null;
		try {
			Query q = entityManager
					.createQuery("SELECT p FROM SolicitudregistroModel p WHERE p.estadoid = 1 and p.periodo=:periodo ", SolicitudregistroModel.class)
					.setParameter("periodo", periodo);
			solicitudregistroModel = (SolicitudregistroModel) q.getSingleResult();
		} catch (NoResultException e) {
			 e.printStackTrace();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return solicitudregistroModel;
	}

	@Override
	public List<SolicitudregistroModel> getSolicitudregistroEvaluadas(String periodo) {
		List<SolicitudregistroModel> solicitudregistroModel = null;
		try {
			Query q = entityManager
					.createQuery("SELECT p FROM SolicitudregistroModel p WHERE p.estadoid = 2 and p.periodo=:periodo ", SolicitudregistroModel.class)
					.setParameter("periodo", periodo);
			solicitudregistroModel = (List<SolicitudregistroModel>) q.getResultList();
		} catch (NoResultException e) {
			 e.printStackTrace();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return solicitudregistroModel;
	}

	@Override
	public List<SolicitudregistroModel> getSolicitudregistroByActareunionId(int id) {
		List<SolicitudregistroModel> solicitudregistros = null;
		try {
			Query q = entityManager.createQuery("SELECT p FROM SolicitudregistroModel p WHERE p.actareunionId=:actareunionId ", SolicitudregistroModel.class)
					.setParameter("actareunionId", id);
			solicitudregistros = (List<SolicitudregistroModel>) q.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return solicitudregistros;
	}

	@Override
	public int delete(int id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("INDRASD_SOLICITUDREGISTRO");
		storedProcedure.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("id", id);
		storedProcedure.execute();
		return 1;
	}
	
}
