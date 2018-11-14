package pe.edu.upc.tp.auditoria.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.Error;
import pe.edu.upc.tp.auditoria.bean.PlanAnualBean;
import pe.edu.upc.tp.auditoria.bean.PlanAnualInit;
import pe.edu.upc.tp.auditoria.constantes.OtrosConstantes;
import pe.edu.upc.tp.auditoria.dao.ActaReunionDao;
import pe.edu.upc.tp.auditoria.dao.EmpleadoDao;
import pe.edu.upc.tp.auditoria.dao.GenericDao;
import pe.edu.upc.tp.auditoria.dao.PlanAnualDao;
import pe.edu.upc.tp.auditoria.dao.PlanEspecificoDao;
import pe.edu.upc.tp.auditoria.dao.ProcedimientoDao;
import pe.edu.upc.tp.auditoria.dao.ProcesoDao;
import pe.edu.upc.tp.auditoria.dao.SolicitudRegistroDao;
import pe.edu.upc.tp.auditoria.model.ActareunionModel;
import pe.edu.upc.tp.auditoria.model.ActareunionProcesoModel;
import pe.edu.upc.tp.auditoria.model.EmpleadoModel;
import pe.edu.upc.tp.auditoria.model.FechasprocesoplananualModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.model.ProcesoModel;
import pe.edu.upc.tp.auditoria.model.ProgramaModel;
import pe.edu.upc.tp.auditoria.model.SolicitudregistroModel;
import pe.edu.upc.tp.auditoria.service.PlanAnualService;
import pe.edu.upc.tp.auditoria.transfer.ProcesoTransfer;
import pe.edu.upc.tp.auditoria.util.UtilTransfer;

@Service
public class PlanAnualServiceImpl implements PlanAnualService {

	@Autowired
	EmpleadoDao empleadoDao;

	@Autowired
	ProcedimientoDao procedimientoDao;

	@Autowired
	ProcesoDao procesoDao;

	@Autowired
	GenericDao genericDao;

	@Autowired
	PlanAnualDao planAuditoriaAnualDao;

	@Autowired
	PlanEspecificoDao planAuditoriaEspecificaDao;
	
	@Autowired
	SolicitudRegistroDao solicitudregistroDao;
	
	@Autowired
	ActaReunionDao actareunionDao;

	@Override
	public String nombreMenu() {
		FechasprocesoplananualModel fechasprocesoplananual = genericDao.getFechasprocesoplananual();
		String nombreMenu = "0";
		Date currentDate = new Date();
		if (fechasprocesoplananual.getInicio().getTime() < currentDate.getTime()
				&& currentDate.getTime() < fechasprocesoplananual.getFin().getTime()) {
			int cant = planAuditoriaAnualDao.getCantidadPlanAnualByPeriodo(UtilTransfer.getAñoSiguiente());
			// nombreMenu = "Generar Plan Anual de Auditoría";
			nombreMenu = "1";
			if (cant > 0) {
				// nombreMenu = "Volver a Generar Plan Anual de Auditoría";
				nombreMenu = "2";
			}
		}
		return nombreMenu;
	}

