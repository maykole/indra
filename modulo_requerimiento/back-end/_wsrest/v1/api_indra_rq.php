<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/rq/
  //
  error_reporting(E_ALL ^ E_NOTICE);
  ini_set('display_errors', 1);
  //
  class Api extends ApiBase {
   //
   function lista($type,$desc,$rqm){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    //$type = (int) $this->dataRequest['tp'];
    //$desc = $this->dataRequest['ds'];
    //$rqm  = $this->dataRequest['rq'];
    if (!isset($type,$desc,$rqm)){
      $this->showResponse($this->convertJson($this->returnError(0)), 404);
    };
    //
    $cond_date = "";
    $func_click = "optOnClick();";
    //
    if(!empty($this->dataRequest['days'])){
     $rng  = explode("-",trim($this->dataRequest['days']));  
     $date = str_replace('/', '-', trim($rng[0]));
     $date_start = date('Y-m-d', strtotime($date));
     //
     $date = str_replace('/', '-', trim($rng[1]));
     $date_end = date('Y-m-d', strtotime($date));
     //
     $cond_date = "and t1.fecha_creacion>='$date_start' and t1.fecha_cierre<='$date_end'"; 
     $func_click = "optOnClickRep();"; 
    }
    //
    $fields = "concat(t1.id,'|',(select nombre from ".self::TBL_CLIENTE." where id=t2.id_cliente)) as Codigo,t1.nombre as Descripcion,date_format(t1.Fecha_creacion,'%d/%m/%Y') as Fecha_creacion,date_format(t1.Fecha_cierre,'%d/%m/%Y') as Fecha_cierre,t1.Prioridad"; //t1.Avance,Estado
    //
    //
    switch ($type) {
     case 1: 
      $sql = "SELECT $fields FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.id_proyecto=t2.id WHERE t1.nombre like '%$rqm%' AND t2.descripcion like '%$desc%' $cond_date"; 
      break;
     case 2:
      $sql = "SELECT $fields FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.id_proyecto=t2.id WHERE t1.nombre like '%$rqm%' AND id_proyecto=(SELECT t2.id as id_proyecto FROM ".self::TBL_PROYECTO." t2 inner join ".self::TBL_CLIENTE." t3 on t2.id_cliente=t3.id WHERE t3.nombre like '%$desc%') $cond_date"; 
      break; 
    }
    //echo $sql;
    //
    $query   = $this->pConnect->Query($sql);
    $num_col = $this->pConnect->getNumFields();
    $data    = $this->pConnect->GetLink();
    $num     = count($data);

    //
    if ($num > 0) { 
     $cont   = 0; 
     $str_th = ""; 
     $str_tr = ""; 
     $str_td = "";
     foreach($data as $row){
      $cont++; $str_td="";
      $array = explode("|",$row['Codigo']);
      $id = trim($array[0]);
      $cs = trim($array[1]);
      foreach ($row as $col => $val){ 
       if($cont==1){ $str_th = $str_th."<th class='sorting'>$col</th>"; }
       //
       $str_elem = "";
       if($col=="Codigo"){
        $str_elem = "<input type='radio' name='opc_row' id='r-$id' value='$id' onclick='$func_click' style='cursor: pointer;'><input type='hidden' id='cs-$id' value='$cs'>
          <label for='r-$id' style='cursor: pointer;'>$id</label>";
        $value = "";
       }else{
        $value = $val;
       } 
       // 
        $str_td = $str_td."<td>$str_elem $value </td>";
      } 
      $str_tr = $str_tr."<tr id='$id'>$str_td</tr>"; 
     }
     $str_th = "<tr><th colspan='$num_col'> <i class='fa fa-list'></i> Listado de Requerimientos</th></tr><tr>".str_replace("_", " ", $str_th)."</tr>";
     $table  = "<table class='table table-hover table-striped dataTable' style='cursor: pointer;'>$str_th $str_tr</table>";
     //
     $resp   = array( "tbl" => $table);
     $this->showResponse($this->convertJson($resp), 200);  
    }else{
      $resp = array("tbl" => self::msg_box);  
      $this->showResponse($this->convertJson($resp), 200); 
    }
    //
    $this->showResponse($this->returnError(2), 204);
   }
   // 
   function actividad($type){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    //$type = (int) $this->dataRequest['tp'];
    //
    $sql = "SELECT id_actividad as Codigo, Descripcion,(select descripcion from ".self::TBL_ACTIVIDAD_TIPO." where id=t2.id_actividad_tipo) as Tipo,
     date_format(Fecha_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(Fecha_fin,'%d/%m/%Y') as Fecha_fin, 
     (select concat(nombres,' ',apellidos) from ".self::TBL_EMPLEADO." where id=t2.id_empleado) as Responsable 
    FROM ".self::TBL_REQUERIMIENTO_DETALLE." t1 inner join ".self::TBL_ACTIVIDAD." t2 on t1.id_actividad=t2.id WHERE id_requerimiento=$type"; //Avance,
    //
    $array = $this->htmlTagTable($sql,2,"Codigo","act");
    if(!empty($array)){
     $num_col = $this->pConnect->getNumFields();
     $str_tr  = $array['tr'];
     $str_th  = "<tr> <th colspan='".($num_col)."'> <i class='fa fa-tasks'></i> Actividades </th> </tr> <tr>".str_replace("_", " ", $array['th'])."</tr>";
     $table   = "<table class='table table-hover table-striped dataTable' style='cursor: pointer;'> $str_th  $str_tr</table>";
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