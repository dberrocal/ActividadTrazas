
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
                proxyURL: "https://demos.telerik.com/kendo-ui/service/export",
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
                            tareas: { type: "string" },
                            nombre: { type: "string" },
                            duracion: { type: "string" },
                        }
                    }
                },
                pageSize: 7,
                group: {
                    field: "tareas", aggregates: [
                        { field: "nombre", aggregate: "count" },
                        { field: "duracion", aggregate: "sum"},
//                        { field: "UnitsOnOrder", aggregate: "average" },
//                        { field: "UnitsInStock", aggregate: "count" }
                    ]
                },
                aggregate: [
                    { field: "tareas", aggregate: "count" },
                    { field: "nombre", aggregate: "count" },
                    { field: "duracion", aggregate: "sum" },
//                    { field: "UnitsInStock", aggregate: "min" },
//                    { field: "UnitsInStock", aggregate: "max" }
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
                { field: "actividad", title: "actividad", /*aggregates: ["sum"] */},
                { field: "nombre", title: "nombre", /*aggregates: ["average"], footerTemplate: "Average: #=average#",
                    groupFooterTemplate: "Average: #=average#" */},
                { field: "nivel", title: "nivel", /*aggregates: ["sum"] */},
                { field: "tareas", title: "tareas",/* aggregates: ["min", "max", "count"], footerTemplate: "Min: #= min # Max: #= max #",groupHeaderTemplate: "Units In Stock: #= value # (Count: #= count#)" */}
            ]
        });
  
                
  }
  
  
 