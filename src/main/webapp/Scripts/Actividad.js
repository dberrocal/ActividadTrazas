
var Actividad = {
    about: 'Versión 1.0 | Developer: Alicia',
    formConst: {},
    util: {}
}

Actividad.appConst ={
    
    TextField:"",
    ValueField:"",
    UriGet: "",
    strOnChangeEvent: "",
    template: "",
    urlGetActividad:"http://localhost:8080/ActividadTrazas/webresources/secuencia/actividad",
    urlGetNivel:"http://localhost:8080/ActividadTrazas/webresources/secuencia/niveles",
    urlGetActividiadByScuenciaID : "http://localhost:8080/ActividadTrazas/webresources/secuencia/actividadbyid/",
    urlGetActividiadByID : "http://localhost:8080/ActividadTrazas/webresources/actividad/actividadbyid/",
    urlGetValidarActividad: "http://localhost:8080/ActividadTrazas/webresources/actividad/validar/",
    ActividadesSecuencia: null,
}

Actividad.controls ={
    
    SetDropDownUrl: function (elementId) {
        $("#" + elementId)
            .kendoDropDownList({
                autoBind: false,
                filter: "contains",
                dataTextField: Actividad.appConst.TextField,
                dataValueField: Actividad.appConst.ValueField,
                dataSource: {
                    transport: {
                        read: Actividad.appConst.UriGet,
                        
                    }
                },

                change: Actividad.appConst.strOnChangeEvent,
                optionLabel: 'Seleccione:',
                template:Actividad.appConst.template,
            });
    },
    
    SetDropDownLocal: function (elementId, DataSource) {
        $("#" + elementId).kendoDropDownList({
                autoBind: false,
                filter: "contains",
                dataTextField: Actividad.appConst.TextField,
                dataValueField: Actividad.appConst.ValueField,
                dataSource:DataSource,
                change: Actividad.appConst.strOnChangeEvent,
                optionLabel: 'Seleccione:',
                template:Actividad.appConst.template,
            });
    },
    
      SetDropDownActividadXNivel: function (elementId, elementIdCascade) {

        
        $("#" + elementId).kendoDropDownList({
            optionLabel: "Seleccione un nivel",
            dataTextField: "Nivel",
            dataValueField: "NivelID",
            filter: "contains",
        //    dataSource: dataNivel,
            dataSource: {
            
                transport: {
                    read:
                        {
                            url: Actividad.appConst.urlGetNivel,
                            type: "GET",
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                             
                        }
                },
            },
            cascade: function () {
                $("#" + elementIdCascade).kendoDropDownList({
                    autoBind: false,
                    cascadeFrom: "NivelID",
                    optionLabel: "Seleccione una actividad",
                    dataTextField: "Descripcion",
                    dataValueField: "ActividadID",
                    filter: "contains",
               //     dataSource: dataActividad,
                    dataSource: {
                      
                        transport: {
                            read:
                                {
                                    url: Actividad.appConst.urlGetActividad,
                                    type: "GET",
                                    contentType: "application/json; charset=utf-8",
                                    dataType: "json",
                                    data: function () {
                                        
                                        var nivel = $("#"+ elementId).data("kendoDropDownList");
                                        
                                        return { Nivel: nivel.value() };
                                    }
                                }
                        },
                        schema: {
                            data: function (data) { //specify the array that contains the data
                                console.log(data);
                                return data.data || data;
                            }
                        }

                    }
                });

            }
        });

   
    },
}

