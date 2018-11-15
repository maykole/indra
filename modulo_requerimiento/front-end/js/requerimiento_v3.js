var strURL  = "//"+location.hostname+"/_wsrest/v1/indra/requerimiento";
var msgBox  = '<div class="alert alert-info alert-dismissible callout callout-info"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button><h4><i class="icon fa fa-info"></i> ATENCION </h4><p> Consulta sin Informacion. </p></div>';
var strBtn  = '<div class="btn-group"> <button type="button" class="btn btn-default" onclick="exportToExcel();" title="Exportar a Excel"><i class="fa fa-file-excel-o"></i></button> <button type="button" class="btn btn-default" onclick="exportToPdf();" title="Exportar a PDF"><i class="fa fa-file-pdf-o"></i></button></div>';
var aDatos  = [{"id":1,"descripcion": "1"},{"id":2,"descripcion": "2"},{"id":3,"descripcion": "3"},{"id":4,"descripcion": "4"}];
var rq_elem = ['rq_prj','rq_desc','rq_type','rq_pri'];
var act_elem = ['act_desc','act_type','act_pri'];//,'act_prj','act_rq'];
var rcs_elem = ['rcs_type','rcs_pers'];

function configReport(id){
 switch(parseInt(id)){
  case 1:
   getProject('div-prj');
   break;
  case 2: 
   frmValidate('frm-2');
   frmReset('frm-2');
   btnSend('btn-send','frm-2');
   optOnClickTxt('opc_tp');
   elemDatePicker('opc_date');
   break;
  case 3: 
   frmValidate('frm-3');
   frmReset('frm-3');
   btnSend('btn-send','frm-3');
   getRole('opc_rol');
   elemDatePicker('opc_date');
   break;
 }
}

function clearLbl(){
 $('#lb-cs').html('');
 $('#lb-rq').html('');
 //$('#lb-st').html('');
 $('#lb-d1').html('');
 $('#lb-d2').html('');
}

function elemDatePicker(id){
  //$('#'+id).daterangepicker({format:'YYYY-MM-DD'});
  $('#'+id).daterangepicker({locale: { format: 'DD/MM/YYYY' }});
}

function frmValidate(id){
 switch(id){
  case 'frm': frm(id); break;
  case 'frm-1': frm1(id); break;
  case 'frm-2': frm2(id); break;
  case 'frm-3': frm3(id); break;
  case 'frm-4': frm4(id); break;
  case 'frm-5': frm5(id); break;
 }
} 

function frm(id){
 $('#'+id).validate({
      errorClass: 'text-danger',
      errorElement: 'span',
      rules:{ 
       opc_rol:{ required: true },
       opc_actv:{ required: true },
       'opc_ind[]':{ required: true },
       opc_date:{ required: true }
      },
     messages: {
      opc_rol: { required: 'elegir una opción' },
      opc_actv: { required: 'elegir una opción' },
      'opc_ind[]': { required: 'elegir una opción' },
      opc_date: { required: 'elegir rango de fecha' }
     }
 });
}

function frm1(id){
 $('#'+id).validate({
      errorClass: 'text-danger',
      errorElement: 'span',
      rules:{ 
       opc_tp:{ required: true },
       opc_rq:{ required: true },
       opc1_txt: { required: function(element) {
         return (parseInt($('input:radio[name=opc_tp]:checked').val())==1);
        }
       },
       opc2_txt: { required: function(element) {
         return (parseInt($('input:radio[name=opc_tp]:checked').val())==2);
        }
       }
      },
     messages: {
      opc_tp: { required: 'elegir una opción' },
      opc_rq: { required: 'ingresar descripción' },
      opc1_txt: { required: 'ingrese proyecto' },
      opc2_txt: { required: 'ingrese cliente' }
     }
 });
}

function frm2(id){
 $('#'+id).validate({
      errorClass: 'text-danger',
      errorElement: 'span',
      rules:{ 
       opc_tp:{ required: true },
       opc_rep:{ required: true },
       opc1_txt: { required: function(element) {
         return (parseInt($('input:radio[name=opc_tp]:checked').val())==1);
        }
       },
       opc2_txt: { required: function(element) {
         return (parseInt($('input:radio[name=opc_tp]:checked').val())==2);
        }
       }
      },
     messages: {
      opc_tp: { required: 'elegir una opción' },
      opc_rep: { required: 'elegir una opción' },
      opc1_txt: { required: 'ingrese proyecto' },
      opc2_txt: { required: 'ingrese cliente' },
      opc_date: { required: 'elegir rango de fecha' }
     }
 });
}

function frm3(id){
 $('#'+id).validate({
  errorClass: 'text-danger',
  errorElement: 'span',
  rules:{ 
       opc_rol:{ required: true },
       opc_date:{ required: true }
      },
     messages: {
      opc_rol: { required: 'elegir una opción' },
      opc_date: { required: 'elegir rango de fecha' }
     }
 }); 
}

