<?php  
  include("class_mysql.php");
  //
  class Rest {
   //const name_db = "bd_indra";
   //
   private $_method;  
   private $_arguments;  
   private $_codStatus = 200;
   private $_logError  = NULL;
   //
   public $pConnect = NULL;  
   public $dataRequest = array();  
   //
   public function __construct($db) {
    set_error_handler(array($this,"errorHandler")); // establece funcion para capturar error
    date_default_timezone_set("America/Lima"); //establece la zona horaria
    //
    if(empty($db)){ $db=self::DB_NAME;}
    $this->pConnect = new cMySQL($db);
	   $this->requestMethod();  
   } 
   //
   public function errorHandler($errno,$errstr,$errfile,$errline,$errcontext){
        // error types
    $errortype = array (
     E_ERROR              => 'Error',
     E_WARNING            => 'Warning',
     E_PARSE              => 'Parsing Error',
     E_NOTICE             => 'Notice',
     E_CORE_ERROR         => 'Core Error',
     E_CORE_WARNING       => 'Core Warning',
     E_COMPILE_ERROR      => 'Compile Error',
     E_COMPILE_WARNING    => 'Compile Warning',
     E_USER_ERROR         => 'User Error',
     E_USER_WARNING       => 'User Warning',
     E_USER_NOTICE        => 'User Notice',
     E_STRICT             => 'Runtime Notice',
     E_RECOVERABLE_ERROR  => 'Catchable Fatal Error'
    );
    // error threshold
    $threshold = array( 
     1 => E_USER_ERROR , 
     2 => E_USER_WARNING | E_USER_ERROR ,  
     3 => E_ALL
    );
    // save message into log
    //if( CFG_ERROR_LOG_REGISTER && ($threshold[CFG_ERROR_LOG_THRESHOLD] & $errno) == $errno ){
            // create error message
      $error_msg =  "[".date("d-m-Y H:i:s")."] - ".$errortype[$errno]." - $errfile, line $errline";
      $error_msg .= "\n$errstr";
    
      if( isset($errcontext['this']) ){
         if( is_object($errcontext['this']) ){
            $classname = get_class($errcontext['this']);
            $parentclass = get_parent_class($errcontext['this']);
            $error_msg .= "\nObject/Class: '$classname', Parent Class: '$parentclass'";
         }
      }
      $error_msg .= "\n\n";            
      //
      $this->_logError = true;
      $fichero = "error_php_reporting.txt";                
      // guardamos el mensaje en el fichero        
     file_put_contents($fichero,$error_msg, FILE_APPEND);              
   } 
   //
   public function convertJson($data) { return json_encode($data); } 
   // 
   public function showResponse($data, $status) { 
    $this->_codStatus = ($status) ? $status : 200;//si no se envía $status por defecto será 200  
    $this->setHeader();
    echo $data;  
    exit;  
   }
   //
   public function showResponseArray($condition,$array,$error,$status) {
    if ($condition) {
     $array["log"] = $this->_logError;
     $this->showResponse($this->convertJson($array), 200);   
    }  
    $this->showResponse($this->convertJson($this->returnError($error)),$status);  
   }
   //
   public function showResponseQuery($str_query) {
    $query     = $this->pConnect->Query($str_query);
    if($query){
     $result    = $this->pConnect->GetLink();
     $num_reg   = $this->pConnect->GetNumRecords();
     $this->pConnect->Close();
     if ($num_reg>0) {
      $this->showResponse($this->convertJson($result), 200);  
      //$this->showResponse($this->JsonQuery($result),200);  
     }
     $this->showResponse($this->convertJson($this->returnError(2)), 204);  
    }  
    //
    $this->_logError = true;
    $this->showResponse($this->convertJson($this->returnError(12)), 200);  
   }
   //
   public function showResponseQueryAffecArray($query,$array,$error,$status) {
    switch(!empty($query)){
      case true:
       $condition = ($this->pConnect->AffectedRecords()>0);
       if ($condition) {
        $array["log"] = $this->_logError;
        $this->showResponse($this->convertJson($array), 200);   
       }   
       $this->showResponse($this->convertJson($this->returnError($error)),$status);  
       break;
      case false:
       $this->_logError = true;
       $this->showResponse($this->convertJson($this->returnError(12)), 200);
       break;
    }
    $this->showResponse($this->convertJson($this->returnError($error)),$status);   
   }
   //
   public function returnError($id) {  
    $errores = array(  
       array("status" => "error", "msg" => "petición no encontrada"),  
       array("status" => "error", "msg" => "petición no aceptada"),  
       array("status" => "error", "msg" => "petición sin contenido"),  
       array("status" => "error", "msg" => "email o password incorrectos"),  
       array("status" => "error", "msg" => "error al eliminar información"),  
       array("status" => "error", "msg" => "error al actualizar"),  
       array("status" => "error", "msg" => "error buscando usuario por email"),  
       array("status" => "error", "msg" => "error al crear"),  
       array("status" => "error", "msg" => "información ya existe"),
       array("status" => "error", "msg" => "información incompleta"),
       array("status" => "error", "msg" => "acceso no permitido"),
       array("status" => "0", "msg" => "problemas en la conexión", "log" => $this->_logError),
       array("status" => "0", "msg" => "revisar log db", "log" => $this->_logError),
       array("status" => "0", "msg" => "operación no ejecutada")      
    ); 
    return $errores[$id];  
   }
   //
   public function applyQuery_utf8($data){
    foreach ($data as $row){
	   $array [] = array_map('utf8_encode', $row); 
    }
	  return $array;
   }
   //
   public function jsonQuery($data){
    $json_str = "";
    foreach ($data as $row){
	  $str = "";
	  foreach ($row as $col => $item){ $str = $str."\"$col\": \"$item\","; }
	   $json_str = $json_str."{".substr($str,0,strlen($str)-1)."},";
    }
	  $json_array = "[".substr($json_str,0,strlen($json_str)-1)."]";
	  return $json_array;
   }
   //
   public function strQuery($data){
    $query_str = "";
    foreach ($data as $row){
	   $str = "";
	   foreach ($row as $col => $item){ $str = $str."\"$col\": \"$item\","; }
	   $query_str = $query_str."{".substr($str,0,strlen($str)-1)."},";
    }
	  return substr($query_str,0,strlen($query_str)-1)."*";
   } 
   //
   public function createLog($path,$file,$str){
    //chmod($path, 0775); 
    $dir  = (!empty($path) ? "$path/$file" : "$file");
    $file = fopen("$dir", "a");
    $date = date("Y-m-d H:i:s"); 
    if(is_array($str)){
     fwrite($file,"[$date] ".print_r($str, TRUE));
     fwrite($file,PHP_EOL);  
    }else{
     fwrite($file,"[$date] $str".PHP_EOL);
    }
    fclose($file);
   } 
   //
   private function curl_get_contents($url){
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_HEADER, 0);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_URL, $url);
    $data = curl_exec($ch);
    curl_close($ch);
    //
    return $data;
   }
   //
   private function setHeader() {
    $desc_type	  = "application/json";     
	  $desc_charset = ["utf-8","iso-8859-1"];
	  header("HTTP/1.1 " . $this->_codStatus . " " . $this->getCodeStatus());  
    header("Content-Type: $desc_type;charset=".$desc_charset[1]);
   }
   //
   private function cleanIn($data) {  
    $input_data = array(); 
    //
    if (is_array($data)) {  
     foreach ($data as $key => $value) { $input_data[$key] = $this->cleanIn($value); }  
    } else {  
     if (get_magic_quotes_gpc()) {  
      //Quitamos las barras de un string con comillas escapadas  
      //Aunque actualmente se desaconseja su uso, muchos serveres tienen activada la extensión magic_quotes_gpc.   
      //Cuando esta extensión está activada, PHP añade automáticamente caracteres de escape (\) delante de las comillas que se escriban en un campo de formulario.   
      $data = trim(stripslashes($data));  
     }  
     //eliminamos etiquetas html y php  
     $data = strip_tags($data);  
     //Conviertimos todos los caracteres aplicables a entidades HTML  
     $data = htmlentities($data);  
     $input_data = trim($data);  
    }  
    return $input_data;  
   }
   //
   private function requestMethod() {  
    $metodo = $_SERVER['REQUEST_METHOD'];

    switch ($metodo) {  
     case "GET": $this->dataRequest = $this->cleanIn($_GET); break;  
     case "POST": 
       $data = json_decode(file_get_contents('php://input'), true);
      //if(empty($data)){ $this->dataRequest = $this->cleanIn($_POST); }
      if(empty($data)){  
       $this->dataRequest = $this->cleanIn($_POST); 
       //parse_str(file_get_contents("php://input"), $this->dataRequest);
      }else{
       $this->dataRequest = $this->cleanIn($data);
      }
      break;  
     case "DELETE"://"falling though". Se ejecutará el case siguiente  
     case "PUT":  
      //php no tiene un método propiamente dicho para leer una petición PUT o DELETE por lo que se usa un "truco":  
      //leer el stream de entrada file_get_contents("php://input") que transfiere un fichero a una cadena.  
      //Con ello obtenemos una cadena de pares clave valor de variables (variable1=dato1&variable2=data2...)
      //que evidentemente tendremos que transformarla a un array asociativo.  
      //Con parse_str meteremos la cadena en un array donde cada par de elementos es un componente del array.
      //     
      $data = json_decode(file_get_contents('php://input'), true);  
      if(empty($data)){
        $data = parse_str(file_get_contents("php://input"), $this->dataRequest);  
      }
      //
      $this->dataRequest = $this->cleanIn($data);                   //$this->cleanIn($this->dataRequest);  
      $this->CreateLog("","log_rest.txt",$this->dataRequest);
      //
      //$this->dataRequest = $this->curl_get_contents("php://input");
      //$this->dataRequest = $this->cleanIn($this->dataRequest);
      //echo "PUT";
      //print_r($this->dataRequest);
      //
      /*
      $data = json_decode(file_get_contents('php://input'), true);
      
      if(!empty($data)){
       echo "data";
       
       $this->dataRequest = $this->cleanIn($data);
      } else{
       parse_str(file_get_contents("php://input"), $this->dataRequest);  
       $this->dataRequest = $this->cleanIn($this->dataRequest);   
      } 
      */
      break;  
     default: $this->response('', 404); break;  
    }  
   } 
   //
   public function processRequest() { 
    if (isset($_REQUEST['url'])) { 
     //si por ejemplo pasamos explode('/','////controller///method////args///') el resultado es un array con elem vacios;
     //Array ( [0] => [1] => [2] => [3] => [4] => controller [5] => [6] => [7] => method [8] => [9] => [10] => [11] => args [12] => [13] => [14] => )
     $url = explode('/', trim($_REQUEST['url']));  
     //con array_filter() filtramos elementos de un array pasando función callback, que es opcional. Si no le pasamos función callback, los elementos false o vacios del array serán borrados 
     //por lo tanto la entre la anterior función (explode) y esta eliminamos los '/' sobrantes de la URL
     $url = array_filter($url); 
     $this->_method = strtolower(array_shift($url));  
     $this->_arguments = $url;  
     $func = $this->_method;
     //
     //print_r($url);
     //
     if ((int) method_exists($this, $func) > 0) {  
      if (count($this->_arguments) > 0) {  
       call_user_func_array(array($this, $this->_method), $this->_arguments);  
      } else {//si no lo llamamos sin argumentos, al metodo del controlador  
       call_user_func(array($this, $this->_method));  
      }  
     }  
     else{  
      $this->showResponse($this->convertJson($this->returnError(0)), 404); 
     }  
    }  
    $this->showResponse($this->convertJson($this->returnError(0)), 404);  
   } 
   //  
   private function getCodeStatus() {  
    $status = array(  
      200 => 'OK',  
      201 => 'Created',  
      202 => 'Accepted',  
      204 => 'No Content',  
      301 => 'Moved Permanently',  
      302 => 'Found',  
      303 => 'See Other',  
      304 => 'Not Modified',  
      400 => 'Bad Request',  
      401 => 'Unauthorized',  
      403 => 'Forbidden',  
      404 => 'Not Found',  
      405 => 'Method Not Allowed',  
      500 => 'Internal Server Error');  
    $response = ($status[$this->_codStatus]) ? $status[$this->_codStatus] : $status[500];  
    return $response;  
   }
   //
  }  
 ?>