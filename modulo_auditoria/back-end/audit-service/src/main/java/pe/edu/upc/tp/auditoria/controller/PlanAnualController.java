package pe.edu.upc.tp.auditoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.tp.auditoria.bean.PlanAnualInit;
import pe.edu.upc.tp.auditoria.bean.PlanAnualBean;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.service.PlanAnualService;

@RestController
public class PlanAnualController {
	
	@Autowired
	PlanAnualService planAnualService;
	
	@GetMapping("/initMenu")
	public String initMenu(){
		return planAnualService.nombreMenu();
	}
	
	@GetMapping("/planesAnuales/init")
	public PlanAnualInit init(){
		return planAnualService.init();
	}
	
	@PostMapping("/planesAnuales")
	public PlanauditoriaanualModel create(@RequestBody PlanAnualBean planAnualRQ){
		PlanauditoriaanualModel planauditoriaanualModel = planAnualService.create(planAnualRQ);
		return planauditoriaanualModel;
	}
	
	@GetMapping("/planesAnuales")
	public List<PlanauditoriaanualModel> list(){
		return planAnualService.list();
	}

	@DeleteMapping("/planesAnuales/{id}")
	public int delete(@PathVariable int id){
		return planAnualService.delete(id);
	}
	

}