function frm4(id){
 $('#'+id).validate({
  errorClass: 'text-danger',
  errorElement: 'span',
  rules:{ 
   rq_prj :{ required: true },
   rq_desc:{ required: true },
   rq_type:{ required: true },
   rq_pri :{ required: true }
  },
  messages: {
   rq_prj : { required: 'elegir una opción' },
   rq_desc: { required: 'ingresar descripcion' },
   rq_type: { required: 'elegir una opción' },
   rq_pri : { required: 'elegir una opción' }
  }
 }); 
}

function frm5(id){
 $('#'+id).validate({
  errorClass: 'text-danger',
  errorElement: 'span',
  rules:{ 
   act_prj :{ required: true },
   act_rq  :{ required: true },
   act_desc:{ required: true },
   act_type:{ required: true },
   act_pri :{ required: true }
  },
  messages: {
   act_prj : { required: 'elegir una opción' },
   act_rq  : { required: 'elegir una opción' },
   act_desc: { required: 'ingresar descripcion' },
   act_type: { required: 'elegir una opción' },
   act_pri : { required: 'elegir una opción' }
  }
 }); 
}

function frmReset(id){
  $('#'+id).bind('reset', function() {
    $('#div-responsive').html('');
  });
 }

function btnSend(b,f){
 $('#'+b).click(function(){
  if(!$('#'+f).valid()){
   return;
  }
  switch(f){
   case 'frm': getIndicatorReport(); break;
   case 'frm-1': getRq(); break;
   case 'frm-2': getProjectSearch(); break;
   case 'frm-3': getReport(); break;
  }
 });  
}

function getRowInfo(id){
 $('#lb-cs').html(': '+$('#'+id).children().eq(1).text());
 $('#lb-rq').html(': '+$('#'+id).children().eq(2).text());

 $('#lb-d1').html(': '+$('#'+id).children().eq(3).text());
 $('#lb-d2').html(': '+$('#'+id).children().eq(4).text());
 $('#div-data').show();
}

function getValuesElem(elem){
 var str = '';
 $(elem).each(function(){
  str += $(this).val() + ',';
 });
 if(str.length>0){
  str = str.substring(0,str.length-1);  
 }
 return str;
}

function optOnClickAct(id){
 $('input[name='+id+']').click(function(){
   var cod = $(this).val();
   var str = $('#'+cod).children().eq(3).text();
   var b = (str ==' 00/00/0000 ');
   $('#btn-gnr').attr('disabled',!b);
   $('#btn-edt').attr('disabled',b);
   $('#datepicker').attr('disabled',false);
 });
}

function optOnClickTxt(id){
 $('input[name='+id+']').click(function(){
   var vsel = parseInt($(this).val());
   var nsel = (vsel==1 ? 2 : 1);
   //
   $('#opc'+nsel+'_txt').val('');
   $('#opc'+nsel+'_txt').attr('disabled',true);
   //
   $('#opc'+vsel+'_txt').attr('disabled',false);
   $('#opc'+vsel+'_txt').val('');
   $('#opc'+vsel+'_txt').focus();
 });
}

//

