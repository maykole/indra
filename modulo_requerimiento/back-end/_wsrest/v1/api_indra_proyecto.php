<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/proyecto/
  //
  class Api extends ApiBase {
   //
   function lista($rep,$type,$desc,$dd){ 
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    //$type = (int) $this->dataRequest['tp'];
    //$desc = $this->dataRequest['ds'];
    //$rep  = (int) $this->dataRequest['trp'];
    //
    $cond_date = "";
    switch($rep){
     case 1: $func_click = "onclick='optOnClickRep();'"; break;
     case 2: $func_click = "onclick='optOnClickRep();'"; break;
     case 3: $func_click = "onclick='optOnClickRep();'"; break;
    }
    //
    //$dd = $this->dataRequest['days']);
    if(!empty($dd)){
     $rng    = explode(",",trim($dd));  
     $date   = str_replace('/', '-', trim($rng[0]));
     $dd_start = date('Y-m-d', strtotime($date));
     //
     $date   = str_replace('/', '-', trim($rng[1]));
     $dd_end = date('Y-m-d', strtotime($date));
     //
     $cond_date = "and t1.fecha_creacion>='$dd_start' and t1.fecha_termino<='$dd_end'"; 
     //$func_click = "optOnClickRep();"; 
    }
    //
    $fields = "t1.id as Codigo, t2.nombre as Cliente,descripcion as Proyecto,date_format(t1.Fecha_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(t1.Fecha_termino,'%d/%m/%Y') as Fecha_cierre";
    //
    //
    switch ($type) {
     case 1: $sql = "SELECT $fields FROM ".self::TBL_PROYECTO." t1 inner join ".self::TBL_CLIENTE." t2 on t1.id_cliente=t2.id WHERE t1.descripcion like '%$desc%' $cond_date"; break;
     case 2: $sql = "SELECT $fields FROM ".self::TBL_PROYECTO." t1 inner join ".self::TBL_CLIENTE." t2 on t1.id_cliente=t2.id WHERE t2.nombre like '%$desc%'  $cond_date"; break; 
    }
    //echo "$sql<br>$dd";
    $array = $this->htmlTagTable($sql,2,"Codigo","opc",$func_click);
    if(!empty($array)){
     $num_col = $this->pConnect->getNumFields();
     $str_th  = "<tr><th colspan='$num_col'> <i class='fa fa-list'></i> Listado de Proyectos</th></tr><tr>".str_replace("_", " ", $array['th'])."</tr>";
     $str_tr  = $array['tr'];
     $table   = "<table id='tbl-result-1' class='table table-hover table-striped dataTable' style='cursor: pointer;'>$str_th $str_tr</table>";
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