function register(){
    let json = $("#registerform").serialize();
    console.log(json);
    request(json, 'POST', '/registration')
    .then(function(response){
        if(response.status === 200){
            alert("Te has registrado correctamente");
            alert("Proceda a iniciar sesion");
            window.location.replace("login.html")
        }else{
            alert("Ha ocurrido un error al registrarte");
            console.log(response);
        }


    }).catch(function(error){
        alert(error);
    })


}