function getDifficulty(id){
 $('#'+id).html('');
 $.ajax({
  type: "GET",
  url: strURL+'/otros/complejidad',
  contentType: "application/json; charset=utf-8",
  dataType: "json",
  success: function (data, status, jqXHR) { 
   if(data){
    htmlTagOption(data,id,'Seleccione complejidad');
   }
  },
  error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}

function getEstimation(id){
 var act = $('input:radio[name=act_row]:checked').val();
 var dt  = $('#datepicker').val().replace(/[/]/g, '-');
 if(!act || !dt){
  alertWindow("<span class='dark-gray'> Debe Seleccionar Actividad y/o fecha</span>");
  return;
 }
 var datos = {'act': act,'dd' : dt };
 //
 $.ajax({
   url:  strURL+'/estimacion',      
   type: 'GET',
	  data: datos,
   contentType: 'application/json; charset=utf-8',
   dataType: 'json',
   beforeSend: function () {
    $('#lbl-'+act).html(Iconload[0]);
   },
   error: function(jqXHR, status){
    alertWindow("<span class='dark-gray'>"+jqXHR.statusText+"</span>");
   },
   success:function(data, status, jqXHR){ 
    getRqActivity();
    $('#datepicker').val('');
    alertWindow("<span class='dark-gray'>"+data['msg']+"</span>");
   }
 });
}

function getEstimationEdit(id){
  bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿stas seguro de modificar los datos?</span>", function(result) {
   if (result) {
     getEstimation(id);  
   }
  });
}
//
function getRq(){ //getFrm1
 var datos =  getFrmRq1();
 $('#div-responsive').html('');
 $('#div-responsive-2').html('');
 $('#div-data').hide();
 clearLbl();
 $.ajax({
    url:  strURL+'/rq',      
    type: 'GET',
	   data: datos,
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    beforeSend: function () {
     $('#div-responsive').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
    },
    error: function(jqXHR, status){
     alertJson(jqXHR);
     $('#div-responsive').html(msgBox);
    },
    success:function(data, status, jqXHR){
     var tbl=htmlTagTableRadio(data,'Codigo','opc','onclick="getRqActivity();"');
     var str_th = '<tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr>';
     var str_tr = tbl[1];
     var table  = '<table id="tbl-result" class="table table-hover table-striped dataTable" style="cursor: pointer;">'+str_th+'\n'+str_tr+'</table>';
     $('#div-responsive').html(table);  
    }
 });     
}
//
function getFrmRq1(){ //getInfoFrm1
 var tp =  $('input:radio[name=opc_tp]:checked').val();
 var info = {
  'tp': tp,
  'ds': $('#opc'+tp+'_txt').val(),
  'rq': $('#opc_rq').val()
 }; 
 return info; 
}
//
function getRqTypes(id){ 
 $('#'+id).html('');
 var str ='Seleccionar tipo';
 $.ajax({
    type: "GET",
    url: strURL+'/rq/tipos',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) { 
     if(data){
      htmlTagOption(data,id,str);
     }
    },
    error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}

function getRqActivity(){ //optOnClick() 
  var rq = $('input:radio[name=opc_row]:checked').val();
  $('#div-responsive-2').html('');
  $('#div-data').hide();
  getRowInfo(rq);
  $.ajax({
    url:   strURL+'/rq/'+rq+'/actividad',      
    type: 'GET',
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    beforeSend: function () {
     $('#div-responsive-2').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
    },
    error: function(jqXHR, status){
     alertJson(jqXHR);
     $('#div-responsive-2').html(msgBox);
    },
    success:function(data, status, jqXHR){ 
     var tbl=htmlTagTableRadio(data,'Codigo','act');
     //
     var str_th = '<tr> <th colspan='+tbl[2]+'> <i class="fa fa-tasks"></i> Actividades </th> </tr> <tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr>';
     var str_tr = tbl[1];
     var table  = '<table id="tbl-result" class="table table-hover table-striped dataTable" style="cursor: pointer;">'+str_th+'\n'+str_tr+'</table>';
     $('#div-responsive-2').html(table);
     optOnClickAct('act_row');
    }
  });   
}

function getRqActivityCbo(){ 
  var rq = $('#act_rq').val();
  var tp = $('#act_fil').val();
  $('#div-responsive-tbl').html('');
  $.ajax({
    url:   strURL+'/rq/'+rq+'/actividad',      
    type: 'GET',
    data: {"type": tp},
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    beforeSend: function () {
     $('#div-responsive-tbl').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
    },
    error: function(jqXHR, status){
     alertJson(jqXHR);
     $('#div-responsive-tbl').html(msgBox);
    },
    success:function(data, status, jqXHR){ 
     var tbl    = htmlTagTableMenu(data,''); 
     var str_th = '<thead> <tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr> </thead>';
     var str_tr = '<tbody id="tbl-list-body">'+tbl[1]+'</tbody>';
     var table  = '<table id="tbl-list" class="table table-hover table-striped dataTable" style="cursor: pointer;">'+str_th+'\n'+str_tr+'</table>';
     $('#div-responsive-tbl').html(table);
     $('#tbl-list').DataTable({
       'paging'      : true,
       'lengthChange': false,
       'searching'   : false,
       'ordering'    : true,
       'info'        : true,
       'autoWidth'   : false
     });
     //
     switch(parseInt(tp)){
      case 1:
       delRqAct('tbl-list-body');
       selRowTable('tbl-list-body',2,act_elem,'updRqAct');
       break;
      case 2:
       //delRqAct('tbl-list-body');
       selRowTable('tbl-list-body',3,rcs_elem,'updRsc');
       break;
     
     }
     
    }
  });   
}

function getRqActivityType(id){
 $('#'+id).html('');
 $.ajax({
  type: "GET",
  url: strURL+'/otros/actividad',
  contentType: "application/json; charset=utf-8",
  dataType: "json",
  success: function (data, status, jqXHR) { 
   if(data){
    htmlTagOption(data,id,'Seleccione Tipo');
   }
  },
  error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}

//
function getIndicator(id){ //listCheck
 var strHtml = '<b>Tipo de Indicador</b><br/>';
 $.ajax({
    type: "GET",
    url: strURL+'/indicadores',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) {  
     if(data) {
      for (var key in data){
       strHtml = strHtml + '<div class="checkbox"> <label> <input type="checkbox" id="opc_ind" name="opc_ind[]" value="'+data[key]['id']+'"/>'+data[key]['descripcion']+'</label> </div>';
      }
      $('#'+id).html(strHtml);
     }
    },
    error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}

function getIndicatorReport(){
 var datos =  getFrmInd();
 $('#div-responsive').html('');
 $.ajax({
    url:  strURL+'/indicador/informe',
    type: 'GET',
    data: datos,
	   contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    beforeSend: function () {
     $('#div-responsive').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
    },
    error: function(jqXHR, status){
     alertJson(jqXHR);
     $('#div-responsive').html(msgBox);
    },
    success:function(data, status, jqXHR){ 
     var tbl=htmlTagTableSimple(data);
     //
     var str_th = '<thead> <tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr> </thead>';
     var str_tr = tbl[1];
     var table  = '<table id="tbl-result" class="table table-hover table-striped dataTable">'+str_th+'\n'+str_tr+'</table>';
     $('#div-responsive').html(table);
     //
     $('#tbl-result').DataTable({
       'paging'      : false,
       'lengthChange': false,
       'searching'   : false,
       'ordering'    : true,
       'info'        : true,
       'autoWidth'   : false
     });
    }
 });     
}

function getFrmInd(){
 var valind = getValuesElem('input[name="opc_ind[]"]:checked');
 var dd = $('#opc_date').val().split("-");
 var info = {
  'ind': valind,
  'rol': $('#opc_rol').val(),
  'act': $('#opc_actv').val(),
  'd1' : dd[0],
  'd2' : dd[1]
 }; 
 return info; 
}
//
function getProject(id,t=1){
 $('#'+id).html('');
 $.ajax({
  type: "GET",
  url: strURL+'/proyectos', //'/proyecto/listado'
  contentType: "application/json; charset=utf-8",
  dataType: "json",
  success: function (data, status, jqXHR) { 
   if(data){
    switch(t){
     case 1: htmlTagUlRadio(data,id); break; 
     case 2: htmlTagOption(data,id,'Seleccione Proyecto'); break;
    }
   }
  },
  error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}

function getProjectRq(){
 var id = $('#rq_prj').val();
 if(!id){id=0;}
 $('#div-responsive-tbl').html('');
 $.ajax({
  type: "GET",
  url: strURL+'/rq/'+id,
  contentType: "application/json; charset=utf-8",
  dataType: "json",
  beforeSend: function () {
   $('#div-responsive-tbl').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
  },
  error: function(jqXHR, status){
   alertJson(jqXHR);
   $('#div-responsive-tbl').html(msgBox);
  },
  success:function(data, status, jqXHR){
   var tbl    = htmlTagTableMenu(data,'Estado'); //htmlTagTableSimple(data);
   var str_th = '<thead> <tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr> </thead>';
   var str_tr = '<tbody id="tbl-list-body">'+tbl[1]+'</tbody>';
   var table  = '<table id="tbl-list" class="table table-hover table-striped dataTable" style="cursor: pointer;">'+str_th+'\n'+str_tr+'</table>';
   $('#div-responsive-tbl').html(table);
   $('#tbl-list').DataTable({
       'paging'      : true,
       'lengthChange': false,
       'searching'   : false,
       'ordering'    : true,
       'info'        : true,
       'autoWidth'   : false
    });
    delRq('tbl-list-body');
    enableRq('tbl-list-body');
    selRowTable('tbl-list-body',1,rq_elem,'updRq');
  } 
 });
}

function getProjectRqCbo(){
 var id = $('#act_prj').val();
 $('#div-responsive-tbl').html('');
 if(!id){id=0;}
 $.ajax({
  type: "GET",
  url: strURL+'/rq/'+id,
  contentType: "application/json; charset=utf-8",
  dataType: "json",
  beforeSend: function () {
   $('#act_rq').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
  },
  error: function(jqXHR, status){
   alertJson(jqXHR);
   $('#act_rq').html('');
  },
  success:function(data, status, jqXHR){
   htmlTagOption(data,'act_rq','','Codigo','Requerimiento');
  } 
 });
}

function getProjectSearch(){
 var datos = getFrmPrj();
 $('#div-responsive').html('');
 $('#div-responsive-tbl').html('');
 $('#div-responsive-chart').html('');
 clearLbl();
 $.ajax({
    url:  strURL+'/proyectos', 
    type: 'GET',
    data: datos,
	   contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    beforeSend: function () {
     $('#div-responsive').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
    },
    error: function(jqXHR, status){
     alertJson(jqXHR);
     $('#div-responsive').html(msgBox);
    },
    success:function(data, status, jqXHR){ 
     $('#div-responsive').html(data['tbl']);
     var tbl=htmlTagTableRadio(data,'Codigo','opc','onclick="getReport();"');
     //<tr> <th colspan='+tbl[2]+'> <i class="fa fa-list"></i> Listado de Proyectos </th> </tr>
     var str_th = '<tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr>';
     var str_tr = tbl[1];
     var table  = '<table id="tbl-list" class="table table-hover table-striped dataTable" style="cursor: pointer;">'+str_th+'\n'+str_tr+'</table>';
     $('#div-responsive').html(table);   
    }
 });     
}

function getFrmPrj(){
 var tp = $('input:radio[name=opc_tp]:checked').val();
 var dd = $('#opc_date').val().split('-');
 var info = {
  'tp': tp,
  'ds': $('#opc'+tp+'_txt').val(),
  'd1': dd[0],
  'd2': dd[1]
 }; 
 return info; 
}
//

function getPersonal(id){
 $('#'+id).html('');
 var str=''; 
 $.ajax({
    type: "GET",
    url: strURL+'/recurso/personal/'+$('#rcs_type').val(),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) { 
     if(data){
      htmlTagOption(data,id,str,'id','Personal');
     }
    },
    error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}

//
function getRole(id){
 $('#'+id).html('');
 var str ='Seleccionar Rol';
 $.ajax({
    type: "GET",
    url: strURL+'/roles',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) { 
     if(data){
      htmlTagOption(data,id,str);
     }
    },
    error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}
//
function getRoleActivity(id){
 $('#'+id).html('');
 var str='Seleccionar Actividad'; 
 $.ajax({
    type: "GET",
    url: strURL+'/rol/'+$('#opc_rol').val()+'/actividad',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) { 
     if(data){
      htmlTagOption(data,id,str);
     }
    },
    error: function (jqXHR, status) { alertJson(jqXHR); }
 });
}
//
function getReport(){ 
 var str_chart = '<canvas id="barChart" style="width: 380px; height: 190px;"></canvas>';  
 var trp = parseInt($('#opc_rep').val());
 //
 switch(trp){
  case 1: case 2: var datos = getFrmRep1(); break;
  case 3: var datos = getFrmRep3(); break;
 }
 //
 $('#div-responsive-tbl').html('');
 $('#div-responsive-chart').html('');
 //
 $.ajax({
   url:  strURL+'/reporte/informe/'+trp,  
   type: 'GET',
	  data: datos,
   contentType: 'application/json; charset=utf-8',
   dataType: 'json',
   beforeSend: function () {
    $('#div-responsive-tbl').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
   },
   error: function(jqXHR, status){
    alertJson(jqXHR);
    $('#div-responsive-tbl').html(msgBox); 
    $('#div-responsive-chart').html(''); 
    $('#div-btn').html(''); 
   },
   success:function(data, status, jqXHR){
    switch(trp){
     case 1: var str_lbl='Requerimiento'; var str_data='Avance'; break;
     case 2: var str_lbl='Requerimiento'; var str_data='Personal'; break;
     case 3: var str_lbl='Empleado'; var str_data='Nro_Actividades'; break;
    }
    var arrays = getArrayData(data,str_lbl,str_data);
    //
    var tbl=htmlTagTableSimple(data);
    //
    var str_th = '<thead> <tr role="row">'+tbl[0].replace(/_/g,' ')+'</tr> </thead>';
    var str_tr = tbl[1];
    var table  = '<table id="tbl-result" class="table table-hover table-striped dataTable">'+str_th+'\n'+str_tr+'</table>';
    $('#div-responsive-tbl').html(table);
    $('#div-btn').html(strBtn);
    //
    $('#tbl-result').DataTable({
       'paging'      : true,
       'lengthChange': false,
       'searching'   : false,
       'ordering'    : true,
       'info'        : true,
       'autoWidth'   : false
    });
    $('#div-responsive-chart').html(str_chart)
    getBarChart('barChart',arrays['label'],arrays['data'],0);
   }
 });   
}
//
function getFrmRep1(){ //getInfoFrm2()
 var info = { 'prj': $('input:radio[name=opc_row]:checked').val() }; 
 return info; 
}
//
function getFrmRep3(){
 var dd   = $('#opc_date').val().split("-");
 var info = {
  'rol' : $('#opc_rol').val(),
  'd1': dd[0],
  'd2': dd[1] 
 }; 
 return info; 
}
//
function htmlTagOption(data,id,str,val='',txt=''){
 $('#'+id).html('');
 if(data) {
  if(str.length>0){
   $('#'+id).append($('<option>',{ value: '',text: str })); 
  }
  //
  if(val.length==0){val='id';}
  if(txt.length==0){txt='descripcion';}
  //
  for (var key in data){
   var getKey = data[key];
   $('#'+id).append($('<option>', {
     value: data[key][val],
     text: ' '+data[key][txt]+' ',
     class: ''
   })); 
  }
 }
}

function htmlTagTableMenu(data,mn=''){ 
 var tbl    = [];
 var cont   = 0; 
 var str_th = ''; 
 var str_tr = ''; 
 var str_td = ''; 
 //
 var str_mn = '<a href="#" title="Editar" class="edit"><i class="fa fa-pencil"></i></a> <a href="#" title="Cancelar" class="cancel"><i class="fa fa-ban"></i></a> <a href="#" title="Eliminar" class="trash"><i class="fa fa-trash-o"></i></a>'; 
	//
 for (var key in data){
  var getKey = data[key];
  var ncol   = Object.keys(getKey).length;
  cont++; 
  str_td = '';
  for(var col in getKey){
   str_val = getKey[col];
   if(cont==1){ str_th = str_th+'<th class="sorting">'+col+'</th>'; }
   
   if(mn.length>0){
    if(col==mn){
	    var icon = (parseInt(str_val)==1 ? 'on':'off');
	    str_val = '<a href="#" title="Estado" class="status"><i class="fa fa-toggle-'+icon+'"></i></a> '+str_mn;
   	}
   }
   str_td = str_td+'<td>'+str_val+'</td>';
  } 
  str_tr = str_tr+'<tr>'+str_td+(mn.length==0 ? '<td>'+str_mn+'</td>':'')+'</tr>';  
 }
 //
 if(mn.length==0){
  str_th = str_th+'<th class="sorting"> </th>';
 } 
 //
 tbl = [str_th,str_tr,ncol];
 return tbl;
}

function htmlTagTableSimple(data){ 
 var tbl    = [];
 var cont   = 0; 
 var str_th = ''; 
 var str_tr = ''; 
 var str_td = ''; 
 for (var key in data){
  var getKey = data[key];
  var ncol   = Object.keys(getKey).length;
  cont++; 
  str_td = '';
  for(var col in getKey){
   if(cont==1){ str_th = str_th+'<th class="sorting">'+col+'</th>'; }
   str_td = str_td+'<td>'+getKey[col]+'</td>';
  } 
  str_tr = str_tr+'<tr>'+str_td+'</tr>'; 
 }
 //
 tbl = [str_th,str_tr,ncol];
 return tbl;
}

function htmlTagTableRadio(data,field,desc,fnc=''){
 var tbl    = [];
 var cont   = 0; 
 var str_th = ''; 
 var str_tr = ''; 
 var str_td = ''; 
 for (var key in data){
  var getKey = data[key];
  var id = getKey[field];
  var ncol = Object.keys(getKey).length;
  cont++; 
	 str_td ='';
  for(var col in getKey){
   if(cont==1){ str_th = str_th+'<th class="sorting">'+col+'</th>'; }
   var value = getKey[col];
   var str_elem = (col==field ? '<input type="radio" name="'+desc+'_row" id="desc-'+value+'" '+fnc+' value="'+value+'" style="cursor: pointer;"> ' : '');
   value    = (col==field ? '<label id=lbl-"'+value+'" for="desc-'+value+'" style="cursor: pointer;">'+value+'</label>' : value);
   str_td   = str_td+'<td>'+str_elem + value+'</td>';
  } 
  str_tr = str_tr+'<tr id="'+id+'">'+str_td+'</tr>'; 
 }    
 tbl = [str_th,str_tr,ncol];
 return tbl;
}

function htmlTagUlRadio(data,id){
 var str_icon = '<span class="handle ui-sortable-handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>';
 var str_li   = '';
 var str_ul   = '';
 $('#'+id).html('');
 if(data) {
  for (var key in data){
   var getKey = data[key];
   str_li = str_li + '<li>'+ str_icon +'<input type="radio" name="opc_row" onclick="getReport();" value="'+data[key]['id']+'" style="cursor: pointer;"><span class="text">'+data[key]['descripcion']+'</span> </li>';
  }
 }
 str_ul = '<ul class="todo-list ui-sortable">'+ str_li +'</ul>';
 $('#'+id).html(str_ul);
}

function exportToExcel(){
 var htmls = "";
 var uri = 'data:application/vnd.ms-excel;base64,';
 var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'; 
 var base64 = function(s) {
  return window.btoa(unescape(encodeURIComponent(s)))
 };
 var format = function(s, c) {
  return s.replace(/{(\w+)}/g, function(m, p) { return c[p];})
 };

 htmls = document.getElementById('tbl-result').innerHTML;

 var ctx = {
  worksheet : 'Reporte',
  table : htmls
 }

 var link = document.createElement("a");
 document.body.appendChild(link);
 link.href = uri + base64(format(template, ctx));
 link.download = "Reporte.xls";
 link.click();
}

function exportToPdf() {
  var doc  = new jsPDF('l', 'pt'); // landscape [l], portrait [p]
  var elem = document.getElementById("tbl-result");
  var res  = doc.autoTableHtmlToJson(elem);
  var header = function(data) {
    doc.setFontSize(18);
    doc.setTextColor(40);
    doc.setFontStyle('normal');
    //doc.addImage(headerImgData, 'JPEG', data.settings.margin.left, 20, 50, 50);
    var title = $('#title').html();
    doc.text(title, data.settings.margin.left, 50);
  };
  var options = {
   beforePageContent: header,
   margin: {
    top: 20
   },
   startY: 60//doc.autoTableEndPosY() + 20
  };

  doc.autoTable(res.columns, res.data, options);

  doc.save("reporte.pdf");
}

function getArrayData(data,lbl,dts){ 
 var array1 = [];
 var array2 = []; 
 for (var key in data){
  var getKey = data[key];
  array1.push(getKey[lbl]);
  array2.push(getKey[dts]);
 }
 //
 var arrays = {'label':array1,'data':array2};
 return arrays;
}

function getBarChart(id,lbl,d1,d2){  
    var areaChartData = {
      labels  : lbl,//['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label               : 'Electronics',
          fillColor           : 'rgba(210, 214, 222, 1)',
          strokeColor         : 'rgba(210, 214, 222, 1)',
          pointColor          : 'rgba(210, 214, 222, 1)',
          pointStrokeColor    : '#c1c7d1',
          pointHighlightFill  : '#fff',
          pointHighlightStroke: 'rgba(220,220,220,1)',
          data                : d1 //[65, 59, 80, 81, 56, 55, 40]
        }/*,
        {
          label               : 'Digital Goods',
          fillColor           : 'rgba(60,141,188,0.9)',
          strokeColor         : 'rgba(60,141,188,0.8)',
          pointColor          : '#3b8bba',
          pointStrokeColor    : 'rgba(60,141,188,1)',
          pointHighlightFill  : '#fff',
          pointHighlightStroke: 'rgba(60,141,188,1)',
          data                : d2 //[28, 48, 40, 19, 86, 27, 90]
        }*/
      ]
    } 
 
 
 //- BAR CHART -
    //-------------
    var barChartCanvas                   = $('#'+id).get(0).getContext('2d');
    var barChart                         = new Chart(barChartCanvas);
    var barChartData                     = areaChartData;
    barChartData.datasets[0].fillColor   = '#00a65a';
    barChartData.datasets[0].strokeColor = '#00a65a';
    barChartData.datasets[0].pointColor  = '#00a65a';
    var barChartOptions                  = {
      //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
      scaleBeginAtZero        : true,
      //Boolean - Whether grid lines are shown across the chart
      scaleShowGridLines      : true,
      //String - Colour of the grid lines
      scaleGridLineColor      : 'rgba(0,0,0,.05)',
      //Number - Width of the grid lines
      scaleGridLineWidth      : 1,
      //Boolean - Whether to show horizontal lines (except X axis)
      scaleShowHorizontalLines: true,
      //Boolean - Whether to show vertical lines (except Y axis)
      scaleShowVerticalLines  : true,
      //Boolean - If there is a stroke on each bar
      barShowStroke           : true,
      //Number - Pixel width of the bar stroke
      barStrokeWidth          : 2,
      //Number - Spacing between each of the X value sets
      barValueSpacing         : 5,
      //Number - Spacing between data sets within X values
      barDatasetSpacing       : 1,
      //String - A legend template
      legendTemplate          : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
      //Boolean - whether to make the chart responsive
      responsive              : true,
      maintainAspectRatio     : true
    }

    barChartOptions.datasetFill = false
    barChart.Bar(barChartData, barChartOptions);
  }
  
//
function getPriority(id){
 $('#'+id).html('');
 htmlTagOption(aDatos,id,'Seleccione prioridad');
}

function addRq(){
 if(!$('#frm-4').valid()){
   return;
 }
 //
 bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Esta seguro de GRABAR la información?</span>", function(result) {
  if (result){
   var data = JSON.stringify({
    "prj": $('#rq_prj').val(),
    "rq" : $('#rq_desc').val(), 
	   "rqt": $('#rq_type').val(),
	   "pri": $('#rq_pri').val()
   });
   $.ajax({
    type: "POST",
    url: strURL+'/rq/0',
	   data: data,
	   contentType: "application/json; charset=utf-8",
    dataType: "json",
    error: function (jqXHR, status) { alertJson(jqXHR);},
    success: function (data, status, jqXHR) { 
	    getProjectRq();
	    enableElem(0,1);
	    alertWindow('La información ha sido guardada...');
	   }
   });
  }
 });  
}

function delRq(t){
 $('#'+t).on('click', '.trash', function(){
  var row = $(this).parents('tr');
  //
  bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Esta seguro de eliminar la informacion?</span>", function(result) {
   var id = parseInt(row.find("td").eq(0).text());
   if(result){
    var rqst = $.ajax({
      type: 'DELETE',
	     url:  strURL+'/rq/'+id,
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      error: function(jqXHR, status){ alertJson(jqXHR);}
    });
    rqst.done(function( data ) { 
     row.eq(0).remove();
    });
   }
  });
 });
}

function enableRq(t){
 $('#'+t).on('click', '.status', function(){
  var row = $(this).parents('tr');
  var id = parseInt(row.find("td").eq(0).text());
	 var icon = '<i class="'+($(this).children().attr('class')=='fa fa-toggle-off' ? 'fa fa-toggle-on':'fa fa-toggle-off')+'"></i>';
  var cell = $(this);
  $.ajax({
    type: "PUT",
    url: strURL+'/rq/'+id+'/estado',
	   contentType: "application/json; charset=utf-8",
    dataType: "json",
	   error: function (jqXHR, status) { alertJson(jqXHR);},
    success: function (data, status, jqXHR) { 
	    cell.html(icon);
	   },
  });
 });
}

function updRq(id){
 if(!$('#frm-4').valid()){
  return;
 }
 bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Esta seguro de GRABAR las modificaciones?</span>", function(result) {
  if (result){
   var datos = JSON.stringify({
    "prj": $('#rq_prj').val(),
    "rq" : $('#rq_desc').val(), 
	   "rqt": $('#rq_type').val(),
	   "pri": $('#rq_pri').val()
   });
   $.ajax({
    type: "PUT",
    url: strURL+'/rq/'+id,
	   data: datos,
	   contentType: "application/json; charset=utf-8",
    dataType: "json",
    error: function (jqXHR, status) { alertJson(jqXHR);},
    success: function (data, status, jqXHR) { 
	    getProjectRq();
	    enableElem(1,1);
	    alertWindow('La información ha sido modificada...');
	   }
   });
  }
 }); 
}

