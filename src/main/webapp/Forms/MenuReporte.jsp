<%-- 
    Document   : MenuReporte
    Created on : 1/05/2018, 12:40:27 PM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
                
        <title>Actividad</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/boostrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../css/Actividad.css" rel="stylesheet" type="text/css"/>   
        <link href="../css/boostrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.common-material.min.css" />
        <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.material.min.css" />
        <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.1.221/styles/kendo.material.mobile.min.css" />
    
        <script src="../Scripts/Actividad.js" type="text/javascript"></script>
        <script src="../Scripts/Login.js" type="text/javascript"></script>
        <script src="../Scripts/General.js" type="text/javascript"></script>
        <script src="../Scripts/jQuery/jquery-2.1.4.js" type="text/javascript"></script>
        <script src="../Scripts/jQuery/jquery-2.1.4.min.js" type="text/javascript"></script>
        <script src="../Scripts/jQuery/jquery-2.2.3.js" type="text/javascript"></script>
        <script src="../Scripts/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="https://kendo.cdn.telerik.com/2018.1.221/js/kendo.all.min.js"></script>
    </head>
    <body>
        <div class="container" id='Seleccion'>
            <div class="flat-form">
                <h2 class="text-center" style="font-weight: bold;">Actividad </h2>

                <div class="f-cont-form-act text-center">        
        
                    <h5 class="text-center">Seleccione el reporte a generar</h5>
                    <br>
                      <form onsubmit="return false" class="f-form-actividad">
                          <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label text-left col-md-12">Nivel</label>
                                <input type="text"  id="Nivel"  style="width: 100%"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label text-left col-md-12">Actividad</label>
                                <input type="text"   id="Actividad" style="width: 100%"/>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Buscar" class="button" id ="Buscar" onclick="BuscarActividad()" />
                            </div>
                        </div>
                      </form>

                </div>
            </div>
        </div>
        
        
        <div class="container-fluid">
            <div class="container-pregunta">
                <div class="col-md-12"> 
                    
                    <div class="col-md-12">

                        <label class="control-label text-left col-md-12" style="margin-bottom: 19px"><span > <i>Estudiante </i></span>Maria Perez   <span style="padding: 98%" id="tmp_contador"></span>  </label>                       
                     </div>        
                    <ul id="FormActividad">  
                        
                    </ul>
                </div>
            </div>
        </div>
        
        
        <script>
        $(document).ready(function () {
            Actividad.controls.SetDropDownActividadXNivel("Nivel","Actividad");
            
            var seg = 0,min = 00,hor=0;            
            
            setInterval(function(){
                seg +=1;
                if(seg >= 60){ seg = 0;min +=1;}
                if(min >= 60){ min = 0;hor +=1;}
                
                $('#tmp_contador').text(hor+":"+min+":"+seg)
            },1000)
        });
        </script>  
        <script src="../Scripts/boostrap/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>