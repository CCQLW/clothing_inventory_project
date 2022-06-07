function signin() {
    document.querySelector('.button.submit').addEventListener('click', function () {
        var username = document.querySelector('#user').value;
        var passwd = document.querySelector('#pass').value;
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/user/login');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            var resp = JSON.parse(xhr.responseText);
            // console.log(resp);
            if (resp.result === "success") {
                console.log(resp.data);
                window.location.href = '/static/html/index.html';
            } else {
                console.log(resp.data);
            }
        };
        xhr.send(JSON.stringify({"username": username, "passwd": passwd}));
    });
}

function login(){
    var username = document.querySelector('#user').value;
    var passwd = document.querySelector('#pass').value;
    var check = document.querySelector('#check').checked;
    if (user === '' || pass === '') {
        alert('用户名或密码不能为空');
        return false;
    }
    var xhr = new XMLHttpRequest();
    xhr.open('post', 'user/login');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send('username=' + username + '&passwd=' + passwd);
    xhr.onreadystatechange = function () {
        // console.log(xhr.responseText);
        if (xhr.readyState === 4 && xhr.status === 200) {
            var resp = JSON.parse(xhr.responseText);
            console.log(resp);
            if (resp.result === 'success') {
                console.log(resp.data);
                // alert('登录成功');
                // window.location.href = 'index.html';
            } else {
                console.log(resp.result);
                // alert('用户名或密码错误');
            }
        }
    }
}

