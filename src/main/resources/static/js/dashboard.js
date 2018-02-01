/**
 * 
 */
function refreshMyConfs() {
		var table = $("#hoverTableId");
		$("#hoverTableId").find("tr:gt(0)").remove();
		var evenCol = "#d4e3e5";
		var oddCol = "#EAF3F3";
		console.log("Table cleared!");
		$.ajax({
			url : "/conferences/mine",
			type : "GET",
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				data.forEach(function(e, i) {
					var col = oddCol;
					if(i % 2 == 0) {
						col = evenCol;
					}
					row = "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='"+col+"';\">";
					row += "<td>" + e.name + "</td>";
					row += "<td>" + e.place + "</td>";
					row += "<td>" + e.theme + "</td>";
					row += "<td>" + new Date(e.startDate).toDateString() + "</td>";
					row += "<td>" + new Date(e.endDate).toDateString() + "</td>";
					row += "<td>" + e.shortDescription + "</td>";
					row += "<td><button type=\"button\" name=\""+ e.id +  "\" class=\"" + "editConf" +"\">Edit</button></td>";
					row += "</tr>";
					table.append(row);
				}
			)},
			error : function(data) {
				console.log(data);
				alert("ERROR!");
			}
		});
	}
	
function showHome() {
	removeActive();
	$("#userInfo").css("display", "block");
}

	function refreshAllConfs() {
		var table = $("#hoverTableIdAll");
		$("#hoverTableIdAll").find("tr:gt(0)").remove();
		var evenCol = "#d4e3e5";
		var oddCol = "#EAF3F3";
		console.log("Table cleared!");
		$.ajax({
			url : "/conferences/all",
			type : "GET",
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				data.forEach(function(e, i) {
					var col = oddCol;
					if(i % 2 == 0) {
						col = evenCol;
					}
					row = "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='"+col+"';\">";
					row += "<td>" + e.name + "</td>";
					row += "<td>" + e.place + "</td>";
					row += "<td>" + e.theme + "</td>";
					row += "<td>" + new Date(e.startDate).toDateString() + "</td>";
					row += "<td>" + new Date(e.endDate).toDateString() + "</td>";
					row += "<td>" + e.shortDescription + "</td>";
					var cls = "attend";
					var text = "Attend";
					if(e.attend) {
						cls = "dontAttendWR";
						text = "Don't attend";
					}
					row += "<td><button type=\"button\" name=\""+ e.id +  "\" class=\"" + cls +"\">" + text +"</button></td>";
					row += "</tr>";
					table.append(row);
				}
			)},
			error : function(data) {
				console.log(data);
				alert("ERROR!");
			}
		});
	}
	
	function refreshAttendaces() {
		var table = $("#hoverTableAttendances");
		$("#hoverTableAttendances").find("tr:gt(0)").remove();
		var evenCol = "#d4e3e5";
		var oddCol = "#EAF3F3";
		console.log("Table cleared!");
		$.ajax({
			url : "/attendances",
			type : "GET",
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				data.forEach(function(e, i) {
					var col = oddCol;
					if(i % 2 == 0) {
						col = evenCol;
					}
					row = "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='"+col+"';\">";
					row += "<td>" + e.name + "</td>";
					row += "<td>" + e.place + "</td>";
					row += "<td>" + e.theme + "</td>";
					row += "<td>" + new Date(e.startDate).toDateString() + "</td>";
					row += "<td>" + new Date(e.endDate).toDateString() + "</td>";
					row += "<td>" + e.shortDescription + "</td>";
					row += "<td><button type=\"button\" name=\""+ e.id +  "\" class=\"dontAttend\">Don't attend</button></td>";
					row += "</tr>";
					table.append(row);
				}
			)},
			error : function(data) {
				console.log(data);
				alert("ERROR!");
			}
		});
	}

function initScreen() {
	$.ajax({
		url : "/getUserInfo",
		async : false,
		type : "GET",
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			document.getElementById("userAvatar").src = "/image/" + data.image;
			$("#userName").text(data.firstName + " " + data.lastName);
		}, 
		error : function(data) {
			alert("ERROR");
			window.location.replace("/");
		}
	});
}

function removeActive() {
	$("#addConfForm").css("display", "none");
	$("#myConfsTable").css("display", "none");
	$("#allConfsTable").css("display", "none");
	$("#userAttendaces").css("display", "none");
	$("#userInfo").css("display", "none");
	$(".nav ul li a").each(function() {
		$(this).removeClass("active");
	});
	clearForm();
}

