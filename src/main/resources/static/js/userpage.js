var MAXPAGE = 5;
$(function () {
    fuzzyOrder(1);
    $("#pre").click(function () {
        var current = sessionStorage.getItem('current');
        if (current > 1) {
            current--;
            fuzzyOrder(current);
        }
    });
    // if(sessionStorage.getItem("current") <= 1){
    //     $(this).addClass("disabled");
    // }else{
    //     $(this).removeClass("disabled");
    // }
    $("#next").click(function () {
        var current = sessionStorage.getItem('current');
        var pages = sessionStorage.getItem('pages');
        if (current < pages) {
            current++;
            // $(this).removeClass("disabled");
            fuzzyOrder(current);
        } else {
            // $(this).addClass("disabled");
        }
    });
    // if(sessionStorage.getItem("current") >= sessionStorage.getItem("pages")){
    //     $("#next").addClass("disabled");
    // }
    document.addEventListener('keydown', function (event) {
        if (event.key === 'ArrowLeft') {
            $("#pre").click();
        } else if (event.key === 'ArrowRight') {
            $("#next").click();
        }
    });
    window.parent.document.addEventListener('keydown', function (event) {
        if (event.key === 'ArrowLeft') {
            $("#pre").click();
        } else if (event.key === 'ArrowRight') {
            $("#next").click();
        }
    });
    $("#search").click(function () {
        fuzzyOrder(1);
    });
    // $("#search").on('click', function () {
    //     fuzzyOrder(1);
    // });
    $("#save").on("click", function () {
        var warehouse = $("#savewarehouse").val();
        var agent = $("#saveagent").val();
        var whereabouts = $("#savesource").val();
        var data;
        if (whereabouts === '') {
            data=JSON.stringify({
                username: warehouse,
                passwd: agent
            })
        }else{
            data=JSON.stringify({
                username: warehouse,
                passwd: agent,
                authority: whereabouts
            })
        }
        $.post({
            url: "/user", //请求地址
            contentType: "application/json",
            data: data,
            success: function (data) {
                if (data.result === "success") {
                    // alert("新增成功");
                    console.log("新增成功");
                    fuzzyOrder(1);
                }
            }
        });
        $(this).siblings().click();
        $("#savewarehouse").val("");
        $("#saveagent").val("");
        $("#savesource").val("");
        // $("#updateModal").click();
    });
    $("#update").on("click", function () {
        updateOrder();
        // $("#updateModal").modal('hide');
        $(this).siblings().click();
        $("#uwarehouse").val("");
        $("#uagent").val("");
        $("#usource").val("");
        $("#updateModal").click();
    });
    $("#updateModal").on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('whatever');
        // console.log("id=" + id);
        var modal = $(this);
        modal.find('.modal-body textarea').val(id);
    });
    $("#tbody").on("click", "a", function () {
        var id = $(this).attr("index");
        sessionStorage.setItem("id", id);
        window.location.href = '/html/details.html';
    });
});


function loadTable() {
    $("tbody").empty();
    var pagedata = JSON.parse(sessionStorage.getItem("pagedata")).records;
    $.each(pagedata, function (index, value) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + (index + 1) + "</td>");
        tr.append("<td>" + value.username + "</td>");
        tr.append("<td>" + value.authority + "</td>");
        buttenUpdate = "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#updateModal' data-whatever='" + value.id + "'>修改</button>";
        buttenDelete = "<button type='button' class='btn btn-danger' onclick='deleteOrder(" + value.id + ")'>删除</button>";
        tr.append("<td>" + buttenUpdate + "&emsp;" + buttenDelete + "</td>");
        $("tbody").append(tr);
    });
}


function setsession(data) {
    sessionStorage.setItem("pagedata", JSON.stringify(data));
    sessionStorage.setItem("total", data.total);
    sessionStorage.setItem("current", data.current);
    sessionStorage.setItem("pages", data.pages);
}

function loadPagination() {
    $(".yemabiaoqian").remove();
    var pages = sessionStorage.getItem("pages");
    var current = sessionStorage.getItem("current");
    for (var i = 1; i <= pages; i++) {
        var li = $("<li class='page-item yemabiaoqian'></li>");
        li.append('<a class="page-link" href="javascript:;"> ' + i + '</a>');
        li.attr("index", i);
        li.on('click', function () {
            fuzzyOrder($(this).attr('index'));
        });
        if (i === parseInt(current)) {
            li.addClass("active");
            li.attr("aria-current", "page");
        }
        $("#next").before(li);
    }
}

function deleteOrder(id) {
    $.ajax({
        url: "/user/" + id,
        type: "DELETE",
        success: function (data) {
            if (data.result === "success") {
                fuzzyOrder(1);
            }
        }
    });
}

function updateOrder() {
    var warehouse = $("#uwarehouse").val();
    var agent = $("#uagent").val();
    var source = $("#usource").val();
    var id = $("#uid").val();
    var url;
    if (source === '') {
        url = "/user/update?id=" + id + "&username=" + warehouse + "&passwd=" + agent;
    } else {
        url = "/user/update?id=" + id + "&username=" + warehouse + "&passwd=" + agent + "&authority=" + source
    }
    $.ajax({
        url: url,
        success: function (data) {
            if (data.result === "success") {
                fuzzyOrder(1);
            }
        }
    });
}

function fuzzyOrder(page) {
    $.get({
        url: "/user/fuzzy",
        data: {
            "page": page,
            "size": MAXPAGE,
            "username": $(".receiptNumber").val(),
            "authority": $(".warehouse").val(),
        },
        success: function (data) {
            if (data.result === "success") {
                setsession(data.data);
                loadTable();
                loadPagination();
            }
        }
    });
}

function fmtDateTime(obj) {
    var d = new Date(obj);
    var year = d.getFullYear();
    var month = "0" + (d.getMonth() + 1);
    var day = "0" + d.getDate();
    return year + '-' + month.substring(month.length - 2, month.length) + '-' + day.substring(day.length - 2, day.length);
}

