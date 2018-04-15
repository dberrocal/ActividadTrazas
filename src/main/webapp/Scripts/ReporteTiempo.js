
var ReporteTiempo = {
    about: 'Versión 1.0 | Developer: Alicia',
    formConst: {},
    util: {}
}

ReporteTiempo.appConst ={
    
    TextField:"",
    ValueField:"",
    UriGet: "",
    strOnChangeEvent: "",
    template: "",
    urlGetReporteTiempo:"http://localhost:8080/ReporteTiempoTrazas/webresources/ReporteTiempo/ReporteTiempo",
    urlGetNivel:"http://localhost:8080/ReporteTiempoTrazas/webresources/ReporteTiempo/nivel",
    urlGetReporteTiempoByID : "http://localhost:8080/ReporteTiempoTrazas/webresources/ReporteTiempo/ReporteTiempoByID/",
    urlGetValidarReporteTiempo: "http://localhost:8080/ReporteTiempoTrazas/webresources/ReporteTiempo/validar/"
}

ReporteTiempo.controls ={
    
    SetDropDownUrl: function (elementId) {
        $("#" + elementId)
            .kendoDropDownList({
                autoBind: false,
                filter: "contains",
                dataTextField: ReporteTiempo.appConst.TextField,
                dataValueField: ReporteTiempo.appConst.ValueField,
                dataSource: {
                    transport: {
                        read: ReporteTiempo.appConst.UriGet,
                        
                    }
                },

                change: ReporteTiempo.appConst.strOnChangeEvent,
                optionLabel: 'Seleccione:',
                template:ReporteTiempo.appConst.template,
            });
    },
    
    SetDropDownLocal: function (elementId, DataSource) {
        $("#" + elementId).kendoDropDownList({
                autoBind: false,
                filter: "contains",
                dataTextField: ReporteTiempo.appConst.TextField,
                dataValueField: ReporteTiempo.appConst.ValueField,
                dataSource:DataSource,
                change: ReporteTiempo.appConst.strOnChangeEvent,
                optionLabel: 'Seleccione:',
                template:ReporteTiempo.appConst.template,
            });
    },
    
 
}



 GenerarReporteTiempo= function (elementID, grupoID, fechaini, fechafin)
  {//http://localhost:8080/ActividadTrazas/webresources/reporte/tiempo/$("#grupo").val()/$("#Fechaini").val()/$("#Fechafin").val()
         $("#" + elementID).kendoGrid({
            toolbar: ["excel"],
            excel: {
                fileName: "Kendo UI Grid Export.xlsx",
              //  proxyURL: "https://demos.telerik.com/kendo-ui/service/export",
                filterable: true
            },
            dataSource: {
                type: "json",
                transport: {
                    read: "http://localhost:8080/ActividadTrazas/webresources/reporte/tiempo/?grupo=" + grupoID + "&fi=" + fechaini + "&ff=" + fechafin
                },
                schema:{
                    model: {
                        fields: {
                            estudiante: { type: "string" },
                            actividad: { type: "string" },
                            nivel: { type: "string" },
                            nombre: { type: "string" },
                            fecha: { type: "date" },
                            duracion: { type: "number" },
                        }
                    }
                },
                pageSize: 7,
                group: {
                    field: "actividad", aggregates: [                       
                        { field: "duracion", aggregate: "sum"},
                    ]
                },
                aggregate: [
                //    { field: "actividad", aggregate: "count" },                    
                    { field: "duracion", aggregate: "sum" },
                ]
            },
            sortable: true,
            pageable: true,
            groupable: true,
            filterable: true,
            columnMenu: true,
            reorderable: true,
            resizable: true,
            columns: [
                { field: "estudiante", title: "Estudiante",/* aggregates: ["count"], footerTemplate: "Total estudiante: #=count#", groupFooterTemplate: "Count: #=count#"*/ },
                { field: "actividad", title: "Actividad", /*aggregates: ["count"], footerTemplate: "Cantidad: #=count#",  groupFooterTemplate: "Cantidad: #= count#" */},
                { field: "nombre", title: "Nombre",},
                { field: "nivel", title: "Nivel", /*aggregates: ["sum"] */},
                { field: "fecha", title: "Fecha",   template: "#= kendo.toString(kendo.parseDate(fecha, 'yyyy-MM-dd'), 'MM/dd/yyyy') #" /*aggregates: ["sum"] */},
                { field: "duracion", title: "Duracion", aggregates: ["sum"], footerTemplate: "Suma: #= sum#",groupHeaderTemplate: "Suma: #= sum#" }
            ]
        });
  
                
  }
  
  
 