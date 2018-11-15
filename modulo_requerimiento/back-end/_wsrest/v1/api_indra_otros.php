<?php
  require_once("api_indra_base.php");
  //
  // 
  //
  class Api extends ApiBase {
   //
   public function complejidad(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $sql = "SELECT co_id as id, tx_descripcion as descripcion FROM tbl_complejidad ORDER BY co_id";
    $this->showResponseQuery($sql);
    //
   } 
   //
   public function actividad() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    $sql  = "SELECT co_id as id,tx_descripcion as descripcion FROM ".self::TBL_ACTIVIDAD_TIPO." ORDER BY co_id";
    $this->showResponseQuery($sql);
   }
   //
  } 
  // 
  $api = new Api();  
  $api->processRequest(); 
  //
?>   