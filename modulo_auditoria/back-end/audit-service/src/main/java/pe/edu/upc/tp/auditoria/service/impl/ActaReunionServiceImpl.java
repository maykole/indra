package pe.edu.upc.tp.auditoria.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.ActaReunionInit;
import pe.edu.upc.tp.auditoria.bean.EmpleadoBean;
import pe.edu.upc.tp.auditoria.bean.Error;
import pe.edu.upc.tp.auditoria.bean.ProcesoBean;
import pe.edu.upc.tp.auditoria.dao.*;
import pe.edu.upc.tp.auditoria.dao.impl.PlanAnualDaoImpl;
import pe.edu.upc.tp.auditoria.dao.impl.SolicitudRegistroDaoImpl;
import pe.edu.upc.tp.auditoria.model.*;
import pe.edu.upc.tp.auditoria.service.ActaReunionService;
import pe.edu.upc.tp.auditoria.service.PlanAnualService;
import pe.edu.upc.tp.auditoria.service.SolicitudRegistroService;
import pe.edu.upc.tp.auditoria.transfer.ActaReunionTransfer;
import pe.edu.upc.tp.auditoria.transfer.EmpleadoTransfer;
import pe.edu.upc.tp.auditoria.transfer.ProcesoTransfer;
import pe.edu.upc.tp.auditoria.util.UtilTransfer;

@Service
public class ActaReunionServiceImpl implements ActaReunionService {

    @Autowired
    GenericDao genericDao;

    @Autowired
    ProcesoDao procesoDao;

    @Autowired
    EmpleadoDao empleadoDao;

    @Autowired
    ActaReunionDao actareunionDao;

    @Autowired
    SolicitudRegistroDao solicitudregistroDao;

    @Autowired
    PlanAnualDao plananualDao;

    @Override
    public ActaReunionInit init() {
    	ActaReunionInit actaReunionInit = new ActaReunionInit();
    	ActareunionModel actareunionModel = actareunionDao.getActareunionByPeriodo(UtilTransfer.getAñoSiguiente());
    	if(actareunionModel == null){
    		ProcesoTransfer procesoTransfer = new ProcesoTransfer();
            EmpleadoTransfer empleadoTransfer = new EmpleadoTransfer();
            actaReunionInit.setPeriodo(UtilTransfer.getAñoSiguiente());
            actaReunionInit.setProcesos(procesoTransfer.getProcesosBean(procesoDao.getProcesos()));
            actaReunionInit.setAsistentes(empleadoTransfer.getEmpleadosBean(empleadoDao.getEmpleados()));
    	}else {
    		actaReunionInit.setError(new Error("Ya existe un acta de reunion, no se puede crear otro"));
    	}
        return actaReunionInit;
    }

    @Override
    @Transactional
    public ActaReunionBean create(ActaReunionBean actaReunionRQ) {
    	ActaReunionBean actaReunionBean = new ActaReunionBean();
        try {
        	ActareunionModel actareunionModel = actareunionDao.getActareunionByPeriodo(UtilTransfer.getAñoSiguiente());
        	if(actareunionModel == null){
        		ActareunionModel actareunion = new ActareunionModel();
                int idActareunion = genericDao.ultimo("id", "ActareunionModel") + 1;
                actareunion.setId(idActareunion);
                actareunion.setPeriodo(actaReunionRQ.getPeriodo());
                actareunion.setTitulo(actaReunionRQ.getTitulo());
                actareunion.setDescripcion(actaReunionRQ.getDescripcion());
                actareunion.setLugar(actaReunionRQ.getLugar());
                actareunion.setFecha(new Date());
                actareunion.setConclusiones(actaReunionRQ.getConclusiones());
                actareunion.setAcuerdos(actaReunionRQ.getAcuerdos());
                List<ActareunionProcesoModel> actareunionProcesos = new ArrayList<>();
                int idActareunionProceso = genericDao.ultimo("id", "ActareunionProcesoModel") + 1;
                for (ProcesoBean p : actaReunionRQ.getProcesos()) {
                    ActareunionProcesoModel ar = new ActareunionProcesoModel();
                    ar.setId(idActareunionProceso);
                    ar.setActareunionId(idActareunion);
                    ar.setProceso(new pe.edu.upc.tp.auditoria.model.ProcesoModel(p.getProcesoId()));
                    actareunionProcesos.add(ar);
                    idActareunionProceso++;
                }
                
                List<ActareunionAsistenteModel> actareunionAsistentes = new ArrayList<>();
                int idActareunionAsistente = genericDao.ultimo("id", "ActareunionAsistenteModel") + 1;
                for (EmpleadoBean e : actaReunionRQ.getAsistentes()) {
                    ActareunionAsistenteModel actareunionAsistente = new ActareunionAsistenteModel();
                    actareunionAsistente.setId(idActareunionAsistente);
                    actareunionAsistente.setActareunionId(idActareunion);
                    actareunionAsistente.setEmpleado(new pe.edu.upc.tp.auditoria.model.EmpleadoModel(e.getEmpleadoId()));
                    actareunionAsistente.setCargo(e.getCargo());
                    actareunionAsistentes.add(actareunionAsistente);
                    idActareunionAsistente++;
                }
                System.out.println("********* " + UtilTransfer.objectToJson(actareunion));
                genericDao.insert(actareunion);
                genericDao.insert(actareunionProcesos);
                genericDao.insert(actareunionAsistentes);
                actaReunionBean.setId(idActareunion);
        	}else {
            	actaReunionBean.setError(new Error("Ya existe un acta de reunion, no se puede crear otro"));
        	}
        	
        } catch (Exception e){
        	e.printStackTrace();
        	actaReunionBean = new ActaReunionBean();
        	actaReunionBean.setError(new Error(e.getMessage()));
        }
        return actaReunionBean;
    }

    @Override
    public ActaReunionBean findById(int id) {
    	ActaReunionBean actaReunionBean = new ActaReunionBean();
        ActaReunionTransfer actareunionTransfer = new ActaReunionTransfer();
		try {
			ActareunionModel actareunionModel = (ActareunionModel) genericDao.findById(id,
					"ActareunionModel");
            actaReunionBean = actareunionTransfer.getActareunionBean(actareunionModel);
			
		} catch (Exception e) {
			e.printStackTrace();
			actaReunionBean = new ActaReunionBean();
			actaReunionBean.setError(new Error(e.getMessage()));
		}
		return actaReunionBean;
	}

    @Override
    public List<ActaReunionBean> getActasreunion() {
        List<ActareunionModel> actasreunionModel = actareunionDao.getActasreunion();
        ActaReunionTransfer actareunionTransfer = new ActaReunionTransfer();
        return actareunionTransfer.getActasreunionBean(actasreunionModel);
    }

    @Override
    @Transactional
    public int delete(int id) {
        List<SolicitudregistroModel> solicitudesregistro = solicitudregistroDao.getSolicitudregistroByActareunionId(id);
        if(solicitudesregistro != null && solicitudesregistro.size() > 0){
            for (SolicitudregistroModel s: solicitudesregistro) {
                solicitudregistroDao.delete(id);
            }
        }

        List<PlanauditoriaanualModel> plananualModel = plananualDao.getPlananualByActarenionId(id);
        if(plananualModel != null && plananualModel.size() > 0){
            for (PlanauditoriaanualModel p: plananualModel) {
                plananualDao.delete(p.getId());
            }
        }
        actareunionDao.delete(id);
        return 1;
    }
}
