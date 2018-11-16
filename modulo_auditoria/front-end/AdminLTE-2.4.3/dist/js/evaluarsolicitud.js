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
    var iplocal="ec2-18-223-99-234.us-east-2.compute.amazonaws.com";
  var ip="ec2-18-223-99-234.us-east-2.compute.amazonaws.com:84/Auditoria";
     var anio="2019";
      var listadoProcesos=[];
       $(document).ready(function() {
  
          var date = new Date();
          var idid=getUrlParameter('id');
$('#textComentario').text('');
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
              if($('#textComentario').val()==''){
                      alert('Ingrese comentario.');
                      return false;
              }
              
              var tempObj={
                "estadoid": 2,               
                
              }

              
              var idid=getUrlParameter('id');
              var urlws="http://"+ip+"/solicitudregistro/evaluar/"+idid;
              var jsontext=JSON.stringify(tempObj);
              $.ajax({
                       type:"PUT",
                       url: urlws,
                       dataType: "json",
                       contentType: "application/json; charset=utf-8",
                       data: jsontext,
                       success: function(xvr){
                           if(xvr.bevaluar)
                        {alert('Aprobado');
                    
                        window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/listasolicitud.html";
                       }       
                           
                       },
                       error: function(err){
                           alert(err)
                           
                       }
                   })
              
  
          })
  
          $('#btnrechazar').click(function(){
            debugger;
            if($('#textComentario').val()==''){
                    alert('Ingrese comentario.');
                    return false;
            }
            
            var tempObj={
              "estadoid": 3,               
              
            }

            
            var idid=getUrlParameter('id');
            var urlws="http://"+ip+"/solicitudregistro/evaluar/"+idid;
            var jsontext=JSON.stringify(tempObj);
            $.ajax({
                     type:"PUT",
                     url: urlws,
                     dataType: "json",
                     contentType: "application/json; charset=utf-8",
                     data: jsontext,
                     success: function(xvr){
                         if(xvr.bevaluar)
                      {alert('Rechazado');
                  
                      window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/listasolicitud.html";
                     }       
                         
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
  