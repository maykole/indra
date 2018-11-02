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
    $sql = "select id,descripcion From ".self::TBL_REPORTE_TIPO." t1 group by id order by id";
    $this->showResponseQuery($sql);
   }
   //
   function informe($rep,$id){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    //$id = (int) $this->dataRequest['tp']; 
    //$rep  = (int) $this->dataRequest['trp']; 
    switch($rep){ 
     case 1 : 
      $sql= "SELECT t1.id as Codigo, nombre as Requerimiento, Prioridad,@val:=ifnull((SELECT round(sum(avance)/count(avance),2) as Avance FROM ".self::TBL_REQUERIMIENTO_DETALLE." a inner join ".self::TBL_ACTIVIDAD." b on a.id_actividad=b.id WHERE id_requerimiento=t1.id),0) as Avance FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.id_proyecto=t2.id WHERE id_proyecto=$id order by avance desc,prioridad,codigo";
      break; 
     case 2 : 
      $sql= "SELECT t1.id as Codigo, nombre as Requerimiento, fn_get_status(t1.id) as Estado FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.id_proyecto=t2.id WHERE id_proyecto=$id order by Estado desc,codigo";
      break;
     case 3:
      $separator =", "; //"</br>";
      $sql= "SELECT t1.id as Codigo, nombre as Descripción, (select descripcion from ".self::TBL_REQUERIMIENTO_TIPO." where id=id_requerimiento_tipo) as Tipo, 
       (select count(distinct id_actividad_tipo) from ".self::TBL_ACTIVIDAD." a inner join ".self::TBL_REQUERIMIENTO_DETALLE." b on a.id=b.id_actividad where b.id_requerimiento=t1.id )  as Nro_Actividades,
       ifnull((select group_concat(distinct (select descripcion from ".self::TBL_ACTIVIDAD."_tipo where id=id_actividad_tipo) separator '$separator') from ".self::TBL_ACTIVIDAD." a inner join ".self::TBL_REQUERIMIENTO_DETALLE." b on a.id=b.id_actividad where b.id_requerimiento=t1.id ),'')  as Actividades,
       (select count(distinct id_empleado) from ".self::TBL_ACTIVIDAD." a inner join ".self::TBL_REQUERIMIENTO_DETALLE." b on a.id=b.id_actividad where b.id_requerimiento=t1.id )  as Personal
      FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.id_proyecto=t2.id WHERE id_proyecto=$id order by personal desc,Nro_Actividades desc"; 
      break;  
    }
    $array = $this->htmlTagTable($sql,1);
    if(!empty($array)){
     $num_col = $this->pConnect->getNumFields();
     $str_th  = "<thead> <tr> <th colspan='".($num_col)."'> <i class='fa fa-tasks'></i> Requerimientos </th> </tr> <tr role='row'>".str_replace("_", " ", $array['th'])."</tr> </thead>";
     $str_tr  = $array['tr'];
     $table   = "<table id='tbl-result' class='table table-hover table-striped dataTable' style='cursor: pointer;'> $str_th  $str_tr</table>";
     $resp    = array( "tbl" => $table);
     $this->showResponse($this->convertJson($resp), 200);
    }else{
     $resp = array("tbl" => self::msg_box);  
     $this->showResponse($this->convertJson($resp), 200); 
    }
    //
    $this->showResponse($this->returnError(2), 204); 
   }
   //
  } 
  // 
  $api = new Api();  
  $api->processRequest(); 
  //
?>   