function addRqAct(){
 if(!$('#frm-5').valid()){
  return;
 }
 bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Esta seguro de GRABAR la información?</span>", function(result) {
  if (result){
   var data = JSON.stringify({
    "prj" : $('#act_prj').val(),
    "rq"  : $('#act_rq').val(), 
    "act" : $('#act_desc').val(), 
	   "actp": $('#act_type').val(),
	   "pri" : $('#act_pri').val()
   });
   $.ajax({
    type: "POST",
    url: strURL+'/rq/actividad/0',
	   data: data,
	   contentType: "application/json; charset=utf-8",
    dataType: "json",
    error: function (jqXHR, status) { alertJson(jqXHR);},
    success: function (data, status, jqXHR) { 
	    getRqActivityCbo();
	    enableElem(0,2);
	    alertWindow('La información ha sido guardada...');
	   }
   });
  }
 });  
}

function delRqAct(t){
 $('#'+t).on('click', '.trash', function(){
  var row = $(this).parents('tr');
  //
  bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Esta seguro de eliminar la informacion?</span>", function(result) {
   var id = parseInt(row.find("td").eq(0).text());
   if(result){
    var rqst = $.ajax({
      type: 'DELETE',
	     url:  strURL+'/rq/actividad/'+id,
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      error: function(jqXHR, status){ alertJson(jqXHR);}
    });
    rqst.done(function( data ) { 
     row.eq(0).remove();
    });
   }
  });
 });
}

