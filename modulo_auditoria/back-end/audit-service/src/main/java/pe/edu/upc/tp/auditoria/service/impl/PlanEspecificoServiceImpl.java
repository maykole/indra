package pe.edu.upc.tp.auditoria.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.tp.auditoria.bean.ProgramacionBean;
import pe.edu.upc.tp.auditoria.constantes.OtrosConstantes;
import pe.edu.upc.tp.auditoria.dao.GenericDao;
import pe.edu.upc.tp.auditoria.dao.PlanAnualDao;
import pe.edu.upc.tp.auditoria.dao.PlanEspecificoDao;
import pe.edu.upc.tp.auditoria.dao.ProcedimientoDao;
import pe.edu.upc.tp.auditoria.dao.ProgramaDao;
import pe.edu.upc.tp.auditoria.model.ActividadModel;
import pe.edu.upc.tp.auditoria.model.PlanactividadModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaAuditorModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.model.PlanprocedimientoModel;
import pe.edu.upc.tp.auditoria.model.ProcedimientoModel;
import pe.edu.upc.tp.auditoria.model.ProgramaModel;
import pe.edu.upc.tp.auditoria.service.EmailService;
import pe.edu.upc.tp.auditoria.service.PlanEspecificoService;

@Service
public class PlanEspecificoServiceImpl implements PlanEspecificoService{

	@Autowired
	GenericDao genericDao;

	@Autowired
	PlanAnualDao planAuditoriaAnualDao;

	@Autowired
	ProgramaDao programaDao;

	@Autowired
	ProcedimientoDao procedimientoDao;

	@Autowired
	PlanEspecificoDao planAuditoriaEspecificaDao;

	@Autowired
	EmailService emailService;

