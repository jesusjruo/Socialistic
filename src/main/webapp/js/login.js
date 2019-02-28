function login() {
    let json = $("#loginform").serialize();
    console.log(json);
    request(json, 'POST', '/login')
    .then(function(response) {

        if(response.status === 200) {
            alert('Sesion Iniciada.')
            window.location.replace("dashboard.html");
        } else { 
            alert('Error al iniciar sesion')
            console.log(response);
        }
    })
    .catch(function(error) {
        alert(error)
    })

}