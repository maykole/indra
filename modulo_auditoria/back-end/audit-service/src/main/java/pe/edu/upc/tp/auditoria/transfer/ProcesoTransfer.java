package pe.edu.upc.tp.auditoria.transfer;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.tp.auditoria.bean.ProcesoBean;

public class ProcesoTransfer {
	
	public List<ProcesoBean> getProcesosBean(List<pe.edu.upc.tp.auditoria.model.ProcesoModel> registroprocesos){
		List<ProcesoBean> procesos = new ArrayList<>();
		for(pe.edu.upc.tp.auditoria.model.ProcesoModel reg: registroprocesos){
			procesos.add(getProcesoBean(reg));
		}
		return procesos;
	}
	
	public ProcesoBean getProcesoBean(pe.edu.upc.tp.auditoria.model.ProcesoModel registroproceso){
		ProcesoBean proceso = new ProcesoBean();
		proceso.setProcesoId(registroproceso.getProcesoId());
		proceso.setNombred(registroproceso.getDescripcion());
		return proceso;
	}

	public List<pe.edu.upc.tp.auditoria.model.ProcesoModel> getProcesosModel(List<ProcesoBean> procesos) {
		List<pe.edu.upc.tp.auditoria.model.ProcesoModel> procesosModel = new ArrayList<>();
		for(ProcesoBean reg: procesos){
			procesosModel.add(getProcesoModel(reg));
		}
		return procesosModel;
	}
	
	public pe.edu.upc.tp.auditoria.model.ProcesoModel getProcesoModel(ProcesoBean proceso){
		pe.edu.upc.tp.auditoria.model.ProcesoModel procesoModel = new pe.edu.upc.tp.auditoria.model.ProcesoModel();
		proceso.setProcesoId(proceso.getProcesoId());
		proceso.setNombred(proceso.getNombre());
		return procesoModel;
	}

}
