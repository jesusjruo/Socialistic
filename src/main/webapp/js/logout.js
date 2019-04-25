
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


function logout() {
    request('', 'POST', '/Socialistic/logout')
    .then(function(response) {
        if(response.status === 200) {
            alert('Session ended, come back soon!')
            window.location.replace("../index.html");
        } else { 
            alert('Something wrong happened')
            console.log(response);
        }
    })
    .catch(function(error) {
        alert(error)
    })

}