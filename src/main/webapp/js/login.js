
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


function login() {
    let json = $("#loginform").serializeFormJSON();
    console.log(json);
    request(json, 'POST', '/Socialistic/login')
    .then(function(response) {
        if(response.status === 200) {
            alert('Session started succesfully!')
            window.location.replace("dashboard.html");
        } else { 
            alert('Something wrong happened')
            console.log(response);
        }
    })
    .catch(function(error) {
        alert(error)
    })

}