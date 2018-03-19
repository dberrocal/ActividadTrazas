
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
            dataTextField: "text",
            dataValueField: "value",
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
                    cascadeFrom: "value",
                    optionLabel: "Seleccione una actividad",
                    dataTextField: "descripcion",
                    dataValueField: "id",
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

                                        var nivel1 = $("#"+ elementId).data("kendoDropDownList");
                                        return { nivel1: nivel.value() };
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
     
     var actividadID=   $("#Actividad").data("kendoDropDownList").value();
        
        var data =[
            {id:1, pregunta:'She loves him. He ESPACIO' , respuesta:'is loved'},
            {id:2, pregunta:'They never used the computer.The computer  ESPACIO' , respuesta:'was never used'},
            {id:3, pregunta:'I have lost my keys.My keys  ESPACIO' , respuesta:'have been lost'}
            
        ];
        
        
                     for (i = 0; i < data.length; i++) {
                         
                         if (data[i].pregunta.indexOf("ESPACIO")>=0){
                         
                            var newpregunta = data[i].pregunta.replace("ESPACIO", "<input type='text' placeholder='Respuesta'  id='" +data[i].id + "'/>");
                             $( "#FormActividad" ).append( "<lalbel> "+ (i + 1) + ". </label>  <label id='" + data[i].id+ "'> " +newpregunta + " </label>"  + "<span class='RespuestaCorrecta' style='display:none'> "+ data[i].respuesta +" </span>"+ "<p> </p>");                             
                            
                         }
                   }
                    $( "#FormActividad" ).append( " <input type='submit' value='Validar' class='button' id ='Validar' onclick='ValidarActividad()' />") ;
                    $( "#FormActividad" ).append( " <input type='submit' value='Ver respuestas' class='button' id ='VerRespuestas' onclick='VerRespuestas()' />") ;
  }
  
  ValidarActividad = function (){
      
      var result = [];
      $("#FormActividad input[type='text']").each(function(){
        result.push({PreguntaID : $(this).attr('id'), RespuestaEst : $(this).val()});
      });
     console.log(result);
  
  }
  
  VerRespuestas = function (){
      
     
      //actividadid
//      var data =[
//            { preguntaID:1, respuesta:'is loved'},
//            { preguntaID:2 , respuesta:'was never used'},
//            { preguntaID:3 , respuesta:'have been lost'}
//            
//        ];
//        
        for (i = 0; i < data.length; i++) {
            $("#FormActividad label").each(function(){
                console.log(data[i].preguntaID);
                console.log($(this).attr('id'));
                if(data[i].preguntaID == $(this).attr('id')){

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
