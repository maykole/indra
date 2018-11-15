<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/reporte/
  //
  class Api extends ApiBase {
   //
   public function tipo() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    $sql = "select co_id as id,tx_descripcion as descripcion From ".self::TBL_REPORTE_TIPO." t1 group by co_id order by co_id";
    $this->showResponseQuery($sql);
   }
   //
   function informe($rep){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    switch($rep){ 
     case 1 : 
      $id   = (int) $this->dataRequest['prj']; 
      $sql= "SELECT t1.co_id as Codigo, no_nombre as Requerimiento, nu_prioridad as Prioridad,fn_get_status(t1.co_id) as Estado,@val:=ifnull((SELECT round(sum(po_avance)/count(po_avance),2) as Avance FROM ".self::TBL_REQUERIMIENTO_DETALLE." a inner join ".self::TBL_ACTIVIDAD." b on a.co_actividad=b.co_id WHERE co_requerimiento=t1.co_id),0) as Avance FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.co_proyecto=t2.co_id WHERE co_proyecto=$id order by Avance desc,nu_prioridad,codigo";
      break; 
     case 2:
      $id   = (int) $this->dataRequest['prj']; 
      $separator =", "; //"</br>";
      $sql= "SELECT t1.co_id as Codigo, no_nombre as Requerimiento, (select tx_descripcion from ".self::TBL_REQUERIMIENTO_TIPO." where co_id=co_requerimiento_tipo) as Tipo, 
       (select count(distinct co_actividad_tipo) from ".self::TBL_ACTIVIDAD." a inner join ".self::TBL_REQUERIMIENTO_DETALLE." b on a.co_id=b.co_actividad where b.co_requerimiento=t1.co_id )  as Nro_Actividades,
       ifnull((select group_concat(distinct (select tx_descripcion from ".self::TBL_ACTIVIDAD_TIPO." where co_id=co_actividad_tipo) separator '$separator') from ".self::TBL_ACTIVIDAD." a inner join ".self::TBL_REQUERIMIENTO_DETALLE." b on a.co_id=b.co_actividad where b.co_requerimiento=t1.co_id ),'')  as Actividades,
       (select count(distinct co_empleado) from ".self::TBL_ACTIVIDAD." a inner join ".self::TBL_REQUERIMIENTO_DETALLE." b on a.co_id=b.co_actividad where b.co_requerimiento=t1.co_id )  as Personal
      FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.co_proyecto=t2.co_id WHERE co_proyecto=$id order by personal desc,Nro_Actividades desc"; 
      break;  
     case 3 : 
      $condition = "";
      $rol = (int) $this->dataRequest['rol'];
      if($rol!=99){
       $condition = "WHERE co_rol=$rol";
      }
      //
      $cond_date = "";
      $d1  = $this->dataRequest['d1'];
      $d2  = $this->dataRequest['d2'];
      //
      if($d1!=$d2){
       $date = str_replace('/', '-', trim($d1));
       $date_start = date('Y-m-d', strtotime($date));
       //
       $date = str_replace('/', '-', trim($d2));
       $date_end = date('Y-m-d', strtotime($date));
       //  
       $cond_date = "and fe_inicio>='$date_start' and fe_fin<='$date_end'";
      }
      //
      $sql= "SELECT co_id as Codigo, concat(no_nombres,' ',no_apellidos) as Empleado,(SELECT tx_descripcion FROM ".self::TBL_ROL." WHERE co_id=t1.co_rol ) as Rol,  
       (SELECT count(co_actividad_tipo) FROM tbl_actividad WHERE co_empleado=t1.co_id $cond_date) as Nro_Actividades,
       (SELECT count(co_requerimiento) FROM tbl_requerimiento_detalle a inner join tbl_actividad b on a.co_actividad=b.co_id WHERE co_empleado=t1.co_id $cond_date) as Nro_requerimientos,
       (SELECT count(distinct co_proyecto) FROM tbl_requerimiento_detalle a inner join tbl_actividad b on a.co_actividad=b.co_id inner join tbl_requerimiento c on c.co_id=a.co_requerimiento WHERE co_empleado=t1.co_id $cond_date) as Nro_Proyectos,
       ifnull((SELECT group_concat(distinct co_complejidad order by co_complejidad) FROM tbl_requerimiento_detalle a inner join tbl_actividad b on a.co_actividad=b.co_id WHERE co_empleado=t1.co_id $cond_date),'S.I.') as Complejidad,
       avg((ifnull((SELECT sum(po_avance) FROM tbl_requerimiento_detalle a inner join tbl_actividad b on a.co_actividad=b.co_id WHERE co_empleado=t1.co_id $cond_date),0))) Promedio_avance
      FROM ".self::TBL_EMPLEADO." t1 $condition GROUP BY co_id";
      //echo $sql;
      break;
    }
    //
    $this->showResponseQuery($sql);
   }
   //
  } 
  // 
  $api = new Api();  
  $api->processRequest(); 
  //
?>   