package pe.edu.upc.tp.auditoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.SolicitudRegistroBean;
import pe.edu.upc.tp.auditoria.bean.SolicitudRegistroInit;
import pe.edu.upc.tp.auditoria.service.SolicitudRegistroService;

import java.util.List;

@RestController
public class SolicitudRegistroController {

	@Autowired
	SolicitudRegistroService solicitudregistroService;

	@GetMapping("/solicitudregistro/init")
	public ResponseEntity<SolicitudRegistroInit> init() {
		SolicitudRegistroInit solicitudregistroInit = solicitudregistroService.init();
		if (solicitudregistroInit.getError() != null) {
			return new ResponseEntity<SolicitudRegistroInit>(solicitudregistroInit, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SolicitudRegistroInit>(solicitudregistroInit, HttpStatus.OK);
	}

	@PostMapping("/solicitudregistro")
	public ResponseEntity<SolicitudRegistroBean> create(@RequestBody SolicitudRegistroBean solicitudregistro) {
		SolicitudRegistroBean solicitudregistroBean = solicitudregistroService.create(solicitudregistro);
		if (solicitudregistroBean.getError() != null) {
			return new ResponseEntity<SolicitudRegistroBean>(solicitudregistroBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SolicitudRegistroBean>(solicitudregistroBean, HttpStatus.OK);
	}
	
	@PutMapping("/solicitudregistro/evaluar/{id}")
	public ResponseEntity<SolicitudRegistroBean> evaluar(@PathVariable int id, @RequestBody SolicitudRegistroBean solicitudregistro) {
		SolicitudRegistroBean solicitudregistroBean = solicitudregistroService.evaluar(id, solicitudregistro);
		if (solicitudregistroBean.getError() != null) {
			return new ResponseEntity<SolicitudRegistroBean>(solicitudregistroBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SolicitudRegistroBean>(solicitudregistroBean, HttpStatus.OK);
	}
	
	@GetMapping("/solicitudregistro/{id}")
	public ResponseEntity<SolicitudRegistroBean> findById(@PathVariable int id) {
		SolicitudRegistroBean solicitudregistroBean = solicitudregistroService.findById(id);
		if (solicitudregistroBean.getError() != null) {
			return new ResponseEntity<SolicitudRegistroBean>(solicitudregistroBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SolicitudRegistroBean>(solicitudregistroBean, HttpStatus.OK);
	}

	@GetMapping("/solicitudregistro/")
	public ResponseEntity<List<SolicitudRegistroBean>> list() {
		List<SolicitudRegistroBean> solicitudesregistroBean = solicitudregistroService.getSolicitudesregistros();
		return new ResponseEntity<List<SolicitudRegistroBean>>(solicitudesregistroBean, HttpStatus.OK);
	}

	@DeleteMapping("/solicitudregistro/{id}")
	public int delete(@PathVariable int id){
		return solicitudregistroService.delete(id);
	}

}
