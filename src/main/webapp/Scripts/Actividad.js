
var Actividad = {
    about: 'Versión 1.0 | Developer: Elker Hernandez',
    formConst: {},
    util: {}
}

Actividad.appConst ={
    
    TextField:"",
    ValueField:"",
    UriGet: "",
    strOnChangeEvent: "",
    template: "",
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
    
      SetDropDownActividadXNivel: function (elementId, elementIdCascade, dataNivel,dataActividad) {

        
        $("#" + elementId).kendoDropDownList({
            optionLabel: "Seleccione un nivel",
            dataTextField: "text",
            dataValueField: "value",
            filter: "contains",
            dataSource: dataNivel,
//            dataSource: {
//            
//                transport: {
//                    read:
//                        {
//                            url: FC_Estaciones.appConst.UriGetEstacionesList + '?onlydistracom_ci=true',
//                            type: "GET",
//                            contentType: "application/json; charset=utf-8",
//                            dataType: "json",
//                             
//                        }
//                },
//                
//
//            },
            cascade: function () {
                $("#" + elementIdCascade).kendoDropDownList({
                    autoBind: false,
                    cascadeFrom: "value",
                    optionLabel: "Seleccione una actividad",
                    dataTextField: "text1",
                    dataValueField: "value1",
                    filter: "contains",
                    dataSource: dataActividad,
//                    dataSource: {
//                      
//                        transport: {
//                            read:
//                                {
//                                    url: Actividad.appConst.UriGetSurtidoresByEstacionID,
//                                    type: "GET",
//                                    contentType: "application/json; charset=utf-8",
//                                    dataType: "json",
//                                    data: function () {
//
//                                        var estacionID = $("#"+ elementId).data("kendoDropDownList");
//                                        return { EstacionID: estacionID.value() };
//                                    }
//                                }
//                        },
//                        schema: {
//                            data: function (data) { //specify the array that contains the data
//                                return data.data || data;
//                            }
//                        }
//
//                    }
                });

            }
        });

   
    },
}



 BuscarActividad= function ()
  {
     
     var actividadID=   $("#Actividad").data("kendoDropDownList").value();
        
        
        $( "#FormActividad" ).append( "<p>Test</p>  <input type='text' placeholder='Nivel' id='Nivel' />" );
  }
