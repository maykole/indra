<?php
 //
 //error_reporting(E_ALL ^ E_NOTICE);
 //ini_set('display_errors', 1);
 //
 require_once("../../_class/class_rest.php");
 //
 class Api extends Rest {
   const msg_box = "<div class='alert alert-info alert-dismissible callout callout-info'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><h4><i class='icon fa fa-info'></i> ATENCION </h4><p> Consulta sin Informacion. </p></div>";
   //
   public function __construct() {  
     parent::__construct("");   
   }
   //
   private function htmlTagOption($sql,$str,$value,$description){
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
	  private function htmlTagTableSimple($data){ 
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
   private function htmlTagTableRadio($data,$field,$desc,$function){
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
	  private function htmlTagTable($sql,$opt,$field="",$desc="",$function=""){	
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
   public function getTypeReport() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
	   $sql = "select id,descripcion From sgrq_reporte_tipo t1 group by id order by id";
	   $this->showResponseQuery($sql);
   }
   //
   public function getTypeIndicator() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
   	$sql = "select id,descripcion From sgrq_indicador_tipo t1 group by id order by id";
	   $this->showResponseQuery($sql);
   }
   //
   public function getListRol() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    $sql  = "SELECT id,descripcion FROM sgrq_rol t1 GROUP BY id ORDER BY id";
    $resp = $this->htmlTagOption($sql,"Seleccione Rol","id","descripcion");
    if(!empty($resp)){
     $this->showResponse($this->convertJson($resp), 200);
    }
    //
    $this->showResponse($this->returnError(2), 204);  
   }
   //
   public function getTypeActivity() {  
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    } 
    //
    if(!empty($this->dataRequest['id'])){
     $id   = $this->dataRequest['id'];
     $sql  = "SELECT id_actividad_tipo as id,(select descripcion from sgrq_actividad_tipo where id=id_actividad_tipo) as descripcion FROM sgrq_actividad_rol t1 where id_rol=$id";
     $resp = $this->htmlTagOption($sql,"Seleccione Actividad","id","descripcion");
     if(!empty($resp)){
      $this->showResponse($this->convertJson($resp), 200);
     }
    }
    //
    $this->showResponse($this->returnError(2), 204);  
   }
   //
   public function getIndicator(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
     //
     $ind = explode(",",$this->dataRequest['ind']);
     $rol = $this->dataRequest['rol'];
     $act = $this->dataRequest['actv'];
     $rng = explode("-",trim($this->dataRequest['days']));
     //print_r($ind);
     $cant = count($ind);
     //
     $str = "";
     for($x=0;$x<$cant;$x++){
      $opc = (int)$ind[$x];
       switch($opc){
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
     $date = str_replace('/', '-', $rng[0]);
     $fec_ini = date('Y-m-d', strtotime($date));
     //
     $date = str_replace('/', '-', trim($rng[1]));
     $fec_fin = date('Y-m-d', strtotime($date));
     //
     $sql = "SELECT id_empleado as ID,(select concat(nombres,' ',apellidos) from sgrq_empleado where id=t1.id_empleado) as Colaborador,
      (select b.descripcion from sgrq_empleado a inner join sgrq_rol b on a.id_rol=b.id where a.id=t1.id_empleado) as Rol,
      (select descripcion from sgrq_actividad_tipo where id=t1.id_actividad_tipo) as Actividad,$str 
     FROM sgrq_actividad t1 WHERE id_actividad_tipo=$act and fecha_inicio>='$fec_ini' and fecha_fin<='$fec_fin' and (select id_rol from sgrq_empleado where id=t1.id_empleado)=$rol
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
   function getListProj(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $type = (int) $this->dataRequest['tp'];
    $desc = $this->dataRequest['ds'];
    $rep  = (int) $this->dataRequest['trp'];
    //
    $cond_fecha = "";
    switch($rep){
     case 1: $func_click = "onclick='optOnClickRep();'"; break;
     case 2: $func_click = "onclick='optOnClickRep();'"; break;
     case 3: $func_click = "onclick='optOnClickRep();'"; break;
    }
    //
    if(!empty($this->dataRequest['days'])){
     $rng  = explode("-",trim($this->dataRequest['days']));  
     $date = str_replace('/', '-', $rng[0]);
     $fec_ini = date('Y-m-d', strtotime($date));
     //
     $date = str_replace('/', '-', trim($rng[1]));
     $fec_fin = date('Y-m-d', strtotime($date));
     //
     $cond_fecha = "and t1.fecha_creacion>='$fec_ini' and t1.fecha_termino<='$fec_fin'"; 
     //$func_click = "optOnClickRep();"; 
    }
    //
    $fields = "t1.id as Codigo, t2.nombre as Cliente,descripcion as Proyecto,date_format(t1.Fecha_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(t1.Fecha_termino,'%d/%m/%Y') as Fecha_cierre";
    //
    //
    switch ($type) {
     case 1: 
      $sql = "SELECT $fields FROM sgrq_proyecto t1 inner join sgrq_cliente t2 on t1.id_cliente=t2.id WHERE t1.descripcion like '%$desc%' $cond_fecha"; 
      break;
     case 2:
      $sql = "SELECT $fields FROM sgrq_proyecto t1 inner join sgrq_cliente t2 on t1.id_cliente=t2.id WHERE t2.nombre like '%$desc%'  $cond_fecha"; 
      break; 
    }
    //echo "$sql<br>";
    $array = $this->htmlTagTable($sql,2,"Codigo","opc",$func_click);
    if(!empty($array)){
     $str_th = "<tr><th colspan='$num_col'> <i class='fa fa-list'></i> Listado de Proyectos</th></tr><tr>".str_replace("_", " ", $array['th'])."</tr>";
     $str_tr = $array['tr'];
     $table  = "<table id='tbl-result-1' class='table table-hover table-striped dataTable' style='cursor: pointer;'>$str_th $str_tr</table>";
     $resp   = array( "tbl" => $table);
     $this->showResponse($this->convertJson($resp), 200);  
    }else{
	    $resp = array("tbl" => self::msg_box);  
     $this->showResponse($this->convertJson($resp), 200); 
    }
    //
    $this->showResponse($this->returnError(2), 204);
   }
   //
   function getReport(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $id = (int) $this->dataRequest['tp']; 
    $rep  = (int) $this->dataRequest['trp']; 
    switch($rep){ 
      case 1 : 
       $sql= "SELECT t1.id as Codigo, nombre as Requerimiento, Prioridad,@val:=ifnull((SELECT round(sum(avance)/count(avance),2) as Avance FROM sgrq_requerimiento_detalle a inner join sgrq_actividad b on a.id_actividad=b.id WHERE id_requerimiento=t1.id),0) as Avance FROM sgrq_requerimiento t1 inner join sgrq_proyecto t2 on t1.id_proyecto=t2.id WHERE id_proyecto=$id order by avance desc,prioridad,codigo";
       break; 
      case 2 : 
       $sql= "SELECT t1.id as Codigo, nombre as Requerimiento, fn_get_status(t1.id) as Estado FROM sgrq_requerimiento t1 inner join sgrq_proyecto t2 on t1.id_proyecto=t2.id WHERE id_proyecto=$id order by Estado desc,codigo";
       break;
      case 3:
       $separator =", "; //"</br>";
       $sql= "SELECT t1.id as Codigo, nombre as Descripción, (select descripcion from sgrq_requerimiento_tipo where id=id_requerimiento_tipo) as Tipo, (select count(distinct id_actividad_tipo) from sgrq_actividad a inner join sgrq_requerimiento_detalle b on a.id=b.id_actividad where b.id_requerimiento=t1.id )  as Nro_Actividades,
        ifnull((select group_concat(distinct (select descripcion from sgrq_actividad_tipo where id=id_actividad_tipo) separator '$separator') from sgrq_actividad a inner join sgrq_requerimiento_detalle b on a.id=b.id_actividad where b.id_requerimiento=t1.id ),'')  as Actividades,
        (select count(distinct id_empleado) from sgrq_actividad a inner join sgrq_requerimiento_detalle b on a.id=b.id_actividad where b.id_requerimiento=t1.id )  as Personal
       FROM sgrq_requerimiento t1 inner join sgrq_proyecto t2 on t1.id_proyecto=t2.id WHERE id_proyecto=$id order by personal desc,Nro_Actividades desc"; 
	   break;  
    }
    //echo $sql;
    $array = $this->htmlTagTable($sql,1);
    if(!empty($array)){
     $str_th = "<thead> <tr> <th colspan='".($num_col)."'> <i class='fa fa-tasks'></i> Requerimientos </th> </tr> <tr role='row'>".str_replace("_", " ", $array['th'])."</tr> </thead>";
     $str_tr = $array['tr'];
     $table  = "<table id='tbl-result' class='table table-hover table-striped dataTable' style='cursor: pointer;'> $str_th  $str_tr</table>";
     $resp   = array( "tbl" => $table);
     $this->showResponse($this->convertJson($resp), 200);
    }else{
     $resp = array("tbl" => self::msg_box);  
     $this->showResponse($this->convertJson($resp), 200); 
    }
    //
    $this->showResponse($this->returnError(2), 204); 
   }
   //
   function getListRQ(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $type = (int) $this->dataRequest['tp'];
    $desc = $this->dataRequest['ds'];
    $rqm  = $this->dataRequest['rq'];
    //
    $cond_fecha = "";
    $func_click = "optOnClick();";
    //
    if(!empty($this->dataRequest['days'])){
     $rng  = explode("-",trim($this->dataRequest['days']));  
     $date = str_replace('/', '-', $rng[0]);
     $fec_ini = date('Y-m-d', strtotime($date));
     //
     $date = str_replace('/', '-', trim($rng[1]));
     $fec_fin = date('Y-m-d', strtotime($date));
     //
     $cond_fecha = "and t1.fecha_creacion>='$fec_ini' and t1.fecha_cierre<='$fec_fin'"; 
     $func_click = "optOnClickRep();"; 
    }
    //
    $fields = "concat(t1.id,'|',(select nombre from sgrq_cliente where id=t2.id_cliente)) as Codigo,t1.nombre as Descripcion,date_format(t1.Fecha_creacion,'%d/%m/%Y') as Fecha_creacion,date_format(t1.Fecha_cierre,'%d/%m/%Y') as Fecha_cierre,t1.Prioridad"; //t1.Avance,Estado
    //
    //
    switch ($type) {
     case 1: 
      $sql = "SELECT $fields FROM sgrq_requerimiento t1 inner join sgrq_proyecto t2 on t1.id_proyecto=t2.id WHERE t1.nombre like '%$rqm%' AND t2.descripcion like '%$desc%' $cond_fecha"; 
      break;
     case 2:
      $sql = "SELECT $fields FROM sgrq_requerimiento t1 inner join sgrq_proyecto t2 on t1.id_proyecto=t2.id WHERE t1.nombre like '%$rqm%' AND id_proyecto=(SELECT t2.id as id_proyecto FROM sgrq_proyecto t2 inner join sgrq_cliente t3 on t2.id_cliente=t3.id WHERE t3.nombre like '%$desc%') $cond_fecha"; 
      break; 
    }
    //echo $sql;
    //
    $query   = $this->pConnect->Query($sql);
    $num_col = $this->pConnect->getNumFields();
    $data   = $this->pConnect->GetLink();
    $num = count($data);

    //
    if ($num > 0) { 
     $cont=0; $str_th = ""; $str_tr= ""; $str_td="";
     foreach($data as $row){
      $cont++; $str_td="";
      $array = explode("|",$row['Codigo']);
      $id = trim($array[0]);
      $cs = trim($array[1]);
      foreach ($row as $col => $val){ 
       if($cont==1){ $str_th = $str_th."<th class='sorting'>$col</th>"; }
       //
       $str_elem = "";
       if($col=="Codigo"){
        $str_elem = "<input type='radio' name='opc_row' id='r-$id' value='$id' onclick='$func_click' style='cursor: pointer;'><input type='hidden' id='cs-$id' value='$cs'>
          <label for='r-$id' style='cursor: pointer;'>$id</label>";
        $value = "";
       }else{
        $value = $val;
       } 
       // 
        $str_td = $str_td."<td>$str_elem $value </td>";
      } 
      $str_tr = $str_tr."<tr id='$id'>$str_td</tr>"; 
     }
     $str_th = "<tr><th colspan='$num_col'> <i class='fa fa-list'></i> Listado de Requerimientos</th></tr><tr>".str_replace("_", " ", $str_th)."</tr>";
     $table  = "<table class='table table-hover table-striped dataTable' style='cursor: pointer;'>$str_th $str_tr</table>";
     //
     $resp   = array( "tbl" => $table);
     $this->showResponse($this->convertJson($resp), 200);  
    }else{
      $resp = array("tbl" => self::msg_box);  
      $this->showResponse($this->convertJson($resp), 200); 
    }
    //
    $this->showResponse($this->returnError(2), 204);
   }
   //
   function getListActivity(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $type = (int) $this->dataRequest['tp'];
    //
    $sql = "SELECT id_actividad as Codigo, Descripcion,(select descripcion from sgrq_actividad_tipo where id=t2.id_actividad_tipo) as Tipo,date_format(Fecha_inicio,'%d/%m/%Y') as Fecha_inicio,date_format(Fecha_fin,'%d/%m/%Y') as Fecha_fin, (select concat(nombres,' ',apellidos) from sgrq_empleado where id=t2.id_empleado) as Responsable FROM sgrq_requerimiento_detalle t1 inner join sgrq_actividad t2 on t1.id_actividad=t2.id WHERE id_requerimiento=$type"; //Avance,
    //
    //echo $sql;
    $array = $this->htmlTagTable($sql,2,"Codigo","act");
    if(!empty($array)){
     $num_col = $this->pConnect->getNumFields();
     $str_tr  = $array['tr'];
     $str_th  = "<tr> <th colspan='".($num_col)."'> <i class='fa fa-tasks'></i> Actividades </th> </tr> <tr>".str_replace("_", " ", $array['th'])."</tr>";
     $table   = "<table class='table table-hover table-striped dataTable' style='cursor: pointer;'> $str_th  $str_tr</table>";
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
   function getEstimation(){
    if ($_SERVER['REQUEST_METHOD'] != "GET") {  
     $this->showResponse($this->convertJson($this->returnError(1)), 405);  
    }
    //
    $actv  = (int) $this->dataRequest['actv'];
    //
    $date1 = strtotime(str_replace("/", "-",trim($this->dataRequest['days'])));
    $date2 = strtotime ("+2 day",$date1);
    //
    $date1 = date("Y-m-d",$date1);
    $date2 = date("Y-m-d",$date2);
    //
    $sql  = "UPDATE sgrq_actividad SET fecha_inicio='$date1',fecha_fin='$date2' WHERE id=$actv";
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
 } 
 // 
 $api = new Api();  
 $api->processRequest(); 
 //
?>   