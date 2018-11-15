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
   function tipos(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    $sql = "SELECT co_id as id, tx_descripcion as descripcion FROM tbl_requerimiento_tipo ORDER BY co_id";
    $this->showResponseQuery($sql);
   }
   //
   function requerimientos(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    if(!isset($this->dataRequest['tp'],$this->dataRequest['ds'],$this->dataRequest['rq'])){
     $this->showResponse($this->convertJson($this->returnError(0)), 404);  
    }
    //
    $type = (int) $this->dataRequest['tp'];
    $desc = $this->dataRequest['ds'];
    $rqm  = $this->dataRequest['rq'];
    //
    $cond_date = "";
    //
    if(!empty($this->dataRequest['days'])){
     $rng  = explode("-",trim($this->dataRequest['days']));  
     $date = str_replace('/', '-', trim($rng[0]));
     $date_start = date('Y-m-d', strtotime($date));
     //
     $date = str_replace('/', '-', trim($rng[1]));
     $date_end = date('Y-m-d', strtotime($date));
     //
     $cond_date = "and t1.fe_creacion>='$date_start' and t1.fec_cierre<='$date_end'"; 
    }
    //concat(t1.co_id,'|',(select no_nombre from ".self::TBL_CLIENTE." where co_id=t2.co_cliente))
    $fields = "t1.co_id as Codigo,(select no_nombre from ".self::TBL_CLIENTE." where co_id=t2.co_cliente) as Cliente,t1.no_nombre as Descripcion,date_format(t1.fe_creacion,'%d/%m/%Y') as Fecha_creacion,date_format(t1.fe_cierre,'%d/%m/%Y') as Fecha_cierre,t1.nu_prioridad as Prioridad"; //t1.Avance,Estado
    //
    $OrderBy = "Order by Cliente,Codigo";
    //
    switch ($type) {
     case 1: 
      $sql = "SELECT $fields FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.co_proyecto=t2.co_id WHERE t1.no_nombre like '%$rqm%' AND t2.tx_descripcion like '%$desc%' $cond_date $OrderBy"; 
      break;
     case 2:
      $sql = "SELECT $fields FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.co_proyecto=t2.co_id WHERE t1.no_nombre like '%$rqm%' AND co_proyecto=(SELECT t2.co_id as id_proyecto FROM ".self::TBL_PROYECTO." t2 inner join ".self::TBL_CLIENTE." t3 on t2.co_cliente=t3.co_id WHERE t3.no_nombre like '%$desc%') $cond_date $OrderBy"; 
      break; 
    }
    //echo $sql;
    //
    $this->showResponseQuery($sql);
   }
   //
   public function requerimiento($id){
    switch($_SERVER['REQUEST_METHOD']){
     case "GET" : $this->listarRequerimiento($id); break;
     case "POST": $this->agregarRequerimiento(); break;
     case "PUT" : $this->editarRequerimiento($id); break;
     case "DELETE" : $this->eliminarRequerimiento($id); break;
     default: 
      $this->showResponse($this->convertJson($this->returnError(1)), 405);
      break;
    }
   }
   //
   private function agregarRequerimiento(){
    if ($_SERVER['REQUEST_METHOD'] != "POST") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $prj  = (int) $this->dataRequest['prj'];
    $desc = trim($this->dataRequest['rq']);
    $type = (int) $this->dataRequest['rqt'];
    $pri  = (int) $this->dataRequest['pri'];
    //
    $fields = "co_id,no_nombre,co_proyecto,co_requerimiento_tipo,nu_prioridad,fe_creacion,fe_cierre,po_avance,fl_estado ";
    $sql    = "INSERT INTO ".self::TBL_REQUERIMIENTO." ($fields) VALUES (0,'$desc',$prj,$type,$pri,'0000-00-00','0000-00-00',0,1)";
    $array  = array( "msg" => "La información se guardo satisfactoriamente");
    //$this->showResponseQueryAffecArray($sql,$array,0,404);
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $this->showResponse($this->convertJson($array), 200);  
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   }
   //
   private function editarRequerimiento($id){
    if ($_SERVER['REQUEST_METHOD'] != "PUT") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $prj  = (int) $this->dataRequest['prj'];
    $desc = trim($this->dataRequest['rq']);
    $type = (int) $this->dataRequest['rqt'];
    $pri  = (int) $this->dataRequest['pri'];
    //
    $sql   = "UPDATE ".self::TBL_REQUERIMIENTO." SET co_proyecto=$prj,no_nombre='$desc',co_requerimiento_tipo=$type,nu_prioridad=$pri WHERE co_id=$id";
    $array = array( "msg" => "La información se editó satisfactoriamente");
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $this->showResponse($this->convertJson($array), 200);  
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   }
   //
   private function eliminarRequerimiento($id){
    if ($_SERVER['REQUEST_METHOD'] != "DELETE") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $sql   = "DELETE FROM ".self::TBL_REQUERIMIENTO." WHERE co_id=$id";
    $array = array( "msg" => "La información se eliminó satisfactoriamente");
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $this->showResponse($this->convertJson($array), 200);  
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   }
   //
   private function listarRequerimiento($id){//requerimiento($id){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    if(!isset($id)){
     //$this->showResponse($this->convertJson($this->returnError(0)), 404);
     $condition = "WHERE co_proyecto>0";
    }else{
     $condition = "WHERE co_proyecto=$id";
    }
    //
    $fields = "t1.co_id as Codigo,t2.tx_descripcion as Proyecto,t1.no_nombre as Requerimiento,(SELECT tx_descripcion FROM tbl_requerimiento_tipo WHERE co_id=t1.co_requerimiento_tipo) as Tipo_requerimiento,t1.nu_prioridad as Prioridad, fl_estado as Estado"; //t1.Avance,Estado
    $sql = "SELECT $fields FROM ".self::TBL_REQUERIMIENTO." t1 inner join ".self::TBL_PROYECTO." t2 on t1.co_proyecto=t2.co_id $condition"; 
    $this->showResponseQuery($sql);
   } 
   //
   function estado($id){
    if ($_SERVER['REQUEST_METHOD'] != "PUT") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $sql   = "call INDRASU_RQESTADO($id)";
    $array = array( "msg" => "$sql el estado del requerimiento se actualizó correctamente.");
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $this->showResponse($this->convertJson($array), 200);  
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   }
   //
   // operaciones de las actividades del requerimiento 
   //
   public function actividad($id){
    switch($_SERVER['REQUEST_METHOD']){
     case "GET" : $this->listarActividad($id); break;
     case "POST": $this->agregarActividad(); break;
     case "PUT" : $this->editarActividad($id); break;
     case "DELETE" : $this->eliminarActividad($id); break;
     default: $this->showResponse($this->convertJson($this->returnError(1)), 405); break;
    }
   }
   //
   private function agregarActividad(){
    if ($_SERVER['REQUEST_METHOD'] != "POST") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $id_r  = (int) $this->dataRequest['rq'];
    $desc = trim($this->dataRequest['act']);
    $type = (int) $this->dataRequest['actp'];
    $pri  = (int) $this->dataRequest['pri'];
    //
    $fields = "co_id,tx_descripcion,co_actividad_tipo,co_empleado,fe_inicio,fe_fin,po_avance,ss_costo,co_complejidad";
    $sql    = "INSERT INTO ".self::TBL_ACTIVIDAD." ($fields) VALUES (0,'$desc',$type,0,'0000-00-00','0000-00-00',0,0,$pri)";
    $array  = array( "msg" => "La información se guardo satisfactoriamente");
    $query  = $this->pConnect->Query($sql);
    $num    = $this->pConnect->affectedRecords();
    //
    if ($num > 0) {
     $id_a  = $this->pConnect->getLastInsertID();
     $sql   = "INSERT INTO ".self::TBL_REQUERIMIENTO_DETALLE." (co_id,co_requerimiento,co_actividad) VALUES (0,$id_r,$id_a)";
     $query = $this->pConnect->Query($sql);
     $num   = $this->pConnect->affectedRecords();
     if ($num > 0) {
     $this->showResponse($this->convertJson($array), 200);  
     }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
     }
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   }
   //
   private function editarActividad($id){
    if ($_SERVER['REQUEST_METHOD'] != "PUT") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $desc = trim($this->dataRequest['act']);
    $type = (int) $this->dataRequest['actp'];
    $pri  = (int) $this->dataRequest['pri'];
    //
    $sql   = "UPDATE ".self::TBL_ACTIVIDAD." SET tx_descripcion='$desc',co_actividad_tipo=$type,co_complejidad=$pri WHERE co_id=$id";
    $array = array( "msg" => "La información se editó satisfactoriamente");
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $this->showResponse($this->convertJson($array), 200);  
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   }
   //
   private function eliminarActividad($id){
    if ($_SERVER['REQUEST_METHOD'] != "DELETE") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $sql   = "DELETE FROM ".self::TBL_ACTIVIDAD." WHERE co_id=$id";
    $array = array( "msg" => "La información se eliminó satisfactoriamente");
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $sql   = "DELETE FROM ".self::TBL_REQUERIMIENTO_DETALLE." WHERE co_actividad=$id";
     $query = $this->pConnect->Query($sql);
     $num   = $this->pConnect->affectedRecords();
     if ($num > 0) { 
      $this->showResponse($this->convertJson($array), 200); 
     }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
     }
    }else{
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
    }
   } 
   //
   private function listarActividad($id){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    if(isset($this->dataRequest['type'])){
     $opt = (int) $this->dataRequest['type'];
     switch($opt){
      case 1: 
       $fields = "co_actividad as Codigo,tx_descripcion as Descripcion,(select tx_descripcion from ".self::TBL_ACTIVIDAD_TIPO." where co_id=t2.co_actividad_tipo) as Tipo,(SELECT tx_descripcion FROM tbl_complejidad WHERE co_id=t2.co_complejidad) as Complejidad"; 
       break;
      case 2: 
       $fields = "co_actividad as Codigo,tx_descripcion as Descripcion,(select tx_descripcion from ".self::TBL_ACTIVIDAD_TIPO." where co_id=t2.co_actividad_tipo) as Tipo,ifnull((SELECT concat(no_nombres,' ',no_apellidos) FROM ".self::TBL_EMPLEADO." WHERE co_id=co_empleado),'') as Personal,(SELECT tx_descripcion FROM tbl_complejidad WHERE co_id=t2.co_complejidad) as Complejidad"; 
       break; 
     }
     
    }else{
     $fields = "co_actividad as Codigo,tx_descripcion as Descripcion,(select tx_descripcion from ".self::TBL_ACTIVIDAD_TIPO." where co_id=t2.co_actividad_tipo) as Tipo,
     date_format(fe_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(fe_fin,'%d/%m/%Y') as Fecha_fin, 
     (select concat(no_nombres,' ',no_apellidos) from ".self::TBL_EMPLEADO." where co_id=t2.co_empleado) as Responsable";
    }
    //
    $sql = "SELECT $fields FROM ".self::TBL_REQUERIMIENTO_DETALLE." t1 inner join ".self::TBL_ACTIVIDAD." t2 on t1.co_actividad=t2.co_id WHERE co_requerimiento=$id"; //Avance,
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