	@Override
	public PlanAnualInit init() {
		PlanAnualInit initPlanAnual = new PlanAnualInit();
		ActareunionModel actareunionModel = actareunionDao.getActareunionByPeriodo(UtilTransfer.getAñoSiguiente());
		if(actareunionModel != null){
			SolicitudregistroModel solicitudPendiente = solicitudregistroDao.getSolicitudregistroPendiente(UtilTransfer.getAñoSiguiente());
			if(solicitudPendiente == null){
				ProcesoTransfer procesoTransfer = new ProcesoTransfer();
				initPlanAnual.setPeriodoCompleto("Enero " + UtilTransfer.getAñoSiguiente() + " - Diciembre " + UtilTransfer.getAñoSiguiente());
				initPlanAnual.setPeriodo(UtilTransfer.getAñoSiguiente());
				initPlanAnual.setCreadoPor("Jordan Abe");
				initPlanAnual.setFechaCreacion(UtilTransfer.getFechaActual());
				
				List<ProcesoModel> procesosModel = new ArrayList<>();
				for(ActareunionProcesoModel a: actareunionModel.getActareunionProcesos()){
					procesosModel.add(a.getProceso());
				}
				
				List<SolicitudregistroModel> solicitudesEvaluadas = solicitudregistroDao.getSolicitudregistroEvaluadas(UtilTransfer.getAñoSiguiente());
				for(SolicitudregistroModel s: solicitudesEvaluadas){
					procesosModel.add(s.getProceso());
				}
				
				initPlanAnual.setProcesos(procesoTransfer.getProcesosBean(procesosModel));
				
				ActaReunionBean actareunionBean = new ActaReunionBean();
				actareunionBean.setId(actareunionModel.getId());
				actareunionBean.setPeriodo(actareunionModel.getPeriodo());
				initPlanAnual.setActareunion(actareunionBean);
			}else{
				initPlanAnual.setError(new Error("Tiene una solicitud pendiente"));
			}
		}else {
			initPlanAnual.setError(new Error("No existe un acta de reunión, no puede registrar un plan anual"));
		}
		return initPlanAnual;
	}

	@Override
	@Transactional
	public PlanauditoriaanualModel create(PlanAnualBean planAnualRQ) {
		System.out.println("grabarPlanAnual : " + planAnualRQ.getPeriodo());
		PlanauditoriaanualModel planauditoriaanualRS = new PlanauditoriaanualModel();
		try {
			
			List<EmpleadoModel> empleados = empleadoDao.getAuditores();

			int id = genericDao.ultimo("id", "PlanauditoriaanualModel") + 1;
			PlanauditoriaanualModel planauditoriaanual = new PlanauditoriaanualModel();
			planauditoriaanual.setId(id);
			planauditoriaanual.setCreadoPor("Jordan Abe");
			planauditoriaanual.setFechaCreacion(new Date());
			planauditoriaanual.setPeriodo(planAnualRQ.getPeriodo());
			planauditoriaanual.setActareunionId(planAnualRQ.getActareunion().getId());

			List<ProgramaModel> programas = new ArrayList<>();
			Integer idPrograma = genericDao.ultimo("id", "ProgramaModel") + 1;

			planAnualRQ.setProcesos(getProcesosOrdenadosPorPrioridad(planAnualRQ.getProcesos()));
			for (pe.edu.upc.tp.auditoria.bean.ProcesoBean proceso : planAnualRQ.getProcesos()) {
				asignarPrograma(id, idPrograma, proceso, programas, empleados,
						planAnualRQ.getPeriodo());
				idPrograma++;
			}
			PlanauditoriaanualModel planauditoriaanualModel = planAuditoriaAnualDao.getPlanAuditoriaAnualByPeriodo(planAnualRQ.getPeriodo());
			if(planauditoriaanualModel != null){
				delete(planauditoriaanualModel.getId());
			}
			genericDao.insert(planauditoriaanual);
			genericDao.insert(programas);

			planauditoriaanualRS = planAuditoriaAnualDao.getPlanAuditoriaAnualById(id);
			planauditoriaanualRS.setPeriodoDescripcion(
					"Enero " + planauditoriaanualRS.getPeriodo() + " - Diciembre " + planauditoriaanualRS.getPeriodo());
			planauditoriaanualRS
					.setFechaCreacionDescripcion(UtilTransfer.getFechaString(planauditoriaanualRS.getFechaCreacion()));
		} catch (Exception e) {
			e.printStackTrace();
			planauditoriaanualRS = new PlanauditoriaanualModel();
			planauditoriaanualRS.setError(new Error(e.getMessage()));
		}
		System.out.println(UtilTransfer.objectToJson(planauditoriaanualRS));
		return planauditoriaanualRS;

	}

