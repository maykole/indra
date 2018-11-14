package pe.edu.upc.tp.auditoria.service;

import pe.edu.upc.tp.auditoria.model.PlanactividadModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;

import java.util.List;

public interface PlanEspecificoService {

	List<PlanauditoriaModel> getPlanesaditoriaByAnio(String anio);
	int generarPlanEspecifico(String anio, int idProgamacion);
	PlanauditoriaModel findById(int id);
	int registrarIniciado(PlanauditoriaModel planAuditoria, int id);
	int registrarConclusion(PlanauditoriaModel planAuditoria, int id);
	int delete(int id);
	int auditar(PlanactividadModel planactividad, int id);

}
