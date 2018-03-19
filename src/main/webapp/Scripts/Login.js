
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

Login.util ={
    
    ValidarLogin: function ()
    {
       alert("llego") ;
//        if (documento!= ""){
//          
//            General.controls.ShowGeneralMessage("Login","Ingreso");
//        }else{
//          
//           General.controls.ShowGeneralMessage("Login","Debe ingresar el documento de identidad");
//        }
    }
}

  ValidarLogin2= function (documento)
  {
     
        if (documento!= ""){
          
          var prueba ="123"
          
          if (documento != prueba){
                 General.controls.ShowGeneralMessage("Login","Usuario incorrecto");
          }else{
              
              window.location.href = "../Forms/SeleccionActividad.html";
          }
          
        }else{
          
           General.controls.ShowGeneralMessage("Login","Debe ingresar el documento de identidad");
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