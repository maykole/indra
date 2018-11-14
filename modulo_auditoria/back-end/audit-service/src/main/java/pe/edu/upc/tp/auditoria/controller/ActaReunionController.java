package pe.edu.upc.tp.auditoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.ActaReunionInit;
import pe.edu.upc.tp.auditoria.service.ActaReunionService;

@RestController
public class ActaReunionController {

    @Autowired
    ActaReunionService actaReunionService;

    @GetMapping("/actareunion/init")
	public ResponseEntity<ActaReunionInit> init() {
    	ActaReunionInit actaReunionInit = actaReunionService.init();
		if (actaReunionInit.getError() != null) {
			return new ResponseEntity<ActaReunionInit>(actaReunionInit, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ActaReunionInit>(actaReunionInit, HttpStatus.OK);
	}

    @PostMapping("/actareunion")
    public ResponseEntity<ActaReunionBean> create(@RequestBody ActaReunionBean actaReunion){

    	ActaReunionBean actaReunionBean = actaReunionService.create(actaReunion);
		if (actaReunionBean.getError() != null) {
			return new ResponseEntity<ActaReunionBean>(actaReunionBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ActaReunionBean>(actaReunionBean, HttpStatus.OK);
    }
    
    @GetMapping("/actareunion/{id}")
	public ResponseEntity<ActaReunionBean> findById(@PathVariable int id) {
    	ActaReunionBean actaReunionBean = actaReunionService.findById(id);
		if (actaReunionBean.getError() != null) {
			return new ResponseEntity<ActaReunionBean>(actaReunionBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ActaReunionBean>(actaReunionBean, HttpStatus.OK);
	}

	@GetMapping("/actareunion/")
	public ResponseEntity<List<ActaReunionBean>> list() {
		List<ActaReunionBean> actasReunionBean = actaReunionService.getActasreunion();
		return new ResponseEntity<List<ActaReunionBean>>(actasReunionBean, HttpStatus.OK);
	}

	@DeleteMapping("/actareunion/{id}")
	public int delete(@PathVariable int id){
		return actaReunionService.delete(id);
	}

}