	private Date getFechaLimite(Date fechaInicio, String tipo) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(fechaInicio);
		if (tipo.equals("limiteInicio")) {
			currentCalendar.add(Calendar.DATE, -10);
		} else {
			currentCalendar.add(Calendar.DATE, -1);
		}
		return currentCalendar.getTime();
	}

	private List<pe.edu.upc.tp.auditoria.bean.ProcesoBean> getProcesosOrdenadosPorPrioridad(
			List<pe.edu.upc.tp.auditoria.bean.ProcesoBean> procesos) {
		List<pe.edu.upc.tp.auditoria.bean.ProcesoBean> procesosOrdenados = new ArrayList<>();
		for (pe.edu.upc.tp.auditoria.bean.ProcesoBean proceso : procesos) {
			if (proceso.getPrioridad().equals(OtrosConstantes.ALTA)) {
				procesosOrdenados.add(proceso);
			}
		}
		for (pe.edu.upc.tp.auditoria.bean.ProcesoBean proceso : procesos) {
			if (proceso.getPrioridad().equals(OtrosConstantes.MEDIA)) {
				procesosOrdenados.add(proceso);
			}
		}
		for (pe.edu.upc.tp.auditoria.bean.ProcesoBean proceso : procesos) {
			if (proceso.getPrioridad().equals(OtrosConstantes.BAJA)) {
				procesosOrdenados.add(proceso);
			}
		}
		return procesosOrdenados;
	}

	private void asignarPrograma(int id, Integer idPrograma, pe.edu.upc.tp.auditoria.bean.ProcesoBean proceso,
			List<ProgramaModel> programas, List<EmpleadoModel> empleados,
			String periodo) {

		ProgramaModel programa = new ProgramaModel();
		programa.setId(idPrograma);
		programa.setPlanauditoriaanualId(id);
		programa.setProceso(new ProcesoModel(proceso.getProcesoId()));
		programa.setPrioridad(proceso.getPrioridad());
		programa.setDuracion(proceso.getDuracion());
		programa.setEstado(OtrosConstantes.PENDIENTE);
		programa.setAuditor(getAuditorDisponible(empleados, programa,periodo));
		programa.setFechaInicioLimite(getFechaLimite(programa.getFechaInicio(), "limiteInicio"));
		programa.setFechaFinLimite(getFechaLimite(programa.getFechaInicio(), "limiteFin"));
		programas.add(programa);
	}

	private EmpleadoModel getAuditorDisponible(List<EmpleadoModel> empleados, ProgramaModel programa, String periodo) {
		EmpleadoModel empleado = null;
		for (EmpleadoModel e : empleados) {
			if (e.getFechaInicioAuditoria() == null) {
				if (!e.getNivel().equals(OtrosConstantes.PRACTICANTE)) {
					e.setFechaInicioAuditoria(getPrimeraFechaHabilDelAño(periodo));
					e.setFechaFinAuditoria(getUltimaFechaHabil(e.getFechaInicioAuditoria(), programa.getDuracion()));

					e.setFechaInicioAuditoriaString(UtilTransfer.getFechaString(e.getFechaInicioAuditoria()));
					e.setFechaFinAuditoriaString(UtilTransfer.getFechaString(e.getFechaFinAuditoria()));

					programa.setFechaInicio(getPrimeraFechaHabilDelAño(periodo));
					programa.setFechaFin(getUltimaFechaHabil(e.getFechaInicioAuditoria(), programa.getDuracion()));
					empleado = e;
					return empleado;
				}
			}
		}
		if (empleado == null) {
			EmpleadoModel e = getAuditorProximoEnLiberarse(empleados);
			if (!e.getNivel().equals(OtrosConstantes.PRACTICANTE)) {
				e.setFechaFinAuditoria(getUltimaFechaHabil(e.getFechaInicioAuditoria(), programa.getDuracion()));

				e.setFechaInicioAuditoriaString(UtilTransfer.getFechaString(e.getFechaInicioAuditoria()));
				e.setFechaFinAuditoriaString(UtilTransfer.getFechaString(e.getFechaFinAuditoria()));

				programa.setFechaInicio(e.getFechaInicioAuditoria());
				programa.setFechaFin(getUltimaFechaHabil(e.getFechaInicioAuditoria(), programa.getDuracion()));
				empleado = e;
				return empleado;
			}
		}
		return empleado;
	}

	private Date getUltimaFechaHabil(Date fechaInicial, int duracion) {

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(fechaInicial);

		int diasHabilesAcumulados = 0;

		while (diasHabilesAcumulados < duracion) {

			if (currentCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					&& currentCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				diasHabilesAcumulados++;
			}
			currentCalendar.add(Calendar.DATE, 1);
		}
		return currentCalendar.getTime();
	}

	private Date getPrimeraFechaHabilDelAño(String periodo) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String primerDiaDelAño = "02-01-" + periodo;
		Date primerDiaHabilDelAño = null;
		try {
			primerDiaHabilDelAño = sdf.parse(primerDiaDelAño);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(primerDiaHabilDelAño);

		if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			currentCalendar.add(Calendar.DATE, 2);
		} else if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			currentCalendar.add(Calendar.DATE, 1);
		}
		return currentCalendar.getTime();
	}

	private EmpleadoModel getAuditorProximoEnLiberarse(List<EmpleadoModel> empleados) {
		EmpleadoModel auditorProximo = null;
		for (EmpleadoModel e : empleados) {
			if (!e.getNivel().equals(OtrosConstantes.PRACTICANTE)) {
				if (auditorProximo == null) {
					auditorProximo = e;
				}
				if (e.getFechaFinAuditoria().getTime() < auditorProximo.getFechaFinAuditoria().getTime()) {
					auditorProximo = e;
				}
			}
		}
		for (EmpleadoModel e : empleados) {
			if (e.getEmpleadoId() == auditorProximo.getEmpleadoId()) {
				e.setFechaInicioAuditoria(getFechaInicioHabil(e.getFechaFinAuditoria()));
				auditorProximo = e;
			}
		}
		return auditorProximo;
	}

	private Date getFechaInicioHabil(Date fechaFin) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(fechaFin);
		currentCalendar.add(Calendar.DATE, 1);
		if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			currentCalendar.add(Calendar.DATE, 2);
		} else if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			currentCalendar.add(Calendar.DATE, 1);
		}
		return currentCalendar.getTime();
	}

	@Override
	public List<PlanauditoriaanualModel> list() {
		List<PlanauditoriaanualModel> list = planAuditoriaAnualDao.getPlanesAnuales();
		for (PlanauditoriaanualModel plan : list) {
			plan.setPeriodoDescripcion("Enero " + plan.getPeriodo() + " - Diciembre " + plan.getPeriodo());
			plan.setFechaCreacionDescripcion(UtilTransfer.getFechaString(plan.getFechaCreacion()));
		}
		return planAuditoriaAnualDao.getPlanesAnuales();
	}

	@Override
	public int delete(int id) {
        try {
        	PlanEspecificoServiceImpl planEspecificoServiceImpl = new PlanEspecificoServiceImpl();
            PlanauditoriaanualModel planauditoriaanual = planAuditoriaAnualDao.getPlanAuditoriaAnualById(id);
            if(planauditoriaanual != null){
                List<PlanauditoriaModel> planesauditorias = planAuditoriaEspecificaDao
                        .getPlanesEspecificosByPlanAnual(id);
                for (PlanauditoriaModel plan : planesauditorias) {
					planEspecificoServiceImpl.delete(plan.getId());
                }
                planAuditoriaAnualDao.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
		return 1;
	}
}
