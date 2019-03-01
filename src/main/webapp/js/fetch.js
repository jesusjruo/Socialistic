function request(data, method, url) {
    console.log('fetching!')
    return new Promise((res, rej) => {
        switch (method) {
            case 'GET':
                {
                    fetch(url, {
                        method: 'GET'
                    })
                    .then((res) => res.json())
                    .then((data) => {
                        res(data);
                    })
                    .catch((err) => {
                        alert('Error while request...' + err.message);
                    })
                    break;
                }

            case 'POST':
                {
                    console.log('posting')
                    let datajson = {
                        method: 'POST',
                        body: data,
                        withCredentials: true,
                        credentials: 'same-origin',
                        headers: {
                            'Content-type': 'application/x-www-form-urlencoded'
                        }
                    };
                    console.log(datajson);
                    fetch(url, datajson)
                    .then((res) => res.json())
                    .then((data) => {
                        console.log(data);                    
                        res(data);
                    })
                    .catch((err) => {
                        console.log('here error')
                        console.log(err);
                    })
                    break;
                }
            case 'DELETE':
                {
                    let datajson = {
                        method: 'delete'
                    }
                    fetch(data.url + '/' + data.id, datajson)
                    .then((res) => res.json())
                    .then((data) => res(json))
                    .catch((e) => rej(e));
                    break;
                }
        }
    })

}