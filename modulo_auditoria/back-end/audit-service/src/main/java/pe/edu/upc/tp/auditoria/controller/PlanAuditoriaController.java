package pe.edu.upc.tp.auditoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tp.auditoria.bean.PlanAnualBean;
import pe.edu.upc.tp.auditoria.bean.PlanAnualInit;
import pe.edu.upc.tp.auditoria.bean.SolicitudRegistroBean;
import pe.edu.upc.tp.auditoria.model.PlanactividadModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.service.PlanAnualService;
import pe.edu.upc.tp.auditoria.service.PlanEspecificoService;

import java.util.List;

@RestController
public class PlanAuditoriaController {

    @Autowired
    PlanEspecificoService planEspecificoService;

    @GetMapping("/plananual/{anio}/planAuditoria")
    public ResponseEntity<List<PlanauditoriaModel>> list(@PathVariable String anio) {
        List<PlanauditoriaModel> planesauditoria = planEspecificoService.getPlanesaditoriaByAnio(anio);
        return new ResponseEntity<List<PlanauditoriaModel>>(planesauditoria, HttpStatus.OK);
    }

    @GetMapping("/planAuditoria/{anio}/pendiente/programacion/{idProgamacion}")
    public int registrarPendiente(@PathVariable String anio, @PathVariable int idProgamacion){
        return planEspecificoService.generarPlanEspecifico(anio, idProgamacion);
    }

    @GetMapping("/planAuditoria/{id}")
    public PlanauditoriaModel findById(@PathVariable int id){
        return planEspecificoService.findById(id);
    }

    @PutMapping("/planAuditoria/{id}/iniciar")
    public int registrarIniciado(@PathVariable int id, @RequestBody PlanauditoriaModel planAuditoria){
        int result = planEspecificoService.registrarIniciado(planAuditoria, id);
        return result;
    }
    
    @PutMapping("/planAuditoria/{idPlan}/planActividad/{id}")
    public int auditar(@PathVariable int idPlan, @PathVariable int id, @RequestBody PlanactividadModel planactividadModel){
        int result = planEspecificoService.auditar(planactividadModel, id);
        return result;
    }
    
    @PutMapping("/planAuditoria/{id}/concluir")
    public int registrarConclusion(@PathVariable int id, @RequestBody PlanauditoriaModel planAuditoria){
        int result = planEspecificoService.registrarConclusion(planAuditoria, id);
        return result;
    }

    @DeleteMapping("/planAuditoria/{id}")
    public int delete(@PathVariable int id){
        return planEspecificoService.delete(id);
    }

}

