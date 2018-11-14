package pe.edu.upc.tp.auditoria.dao;

import java.util.List;

import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;

public interface PlanEspecificoDao {

	List<PlanauditoriaModel> getPlanesEspecificosByPlanAnual(int id);
	int delete(int id);
	PlanauditoriaModel getPlanAuditoriaByPlanAnualIdAndProgramaId(int planAnualId, int procesoId);
	//int eliminarActividadesByProcedimiento(int procedimientoId);

}
