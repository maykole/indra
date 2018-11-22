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
  var ip="ec2-18-223-99-234.us-east-2.compute.amazonaws.com:8081/Auditoria";
     var anio="2019";
      var listadoProcesos=[];
       $(document).ready(function() {
  
          var date = new Date();
      
          $('#textFecCre').val( date.getDate() + '/' + (date.getMonth() + 1) + '/' +  date.getFullYear()) 
  
           $.ajax({
               type:"GET",
               url: "http://"+ip+"/solicitudregistro/",
               dataType: "json",
               success: function(xvr){
                   
                  
                   var tdtd="";
                  $.each(xvr,function (index,item) {
                    tdtd= tdtd + '<tr><td>' + item.asunto + '</td><td>' + item.estado + '</td><td>' + item.fecharegistro + '</td><td><input type="button" onclick="abrirev('+item.id+');" value="Evaluar" /></td></tr>';
                  })
                  tdtd=tdtd+"";
                  $('#listasolicitud tbody').html(tdtd);
               },
               error: function(){
                   
               }
           })
          
     
  
          
          $('#popupGenerar').click(function(){
              debugger;
              window.location.href = "http://"+iplocal+"/indra/AdminLTE-2.4.3/registrosolicitud.html";
            
              
  
          })
  
         
          
      } );
      
      
      
  })
  function abrirev(id) {
    debugger;
    window.location.href = "http://ec2-18-223-99-234.us-east-2.compute.amazonaws.com/indra/AdminLTE-2.4.3/evaluarsolicitud.html?id="+id;
}