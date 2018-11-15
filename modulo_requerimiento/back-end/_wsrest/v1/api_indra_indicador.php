<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/indicador
  //
  class Api extends ApiBase {
   //
   public function indicadores() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    $sql = "select co_id as id,tx_descripcion as descripcion From ".self::TBL_INDICADOR_TIPO." t1 group by co_id order by co_id";
    $this->showResponseQuery($sql);
   }
   //
   public function informe(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    if(!isset($this->dataRequest['ind'],$this->dataRequest['rol'],$this->dataRequest['act'],$this->dataRequest['d1'],$this->dataRequest['d2'])){
     $this->showResponse($this->convertJson($this->returnError(0)), 404);  
    } 
    //
    $ind = explode(",",$this->dataRequest['ind']);
    $rol = $this->dataRequest['rol'];
    $act = $this->dataRequest['act'];
    $d1  = $this->dataRequest['d1'];
    $d2  = $this->dataRequest['d2'];
    //
    $cant = count($ind);
    //
    $str = "";
    for($x=0;$x<$cant;$x++){
     $opt = (int)$ind[$x];
     switch($opt){
      case 1: 
       $str = $str."round (sum((select (nu_peso) from tbl_indicador where co_indicador=1 AND (t1.po_avance between nu_valor_min and nu_valor_max)))/count(*),2) as Eficiencia,";
       break;
      case 2: 
       $str = $str."round (sum((select (nu_peso) from tbl_indicador where co_indicador=2 AND (t1.po_avance between nu_valor_min and nu_valor_max)))/count(*),2) as Eficacia,";
       break;
      case 3:
       $str = $str."round (sum((select (nu_peso) from tbl_indicador where co_indicador=3 AND (t1.po_avance between nu_valor_min and nu_valor_max)))/count(*),2) as Calidad,";
       break;
      }
    }
    //        
    if (strlen(trim($str))>0) { $str = substr($str,0,strlen($str)-1); } 
    //
    $date = str_replace('/', '-', trim($d1));
    $date_start = date('Y-m-d', strtotime($date));
    //
    $date = str_replace('/', '-', trim($d2));
    $date_end = date('Y-m-d', strtotime($date));
    //
    $sql = "SELECT co_empleado as ID,(select concat(no_nombres,' ',no_apellidos) from ".self::TBL_EMPLEADO." where co_id=t1.co_empleado) as Colaborador,
     (select b.tx_descripcion from ".self::TBL_EMPLEADO." a inner join ".self::TBL_ROL." b on a.co_rol=b.co_id where a.co_id=t1.co_empleado) as Rol,
     (select tx_descripcion from ".self::TBL_ACTIVIDAD_TIPO." where co_id=t1.co_actividad_tipo) as Actividad,$str 
    FROM ".self::TBL_ACTIVIDAD." t1 WHERE co_actividad_tipo=$act and fe_inicio>='$date_start' and fe_fin<='$date_end' and (select co_rol from ".self::TBL_EMPLEADO." where co_id=t1.co_empleado)=$rol
    GROUP BY co_empleado";
    //echo $sql;
    $this->showResponseQuery($sql);
   } 
   //   
  } 
  // 
  $api = new Api();  
  $api->processRequest(); 
  //
?>   