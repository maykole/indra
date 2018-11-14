package pe.edu.upc.tp.auditoria.service;

import java.util.List;

import pe.edu.upc.tp.auditoria.bean.PlanAnualInit;
import pe.edu.upc.tp.auditoria.bean.PlanAnualBean;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;

public interface PlanAnualService {
	
	String nombreMenu();

	PlanAnualInit init();

	PlanauditoriaanualModel create(PlanAnualBean planAnualRQ);

	List<PlanauditoriaanualModel> list();

	int delete(int id);

}
