
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
          
          if (documento != prueba && documento != docente){
                 General.controls.ShowGeneralMessage("Login","Usuario incorrecto");
          }else{
              if(documento == "456"){
                   window.location.href = "../Forms/ReporteTiempo.html";
              }else{
                   window.location.href = "../Forms/SeleccionActividad.html";
             }
          }
          
        }else{
          
           General.controls.ShowGeneralMessage("Login","Debe ingresar el documento de identidad");
        }
  },
  
  
  Registrar = function (documento, nombre, curso)
  {
         if (documento!= "" && nombre != "" && curso != ""){
             General.controls.ShowGeneralMessage("Login","Registro exitoso. Para acceder debe iniciar sesion");
         }else{
               General.controls.ShowGeneralMessage("Login","Debe diligenciar todos los campos");
         }
      
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