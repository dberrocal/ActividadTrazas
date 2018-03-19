General = {
    about: 'Versión 1.0 | Developer: Alicia',
    appConst: {},
    util: {}
}

General.appConst ={
    
    defaultNotificationType : "",
}
General.controls ={
     SetWindows: function (elementoID, width, height, title, onClose) {
        $("#" + elementoID).kendoWindow({
            width: width,
            height: height,
            title: title,
            actions: ["Close"],
            draggable: true,
            modal: true,
            pinned: true,
            visible: false,
            close: onClose
        }).data("kendoWindow");
    },

    
     ShowMessge: function (message, pTipo, pNotificationElmentId) {
        try {
            var tipo = pTipo || General.appConst.defaultNotificationType;
            var notificationElmentId = pNotificationElmentId || General.appConst.defaultNotificationElment;
            if (document.getElementById(notificationElmentId) == null) {
                var span = document.createElement('span');
                span.id = notificationElmentId;
                document.body.appendChild(span);
            }
            var popupKendo = $("#" + notificationElmentId).kendoNotification().data("kendoNotification");
            popupKendo.show(message, tipo)
        }
        catch (err) {
            General.util.ShowGeneralMessage('Error', message);
        }
    },
    
    
    ShowGeneralMessage: function (title, mess, optionalHeight, optionalWidth, onClose) {
        if (typeof windowMss !== 'undefined') { windowMss.destroy(); }
        var respuesta = false;
        $('body').append('<div style="display: none;" id="mss"><div style="text-align: center; margin:10px;">' + mess +
            '</div><div><button style="position: relative; margin:10px; float: right;" onclick="windowMss.close();"' +
            'type="button" class="btn btn-default">Cerrar</button></div>')
        General.controls.SetWindows("mss", optionalWidth != null ? optionalWidth : "", optionalHeight != null ? optionalHeight : "", title, onClose != null ? onClose : "");
        windowMss = $("#mss").data("kendoWindow");
        windowMss.center();
        windowMss.open();
    },
    }
    
   General.Service={
    Get: function (urlService, methodOk, pMethodFailure, pAsync) {

                try {
                    var methodFailure = pMethodFailure;// || FuelControl.util.DefaultMethodFailure;
                    $.ajax({
                        type: "GET",
                        url: urlService,
                        async: pAsync,
                        dataType: 'json',
                        success: methodOk,
                        error: methodFailure,
                        contentType: 'application/json; charset=utf-8',
                        headers: {
                            "Authorization": "Basic " + btoa($("#NombreDeUsuario").text())
                        },
                    });
                }
                catch (err) {
                    General.util.ShowMessge(err.message, 'error');
                }

    },
    
    Post: function (urlService, methodOk, pMethodFailure) {
            try {
                var methodFailure = pMethodFailure;// || FuelControl.util.DefaultMethodFailure;
                $.ajax({
                    type: "POST",
                    url: urlService,
                    dataType: "json",
                    success: methodOk,
                    error: methodFailure,
//                    headers: {
//                        "Authorization": "Basic " + btoa($("#NombreDeUsuario").text())
//                    },
                });
            }
            catch (err) {
                General.util.ShowMessge(err.message, 'error');
            }
        },
        SendPost: function (urlService, pData, methodOk, pMethodFailure) {
            try {
                var methodFailure = pMethodFailure// || FuelControl.util.DefaultMethodFailure;
                //console.log(JSON.stringify(pData));
                $.ajax({
                    type: "POST",
                    url: urlService,
                    dataType: "json1",
                    success: methodOk,
                    error: methodFailure,
                    data: JSON.stringify(pData),
//                    headers: {
//                        "Authorization": "Basic " + btoa($("#NombreDeUsuario").text())
//                    },
                    contentType: "application/json; charset=utf-8",
                });
            }
            catch (err) {
                General.util.ShowMessge(err.message, 'error');
            }
        },
}