function updRqAct(id){
 if(!$('#frm-5').valid()){
  return;
 }
 bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Esta seguro de GRABAR las modificaciones?</span>", function(result) {
  if (result){
   var datos = JSON.stringify({
    "prj" : $('#act_prj').val(),
    "rq"  : $('#act_rq').val(), 
    "act" : $('#act_desc').val(), 
	   "actp": $('#act_type').val(),
	   "pri" : $('#act_pri').val()
   });
   $.ajax({
    type: "PUT",
    url: strURL+'/rq/actividad/'+id,
	   data: datos,
	   contentType: "application/json; charset=utf-8",
    dataType: "json",
    error: function (jqXHR, status) { alertJson(jqXHR);},
    success: function (data, status, jqXHR) { 
	    getRqActivityCbo();
	    enableElem(1,2);
	    alertWindow('La información ha sido modificada...');
	   }
   });
  }
 }); 
}

function enableElem(b,op){
 switch(op){
  case 1: ElemRq(b,rq_elem,'addRq();'); break;
  case 2: ElemRq(b,act_elem,'addRqAct();'); break;
  case 3: ElemRq(b,rcs_elem,'addRcs();'); break;
 }
}

function ElemRq(b,array,str_f){
 var func = (b==0 ? str_f:'');
 for(var x=0;x<array.length;x++){
  var str = '#'+array[x];
  $(str).prop('disabled',b);
  $(str).val('');
  if(b==0 && x==0){$(str).focus();}
 }
 $('#btn-sv').prop('disabled',b);
 $("#btn-sv").attr('onclick', func); 
 ClearSelRow();
}

