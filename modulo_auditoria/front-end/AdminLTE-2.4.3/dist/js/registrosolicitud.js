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
    var obinicial;
    var iplocal="localhost";
  var ip="localhost:84/Auditoria";
     var anio="2019";
      var listadoProcesos=[];
       $(document).ready(function() {
  
        $('#textAsunto').text('');
        $('#textMotivo').text('');
          var date = new Date();
      
          $('#textFecCre').val( date.getDate() + '/' + (date.getMonth() + 1) + '/' +  date.getFullYear()) 
  
           $.ajax({
               type:"GET",
               url: "http://"+ip+"/solicitudregistro/init",
               dataType: "json",
               success: function(xvr){
                obinicial=xvr;
                   var opt = "";
                   $('#textSolicitante').val(xvr.solicitante.nombres);
                   $('#textIdSolic').val(xvr.solicitante.empleadoId);
                  $.each(xvr.procesos,function (index,item) {
                    opt = opt + '<option value="'+item.procesoId+'">' + item.nombre + '</option>';
                  })
                  $('#textProceso').html(opt);
               },
               error: function(xvr){
                   var codig=JSON.parse(xvr.responseText)
                    if(codig.error.mensaje){
                        alert(codig.error.mensaje);
                        window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/listasolicitud.html";
                    }
               }
           })
          
     
  
          
          $('#popupGenerar').click(function(){
              debugger;
              if($('#textProceso').val().length==0){
                      alert('Debe seleccionar algún proceso.');
                      return false;
              }
              
              var tempObj={
                "id": 0,
                "asunto": $('#textAsunto').val(),
                "prioridad": $('#textPrioridad').val(),
                "estadoid": 0,
                "estado": $('#textEstado').val(),
                "fecharegistro": $('#textFecCre').val(),
                "motivo": $('#textMotivo').val(),
                "proceso": $('#textProceso').val(),
                "solicitante": $('#textIdSolic').val()               
              }

              tempObj = {
                  "periodo":anio,
                  "motivo":$('#textMotivo').val(),
                    "asunto": $('#textAsunto').val(),
                    "prioridad": $('#textPrioridad').val(),
                    "proceso": {
                        "procesoId":$('#textProceso').val()
                    },
                    "solicitante":{
                        "empleadoId": $('#textIdSolic').val()
                    },
                    "actareunion":obinicial.actareunion
                }
              
              var jsontext=JSON.stringify(tempObj);
              $.ajax({
                       type:"POST",
                       url: "http://"+ip+"/solicitudregistro",
                       dataType: "json",
                       contentType: "application/json; charset=utf-8",
                       data: jsontext,
                       success: function(xvr){
                        alert('Se registro la Solicitud satisfactoriamente (id: '+xvr.id+').');
                        window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/listasolicitud.html";
                           
                           
                       },
                       error: function(err){
                           alert(err)
                           
                       }
                   })
              
  
          })
  
        
          
      } );
      
      
      
  })
  