package pe.edu.upc.tp.auditoria.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.tp.auditoria.bean.*;
import pe.edu.upc.tp.auditoria.bean.Error;
import pe.edu.upc.tp.auditoria.constantes.SolicitudEstadosConstantes;
import pe.edu.upc.tp.auditoria.dao.ActaReunionDao;
import pe.edu.upc.tp.auditoria.dao.GenericDao;
import pe.edu.upc.tp.auditoria.dao.PlanAnualDao;
import pe.edu.upc.tp.auditoria.dao.ProcesoDao;
import pe.edu.upc.tp.auditoria.dao.SolicitudRegistroDao;
import pe.edu.upc.tp.auditoria.model.ActareunionModel;
import pe.edu.upc.tp.auditoria.model.EmpleadoModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.model.ProcesoModel;
import pe.edu.upc.tp.auditoria.model.SolicitudregistroModel;
import pe.edu.upc.tp.auditoria.service.SolicitudRegistroService;
import pe.edu.upc.tp.auditoria.transfer.ProcesoTransfer;
import pe.edu.upc.tp.auditoria.transfer.SolicitudRegistroTransfer;
import pe.edu.upc.tp.auditoria.util.UtilTransfer;

@Service
public class SolicitudRegistroServiceImpl implements SolicitudRegistroService {

	@Autowired
	GenericDao genericDao;

	@Autowired
	ProcesoDao procesoDao;

	@Autowired
	ActaReunionDao actareunionDao;

	@Autowired
	SolicitudRegistroDao solicitudregistroDao;
	
	@Autowired
	PlanAnualDao planAuditoriaAnualDao;

	@Override
	public SolicitudRegistroInit init() {
		SolicitudRegistroInit solicitudregistroInit = new SolicitudRegistroInit();
		try {
			ActareunionModel actareunionModel = actareunionDao.getActareunionByPeriodo(UtilTransfer.getAñoSiguiente());
			if(actareunionModel != null){
				PlanauditoriaanualModel planauditoriaanualModel = planAuditoriaAnualDao.getPlanAuditoriaAnualByPeriodo(UtilTransfer.getAñoSiguiente());
				if(planauditoriaanualModel == null){
					ProcesoTransfer procesoTransfer = new ProcesoTransfer();
					EmpleadoBean solicitante = new EmpleadoBean();
					solicitante.setEmpleadoId(1);
					solicitante.setNombres("Jordan Abe");
					solicitante.setCargo("Gerente de Sistemas");
					solicitudregistroInit.setSolicitante(solicitante);
					
					solicitudregistroInit.setPeriodo(UtilTransfer.getAñoSiguiente());

					ActaReunionBean actareunionBean = new ActaReunionBean();
					actareunionBean.setId(actareunionModel.getId());
					actareunionBean.setPeriodo(actareunionModel.getPeriodo());
					solicitudregistroInit.setActareunion(actareunionBean);

					solicitudregistroInit.setProcesos(procesoTransfer.getProcesosBean(procesoDao.getProcesos()));
				}else {
					solicitudregistroInit.setError(new Error("Ya existe un plan anual, no puede registrar una solicitud"));
				}
				
			} else {
				solicitudregistroInit.setError(new Error("No existe un acta de reunión, no puede registrar una solicitud"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			solicitudregistroInit = new SolicitudRegistroInit();
			solicitudregistroInit.setError(new Error(e.getMessage()));
		}
		return solicitudregistroInit;
	}

	@Override
	@Transactional
	public SolicitudRegistroBean create(SolicitudRegistroBean solicitudregistroRQ) {
		SolicitudRegistroBean solicitudregistroBean = new SolicitudRegistroBean();
		try {
			int idSolicitudregistro = genericDao.ultimo("id", "SolicitudregistroModel") + 1;
			SolicitudregistroModel solicitudregistroModel = new SolicitudregistroModel();
			solicitudregistroModel.setId(idSolicitudregistro);
			solicitudregistroModel.setAsunto(solicitudregistroRQ.getAsunto());
			solicitudregistroModel.setMotivo(solicitudregistroRQ.getMotivo());
			solicitudregistroModel.setPeriodo(solicitudregistroRQ.getPeriodo());
			solicitudregistroModel.setEstadoid(SolicitudEstadosConstantes.SOLICITUD_PENDIENTE);
			solicitudregistroModel.setFecharegistro(new Date());
			solicitudregistroModel.setPrioridad(solicitudregistroRQ.getPrioridad());
			solicitudregistroModel
					.setSolicitante(new EmpleadoModel(solicitudregistroRQ.getSolicitante().getEmpleadoId()));
			solicitudregistroModel.setProceso(new ProcesoModel(solicitudregistroRQ.getProceso().getProcesoId()));
			solicitudregistroModel.setActareunionId(solicitudregistroRQ.getActareunion().getId());
			genericDao.insert(solicitudregistroModel);
			solicitudregistroBean.setId(idSolicitudregistro);
		} catch (Exception e) {
			e.printStackTrace();
			solicitudregistroBean = new SolicitudRegistroBean();
			solicitudregistroBean.setError(new Error(e.getMessage()));
		}
		return solicitudregistroBean;
	}

	@Override
	public SolicitudRegistroBean findById(int id) {
		SolicitudRegistroBean solicitudregistroBean = new SolicitudRegistroBean();
		SolicitudRegistroTransfer solicitudregistroTransfer = new SolicitudRegistroTransfer();
		try {
			SolicitudregistroModel solicitudregistroModel = (SolicitudregistroModel) genericDao.findById(id,
					"SolicitudregistroModel");
			solicitudregistroBean = solicitudregistroTransfer.getSolicitudregistroBean(solicitudregistroModel);
		} catch (Exception e) {
			e.printStackTrace();
			solicitudregistroBean = new SolicitudRegistroBean();
			solicitudregistroBean.setError(new Error(e.getMessage()));
		}
		return solicitudregistroBean;
	}

	@Override
	@Transactional
	public SolicitudRegistroBean evaluar(int id, SolicitudRegistroBean solicitudregistro) {
		SolicitudRegistroBean solicitudregistroBean = new SolicitudRegistroBean();
		solicitudregistroBean.setId(id);
		try {
			SolicitudregistroModel solicitudregistroModel = (SolicitudregistroModel) genericDao.findById(id,
					"SolicitudregistroModel");
			solicitudregistroModel.setEstadoid(solicitudregistro.getEstadoid());
			solicitudregistroModel.setFechaevaluacion(new Date());
			solicitudregistroModel.setEvaluador(new EmpleadoModel(2));
			genericDao.edit(solicitudregistroModel);
			
			solicitudregistroBean.setEstadoid(solicitudregistro.getEstadoid());
			solicitudregistroBean.setBevaluar(true);
		} catch (Exception e) {
			e.printStackTrace();
			solicitudregistroBean = new SolicitudRegistroBean();
			solicitudregistroBean.setError(new Error(e.getMessage()));
		}
		return solicitudregistroBean;
	}

	@Override
	public List<SolicitudRegistroBean> getSolicitudesregistros() {
		SolicitudRegistroTransfer solicitudregistroTransfer = new SolicitudRegistroTransfer();
		return solicitudregistroTransfer.getSolicitudesregistroBean(solicitudregistroDao.getSolicitudesregistro());
	}

	@Override
	public int delete(int id) {
		solicitudregistroDao.delete(id);
		return 1;
	}

}
