
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
    urlGetActividad:"http://localhost:8080/ActividadTrazas/webresources/actividad/actividad",
    urlGetNivel:"http://localhost:8080/ActividadTrazas/webresources/actividad/nivel",
    urlGetActividadByID : "http://localhost:8080/ActividadTrazas/webresources/actividad/actividadByID/",
    urlGetValidarActividad: "http://localhost:8080/ActividadTrazas/webresources/actividad/validar/"
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
                                return data.data || data;
                            }
                        }

                    }
                });

            }
        });

   
    },
}



 BuscarActividad= function ()
  {
      $("#Seleccion").hide();
      $("#DivActividad").show();
     
     var actividadID=  $("#Actividad").data("kendoDropDownList").value();
        
        
        
        General.Service.Get(Actividad.appConst.urlGetActividadByID + actividadID ,
        function (data){
            console.log(data);
                 for (i = 0; i < data.length; i++) {
                         
                         if (data[i].descripcion.indexOf("ESPACIO")>=0){
                         
                            var newpregunta = data[i].descripcion.replace("ESPACIO", "<input  class ='form-control f-input-question'type='text' placeholder='Respuesta'  id='" +data[i].id + "'/>");
//                             $( "#FormActividad" ).append( "<lalbel  class='col-sm-12'> "+ (i + 1) + ". </label>  <label id='" + data[i].id+ "' class='col-sm-12'> " +newpregunta + " </label>"  + "<input type='hidden' readonly class='sinbordes' style='display:none' id='respuesta"+ data[i].id + "'/>"+ "<p> </p>");                             
                            $( "#FormActividad" ).append( "<li><div class='content-question'> <label id='" + data[i].id+ "' class='col-sm-12'> " +newpregunta +  "<input type='hidden' readonly class='sinbordes form-control f-input-question' style='display:none' id='respuesta"+ data[i].id + "'/></label>  </div></li>"+ "<p> </p>");                             
                         }
                         
                   }
                    $( "#FormActividad" ).append( " <div class='btn-btn-gropup'><button style='margin-right: 20%;' class='button' id ='Validar' onclick='ValidarActividad()'> Validar </button><input type='submit' value='Ver respuestas' class='button' id ='VerRespuestas' onclick='VerRespuestas()' /> </div>") ;
//                    $( "#FormActividad" ).append( " <div class='col-md-6'> <div class='col-md-6 cont-col'><input type='submit' value='Ver respuestas' class='btn button' id ='VerRespuestas' onclick='VerRespuestas()' /></div></div>") ;
            
        },
        function (data){
            General.controls.ShowMessge("Ocurrio un error al consultar las pregunras de la actividad"+ data);
                console.log(data);
            }, null)
//        var data =[
//            {id:1, pregunta:'She loves him. He ESPACIO' , respuesta:'is loved'},
//            {id:2, pregunta:'They never used the computer.The computer  ESPACIO' , respuesta:'was never used'},
//            {id:3, pregunta:'I have lost my keys.My keys  ESPACIO' , respuesta:'have been lost'}
//            
//        ];
//        
      //  $("#Validar").prop("disabled",false);
                
  }
  
  ValidarActividad = function (){
      
      
      var actividadID=   $("#Actividad").data("kendoDropDownList").value();
      var result = [];
      $("#FormActividad input[type='text']").each(function(){
        result.push({pregunta : parseInt($(this).attr('id')), respuesta : $(this).val()});
      });
      
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
  
  VerRespuestas = function (){
      
     
      //actividadid
      var data =[
            { preguntaID:1 , respuesta:'is loved'},
            { preguntaID:2 , respuesta:'was never used'},
            { preguntaID:3 , respuesta:'have been lost'}
            
        ];
        
        for (i = 0; i < data.length; i++) {
            $("#FormActividad input[type='hidden']").each(function(){
             
                if("respuesta" + data[i].preguntaID == $(this).attr('id')){
                    
                    //if(data[i].respuesta == $(this).val()){
                      $( "#respuesta"+ data[i].preguntaID ).show();
                      $( "#respuesta"+ data[i].preguntaID ).val(data[i].respuesta)
                      $( "#respuesta"+ data[i].preguntaID ).prop('type', 'text');
//                     }else{
//                         console.log("Respuesta " + data[i].respuesta + "pregunta" + data[i].preguntaID  +"respuesta estudiante" + $(this).val() )
//                     }
//  $( "#FormActividad" ).append( "aaaa") ;
                }
            });
        }
    
//      $("#FormActividad span").each(function(){
//        $(".RespuestaCorrecta").show();
//      });
//      
//      $("#FormActividad input[type='text']").each(function(){
//        $("input[type='text']").css("")
//      });
     //console.log(result);
          
      
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