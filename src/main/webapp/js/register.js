console.log("Visualizar proceso del registro en la parte inferior");

(function ($) {
    $.fn.serializeFormJSON = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

// function upload(){

//     function $ (id) {
//         return document.getElementById(id);
//     }

//     var formData = new FormData();
//     formData.append("file", $("file").files[0]);
//     myFile = $("file").files[0].name;

//     request(formData, 'POST', '/Socialistic/setAvatarServlet')
//     .then(function(response){
//         if(response.status === 200){
//             alert("Profile image uploaded succesfully");
//         }else{
//             alert("Error uploading your profile image");
//             console.log(response);
//         }
//     }).catch(function(error){
//         alert(error);
//     })
// }

function register() {
    const json = $("#registerForm").serializeFormJSON();
    request(json, 'POST', '/Socialistic/registration')
    .then(function(response){
        if(response.status === 200){
            alert("Registration Succesfull!");
            alert("Proceed to set a profile avatar");
            window.location.replace("avatar.html")
        }else{
            alert("Something went wrong");
            console.log(response);
        }
    }).catch(function(error){
        alert(error);
    })


}