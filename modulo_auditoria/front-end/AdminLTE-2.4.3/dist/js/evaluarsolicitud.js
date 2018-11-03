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
    var iplocal="192.168.43.49";
  var ip="192.168.43.56:8081";
     var anio="2019";
      var listadoProcesos=[];
       $(document).ready(function() {
  
          var date = new Date();
          var idid=getUrlParameter('id');

          $('#textfecev').val( date.getDate() + '/' + (date.getMonth() + 1) + '/' +  date.getFullYear()) 
  
           $.ajax({
               type:"GET",
               url: "http://"+ip+"/solicitudregistro/"+idid,
               dataType: "json",
               success: function(xvr){
                   
                    var opt = "";
                    $('#textComentario').text('');
                   $('#textSolicitante').val(xvr.solicitante.nombres);
                   $('#textIdSolic').val(xvr.solicitante.empleadoId);
                 
                  $('#textProceso').val(xvr.proceso.nombre);
                  $('#textPrioridad').val(xvr.prioridad);
                  $('#textAsunto').val(xvr.asunto);
                  $('#textMotivo').val(xvr.motivo);
                  $('#textEstado').val(xvr.estado);
                  $('#textFecCre').val(xvr.fecharegistro);
               },
               error: function(){
                   
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
                    "asunto": $('#textAsunto').val(),
                    "prioridad": $('#textPrioridad').val(),
                    "proceso": {
                        "procesoId":$('#textProceso').val()
                    },
                    "solicitante":{
                        "empleadoId": $('#textIdSolic').val()
                    }
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
                        location.reload()
                           
                           
                       },
                       error: function(err){
                           alert(err)
                           
                       }
                   })
              
  
          })
  
          
        

        
          
      } );
      
      
      var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;
    
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
    
            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };
  })
  