function $ (id) {
	return document.getElementById(id);
}

var xhr = new XMLHttpRequest();
var myFile = "";

function upload () {
	var formData = new FormData();
	formData.append("file", $("file").files[0]);
	myFile = $("file").files[0].name;
	
	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			$("uploadStatus").textContent = xhr.responseText + "\nFile uploaded";
		}
	}
	
	xhr.open('POST', '/Socialistic/setAvatar', true);
	xhr.send(formData);
	
}