<%-- 
    Document   : ReporteTiempo
    Created on : 5/05/2018, 04:45:45 AM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.material.mobile.min.css" />
    <link href="../css/boostrap/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../css/boostrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/General.css" rel="stylesheet" type="text/css"/>
<!--        
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.blueopal.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.blueopal.mobile.min.css" />-->

    <script src="https://kendo.cdn.telerik.com/2018.1.221/js/jquery.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2018.1.221/js/jszip.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2018.1.221/js/kendo.all.min.js"></script>
    <script src="../Scripts/ReporteTiempo.js" type="text/javascript"></script>
    <script src="../Scripts/General.js" type="text/javascript"></script>
    
<!--    <script src="https://kendo.cdn.telerik.com/2018.1.221/js/jquery.min.js"></script>
    <link href="../Scripts/Kendo/Custom/kendo.distracom.css" rel="stylesheet" type="text/css"/>
    <script src="https://kendo.cdn.telerik.com/2018.1.221/js/kendo.all.min.js"></script>-->
<!--    <script src="js/jquery.min.js"></script>
    <script src="js/kendo.all.min.js"></script>-->

    

</head>
<body>

    <div class="container" id ="SeleccionReporte" >
        <div class="flat-form">
            
                <div class="f-cont-form-act text-center">      
                    <h5 class="text-center" style="padding-TOP:10%">Seleccione el informe a mostrar</h5>
                    <br>
        
                        <form onsubmit="return false" class="f-form-actividad">
                             <div class="col-md-6">   
                                    <div class="form-group">
                                        <input id="Reporte"  placeholder="Seleccione el reporte" style="width: 100%;"/>
                                     </div>
                                     <div class="form-group">
                                         <button  onclick="TraerReporte();" type="button" class="button"><span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Generar</button>
                                     </div>

                             </div>
                         </form>
                  </div>
                
                <script>
                    function TraerReporte() {
                        if($("#Reporte").val() == ""){
                            
                            General.controls.ShowGeneralMessage("Advertencia", "Debe seleccionar un informe");
                        }else{
                        
                                            $("#SeleccionReporte").hide();
                                            $("#ReporteTiempo").fadeIn();
                                            $("#ReporteTiempo").show();
                                
                            var tipoReporte = $("#Reporte").val();
                            switch (tipoReporte) { 
                                    case "1": 
                                            $("#gridTiempo").show();
                                            $("#gridErrores").hide();
                                            $("#GenerarTiempo").show();
                                            $("#GenerarErrores").hide();
                                            break;
                                    case "2":
                                            $("#gridTiempo").hide();
                                            $("#gridErrores").show();
                                            $("#GenerarTiempo").hide();
                                            $("#GenerarErrores").show();
                                            break;
                                    case "3":
                                            alert('mootools Wins!');
                                            break;		
                                    case "4":
                                            alert('dojo Wins!');
                                            break;
                                    default:
                                            $("#SeleccionReporte").show();
                                            $("#ReporteTiempo").hide();
                                            General.controls.ShowGeneralMessage("Advertencia", "Debe seleccionar un informe");
                            }
                            //outputs "jQuery Wins!"

                         
                        }
                    }

                    $(document).ready(function () {

                            
                           var dataReporte = [
                                {idReporte: 1, NombreReporte: "Reporte Tiempo"},
                                {idReporte: 2, NombreReporte: "Errores"},
                                {idReporte: 3, NombreReporte: "Nivel de dificultad"},
                                {idReporte: 4, NombreReporte: "Total"}
                            ];
                            ReporteTiempo.appConst.TextField = "NombreReporte";
                            ReporteTiempo.appConst.ValueField= "idReporte";
                            ReporteTiempo.controls.SetDropDownLocal("Reporte",dataReporte);




                        
                    });
                </script>
            </div>     

</div>
    
    
    <div id ="ReporteTiempo"  style="display:none" >
    <div class="container" >

        <div class="flat-form" >
            <h2 class="text-center" style="font-weight: bold;padding: 20px">Informe Tiempo Trazas </h2>
                      
                <div class="f-cont-form-act text-center">      
                    <h5 class="text-center">Diligencie todos los campos para generar el informe </h5> 
                     
                    <br>
        
                        <form onsubmit="return false" class="f-form-actividad">
                             <div class="col-md-6">   

                                     <div class="form-group">