function selRowTable(t,op,array,fnc){
 $('#'+t).on('click', '.edit', function(){
  var row = $(this).parents('tr');
  rowTable(row,0,op,array,fnc);
 });
 //0
 $('#'+t).on('click', '.cancel', function(){
  var row = $(this).parents('tr');
  rowTable(row,1,op,array,fnc);   
 });
} 

function rowTable(row,b,op,array,str_f){ 
 var id = parseInt(row.find("td").eq(0).text());
 enableElem(b,op);
 switch(b){
  case 0:
   var colortxt='#205B92';
   var color='#E6E6E6'; 
   var tipo='bold';
   var icon='<i class="fa fa-check"></i> ';
   var func=str_f+'('+id+');';
   row.find("td").each(function (x){
    switch(op){
     case 3:
      var bcond=(x==2 || x==3); 
      var bchange = (x==2);
      var pos = x-2;
      break;
     default: 
      var bcond=(x!=0 && x<=array.length); 
      var pos = x-1;
      var bchange = false;
      break;
    }
	   if(bcond){
	    var str = '#'+array[(pos)];
     var value=$(this).text();
     var tag = $(str)[0].tagName;
	    switch(tag){
	     case 'INPUT': $(str).val(value); break;
	     case 'SELECT':
	      SelecOptionStr(array[(pos)],value); 
	      if(bchange) {$(str).trigger("change");}
	      break;
	    }
	   } 
   });  
   break;
  case 1: 
   var colortxt='';
   var color=''; 
   var tipo='';
   var icon='';
   var func='';  
   break;
 }
 row.css('backgroundColor',color); 
 row.css('fontWeight',tipo);
 row.css('color',colortxt); 
 row.find("td").eq(0).html(icon+id);
 $('#btn-sv').attr('onclick', func); 
 //	
}

function SelecOptionStr(id,sop){
 var ds=document.getElementById(id);
 var str=sop.split(','); 
 for (var i=0;i<(ds.options.length);i++) {
  var opt = ds.options[i];
  for (var j=0;j<str.length;j++){
   if ( str[j]==opt.text ) {
    opt.selected = true;
   }
  }
 }
}

function ClearSelRow(){
 var icon='<i class="fa fa-check"></i> ';
 $('#tbl-list-body tr').each(function () {
  $(this).css('backgroundColor',''); 
  $(this).css('fontWeight','');
  $(this).css('color','');
  var str =  $(this).find('td').eq(0).html();
  str=str.replace(icon,'');
  $(this).find('td').eq(0).html(str);
 });
}
