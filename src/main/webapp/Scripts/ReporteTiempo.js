
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



 GenerarReporteTiempo= function (elementID)
  {
         $("#" + elementID).kendoGrid({
            toolbar: ["excel"],
            excel: {
                fileName: "Kendo UI Grid Export.xlsx",
                proxyURL: "https://demos.telerik.com/kendo-ui/service/export",
                filterable: true
            },
            dataSource: {
                type: "odata",
                transport: {
                    read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Products"
                },
                schema:{
                    model: {
                        fields: {
                            UnitsInStock: { type: "number" },
                            ProductName: { type: "string" },
                            UnitPrice: { type: "number" },
                            UnitsOnOrder: { type: "number" },
                            UnitsInStock: { type: "number" }
                        }
                    }
                },
                pageSize: 7,
                group: {
                    field: "UnitsInStock", aggregates: [
                        { field: "ProductName", aggregate: "count" },
                        { field: "UnitPrice", aggregate: "sum"},
                        { field: "UnitsOnOrder", aggregate: "average" },
                        { field: "UnitsInStock", aggregate: "count" }
                    ]
                },
                aggregate: [
                    { field: "ProductName", aggregate: "count" },
                    { field: "UnitPrice", aggregate: "sum" },
                    { field: "UnitsOnOrder", aggregate: "average" },
                    { field: "UnitsInStock", aggregate: "min" },
                    { field: "UnitsInStock", aggregate: "max" }
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
                { field: "ProductName", title: "Product Name", aggregates: ["count"], footerTemplate: "Total Count: #=count#", groupFooterTemplate: "Count: #=count#" },
                { field: "UnitPrice", title: "Unit Price", aggregates: ["sum"] },
                { field: "UnitsOnOrder", title: "Units On Order", aggregates: ["average"], footerTemplate: "Average: #=average#",
                    groupFooterTemplate: "Average: #=average#" },
                { field: "UnitsInStock", title: "Units In Stock", aggregates: ["min", "max", "count"], footerTemplate: "Min: #= min # Max: #= max #",
                groupHeaderTemplate: "Units In Stock: #= value # (Count: #= count#)" }
            ]
        });
  
                
  }
  
  ValidarReporteTiempo = function (){
      
      var ReporteTiempoID=   $("#ReporteTiempo").data("kendoDropDownList").value();
      var result = [];
      $("#FormReporteTiempo input[type='text']").each(function(){
        result.push({pregunta : parseInt($(this).attr('id')), respuesta : $(this).val()});
      });
      
      General.Service.SendPost(ReporteTiempo.appConst.urlGetValidarReporteTiempo + ReporteTiempoID, result,
      function(data){
          console.log(data);
      },
      function (data){
          console.log(data);
      })
     //console.log(result);
  
  }
  
  VerRespuestas = function (){
      
     
      //ReporteTiempoid
      var data =[
            { preguntaID:1 , respuesta:'is loved'},
            { preguntaID:2 , respuesta:'was never used'},
            { preguntaID:3 , respuesta:'have been lost'}
            
        ];
        
        for (i = 0; i < data.length; i++) {
            $("#FormReporteTiempo input[type='hidden']").each(function(){
             
                if("respuesta" + data[i].preguntaID == $(this).attr('id')){
                    
                    //if(data[i].respuesta == $(this).val()){
                      $( "#respuesta"+ data[i].preguntaID ).show();
                      $( "#respuesta"+ data[i].preguntaID ).val(data[i].respuesta)
                      $( "#respuesta"+ data[i].preguntaID ).prop('type', 'text');
//                     }else{
//                         console.log("Respuesta " + data[i].respuesta + "pregunta" + data[i].preguntaID  +"respuesta estudiante" + $(this).val() )
//                     }
//  $( "#FormReporteTiempo" ).append( "aaaa") ;
                }
            });
        }
    
//      $("#FormReporteTiempo span").each(function(){
//        $(".RespuestaCorrecta").show();
//      });
//      
//      $("#FormReporteTiempo input[type='text']").each(function(){
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