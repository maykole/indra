<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/rol/
  //
  class Api extends ApiBase {
   //
   public function lista() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    $sql  = "SELECT id,descripcion FROM ".self::TBL_ROL." t1 GROUP BY id ORDER BY id";
    $resp = $this->htmlTagOption($sql,"Seleccione Rol","id","descripcion");
    if(!empty($resp)){
     $this->showResponse($this->convertJson($resp), 200);
    }
    //
    $this->showResponse($this->returnError(2), 204);  
   }
   //
   public function tipo($id) {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //$id   = $this->dataRequest['id'];
    if(!empty($id)){
     $sql  = "SELECT id_actividad_tipo as id,(select descripcion from ".self::TBL_ACTIVIDAD_TIPO." where id=id_actividad_tipo) as descripcion FROM ".self::TBL_ROL_ACTIVIDAD." t1 where id_rol=$id";
     $resp = $this->htmlTagOption($sql,"Seleccione Actividad","id","descripcion");
     if(!empty($resp)){
      $this->showResponse($this->convertJson($resp), 200);
     }
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