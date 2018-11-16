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
	var inicial;
var objinicial;
var objActividadSt;
	
	 $(document).ready(function() {

		$('.btnmodal').on('click',function (params) {
			console.log($(this));
			$('#codemodal').modal('show');
		})
			
		var idproceso = getUrlParameter('id');
		var date = new Date();
	
		$('#textFecCre').val( date.getDate() + '/' + (date.getMonth() + 1) + '/' +  date.getFullYear()) 
debugger;
		 $.ajax({
			 type:"GET",
			 url: "http://"+ip+"/planAuditoria/"+idproceso,
			 dataType: "json",
			 success: function(xvr){
				//  if(xvr==0){
						
				// 		$('#popupGenerar').hide();
				//  }
				//  else if(xvr==1){
					$('#textAlcance').val(xvr.alcance);
					$('#txtFecIni').val(xvr.fechaInicio);
					$('#txtFechaFin').val(xvr.fechaInicio);
					$('#txtRespo').val('Eduardo Bonilla');
					$('#textObje').text(xvr.objetivo);
					inicial=xvr;
					var opt="";
						$('#procesoid').html(xvr.descripcion);
						$.each(xvr.planprocedimientos,function (index,item) {
							
							$.each(item.planactividades,function (index2,item2) {
								if(item2.auditar == true){
									var verdadero=item2.auditado==true?"SI":"NO";
								opt=opt+'<tr><td data-id="'+item.planprocedimientoId+'">'+item.descripcion+'</td>';
								opt=opt+'<td data-idact="'+item2.planactividadId+'">'+item2.descripcion+'</td><td><div class="input-group date datetimepicker1"><input type="text" disabled value="'+item2.fecha.substring(0,10)+'" class="form-control" /></div></td>';
								//opt=opt+'<td class="text-center"><input type="checkbox"  value="Ejecución" /><input type="hidden" data-idplan="'+item.planprocedimientoId+'"  data-idact="'+item2.planactividadId+'" class="inputhidden" /></td>';
								opt=opt+'<td class="text-center"><span>'+verdadero+'</span><input type="hidden" data-idplan="'+item.planprocedimientoId+'"  data-idact="'+item2.planactividadId+'" class="inputhidden" /></td>';
								opt=opt+'</tr>';
								}
							})
							
							
						})

						$('#listaProceso tbody').html(opt);

					

						$('.btnmodal').on('click',function (params) {
							console.log($(this).parent());
							$('#codemodal').modal('show');
							
							var idproc = $($(this).parent().children()[1]).attr('data-idplan');
							var idact = $($(this).parent().children()[1]).attr('data-idact');
							var objAct;
							var objProc;
							$.each(inicial.planprocedimientos,function (index2,item2) {
								if(item2.planprocedimientoId == idproc){
									objProc = item2;
									$.each(item2.planactividades,function (index3,item3) {
										if(item3.planactividadId == idact){
											objAct = item3;
											objActividadSt = item3;
											return false;
										}
									})
									//objtempo.push(objts);
								}					
							})
							 
							$('#textProcMod').val(objProc.descripcion);
							$('#txtFecMod').val(objAct.fecha.substring(0,10));
							$('#txtRespoMod').val('Eduardo Bonilla');
							$('#txtActMod').val(objAct.descripcion);


						})
					
			 },
			 error: function(){
				 
			 }
		 })
		 
	var objtempo=[];
		 $('#idGrabar').click(function () {
			if($("input:radio[name ='optFin']:checked").val() == undefined){
				alert('Debe marcar SI/NO cumplió!');
				return false;
			}

			var objenviar = {
				"id": inicial.id,
				"resultado": $("input:radio[name ='optFin']:checked").val(),
				"observacion": $('#textObsFin').val()
			}

			var jsontext=JSON.stringify(objenviar);
			$.ajax({
				type:"PUT",
				url: "http://"+ip+"/planAuditoria/"+inicial.id+"/concluir",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				data: jsontext,
				success: function(xvr){
					debugger;
					if(xvr==1){
						alert('Se concluyo la Actividad!')
						location.reload();
					}
					
				},
				error: function(err){
					debugger
				}
			})


		 })


	
		$('#popupGenerar').click(function(){
				$('#code').modal('show');
				$('#listaProceso tbody').html('');
				listadoProcesos=[];
				$.ajax({
				type:"GET",
				url: "http://"+ip+"/initPlanAnual/"+anio,
				dataType: "json",
				success: function(xvr){
					debugger;
					if(xvr!=undefined){
						$('#textPeriodo').val(xvr.periodo);
						$('#textFecCre').val(xvr.fechaCreacion);
						$('#textGenPor').val(xvr.creadoPor);
						if(xvr.procesos.length>0){
							var cadena="";
							$.each(xvr.procesos,function(index, item){
								cadena=cadena+'<option texto="'+item.procesoId+'" value="'+item.procesoId+'">'+item.nombre+'</option>';
							})
							$('.browsers').html(cadena)
						}
					}
					
				},
				error: function(){
					
				}
			})

			
		});
		
	
		

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

// modalejecutar = function (params) {
// 	$('#codemodal').modal('show');
// }
$('#datetimepicker1').datepicker();