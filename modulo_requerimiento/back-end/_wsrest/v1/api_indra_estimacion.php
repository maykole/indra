<?php
  require_once("api_indra_base.php");
    //
  // indra/requerimiento/estimacion
  //
  class Api extends ApiBase {
   //
   public function estimacion(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    if(!isset($this->dataRequest['act'],$this->dataRequest['dd'])){
     $this->showResponse($this->convertJson($this->returnError(0)), 404);    
    }
    //
    $actv  = (int) $this->dataRequest['act'];
    $date  = $this->dataRequest['dd'];
    //
    $date1 = date("Y-m-d", strtotime(str_replace("/", "-",trim($date))));
    //
    $sql   = "SELECT fn_get_estimation('$date1',t1.co_actividad_tipo,t1.co_complejidad) as fecha FROM ".self::TBL_ACTIVIDAD." t1 where co_id=$actv";
    $query = $this->pConnect->Query($sql);
    $data  = $this->pConnect->GetLink();
    //
    $date2 = trim($data[0]['fecha']);
    //
    $sql  = "UPDATE ".self::TBL_ACTIVIDAD." SET fe_inicio='$date1',fe_fin='$date2' WHERE co_id=$actv";
    $query = $this->pConnect->Query($sql);
    $num   = $this->pConnect->affectedRecords();
    //
    if ($num > 0) { 
     $resp   = array( "msg" => "La fecha ha sido estimada");
     $this->showResponse($this->convertJson($resp), 200);  
    }else{
      $resp = array( "msg" => "No se pudo estimar la fecha, reintente nuevamente");  
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