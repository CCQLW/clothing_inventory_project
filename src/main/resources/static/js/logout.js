function logout() {
    document.querySelector('.logout').addEventListener('click', function () {
        var xhr = new XMLHttpRequest();
        xhr.open('get', '/user/logout?username=');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            var resp = JSON.parse(xhr.responseText);
            // console.log(resp);
            if (resp.result === "success") {
                console.log(resp.data);
                // alert("ccc")
                // window.location.href = 'www.baidu.com';
            } else {
                console.log(resp.data);
            }
        };
        xhr.send(JSON.stringify({"username": username, "passwd": passwd}));
    });
}