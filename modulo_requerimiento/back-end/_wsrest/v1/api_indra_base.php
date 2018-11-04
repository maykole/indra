<?php
  error_reporting(E_ALL ^ E_NOTICE);
  ini_set('display_errors', 1);
  //
  require_once("../../_class/class_rest.php");
  //
  class ApiBase extends Rest {
   const DB_NAME = "bd_indra";
   // 
   const TBL_ACTIVIDAD      = "sgrq_actividad";
   const TBL_ACTIVIDAD_TIPO = "sgrq_actividad_tipo";
   const TBL_CLIENTE        = "sgrq_cliente";
   const TBL_INDICADOR_TIPO = "sgrq_indicador_tipo";
   const TBL_EMPLEADO       = "sgrq_empleado";
   const TBL_PROYECTO       = "sgrq_proyecto";
   const TBL_REPORTE_TIPO   = "sgrq_reporte_tipo"; 
   const TBL_REQUERIMIENTO  = "sgrq_requerimiento";
   const TBL_REQUERIMIENTO_TIPO     = "sgrq_requerimiento_tipo";
   const TBL_REQUERIMIENTO_DETALLE  = "sgrq_requerimiento_detalle";
   const TBL_ROL            = "sgrq_rol";
   const TBL_ROL_ACTIVIDAD  = "sgrq_actividad_rol";
   
   //
   const msg_box = "<div class='alert alert-info alert-dismissible callout callout-info'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><h4><i class='icon fa fa-info'></i> ATENCION </h4><p> Consulta sin Informacion. </p></div>";
   //
   public function __construct() {  
     parent::__construct(self::DB_NAME);   
   }
   //
   public function htmlTagOption($sql,$str,$value,$description){
	   $array  = array();
	   $query = $this->pConnect->Query($sql);
    $data  = $this->pConnect->GetLink();
    $num   = count($data);
    if ($num > 0) {
     $strOpt    = "<option value=''> $str </option>\n";
     foreach($data as $row){
      $strOpt   = $strOpt."<option value='".$row[$value]."'> ".$row[$description]." </option>\n";
     }
     $array = array("opt" => $strOpt);
	  }
    return $array; 
   }
   //
   public function htmlTagTableSimple($data){ 
	   $array  = array();
	   $cont   = 0; 
	   $str_th = ""; 
	   $str_tr = ""; 
	   $str_td = ""; 
    foreach($data as $row){
     $cont++; 
	    $str_td = "";
     foreach ($row as $col => $value){ 
      if($cont==1){ $str_th = $str_th."<th class='sorting'>$col</th>"; }
      $str_td = $str_td."<td>$value</td>";
     } 
     $str_tr = $str_tr."<tr>$str_td</tr>"; 
    }
	  $array = array("th" => $str_th,"tr" => $str_tr);
	  //
	  return $array;
  }
  //
  public function htmlTagTableRadio($data,$field,$desc,$function){
	  $array  = array();
	  $cont   = 0; 
	  $str_th = ""; 
	  $str_tr = ""; 
	  $str_td = "";
   foreach($data as $row){
    $cont++; 
	   $str_td ="";
    $id = $row[$field];
    foreach ($row as $col => $value){ 
     if($cont==1){ $str_th = $str_th."<th class='sorting'>$col</th>"; }
     $str_elem = ($col==$field ? "<input type='radio' name='".$desc."_row' id='$desc-$value' $function value='$value' style='cursor: pointer;'>" : "");
     $value    = ($col==$field ? "<label id='lbl-$value' for='$desc-$value' style='cursor: pointer;'>$value</label>" : $value);
     $str_td   = $str_td."<td>$str_elem $value </td>";
    } 
    $str_tr = $str_tr."<tr id='$id'>$str_td</tr>"; 
   }    
   $array = array("th" => $str_th,"tr" => $str_tr);
	  //
	  return $array;
  }
  //
  public function htmlTagTable($sql,$opt,$field="",$desc="",$function=""){	
	  $array   = array();
	  $query   = $this->pConnect->Query($sql);
   $num_col = $this->pConnect->getNumFields();
   $data    = $this->pConnect->GetLink();
   $num     = count($data);
	  //
	  if ($num > 0) { 
    switch($opt){
	    case 1: $array = $this->htmlTagTableSimple($data); break;
	    case 2: $array = $this->htmlTagTableRadio($data,$field,$desc,$function); break;
	   }
   }
	  //
	  return $array;
  }
  //
 }
 // 
?>   