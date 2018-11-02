var strURL   = "//"+location.hostname+"/_wsrest/v1/indra/requerimiento";

function frmValidate(id){
 switch(id){
  case 'frm': frm(id); break;
  case 'frm-1': frm1(id); break;
  case 'frm-2': frm2(id); break;
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
      'opc_ind[]': { required: 'elegir una opción ' },
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
   case 'frm': getFrm(); break;
   case 'frm-1': getFrm1(); break;
   case 'frm-2': getFrm2(); break;
  }
 });  
}

function getFrm(){
 var datos =  getInfoFrm();
 $('#div-responsive').html('');
 $.ajax({
       url:  strURL+'/indicador/informe/'+datos,
       type: 'GET',
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       beforeSend: function () {
        $('#div-responsive').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
       },
       error: function(jqXHR, status){
         alert(jqXHR.statusText);
       },
       success:function(data, status, jqXHR){ 
         $('#div-responsive').html(data['tbl']);
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

function getFrm1(){
 var datos =  getInfoFrm1();
 $('#div-responsive').html('');
 $('#div-responsive-2').html('');
 $('#div-data').hide();
 clearLbl();
 $.ajax({
       url:  strURL+'/rq/lista/'+datos,      
       type: 'GET',
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       beforeSend: function () {
        $('#div-responsive').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
       },
       error: function(jqXHR, status){
         alert(jqXHR.statusText);
       },
       success:function(data, status, jqXHR){ 
         $('#div-responsive').html(data['tbl']);  
       }
    });     
}

function getFrm2(){
 var datos =  getInfoFrm2();
 $('#div-responsive').html('');
 $('#div-responsive-2').html('');
 $('#div-data').hide();
 clearLbl();
 $.ajax({
       url:  strURL+'/proyecto/lista/'+datos, 
       type: 'GET',
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       beforeSend: function () {
        $('#div-responsive').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
       },
       error: function(jqXHR, status){
         alert(jqXHR.statusText);
       },
       success:function(data, status, jqXHR){ 
         $('#div-responsive').html(data['tbl']);  
       }
    });     
}

function elemDatePicker(id){
  //$('#'+id).daterangepicker({format:'YYYY-MM-DD'});
  $('#'+id).daterangepicker({locale: { format: 'DD/MM/YYYY' }});
}

function getInfo(id){
 var datos = ''; 
 var b = 1;
 switch(id){
  case 'opc_actv': var path = '/rol/tipo/'+$('#opc_rol').val(); break;
  case 'opc_rol' : var path = '/rol/lista'; break;
 }
 $('#'+id).html('');
 $.ajax({
    type: "GET",
    url: strURL+path,
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) { 
     if(data){
      $('#'+id).html(data['opt']);  
     //listInfoCbo(data,id,b);  
     }
    },
    error: function (jqXHR, status) { alert(jqXHR); }
 });
}

function clearCbo(id,b){
 $('#'+id).html('');
 if(b){
 $('#'+id).append($('<option>', { value: 0, text: '' }));}
}

function listInfoCbo(data,id,b){
 clearCbo(id,b);
 if(data) {
  cant = (data.length);
  for (var key in data){
   var getKey = data[key];
   $('#'+id).append($('<option>', {
    value: data[key]['id'],
    text: ' '+data[key]['descripcion']
    //class: 'fa fa-square-o'
   })); 
  }
 }
}

function listCheck(id){
 var strHtml = '<b>Tipo de Indicador</b><br/>';
 $.ajax({
    type: "GET",
    url: strURL+'/indicador/tipo',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) {  
   if(data) {
      for (var key in data){
       var getKey = data[key];
       strHtml = strHtml + '<div class="checkbox"> <label> <input type="checkbox" id="opc_ind" name="opc_ind[]" value="'+data[key]['id']+'"/>'+data[key]['descripcion']+'</label> </div>';
      }
    $('#'+id).html(strHtml);
     }
  },
    error: function (jqXHR, status) { alert(jqXHR); }
 });
}

function listOption(id){
 var strHtml = '<b>Tipo de Reporte</b><br/>';
 $.ajax({
    type: "GET",
    url: strURL+'/reporte/tipo',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data, status, jqXHR) {  
     if(data) {
      for (var key in data){
       var getKey = data[key];
       strHtml = strHtml + '<div class="checkbox"> <label> <input type="radio" id="opc_rep_'+data[key]['id']+'" name="opc_rep" value="'+data[key]['id']+'"/> '+data[key]['descripcion']+'</label> </div>';
      }
      $('#'+id).html(strHtml);
     }
    },
    error: function (jqXHR, status) { alert(jqXHR); }
 });
}


function getInfoFrm(){
 var valind = getValuesElem('input[name="opc_ind[]"]:checked');
 var ind = getValuesElem('input[name="opc_ind[]"]:checked');
 var rol = $('#opc_rol').val();
 var act = $('#opc_actv').val();
 var dd  = $.trim($('#opc_date').val().replace(/-/g,','));
     dd  = $.trim(dd.replace(/[/]/g,'-')); 
 var str = ind+'/'+rol+'/'+act+'/'+dd;      
 return str; 
}

function getInfoFrm1(){
 var tp = $('input:radio[name=opc_tp]:checked').val();
 var ds = $('#opc'+tp+'_txt').val();
 var rq = $('#opc_rq').val();
 var str = tp+'/'+ds+'/'+rq;
 return str; 
}

function getInfoFrm2(){
 var tp  = $('input:radio[name=opc_tp]:checked').val();
 var trp = $('input:radio[name=opc_rep]:checked').val();
 var dd  = $.trim($('#opc_date').val().replace(/-/g,','));
     dd  = $.trim(dd.replace(/[/]/g,'-'));
 var ds  = $('#opc'+tp+'_txt').val();
 var str = trp+'/'+tp+'/'+ds+'/'+dd;
 return str; 
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

function optOnClick(){ 
  var tp = $('input:radio[name=opc_row]:checked').val();
  $('#div-responsive-2').html('');
  $('#div-data').hide();
  getRowInfo(tp);
  $.ajax({
    url:   strURL+'/rq/actividad/'+tp,      
    type: 'GET',
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    beforeSend: function () {
     $('#div-responsive-2').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
    },
    error: function(jqXHR, status){
     alert(jqXHR.statusText);
    },
    success:function(data, status, jqXHR){ 
     $('#div-responsive-2').html(data['tbl']);
     optOnClickAct('act_row');
    }
  });   
}

function optOnClickRep(){ 
  var tp = $('input:radio[name=opc_row]:checked').val();
  var trp = $('input:radio[name=opc_rep]:checked').val();
  $('#div-responsive-2').html('');
  $('#div-data').hide();
  //getRowInfo(tp);
  $.ajax({
       url:  strURL+'/reporte/informe/'+trp+'/'+tp,  
       type: 'GET',
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       beforeSend: function () {
        $('#div-responsive-2').html('<tr> <td colspan=4 class="text-center"> '+ Iconload[1] +' Procesando, espere por favor...</td></tr>');
       },
       error: function(jqXHR, status){
         alert(jqXHR.statusText);
       },
       success:function(data, status, jqXHR){ 
         $('#div-responsive-2').html(data['tbl']);
         $('#tbl-result').DataTable({
          'paging'      : true,
          'lengthChange': false,
          'searching'   : false,
          'ordering'    : true,
          'info'        : true,
          'autoWidth'   : false
         });
       }
    });   
}

function clearLbl(){
 $('#lb-cs').html('');
 $('#lb-rq').html('');
 //$('#lb-st').html('');
 $('#lb-d1').html('');
 $('#lb-d2').html('');
}

function getRowInfo(id){
 $('#lb-cs').html(': '+$('#cs-'+id).val());
 $('#lb-rq').html(': '+$('#'+id).children().eq(1).text());
 //$('#lb-st').html(': '+$('#'+id).children().eq(6).text());
 $('#lb-d1').html(': '+$('#'+id).children().eq(2).text());
 $('#lb-d2').html(': '+$('#'+id).children().eq(3).text());
 $('#div-data').show();
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

function getEstimation(id){
 var rq  = $('input:radio[name=opc_row]:checked').val();
 var act = $('input:radio[name=act_row]:checked').val();
 var dt  = $('#datepicker').val().replace(/[/]/g, '-');
 if(!act || !dt){
  alertWindow("<span class='dark-gray'> Debe Seleccionar Actividad y/o fecha</span>");
  return;
 }
 $.ajax({
       url:  strURL+'/estimacion/'+act+'/'+dt,      
       type: 'GET',
       contentType: 'application/json; charset=utf-8',
       dataType: 'json',
       beforeSend: function () {
        $('#lbl-'+act).html(Iconload[0]);
       },
       error: function(jqXHR, status){
         alertWindow("<span class='dark-gray'>"+jqXHR.statusText+"</span>");
       },
       success:function(data, status, jqXHR){ 
         optOnClick();
         $('#datepicker').val('');
         alertWindow("<span class='dark-gray'>"+data['msg']+"</span>");
       }
    });
}

function getEstimationEdit(id){
  bootbox.confirm(IconMsj + " <br/> <span class='dark-gray'>¿Estas seguro de modificar los datos?</span>", function(result) {
   if (result) {
     getEstimation(id);  
   }
  });
}

