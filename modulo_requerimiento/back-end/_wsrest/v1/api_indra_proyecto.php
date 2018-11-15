<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/proyecto/
  //
  class Api extends ApiBase {
   //
   public function proyectos(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    if(isset($this->dataRequest['tp'], $this->dataRequest['ds'], $this->dataRequest['d1'], $this->dataRequest['d2'])){
     $this->buscar();
    }else{
     $this->listar();
    }
    //
   }
   //
   private function buscar(){ 
    //
    $type = $this->dataRequest['tp'];
    $desc = $this->dataRequest['ds'];
    $d1 = $this->dataRequest['d1'];
    $d2 = $this->dataRequest['d2'];
    //
    $date   = str_replace('/', '-', trim($d1));
    $dd_start = date('Y-m-d', strtotime($date));
    //
    $date   = str_replace('/', '-', trim($d2));
    $dd_end = date('Y-m-d', strtotime($date));
    //
    $cond_date = "and t1.fe_creacion>='$dd_start' and t1.fe_termino<='$dd_end'"; 
    //
    $fields = "t1.co_id as Codigo, t2.no_nombre as Cliente,tx_descripcion as Proyecto,date_format(t1.fe_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(t1.fe_termino,'%d/%m/%Y') as Fecha_cierre";
    //
    //
    switch ($type) {
     case 1: $sql = "SELECT $fields FROM ".self::TBL_PROYECTO." t1 inner join ".self::TBL_CLIENTE." t2 on t1.co_cliente=t2.co_id WHERE t1.tx_descripcion like '%$desc%' $cond_date"; break;
     case 2: $sql = "SELECT $fields FROM ".self::TBL_PROYECTO." t1 inner join ".self::TBL_CLIENTE." t2 on t1.co_cliente=t2.co_id WHERE t2.no_nombre like '%$desc%'  $cond_date"; break; 
    }
    //echo "$sql<br>$dd";
    $this->showResponseQuery($sql);
    //
   }
   //
   private function listar(){ 
    $fields = "t1.co_id as id, t2.no_nombre as Cliente,tx_descripcion as descripcion,date_format(t1.fe_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(t1.fe_termino,'%d/%m/%Y') as Fecha_cierre";
    $sql    = "SELECT $fields FROM ".self::TBL_PROYECTO." t1 inner join ".self::TBL_CLIENTE." t2 on t1.co_cliente=t2.co_id";
    //
    $this->showResponseQuery($sql);
    //
   }
   //
  } 
  // 
  $api = new Api();  
  $api->processRequest(); 
  //
?>   