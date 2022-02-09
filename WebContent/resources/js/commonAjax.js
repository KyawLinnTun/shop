function ajaxCall(url, data, callback, dialogueErrMsg, emptyCallBack) {
	$.ajax({
		type : "GET",
		encoding : "UTF-8",
		url : url,
		contentType : "application/json",
		data : data,
		dataType : 'json',
		// timeout: 10000,
		success : function(res) {
			if (res.information != null) {
				document.getElementById("modalBounceInDown").style.display='block';
				$("#commonLabel").removeClass('custom_error_hide');
			} else if (res.emptyData != null) {
				if (typeof emptyCallBack == "function") {
					emptyCallBack();
				}
			} else {
				if (res.successMessage != null) {
					document.getElementById("modalBounceInDown").style.display='block';
					$("#commonLabel").removeClass('custom_error_hide');
				}else if (typeof callback == "function") {
					return callback(res.data);
				}
			}
		},
		error : function(e) {
			document.getElementById("modalBounceInDown").style.display='block';
			$("#commonLabel").removeClass('custom_error_hide');
		}
	});
}