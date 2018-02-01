function uploadFile(file) {
	var files = document.getElementById("image").files;
	if(files) {
		var oMyForm = new FormData();
		oMyForm.append("file", files[0]);
		$.ajax({
			url : "/upload",
			data: oMyForm,
			async: false,
		    dataType: 'text',
		    processData: false,
		    contentType: false,
		    type: 'POST',
		    success : function(data) {
		    	console.log(data);
		    	file.fileName = data;
				console.log("SuCCESS");
			},
			error : function() {
				console.log("ERROR");
			}
		});
	}
}

$(document).ready(function(){
	$("#f2").css("display", "none");
	$("#login").click(function(){
		var username = $("#uname").val();
		var password = $("#password").val();
		console.log(username);
		console.log(password)
		$.ajax({
			url: "/login",
			type: "POST",
			data:JSON.stringify( {
				"username" : username,
				"password":   password
			}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				console.log(data)
				window.location.replace("/dashboard");
			},
			error: function() {
				alert("Wrong username or password!");
			}

		});

		});
	$("#registrate").click(function() {
		var username = $("#unamereg").val();
		var password = $("#passwordreg").val();
		var conf_password = $("#conf_password").val();
		var firstName = $("#fname").val();
		var lastName = $("#lname").val();
		file = {fileName : ''};
		uploadFile(file);
		console.log('FileName : ' + file.fileName);
		$.ajax({
			url: "/registrate",
			type: "POST",
			data:JSON.stringify( {
				"userName" : username,
				"password":   password,
				"confirmPassword" : conf_password,
				"firstName" : firstName,
				"lastName" : lastName,
				"image" : file.fileName
			}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				$("#f2").css("display", "none");
				$("#f1").css("display", "block");
				alert("Successful registration!");
			},
			error : function(data) {
				console.log(data);
				$("#f2").css("display", "none");
				$("#f1").css("display", "block");
				alert("ERROR");
			}
		});
	});
	$("#reg_form").click(function() {
		$("#f1").css("display", "none");
		$("#f2").css("display", "block");
	});
});
