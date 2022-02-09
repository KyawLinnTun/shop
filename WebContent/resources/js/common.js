'use strict';

/*references ac-notification.js*/

$(document).ready(function() {
    function notify(type, message) {
        $.growl({
            icon: "",
            title: "",
            message: message,
            url: ''
        }, {
            element: 'body',
            type: type,
            allow_dismiss: true,
            placement: {
                from: "top",
                align: "center"
            },
            offset: {
                x: 30,
                y: 30
            },
            spacing: 10,
            z_index: 999999,
            delay: 2500,
            timer: 1000,
            url_target: '_blank',
            mouse_over: false,
            animate: {
                enter: "animated fadeIn",
                exit: "animated fadeOut"
            },
            icon_type: 'class',
            template: '<div data-growl="container" class="alert" role="alert">' +
                '<button type="button" class="close" data-growl="dismiss">' +
                '<span aria-hidden="true">&times;</span>' +
                '<span class="sr-only">Close</span>' +
                '</button>' +
                '<span data-growl="icon"></span>' +
                '<span data-growl="title"></span>' +
                '<span data-growl="message"></span>' +
                '<a href="#!" data-growl="url"></a>' +
                '</div>'
        });
    };
    var frmMessageValue = $("#frmMessage").val();
	if(frmMessageValue != null && typeof frmMessageValue != "undefined" && $.trim(frmMessageValue) !== "") {
		var messageArr = frmMessageValue.split("|");
		if(messageArr && messageArr.length == 2) {
			notify(messageArr[0],messageArr[1]);
		}
	}
	
	$("#frmsystemSetting").attr('action','#');		
	$("#update-sys-setting").click(function(e){
		   e.preventDefault();
			 swal({
	                title: "",
	                text: "Are you sure want to update?",
	                icon: "warning",
	                buttons: true,
	                dangerMode: true,
	            }).then((willDelete)=>{
	                if (willDelete) {
	                	$("#frmsystemSetting").attr('action','system-setting.html');	
	                	$("#frmsystemSetting").submit();
	                } else {
	                    console.log("cancel")
	                }
	            });
	});
	
	$("#frmCompanyInformation").attr('action','#');		
	$("#update-company-info").click(function(e){
		   e.preventDefault();
			 swal({
	                title: "",
	                text: "Are you sure want to update?",
	                icon: "warning",
	                buttons: true,
	                dangerMode: true,
	            }).then((willDelete)=>{
	                if (willDelete) {
	                	$("#frmCompanyInformation").attr('action','company-info.html');	
	                	$("#frmCompanyInformation").submit();
	                } else {
	                    console.log("cancel")
	                }
	            });
	});
	
});
