<?php
  require_once("api_indra_base.php");
  //
  // indra/requerimiento/indicador
  //
  class Api extends ApiBase {
   //
   public function tipo() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    $sql = "select id,descripcion From ".self::TBL_INDICADOR_TIPO." t1 group by id order by id";
    $this->showResponseQuery($sql);
   }
   //
   public function informe($ind,$rol,$act,$dd){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    //$ind = explode(",",$this->dataRequest['ind']);
    //$rol = $this->dataRequest['rol'];
    //$act = $this->dataRequest['actv'];
    //$dd  = $this->dataRequest['days'];
    $rng = explode(",",trim($dd));
    $cant = count($ind);
    //
    $str = "";
    for($x=0;$x<$cant;$x++){
     $opt = (int)$ind[$x];
     switch($opt){
      case 1: 
       $str = $str."round(sum((case when (avance<25) then 1 when (avance>=25 and avance<35) then 2  when (avance>=35 and avance<50) then 3  when (avance>=50 and avance<70) then 4 else 5 end))/count(*),2) as Eficiencia,";
       break;
      case 2: 
       $str = $str."round(sum((case when (avance<20) then 1 when (avance>=20 and avance<30) then 2  when (avance>=30 and avance<45) then 3  when (avance>=45 and avance<60) then 4 else 5 end))/count(*),2) as Eficacia,";
       break;
      case 3: 
       $str = $str."round(sum((case when (avance<20) then 1 when (avance>=20 and avance<35) then 2  when (avance>=35 and avance<45) then 3  when (avance>=45 and avance<65) then 4 else 5 end))/count(*),2) as Calidad,";
       break;
      }
    }
    //        
    if (strlen(trim($str))>0) { $str = substr($str,0,strlen($str)-1); } 
    //
    $date = str_replace('/', '-', trim($rng[0]));
    $date_start = date('Y-m-d', strtotime($date));
    //
    $date = str_replace('/', '-', trim($rng[1]));
    $date_end = date('Y-m-d', strtotime($date));
    //
    $sql = "SELECT id_empleado as ID,(select concat(nombres,' ',apellidos) from ".self::TBL_EMPLEADO." where id=t1.id_empleado) as Colaborador,
     (select b.descripcion from ".self::TBL_EMPLEADO." a inner join sgrq_rol b on a.id_rol=b.id where a.id=t1.id_empleado) as Rol,
     (select descripcion from ".self::TBL_ACTIVIDAD_TIPO." where id=t1.id_actividad_tipo) as Actividad,$str 
    FROM ".self::TBL_ACTIVIDAD." t1 WHERE id_actividad_tipo=$act and fecha_inicio>='$date_start' and fecha_fin<='$date_end' and (select id_rol from ".self::TBL_EMPLEADO." where id=t1.id_empleado)=$rol
    GROUP BY id_empleado";
    //echo $sql;
    $array = $this->htmlTagTable($sql,1);
    if(!empty($array)){
     $str_th = "<thead> </tr> <tr role='row'>".str_replace("_", " ", $array['th'])."</tr> </thead>";
     $str_tr = $array['tr'];
     $table  = "<table id='tbl-result' class='table table-hover table-striped dataTable'>$str_th $str_tr</table>";
     $resp   = array( "tbl" => $table);
     $this->showResponse($this->convertJson($resp), 200);  
    }else{
     $msg  = self::msg_box;
     $resp = array("tbl" => $msg);  
     $this->showResponse($this->convertJson($resp), 200); 
    }  
    $this->showResponse($this->returnError(2), 204);
   }
   //   
  } 
  // 
  $api = new Api();  
  $api->processRequest(); 
  //
?>   