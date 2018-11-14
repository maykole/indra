package pe.edu.upc.tp.auditoria.transfer;

import pe.edu.upc.tp.auditoria.bean.EmpleadoBean;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoTransfer {

    public List<EmpleadoBean> getEmpleadosBean(List<pe.edu.upc.tp.auditoria.model.EmpleadoModel> empleadosModel){
        List<EmpleadoBean> empleados = new ArrayList<>();
        for(pe.edu.upc.tp.auditoria.model.EmpleadoModel model: empleadosModel){
            empleados.add(getEmpleadoBean(model));
        }
        return empleados;
    }

    public EmpleadoBean getEmpleadoBean(pe.edu.upc.tp.auditoria.model.EmpleadoModel empleadoModel){
        EmpleadoBean empleado = new EmpleadoBean();
        empleado.setEmpleadoId(empleadoModel.getEmpleadoId());
        empleado.setNombres(empleadoModel.getNombres() + empleadoModel.getApellidos());
        empleado.setCargo(empleadoModel.getCargo());
        return empleado;
    }

}
