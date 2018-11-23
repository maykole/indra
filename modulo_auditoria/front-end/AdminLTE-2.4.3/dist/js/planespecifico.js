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
					$('#procesoid').html(xvr.descripcion);
					$('#textAlcance').val(xvr.alcance);
					$('#txtFecIni').val(xvr.fechaInicio);
					$('#txtFechaFin').val(xvr.fechaInicio);
					$('#txtRespo').val('Eduardo Bonilla');
					$('#textObje').text(xvr.objetivo);
					inicial=xvr;
					var opt="";
						$.each(xvr.planprocedimientos,function (index,item) {
							
							$.each(item.planactividades,function (index2,item2) {
								opt=opt+'<tr><td data-id="'+item.planprocedimientoId+'">'+item.descripcion+'</td>';
								opt=opt+'<td data-idact="'+item2.planactividadId+'">'+item2.descripcion+'</td><td><div class="input-group date datetimepicker1"><input type="text" class="form-control" /><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></div></td>';
								opt=opt+'<td class="text-center"><input type="checkbox"  value="Ejecución" /><input type="hidden" data-idplan="'+item.planprocedimientoId+'"  data-idact="'+item2.planactividadId+'" class="inputhidden" /></td>';
								
								//opt=opt+'<td class="text-center"><input type="button" class="btnmodal btn btn-default"  value="Ejecución" /></td>';
								opt=opt+'</tr>';
							})
							
							
						})

						$('#listaProceso tbody').html(opt);

						
						$('.datetimepicker1').datepicker("setDate" , "7/11/2018");

						$('.btnmodal').on('click',function (params) {
							console.log($(this));
							$('#codemodal').modal('show');
						})
					
				//$('#popupGenerar').html('Generar a Plan Anual');

				
				//listadoProcesos.push(objTemp);
				
			


				//  }
				//  else if(xvr==2){
						
				// 		$('#popupGenerar').html('Volver a Generar Plan Anual');
				//  }
			 },
			 error: function(){
				 
			 }
		 })
		 
	var objtempo=[];
		 $('#idGrabar').click(function () {
			 var listadotd = $('#listaProceso tbody tr').find('input[type=checkbox]:checked').parent();
			 objtempo=[];

			if(listadotd.length == 0 ){
				alert('Se debe seleccionar por lo menos una actividad!');
				return false;
			}

			 var objts={
							
				"planactividades": []
			}
			 $.each(listadotd,function (index,item) {
				debugger;
				var idproc = $($(item).children()[1]).attr('data-idplan')
				var idact = $($(item).children()[1]).attr('data-idact')
				$.each(inicial.planprocedimientos,function (index2,item2) {
					if(item2.planprocedimientoId == idproc){
					
						$.each(item2.planactividades,function (index3,item3) {
							if(item3.planactividadId == idact){
								item3.auditar = true;
								item3.fecha = '20181110';
								objts.planactividades.push(item3)
							}
						})
						//objtempo.push(objts);
					}					
				})
			 })
			 console.log(objtempo)
			 objtempo={
				"id": inicial.id,
				"alcance":inicial.alcance,
				"objetivo":inicial.objetivo,
				"planprocedimientos":[objts]
			}

			var jsontext=JSON.stringify(objtempo);
			$.ajax({
				type:"PUT",
				url: "http://"+ip+"/planAuditoria/"+inicial.id+"/iniciar",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				data: jsontext,
				success: function(xvr){
					debugger;
					if(xvr==1){
						alert('Plan de Auditoría actualizado correctamente!')
					}
					
				},
				error: function(err){
					debugger
				}
			})


		 })


		$('#addProceso').click(function(){
			if($('#txtProceso').val()==''){
				alert('Ingrese Proceso');
				return false;
			}
			if($('#txtPrioridad').val()==''){
				alert('Ingrese Prioridad');
				return false;
			}
			if($('#txtDuracion').val()==''){
				alert('Ingrese Duración');
				return false;
			}
			
			var objTemp={
				procesoId: $('#txtProceso').val(),
				nombred: $( "#txtProceso option:selected" ).text(),
				seleccionado: true,
				prioridad: $('#txtPrioridad').val(),
				duracion: $('#txtDuracion').val()
			}
			
			listadoProcesos.push(objTemp);
			
			$('#listaProceso tbody').html('');
			var cadena="";
			 $.each(listadoProcesos,function(index, item){
				 cadena=cadena+"<tr>";
				 cadena=cadena+'<td>'+item.nombred+'</td>';
				 cadena=cadena+'<td>'+item.prioridad+'</td>';
				 cadena=cadena+'<td>'+item.duracion+'</td>';
				 cadena=cadena+'<td class="text-center"></td>';
				 cadena=cadena+"</tr>";
			 })			 
			 $('#listaProceso tbody').html(cadena)
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
		
		$('#btnGenerarF').click(function(){
			debugger;
			if(listadoProcesos.length==0){
					alert('Debe seleccionar algún proceso.');
					return false;
			}
			
			

			var tempObj={				
				periodo:anio,
				actareunion:inicial.actareunion,
				procesos:listadoProcesos
			}
			
			var jsontext=JSON.stringify(tempObj);
			$.ajax({
					 type:"POST",
					 url: "http://"+ip+"/planesAnuales",
					 dataType: "json",
					 contentType: "application/json; charset=utf-8",
					 data: jsontext,
					 success: function(xvr){
						 debugger;
						 var cadena2="";
						 var temobj=[];
						 console.log(JSON.stringify(xvr))
						 $('#example').DataTable().ajax.reload();
						 $('#code').modal('hide');
						 if(xvr.programas!=undefined){
							 if(xvr.programas.length>0){
								
								temobj.push([{text: 'Proceso', style: 'tableHeader'}, {text: 'Prioridad', style: 'tableHeader'}, {text: 'Duración', style: 'tableHeader'}, {text: 'Días', style: 'tableHeader'}, {text: 'Auditor', style: 'tableHeader'}]);
								 $.each(xvr.programas,function(index,item){
									//cadena2=cadena2+"['"+item.proceso.descripcion+"','"+item.prioridad+"','"+item.proceso.fechaInicio+"-"+item.proceso.fechaFin+"','"+item.duracion+"'],";
									//var objet=[item.proceso.descripcion,item.prioridad,item.fechaInicio+" / "+item.fechaFin,item.duracion.toString(),item.programaAuditores[0].empleado.nombres +" "+ item.programaAuditores[0].empleado.apellidos];
									var objet=[item.proceso.descripcion,item.prioridad,item.fechaInicio+" / "+item.fechaFin,item.duracion.toString(),item.auditor.nombres +" "+ item.auditor.apellidos];
									temobj.push(objet);
								 })

							 }
						 }
						 generarPDFO(xvr.creadoPor,xvr.fechaCreacionDescripcion,xvr.periodoDescripcion,xvr.periodo,temobj);
					 },
					 error: function(err){
						 alert(err)
						 $('#example').DataTable().ajax.reload();
						 $('#code').modal('hide');
						 generarPDFO('','','',[]);
					 }
				 })
			

		})

		function generarPDFO(creadopor,fechacreacion,periodo,anio,programas){
			var documentDefinition = {
				content: [
					  {
					  text: 'Plan Anual de Auditoria ' + anio,
					  style: 'header',
					  alignment: 'center',
					  margin: [0, 20]
				  },
				  {
					  alignment: 'justify',
					  columns: [
						  {
							  text: [
								  'Periodo: ',
								  {text: periodo, bold: true},
								  ]
						  },
						  {
							  text: [
								  'Fecha de Creación: ',
								  {text: fechacreacion, bold: true},
								  ]
						  }
					  ],
					  margin: [0, 3]
				  },
				  {
					  alignment: 'justify',
					  columns: [
						  {
							  text: [
								  'Generado por: ',
								  {text: creadopor, bold: true},
								  ]
						  }
					  ],
					  margin: [0, 20]
				  },
				  {text: 'Procesos del Plan Anual de Auditoria ' + anio, fontSize: 14, bold: true, margin: [0, 20, 0, 20]},
				  
				  {
					  style: 'tableExample',
					  table: {
						  widths: [ 60,  60, '*',  50, '*'],
						  headerRows: 1,
						  body: 
							  
							  programas
						  
					  },
					  layout: 'lightHorizontalLines'
				  }
			  ],
			  styles: {
				  header: {
					  fontSize: 18,
					  bold: true
				  },
				  bigger: {
					  fontSize: 15,
					  italics: true
				  },
				  tableHeader: {
					  bold: true,
					  fontSize: 12,
					  color: 'black'
				  },
				  tableExample: {
					fontSize: 11,
				  },
			  },
			  defaultStyle: {
				  columnGap: 20
			  }
			};
			
			pdfMake.createPdf(documentDefinition).download('testdoc.pdf');

		}
		
		

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