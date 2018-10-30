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
              debugger;
              if($('#listaAsistente tbody tr').length==0){
                    alert('Debe seleccionar algún Asistente.');
                    return false;
              }

              if($('#listaProceso tbody tr').length==0){
                    alert('Debe seleccionar algún Proceso.');
                    return false;
                }   
              
                var asistenteobj=[];
                $.each($('#listaAsistente tbody tr'),function (index,item) {
                    var objtemp={
                        "empleadoId": $(item).find('td')[0].id,
                        "nombres": $($(item).find('td')[0]).text(),
                        "cargo": ""
                    }
                    asistenteobj.push(objtemp);
                }) 

                var procesoobj=[];
                $.each($('#listaProceso tbody tr'),function (index,item) {
                    var objtemp={
                        "procesoId": $(item).find('td')[0].id,
                        "nombred": $($(item).find('td')[0]).text(),
                    }
                    procesoobj.push(objtemp);
                }) 

              var tempObj={
                "titulo":$('#textAsunto').val(),
                "descripcion":$('#textMotivo').val(),
                "lugar":$('#textLugar').val(),
                "conclusiones":$('#textConclusion').html(),
                "acuerdos":$('#textAcuerdo').html(),
                "asistentes": asistenteobj,
                "procesos": procesoobj
            }

              
              
              var jsontext=JSON.stringify(tempObj);
              $.ajax({
                       type:"POST",
                       url: "http://"+ip+":84/Auditoria/actareunion",
                       dataType: "json",
                       contentType: "application/json; charset=utf-8",
                       data: jsontext,
                       success: function(xvr){
                           
                           alert('Se registro el Acta de Reunión satisfactoriamente (id: '+xvr.id+').');
                           location.reload()
                           
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