var ty = null;

 BuscarActividad= function ()
  {
      
     var actividadID=  $("#Actividad").data("kendoDropDownList").value();
        
        var aa = [];
        General.Service.Get(Actividad.appConst.urlGetActividiadByScuenciaID + actividadID ,
        function (data){
            console.log(data);
             
            Actividad.appConst.ActividadesSecuencia = data;
            if(data.actividad.length > 0){
                CargarActividadByActvidadId(data.actividad[0].actividad, 0)
                $("#Seleccion").hide();
                $("#DivActividad").show();

            }else{
                
             //   General.controls.ShowMessge("No hay actividades para esta secuencia","warning","popupNotification");
                General.controls.ShowGeneralMessage("Informacion", "No hay actividades para esta secuencia");
                
            }
        },
        function(data){
            console.log(data);
        });
        
        
 },
         
        CargarActividadByActvidadId = function (actividadID, posicionActividad){
        
        $("#FormActividad").html("")
        console.log(actividadID);
        General.Service.Get(Actividad.appConst.urlGetActividiadByID + actividadID ,
        function (data){
            console.log(data);
                 for (i = 0; i < data.length; i++) {
                         
                         if (data[i].descripcion.indexOf("ESPACIO")>=0){
                         
                            var newpregunta = data[i].descripcion.replace("ESPACIO", "<input  class ='form-control f-input-question'type='text' placeholder='Respuesta'  id='" +data[i].id + "'/>");
                          $( "#FormActividad" ).append( "<label id='" + data[i].id+ "' class='col-sm-12'>" +(i + 1) + " ."+ newpregunta + " <input type='hidden' readonly class='sinbordes' style='display:none' id='respuesta"+ data[i].id + "'/></label>" + "<p> </p>");                             
                          //$( "#FormActividad" ).append( "<label  class='col-sm-12'> "+ (i + 1) + ". </label>  <label id='" + data[i].id+ "' class='col-sm-12'> " +newpregunta + " </label>"  + "<input type='hidden' readonly class='sinbordes' style='display:none' id='respuesta"+ data[i].id + "'/>"+ "<p> </p>");                             
                    //  $( "#FormActividad" ).append( "<li><div class='content-question'> <label id='" + data[i].id+ "' class='col-sm-12'> " +newpregunta +  "<input type='hidden' readonly class='sinbordes form-control f-input-question' style='display:none' id='respuesta"+ data[i].id + "'/></label>  </div></li>"+ "<p> </p>");                             
                         }
                         
                   }
                    $( "#FormActividad" ).append( " <div class='btn-btn-gropup'><button style='margin-right: 20%;' class='button' id ='Validar' onclick='ValidarActividad("+actividadID+")'> Validar </button><input type='submit' value='Ver respuestas' class='button' id ='VerRespuestas' onclick='VerRespuestas("+actividadID+")' /> </div>") ;
                //    $( "#FormActividad" ).append( " <div class='col-md-6'> <div class='col-md-6 cont-col'><input type='submit' value='Ver respuestas' class='btn button' id ='VerRespuestas' onclick='VerRespuestas()' /></div></div>") ;
                   $( "#FormActividad" ).append( " <div class='btn-btn-gropup'> <div class='col-md-6 cont-col'><input type='submit'  style='margin-left: 66%; margin-top: 5%;'value='Siguiente' class='btn button' id ='SiguienteActividad' onclick='SiguienteActividad("+ posicionActividad+ ")' /></div></div>") ;
            
        },
        function (data){
            //General.controls.ShowMessge("Ocurrio un error al consultar las pregunras de la actividad"+ data);
            General.controls.ShowGeneralMessage("Información","Ocurrio un error al consultar las pregunras de la actividad \n"+ data);
                console.log(data);
            }, null)
            
            
        
        $("#Validar").prop("disabled",false);
            
            
        },
                
  SiguienteActividad = function (posicionActividad){
      
      var sgteAct =  posicionActividad + 1;
      if(Actividad.appConst.ActividadesSecuencia.actividad.length >0)
      {
          if( sgteAct> Actividad.appConst.ActividadesSecuencia.actividad.length - 1 ) 
          {
             //   General.controls.ShowMessge("Finalizo las tareas");
                $("#FinalizoActividad").show();
                $("#FormActividad").hide();
                $("#FormActividad").html("");
          }else{
              
                CargarActividadByActvidadId(Actividad.appConst.ActividadesSecuencia.actividad[sgteAct].actividad,  sgteAct);
            
        }
      }
  },              
  
  ValidarActividad = function (actividadID){
      
      
    //  var actividadID=   $("#Actividad").data("kendoDropDownList").value();
      var result = [];
      $("#FormActividad input[class ='form-control f-input-question']").each(function(){
        result.push({pregunta : parseInt($(this).attr('id')), respuesta : $(this).val()});
      });
      
      console.log(result);
      General.Service.SendPost(Actividad.appConst.urlGetValidarActividad + actividadID, result,
      function(data){
          console.log(data);
          
          if(data != null){
                 for (i = 0; i < data.length; i++) {
                    $("#FormActividad input[type='text']").each(function(){
                        //console.log($(this).attr('id'));

                        if(data[i].pregunta == parseInt($(this).attr('id'))){
                            if(data[i].valido == false){
                            $(this).css("color", "red");
                            $(this).css("font-weight", "bold");
                        }else{
                              $(this).css("color", "green");
                              $(this).css("font-weight", "bold");
                        }
                        }
              //result.push({pregunta : parseInt($(this).attr('id')), respuesta : $(this).val()});
                       });
                }
      
          }
      },
      function (data){
          console.log(data);
      },)
     //console.log(result);
  
  }
  
  VerRespuestas = function (actividadID){
      
    
     General.Service.Get(Actividad.appConst.urlGetActividiadByID + actividadID,
      function(data){
          console.log(data);
          
          if(data != null){
                for (i = 0; i < data.length; i++) {
                    $("#FormActividad input[type='hidden']").each(function(){

                        if("respuesta" + data[i].id == $(this).attr('id')){

                            //if(data[i].respuesta == $(this).val()){
                              $( "#respuesta"+ data[i].id ).show();
                              $( "#respuesta"+ data[i].id ).val(data[i].respuesta)
                              $( "#respuesta"+ data[i].id ).prop('type', 'text');
        //                     }else{
        //                         console.log("Respuesta " + data[i].respuesta + "pregunta" + data[i].preguntaID  +"respuesta estudiante" + $(this).val() )
        //                     }
        //  $( "#FormActividad" ).append( "aaaa") ;
                        }
                    });
                }
          }
      },
      function (data){
          console.log(data);
      },)
      
      //actividadid
//      var data =[
//            { preguntaID:1 , respuesta:'is loved'},
//            { preguntaID:2 , respuesta:'was never used'},
//            { preguntaID:3 , respuesta:'have been lost'}
//            
//        ];
        
        
    
//      $("#FormActividad span").each(function(){
//        $(".RespuestaCorrecta").show();
//      });
//      
//      $("#FormActividad input[type='text']").each(function(){
//        $("input[type='text']").css("")
//      });
     //console.log(result);
          
      
  }
  
  FinalizarActividad = function ()
  {
      $("#DivActividad").hide();
      $("#Nivel").data("kendoDropDownList").value(0);
      $("#Actividad").data("kendoDropDownList").value(0);
      $("#Seleccion").show();
     //window.location.href = "../Forms/Login.html";
      
  }
 	function empezarDetener()
	{
		
	}
 
	function funcionando()
	{
		// obteneos la fecha actual
		var actual = new Date().getTime();
 
		// obtenemos la diferencia entre la fecha actual y la de inicio
		var diff=new Date(actual-inicio);
 
		// mostramos la diferencia entre la fecha actual y la inicial
		var result=LeadingZero(diff.getUTCHours())+":"+LeadingZero(diff.getUTCMinutes())+":"+LeadingZero(diff.getUTCSeconds());
		$("#reloj").val(result);
 
		// Indicamos que se ejecute esta función nuevamente dentro de 1 segundo
		timeout=setTimeout("funcionando()",1000);
	}
 
	/* Funcion que pone un 0 delante de un valor si es necesario */
	function LeadingZero(Time) {
		return (Time < 10) ? "0" + Time : + Time;
	}