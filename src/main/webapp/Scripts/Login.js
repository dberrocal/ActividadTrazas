
var Login = {
    about: 'Versión 1.0 | Developer: Alicia',
    formConst: {},
    util: {}
}

Login.appConst ={
    
    TextField:"",
    ValueField:"",
    UriGet: "",
    strOnChangeEvent: "",
    template: "",
}

Login.controls ={
    
    SetDropDownUrl: function (elementId) {
        $("#" + elementId)
            .kendoDropDownList({
                autoBind: false,
                filter: "contains",
                dataTextField: Login.appConst.TextField,
                dataValueField: Login.appConst.ValueField,
                dataSource: {
                    transport: {
                        read: Login.appConst.UriGet,
                        
                    }
                },

                change: Login.appConst.strOnChangeEvent,
                optionLabel: 'Seleccione:',
                template:Login.appConst.template,
            });
    },
    
    SetDropDownLocal: function (elementId, DataSource) {
        $("#" + elementId).kendoDropDownList({
                autoBind: false,
                filter: "contains",
                dataTextField: Login.appConst.TextField,
                dataValueField: Login.appConst.ValueField,
                dataSource:DataSource,
                change: Login.appConst.strOnChangeEvent,
                optionLabel: 'Seleccione:',
                template:Login.appConst.template,
            });
    }
}


  ValidarLogin= function (documento)
  {
     
        if (documento!= ""){
          
          var prueba ="123"
          var docente ="456"
          
          $.ajax({
            type: "POST",
            url: 'login',
            //dataType: "json",
            success: function (r) {
                if (r.inicio) {
                    if (r.rol === 'p')
                        window.location.href = "Forms/ReporteTiempo.jsp";
                    else
                        window.location.href = "Forms/SeleccionActividad.jsp";
                } else
                          General.controls.ShowGeneralMessage("Login", "El documento no existe");
            },
            error: function(){},
            data: "documento="+documento
        });
          
        }else{
          
                       General.controls.ShowGeneralMessage("Login", "Debe diligenciar el campo Documento");
        }
      LimpiarRegistro();
  },
  
  
  Registrar = function (documento, nombre, curso)
  {
       LimpiarDocLogin();
      General.Service.SendPost('http://localhost:8080/ActividadTrazas/webresources/usuario/usuarios',
        {documento:documento,nombre:nombre,curso:curso,rol:'estudiante'},
        function (r) {
              General.controls.ShowGeneralMessage("Login","Registro exitoso. Para acceder debe iniciar sesion");
        },
        function(){}
      )      
  },
  
  LimpiarDocLogin = function ()
  {
        $('#DocumentoLogin').val("");
        $('#DocumentoLogin').text("");
  },
  
  LimpiarRegistro = function ()
  {
        
        $('#DocumentoRegistro').val("");
        $('#Nombres').val("");
        $('#Curso').val("");
        $('#DocumentoRegistro').text("");
        $('#Nombres').text("");
        $('#Curso').text("");
  }

function InicioLogin (){
  // constants
  var SHOW_CLASS = 'show',
      HIDE_CLASS = 'hide',
      ACTIVE_CLASS = 'active';
  
  $( '.tabs' ).on( 'click', 'li a', function(e){
    e.preventDefault();
   var $tab = $( this )
         href = $tab.attr( 'href' );
//  
     $( '.active' ).removeClass( ACTIVE_CLASS );
     $tab.addClass( ACTIVE_CLASS );
  
     $( '.show' )
        .removeClass( SHOW_CLASS )
        .addClass( HIDE_CLASS )
        .hide();
    
      $(href)
        .removeClass( HIDE_CLASS )
        .addClass( SHOW_CLASS )
        .hide()
        .fadeIn( 550 );
  });
}