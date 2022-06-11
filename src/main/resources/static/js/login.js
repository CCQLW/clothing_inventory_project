function signin() {
        var username = document.querySelector('#user').value;
        var passwd = document.querySelector('#pass').value;
        if (username === ''||passwd === '') {
            alert('用户名或密码不能为空');
            return ;
        }
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/user/login',false);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            var resp = JSON.parse(xhr.responseText);
            // console.log(resp);
            if (resp.result === "success") {
                console.log(resp.data);
                sessionStorage.setItem("userid", resp.data.id);
                window.location.href = '/html/index.html';
            } else {
                console.log(resp.result);
                // alert(resp.result);
            }
        };
        xhr.send(JSON.stringify({"username": username, "passwd": passwd}));
}