    //@Scheduled(fixedRate = 100000)
	@Transactional
	public int generarPlanEspecifico(String anio, int idProgramacion) {
		int result = 1;
		try {
			System.out.println("generarPlanEspecifico");
			PlanauditoriaanualModel planAnual = planAuditoriaAnualDao
					.getPlanAuditoriaAnualByPeriodo(anio);
			if (planAnual != null) {
				Date currentDate = new Date();
//				List<ProgramaModel> programas = programaDao.getProgramasByPlanAnualAndFechas(planAnual.getId(), currentDate);
				List<ProgramaModel> programas = new ArrayList<>();
				ProgramaModel programacion = (ProgramaModel) genericDao.findById(idProgramacion, "ProgramaModel");
				if(programacion != null){
					programas.add(programacion);
				}
				
				if (programas != null && programas.size() > 0) {

					List<PlanauditoriaModel> planesauditorias = new ArrayList<>();
					List<PlanauditoriaAuditorModel> planauditoriaAuditores = new ArrayList<>();
					List<PlanprocedimientoModel> planprocedimientos = new ArrayList<>();
					List<PlanactividadModel> planactividades = new ArrayList<>();

					int idPlanauditoria = genericDao.ultimo("id", "PlanauditoriaModel") + 1;
					int idPlanauditoriaAuditor = genericDao.ultimo("id", "PlanauditoriaAuditorModel") + 1;
					int idPlanprocedimiento = genericDao.ultimo("planprocedimientoId", "PlanprocedimientoModel") + 1;
					int idPlanactividad = genericDao.ultimo("planactividadId", "PlanactividadModel") + 1;

					for (ProgramaModel programa : programas) {
//						if (programa.getEstado().equals(OtrosConstantes.PENDIENTE)) {
							//programa.setEstado(OtrosConstantes.PROCESO);
							PlanauditoriaModel planauditoria = new PlanauditoriaModel();
							planauditoria.setId(idPlanauditoria);
							planauditoria.setPlanauditoriaanualId(planAnual.getId());
							planauditoria.setProceso(programa.getProceso());
							planauditoria.setDescripcion(programa.getProceso().getDescripcion());
							planauditoria.setAlcance(programa.getProceso().getAlcance());
							planauditoria.setObjetivo(programa.getProceso().getObjetivo());
							planauditoria.setEstado(OtrosConstantes.PENDIENTE);
							planauditoria.setFechaCreacion(currentDate);
							planauditoria.setDuracion(programa.getDuracion());
							planauditoria.setFechaInicio(programa.getFechaInicio());
							planauditoria.setFechaFin(programa.getFechaFin());
							planesauditorias.add(planauditoria);

							PlanauditoriaAuditorModel planauditoriaAuditor = new PlanauditoriaAuditorModel();
							planauditoriaAuditor.setId(idPlanauditoriaAuditor);
							planauditoriaAuditor.setPlanauditoriaId(idPlanauditoria);
							planauditoriaAuditor.setEmpleado(programa.getAuditor());
							planauditoriaAuditor.setCargo(programa.getAuditor().getCargo());
							planauditoriaAuditor.setNivel(programa.getAuditor().getNivel());
							planauditoriaAuditores.add(planauditoriaAuditor);


							List<ProcedimientoModel> procedimientos = procedimientoDao.getProcedimientosByProceso(programa.getProceso().getProcesoId());
							for (ProcedimientoModel procedimiento : procedimientos) {
								PlanprocedimientoModel planprocedimiento = new PlanprocedimientoModel();
								planprocedimiento.setPlanprocedimientoId(idPlanprocedimiento);
								planprocedimiento.setDescripcion(procedimiento.getDescripcion());
								planprocedimiento.setPlanauditoriaId(idPlanauditoria);
								planprocedimientos.add(planprocedimiento);
								for(ActividadModel actividad: procedimiento.getActividades()){
									PlanactividadModel planactividad = new PlanactividadModel();
									planactividad.setPlanactividadId(idPlanactividad);
									planactividad.setDescripcion(actividad.getDescripcion());
									planactividad.setAuditado(false);
									planactividad.setAccion(false);
									planactividad.setPlanprocedimientoId(idPlanprocedimiento);
									planactividades.add(planactividad);
									idPlanactividad++;
								}
								idPlanprocedimiento++;
							}
							idPlanauditoria++;
							idPlanauditoriaAuditor++;
//						}
					}
					if (planesauditorias.size() > 0) {
						genericDao.insert(planesauditorias);
						genericDao.insert(planauditoriaAuditores);
						genericDao.insert(planprocedimientos);
						genericDao.insert(planactividades);
						result = 1;
						//genericDao.edit(programas);
						//enviarCorreo(programas, planAnual.getId());
					}
					//enviarCorreo(programas, planAnual.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PlanauditoriaModel findById(int id) {
		PlanauditoriaModel planauditoriaModel = (PlanauditoriaModel) genericDao.findById(id,
				"PlanauditoriaModel");
		if(planauditoriaModel.getEstado().equals(OtrosConstantes.INICIADO)){
			for(PlanprocedimientoModel proc: planauditoriaModel.getPlanprocedimientos()){
				for(PlanactividadModel act: proc.getPlanactividades()){
					if(act.isAuditar()){
						act.setEnlaceAuditar("Auditar");
					}
				}
			}
		}
		return planauditoriaModel;
	}

	@Override
	@Transactional
	public int registrarIniciado(PlanauditoriaModel planAuditoria, int id) {
		int result = 0;
		try{
			PlanauditoriaModel planAuditoriaIniciado = findById(id);
			planAuditoriaIniciado.setAlcance(planAuditoria.getAlcance());
			planAuditoriaIniciado.setObjetivo(planAuditoria.getObjetivo());
			planAuditoriaIniciado.setEstado(OtrosConstantes.INICIADO);
			List<PlanactividadModel> planesActividad = new ArrayList<>();
			for(PlanprocedimientoModel proc:  planAuditoria.getPlanprocedimientos()){
				for(PlanactividadModel act: proc.getPlanactividades()){
					planesActividad.add(act);
				}
			}
			genericDao.edit(planesActividad);
			genericDao.edit(planAuditoriaIniciado);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	@Transactional
	public int registrarConclusion(PlanauditoriaModel planAuditoria, int id) {
		int result = 0;
		try{
			PlanauditoriaModel planAuditoriaConcluido = findById(id);
			planAuditoriaConcluido.setObservacion(planAuditoria.getObservacion());
			planAuditoriaConcluido.setResultado(planAuditoria.isResultado());
			planAuditoriaConcluido.setEstado(OtrosConstantes.CONCLUIDO);
			genericDao.edit(planAuditoriaConcluido);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional
	public int delete(int id) {
		PlanauditoriaModel planauditoriaModel = findById(id);

		if (planauditoriaModel != null) {
			try {
				for (PlanprocedimientoModel pp : planauditoriaModel.getPlanprocedimientos()) {
					for (PlanactividadModel pa : pp.getPlanactividades()) {
						genericDao.remove(pa);
					}
					genericDao.remove(pp);
				}
				for (PlanauditoriaAuditorModel auditor : planauditoriaModel.getPlanauditoriaAuditores()) {
					genericDao.remove(auditor);
				}
				genericDao.remove(planauditoriaModel);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 1;
	}

	@Override
	public List<PlanauditoriaModel> getPlanesaditoriaByAnio(String anio) {
		List<PlanauditoriaModel> planesEspecificos = new ArrayList<>();
		PlanauditoriaanualModel planauditoriaanualModel = planAuditoriaAnualDao.getPlanAuditoriaAnualByPeriodo(anio);
		if(planauditoriaanualModel != null){
			planesEspecificos = planAuditoriaEspecificaDao.getPlanesEspecificosByPlanAnual(planauditoriaanualModel.getId());
		}
		return planesEspecificos;
	}

	/*private List<CronogramaModel> getCronogramas(List<PlanauditoriaModel> planesauditorias) {
		Date lastDate = null;
		List<CronogramaModel> cronogramas = new ArrayList<>();
		try {
			int id = genericDao.ultimo("cronogramaId", "CronogramaModel") + 1;
			for (PlanauditoriaModel p : planesauditorias) {
				int cantPlan = (int) (p.getDuracion() * 0.3);
				for (int i = 0; i < cantPlan; i += 2) {
					CronogramaModel crono = new CronogramaModel();
					crono.setCronogramaId(id);
					crono.setPlanauditoriaId(p.getId());
					crono.setEtapa(OtrosConstantes.PLANIFICACION);
					if (i == 0) {
						lastDate = p.getFechaInicio();
					} else {
						lastDate = getSiguienteDiaUtil(lastDate);
					}
					crono.setFecha(lastDate);
					cronogramas.add(crono);
					id++;
				}
				int cantEjec = (int) (p.getDuracion() * 0.5);
				for (int i = 0; i < cantEjec; i += 2) {
					CronogramaModel crono = new CronogramaModel();
					crono.setCronogramaId(id);
					crono.setPlanauditoriaId(p.getId());
					crono.setEtapa(OtrosConstantes.EJECUCION);
					lastDate = getSiguienteDiaUtil(lastDate);
					crono.setFecha(lastDate);
					cronogramas.add(crono);
					id++;
				}
				int cantInfo = (int) (p.getDuracion() * 0.2);
				for (int i = 0; i < cantInfo; i += 2) {
					CronogramaModel crono = new CronogramaModel();
					crono.setCronogramaId(id);
					crono.setPlanauditoriaId(p.getId());
					crono.setEtapa(OtrosConstantes.INFORME);
					lastDate = getSiguienteDiaUtil(lastDate);
					crono.setFecha(lastDate);
					cronogramas.add(crono);
					id++;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cronogramas;
	}*/

	private Date getSiguienteDiaUtil(Date lastDate) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(lastDate);
		if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			currentCalendar.add(Calendar.DATE, 4);
		} else if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			currentCalendar.add(Calendar.DATE, 4);
		} else {
			currentCalendar.add(Calendar.DATE, 2);
		}
		return currentCalendar.getTime();
	}

	@Override
	@Transactional
	public int auditar(PlanactividadModel planactividad, int id) {
		PlanactividadModel planactividadModel = (PlanactividadModel) genericDao.findById(id, "PlanactividadModel");
		planactividadModel.setAuditado(planactividad.isAuditado());
		planactividadModel.setObservacion(planactividad.getObservacion());
		try {
			genericDao.edit(planactividadModel);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*public void enviarCorreo(List<ProgramaModel> programas, int planAnualId) {
		int cont = 1;
		for (ProgramaModel p : programas) {
			System.out.println("init enviando correo : " + cont);
			PlanauditoriaModel planauditoria = planAuditoriaEspecificaDao
					.getPlanAuditoriaByPlanAnualIdAndProgramaId(planAnualId, p.getProceso().getProcesoId());
			System.out.println(UtilTransfer.objectToJson(planauditoria));
			
			int index = 1;
			for(CronogramaModel c: planauditoria.getCronogramas()){
				c.setIndex(index);
				c.setIndexString("Reunión " + index);
				if(c.getEtapa() == OtrosConstantes.PLANIFICACION){
					c.setEtapaString("Planificación");
				}else if(c.getEtapa() == OtrosConstantes.EJECUCION){
					c.setEtapaString("Ejecución");
				}else{
					c.setEtapaString("Informe");
				}
				index++;
			}
			System.out.println("destinatario : " + planauditoria.getPlanauditoriaAuditores().get(0).getEmpleado().getCorreo());
			try {
				Mail mail = new Mail();
				mail.setFrom("auditoriadmn@gmail.com");
				mail.setTo(planauditoria.getPlanauditoriaAuditores().get(0).getEmpleado().getCorreo());
				mail.setSubject("Cronograma de reuniones de plan de auditoría");
	
				Map<String, Object> model = new HashMap<>();
				model.put("planauditoria", planauditoria);
				mail.setModel(model);

			
				emailService.sendSimpleMessage(mail);
				System.out.println("end enviando correo : " + cont);
				cont++;
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}*/

}
