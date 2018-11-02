<?php
  require_once("api_indra_base.php");
    //
  // indra/requerimiento/estimacion
  //
  class Api extends ApiBase {
   //
   public function estimacion($id,$dd){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $actv  = (int) $id;//$this->dataRequest['actv'];
    $date  = $dd;//$this->dataRequest['days'];
    //
    $date1 = strtotime(str_replace("/", "-",trim($date)));
    $date2 = strtotime ("+2 day",$date1);
    //
    $date1 = date("Y-m-d",$date1);
    $date2 = date("Y-m-d",$date2);
    //
    $sql  = "UPDATE ".self::TBL_ACTVIDAD." SET fecha_inicio='$date1',fecha_fin='$date2' WHERE id=$actv";
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