/**
 * AdminLTE Demo Menu
 * ------------------
 * You should not use this file in production.
 * This file is for demo purposes only.
 */
$(function () {
    'use strict'
  debugger;
    /**
     * Get access to plugins
     */
    var iplocal="ec2-18-223-99-234.us-east-2.compute.amazonaws.com";
    var ip="ec2-18-223-99-234.us-east-2.compute.amazonaws.com:8081/Auditoria";
     var anio="2018";
      var listadoProcesos=[];
       $(document).ready(function() {
  
           var date = new Date();
      
          $('#textFecCre').val( date.getDate() + '/' + (date.getMonth() + 1) + '/' +  date.getFullYear()) 

           $.ajax({
               type:"GET",
               url: "http://"+ip+"/plananual/"+anio+"/planAuditoria",
               dataType: "json",
               success: function(xvr){
                debugger;
                  
                   var tdtd="";
                  $.each(xvr,function (index,item) {
                    tdtd= tdtd + '<tr><td>' + item.alcance + '</td><td>' + item.descripcion + '</td><td>' + item.fechaCreacion + '</td><td><input type="button" onclick="abrirev('+item.id+');" value="Actualizar" />&nbsp;&nbsp;&nbsp;<input type="button" onclick="abrirev2('+item.id+');" value="Ejecutar" />&nbsp;&nbsp;&nbsp;<input type="button" onclick="abrirev3('+item.id+');" value="Concluir" />&nbsp;&nbsp;&nbsp;<input type="button" onclick="abrirev4('+item.id+');" value="Eliminar" /></td></tr>';
                  })
                  tdtd=tdtd+"";
                  $('#listasolicitud tbody').html(tdtd);
               },
               error: function(err){
                   debugger;
               }
           })
          
     
  
          
          $('#popupGenerar').click(function(){
              debugger;
              window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/registrosolicitud.html";
            
              
  
          })
  
         
          
      } );
      
      
      
  })

  var iplocal="localhost";
  var ip="localhost:8081/Auditoria";

  function abrirev(id) {
    debugger;
    window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/planespecifico.html?id="+id;
}

function abrirev2(id) {
    debugger;
    window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/planespecificoejecutar.html?id="+id;
}

function abrirev3(id) {
    debugger;
    window.location.href = "http://"+iplocal+"/tp3indra/modulo_auditoria/front-end/AdminLTE-2.4.3/planespecificoconclusion.html?id="+id;
}

function abrirev4(id) {
    debugger;
    $.ajax({
        type:"DELETE",
        url: "http://"+ip+"/planAuditoria/"+id,
        dataType: "json",
        success: function(xvr){
         debugger;
           
           alert('Se elimino el proceso');
           location.reload()
        },
        error: function(err){
            debugger;
            location.reload()
        }
    })

    // window.location.href = "http://"+ip+"/planAuditoria/"+id;
    // location.reload();
}