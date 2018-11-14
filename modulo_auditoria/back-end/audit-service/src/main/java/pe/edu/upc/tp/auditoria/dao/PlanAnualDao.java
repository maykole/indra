package pe.edu.upc.tp.auditoria.dao;

import java.util.List;

import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;

public interface PlanAnualDao {

	PlanauditoriaanualModel getPlanAuditoriaAnualById(int id);

	List<PlanauditoriaanualModel> getPlananualByActarenionId(int id);
	
	PlanauditoriaanualModel getPlanAuditoriaAnualByPeriodo(String periodo);

	int getCantidadPlanAnualByPeriodo(String periodo);

	List<PlanauditoriaanualModel> getPlanesAnuales();

	int delete(int id);

}
