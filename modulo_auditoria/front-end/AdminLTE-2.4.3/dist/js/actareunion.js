/**
 * AdminLTE Demo Menu
 * ------------------
 * You should not use this file in production.
 * This file is for demo purposes only.
 */
$(function () {
    'use strict'
  
    /**
     * Get access to plugins
     */
    var ip="localhost";
     var anio="2019";
      var listadoProcesos=[];
       $(document).ready(function() {
  
          var date = new Date();
      
          $('#textFecCre').val( date.getDate() + '/' + (date.getMonth() + 1) + '/' +  date.getFullYear()) 
  
           $.ajax({
               type:"GET",
               url: "http://"+ip+":84/Auditoria/actareunion/init",
               dataType: "json",
               success: function(xvr){
                   
                   var opt = "";
                   var optpro = ""; 
                  $.each(xvr.asistentes,function (index,item) {
                    opt = opt + '<option value="'+item.nombres+'">' + item.empleadoId + '</option>';
                  })
                  $('#browsers').html(opt);

                  $.each(xvr.procesos,function (index,item) {
                    optpro = optpro + '<option value="'+item.nombre+'">' + item.procesoId + '</option>';
                  })
                  $('#procesos').html(optpro);
               },
               error: function(){
                   
               }
           })
          
     
           $('#addAsistente').click(function () {
               if($('#asistenteid').val()==''){
                   alert('Seleccione un Asistente');
                   return false;
               }
               var lista = $('#browsers').find('option[value="' + $('#asistenteid').val() + '"]');

               var cadena="<tr><td id="+$(lista).text()+">"+$('#asistenteid').val()+"</td><td style='text-align:center;'><input type='button' class='btn' onclick='quitarAsis(this);' value='-'/></td></tr>"
               $('#listaAsistente tbody').append(cadena);
               $('#asistenteid').val('');
           })

           $('#addProceso').click(function () {
            if($('#procesoid').val()==''){
                alert('Seleccione un Proceso');
                return false;
            }
            var lista = $('#procesos').find('option[value="' + $('#procesoid').val() + '"]');

            var cadena="<tr><td id="+$(lista).text()+">"+$('#procesoid').val()+"</td><td style='text-align:center;'><input type='button' class='btn' onclick='quitarProceso(this);' value='-'/></td></tr>"
            $('#listaProceso tbody').append(cadena);
            $('#procesoid').val('');
        })
          
          $('#popupGenerar').click(function(){
              
              if($('#textProceso').val().length==0){
                      alert('Debe seleccionar algún proceso.');
                      return false;
              }
              
              var tempObj={
                "id": 0,
                "asunto": $('#textAsunto').html(),
                "prioridad": $('#textPrioridad').val(),
                "estadoid": 0,
                "estado": $('#textEstado').val(),
                "fecharegistro": $('#textFecCre').val(),
                "motivo": $('#textMotivo').html(),
                "proceso": $('#textProceso').val(),
                "solicitante": $('#textSolicitante').val()               
              }

              
              
              var jsontext=JSON.stringify(tempObj);
              $.ajax({
                       type:"POST",
                       url: "http://"+ip+":84/Auditoria/solicitudregistro",
                       dataType: "json",
                       contentType: "application/json; charset=utf-8",
                       data: jsontext,
                       success: function(xvr){
                           debugger;
                           var cadena2="";
                           var temobj=[];
                           
                           
                       },
                       error: function(err){
                           alert(err)
                           
                       }
                   })
              
  
          })


        })
  })
  
function quitarAsis(tr) {
    debugger;
    var i = tr.parentNode.parentNode.rowIndex;
    document.getElementById("listaAsistente").deleteRow(i);
}
function quitarProceso(tr) {
    debugger;
    var i = tr.parentNode.parentNode.rowIndex;
    document.getElementById("listaProceso").deleteRow(i);
}