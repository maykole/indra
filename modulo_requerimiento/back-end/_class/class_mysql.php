<?php
 
class cMySQL {
  const DB_HOST = "localhost";  
  const DB_USER = "adm_wserv";
  const DB_PASS = "AdmW1603";	
  const DB_CHARSET = "utf8";

  //lc_time_names = 'es_PE';/
  //group_concat_max_len = 1024;
  //
  protected $pConnection;
  protected $pResult;
  /* número de error y texto error */
  var $Errno = 0;
  var $Error = "";
  var $Sql   = "";
  	//
  public function __construct($strDB) {
   // 
   $this->pConnection = new mysqli(self::DB_HOST,self::DB_USER,self::DB_PASS,$strDB);
   if ($this->pConnection->connect_errno) {
     $this->Errno = $this->pConnection->connect_errno;
     $this->Error = $this->pConnection->connect_error;
    return;    
   }
   $this->pConnection->set_charset(self::DB_CHARSET);
   return $this->pConnection;
  }

  private function logError() {
   	$this->Errno = $this->pConnection->errno;
   	$this->Error = $this->pConnection->error;
    //
    $trace = (debug_backtrace());
    $str = print_r($trace, TRUE);
    $error_info = "";
    foreach ($trace as $row) {
     $file = $row['file'];
     $line = $row['line']; 
     $error_info .= "* $file - $line\n"; 
    }
    $error_msg = "[".date("d-m-Y H:i:s")."] - ".$this->Errno." - ".$this->Error.(!empty($this->Sql) ? " - ".$this->Sql : "")."\n$error_info\n";
    $fichero   = "error_mysql_reporting.txt";                
    file_put_contents($fichero,$error_msg, FILE_APPEND);  
  }

  public function showError() { 
   return array("id" => $this->Errno ,"error" => $this->Error,"query" => $this->Sql);
  }

  public function Query($strQuery="") {
   $this->pResult = $this->pConnection->query($strQuery); 
   $this->Sql = $strQuery;
   if (!$this->pResult) { $this->LogError(); }
   return $this->pResult;
  }
  
  public function getNumRecords() { 
   if (!$this->pResult){
   	$this->LogError();
   	return -1;
   }
   return $this->pResult->num_rows;
  }
  
  public function getFieldName($pos) { 
   //$info_campo->type | $info_campo->table	 
   $info_campo = $this->pResult->fetch_field_direct($pos);
   return $info_campo->name;
  } 

  public function getLinkData($type=MYSQLI_ASSOC) { 
   if (!$this->pResult) {
   	$this->LogError(); 
   	return ; 
   }
   // MYSQLI_ASSOC, MYSQLI_NUM, o MYSQLI_BOTH	
   return $this->pResult->fetch_array($type);
  } 

  public function getLink($type=MYSQLI_ASSOC) { 
   if (!$this->pResult){
    $this->LogError(); 
   	return ; 
   }
	 // MYSQLI_ASSOC, MYSQLI_NUM, o MYSQLI_BOTH
   //return $this->pResult->fetch_all($type);
   return $this->arraySet($type);
  } 

  public function getStatusDB(){ 
    $ping = false;
    if (is_null($this->pConnection->connect_error)){
     $ping = $this->pConnection->ping();  
    }
    return $ping;    
  } 
  
  public function getLinkByField() { return $this->pResult->fetch_row(); }

  public function Seek($pos=0) { return $this->pResult->data_seek($pos); }

  public function affectedRecords() {	return $this->pConnection->affected_rows; }

  public function getNumFields() { return $this->pResult->field_count; }
	
  public function getLastInsertID(){ return $this->pConnection->insert_id; }
	
  public function Close() {
   //if($this->pResult){$this->pResult->close();}
   if ($this->pConnection) { $this->pConnection->close(); }
  }
  
  public function arraySet($type=MYSQLI_ASSOC){
   if (!$this->pResult) {
   	$this->LogError(); 
   	return ; 
   } 
   if ($this->getNumRecords()>0){
    while ($row = $this->GetLinkData($type)) { $rows [] = $row; }
	return $rows;
   }else{
    return ;
   }
   
     
  }

 }

?>