function clearForm() {
	$('input[type="text"]').val('');
	$('input[type="date"]').val('');
	$('input[type="hidden"]').val('');
	$("#contact").trigger('reset');
}

$(document).ready(function(){
	initScreen();
	
	$("#add_conf_a").click(function() {
		removeActive();
		$(this).addClass("active");
		$("#addConfForm").css("display", "block");
	});
	$("#submitConf").click(function() {
		var name = $("#newConfName").val();
		var theme = $("#newConfTheme").val();
		var place = $("#newConfPlace").val();
		var startDate = $("#startDate").val();
		if(startDate == "") {
			startDate = undefined;
		}
		console.log("Start date : " + startDate);
		var endDate = $("#endDate").val();
		if(endDate == "") {
			endDate = undefined;
		}
		console.log("End date : " + endDate);
		var shortDescr = $("#shortDescr").val();
		var longDescr = $("#longDescr").val();
		var id = $("#confId").val();
		$.ajax({
			url: "/conferences/save",
			type: "POST",
			async: false,
			data:JSON.stringify( {
				"id" : id,
				"name" : name,
				"theme":   theme,
				"startDate" : startDate,
				"endDate" : endDate,
				"shortDescription" : shortDescr,
				"longDescription" : longDescr,
				"place" : place
			}),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				console.log(data);
				alert("Conference successfully saved!");
				refreshMyConfs();
				$("#contact").trigger('reset');
				//window.location.replace("/dashboard");
				showHome();
			},
			error: function(data) {
				console.log(data);
				alert("ERROR!");
			}
		});
	});
	$("#my_confs_a").click(function() {
		removeActive();
		$(this).addClass("active");
		if(document.getElementById("hoverTableId").rows.length == 1) { //Only header
			refreshMyConfs();
		}
		$("#myConfsTable").css("display", "block");
	});
	$("#all_confs_a").click(function() {
		removeActive();
		$(this).addClass("active");
		if(document.getElementById("hoverTableIdAll").rows.length == 1) { //Only header
			refreshAllConfs();
		}
		$("#allConfsTable").css("display", "block");
	});
	$("#attends_a").click(function() {
		removeActive();
		$(this).addClass("active");
		if(document.getElementById("hoverTableAttendances").rows.length == 1) {
			refreshAttendaces();
		}
		$("#userAttendaces").css("display", "block");
	});
});
function attend(id, but) {
	console.log("ID : " + id);
	$.ajax({
		url : "/attend",
		type: "POST",
		dataType : "json",
		data : JSON.stringify({
			"id" : id
		}),
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			alert(data.message);
			but.removeClass('attend');
			but.addClass('dontAttendWR');
			but.html("Don't attend");
		},
		error : function() {
			alert("ERROR");
		}
	});
}
function dontAttend(id, but, rem) {
	console.log("ID : " + id);
	$.ajax({
		url : "/attendances/delete",
		type: "POST",
		dataType : "json",
		data : JSON.stringify({
			"id" : id
		}),
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			alert(data.message);
			if(rem) {
				refreshAllConfs();
				but.closest('tr').remove();
			} else {
				refreshAttendaces();
				but.removeClass('dontAttendWR');
				but.addClass('attend');
				but.html("Attend");
			}
		},
		error : function() {
			alert("ERROR");
		}
	});
}

$(document).on("click", ".attend", function() {
	var id = $(this).attr('name');
	id = parseInt(id);
	attend(id, $(this));
});
$(document).on("click",".dontAttend", function() {
	var id = $(this).attr('name');
	id = parseInt(id);
	dontAttend(id, $(this), true);
});
$(document).on("click",".dontAttendWR", function() {
	var id = $(this).attr('name');
	id = parseInt(id);
	dontAttend(id, $(this), false);
});
$(document).on("click", ".editConf", function() {
	var id = $(this).attr('name');
	id = parseInt(id);
	$.ajax({
		url : "/conferences/" + id,
		type: "GET",
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			removeActive();
			$("#newConfName").val(data.name);
			$("#newConfTheme").val(data.theme);
			$("#newConfPlace").val(data.place);
			$("#startDate").val(data.startDate);
			$("#endDate").val(data.endDate);
			$("#shortDescr").val(data.shortDescription);
			$("#longDescr").val(data.longDescription);
			$("#confId").val(id);
			$("#addConfForm").css("display", "block");
		},
		error : function(data) {
			console.log(data);
			alert("ERROR");
		}
	});
});