<!--                                         <label class="control-label text-left col-md-12">Fecha inicial</label>-->
                                         <input id="FechaIni" placeholder="Fecha inicial" type="text" style="width: 100%;"/>
                                     </div>
                                     <div class="form-group">
<!--                                           <label class="control-label text-left col-md-12">Fecha final</label>-->
                                         <input id="FechaFin" placeholder="Fecha final" type="text" style="width: 100%;"/> 
                                     </div>
                                     <div class="form-group">
<!--                                           <label class="control-label text-left col-md-12">Grupo</label>-->
                                         <input id="Grupo"  placeholder="Seleccione el grupo" style="width: 100%;"/>
                                     </div>
                                     <div class="form-group">
                                         <button  onclick="TraerInformeTiempo();" id="GenerarTiempo" type="button" class="button"><span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Generar</button>
                                         <button  onclick="TraerInformeErrores();" id="GenerarErrores" type="button" class="button"><span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Generar</button>
                                         <button  onclick="Regresar();" id="Regresar" style="margin-left: 174%" type="button" class="button"> Regresar</button> 
                                     </div>
                                 

                             </div>
                         </form>
                       
                  </div>
                <div id="gridTiempo" style="display: none"></div>
                <div id="gridErrores" style="display: none"></div>
                
                <script>
                                        function Regresar() {
                        ALERT("aAAA");
//                        $("#ReporteTiempo").hide();
//                        $("#SeleccionReporte").show();
                        
                    }
                    
                    function TraerInformeTiempo() {
                        if($("#FechaIni").val() == "" || $("#FechaFin").val() == "" || $("#Grupo").val() == "" ){
                            
                            General.controls.ShowGeneralMessage("Advertencia", "Debe seleccionar todos los campos");
                        }else{
                        
                            GenerarReporteTiempo("gridTiempo", $("#Grupo").val(), $("#FechaIni").val(), $("#FechaFin").val());
                        }
                    }
                    
                    
                    
                    function TraerInformeErrores() {
                        if($("#FechaIni").val() == "" || $("#FechaFin").val() == "" || $("#Grupo").val() == "" ){
                            
                            General.controls.ShowGeneralMessage("Advertencia", "Debe seleccionar todos los campos");
                        }else{
                        
                            GenerarReporteTiempo("gridErrores", $("#FechaIni").val(), $("#FechaFin").val(), $("#FechaIni").val(),$("#Grupo").val());
                        }
                    }

                    $(document).ready(function () {

                            function startChange() {
                                var startDate = start.value(),
                                endDate = end.value();

                                if (startDate) {
                                    startDate = new Date(startDate);
                                    startDate.setDate(startDate.getDate());
                                    end.min(startDate);
                                } else if (endDate) {
                                    start.max(new Date(endDate));
                                } else {
                                    endDate = new Date();
                                    start.max(endDate);
                                    end.min(endDate);
                                }
                            }

                            function endChange() {
                                var endDate = end.value(),
                                startDate = start.value();

                                if (endDate) {
                                    endDate = new Date(endDate);
                                    endDate.setDate(endDate.getDate());
                                    start.max(endDate);
                                } else if (startDate) {
                                    end.min(new Date(startDate));
                                } else {
                                    endDate = new Date();
                                    start.max(endDate);
                                    end.min(endDate);
                                }
                            }

                            var start = $("#FechaIni").kendoDatePicker({
                                change: startChange,
                                format: "yyyy/MM/dd",
                                culture: "es-ES"
                            }).data("kendoDatePicker");

                            var end = $("#FechaFin").kendoDatePicker({
                                change: endChange,
                                format: "yyyy/MM/dd",
                                culture: "es-ES"

                            }).data("kendoDatePicker");

                            start.max(end.value());
                            end.min(start.value());
                            
                           var dataGrupo = [
                                {idGrupo: 'Curso1', NombreGrupo: "Grupo 1"},
                                {idGrupo: 'Curso2', NombreGrupo: "Grupo 2"},
                                {idGrupo: 'Curso3', NombreGrupo: "Grupo 3"}
                            ];
                            ReporteTiempo.appConst.TextField = "NombreGrupo";
                            ReporteTiempo.appConst.ValueField= "idGrupo";
                            ReporteTiempo.controls.SetDropDownLocal("Grupo",dataGrupo);

                        
                    });
                </script>
            </div>     

</div>
    
</div>
    
    
    

<script src="../Scripts/boostrap/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>