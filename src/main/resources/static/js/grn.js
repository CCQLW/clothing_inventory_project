var MAXPAGE = 5;
$(function () {
    getTableByConditions(1);
    $("#pre").click(function () {
        var current = sessionStorage.getItem('current');
        if (current > 1) {
            current--;
            getTableByConditions(current);
        }
    });
    $("#next").click(function () {
        var current = sessionStorage.getItem('current');
        var pages = sessionStorage.getItem('pages');
        if (current < pages) {
            current++;
            getTableByConditions(current);
        }
    });
    document.addEventListener('keydown', function (event) {
        if (event.key === 'ArrowLeft') {
            $("#pre").click();
        }else if (event.key === 'ArrowRight') {
            $("#next").click();
        }
    });
    window.parent.document.addEventListener('keydown', function (event) {
        if (event.key === 'ArrowLeft') {
            $("#pre").click();
        }else if (event.key === 'ArrowRight') {
            $("#next").click();
        }
    });
    $("#tbody").on('click', 'button', function () {
        var id = $(this).attr("id");
        var cals = $(this).attr("class");
        if(cals === 'btn btn-danger') {
            // console.log(id);
            $.ajax({
                url: "/grn/delete/" + id, //请求地址
                type: "DELETE", //请求方式
                success: function (data) {
                    if (data.code === 20021) {
                        alert("删除成功");
                        getTableByConditions(1);
                    }
                }
            });
        }
    });
    $("#tbody").on("click", "a", function () {
        var id = $(this).attr("id");
        localStorage.setItem("grnId", id);
        // console.log(id);
        window.location.href = '../html/warehousingDatailPate.html';
    });
    $("#save").on("click", function () {
        var warehouse = $("#warehouse").val();
        var agent = $("#agent").val();
        var source = $("#source").val();
        if(warehouse === ''||agent ===  ''||source === ''){
            alert('输入不能为空');
            return ;
        }
        $.ajax({
            url: "/grn/save", //请求地址
            type: "POST", //请求方式
            contentType: "application/json",
            data: JSON.stringify({
                warehouse: warehouse,
                agent: agent,
                source: source
            }),
            success: function (data) {
                if (data.code === 20011) {
                    alert("新增成功");
                    getTableByConditions(1);
                }
            }
        });
        $(this).siblings().click();
        $("#warehouse").val("");
        $("#agent").val("");
        $("#source").val("");
        $("#myModal").click();
    });
    $("#get").click(function () {
        getTableByConditions(1);
    });
    $("#update").on("click", function () {
        var warehouse = $("#uwarehouse").val();
        var agent = $("#uagent").val();
        var source = $("#usource").val();
        var id = $("#uid").val();
        console.log(id);
        if(warehouse === ''&&agent === ''&&source === ''){
            alert("未修改值");
            return;
        }
        // console.log(id);
        $.ajax({
            url: "/grn/update", //请求地址
            type: "PUT", //请求方式
            contentType: "application/json",
            data: JSON.stringify({
                id: id,
                warehouse: warehouse,
                agent: agent,
                source: source
            }),
            success: function (data) {
                if (data.code === 20031) {
                    alert("修改成功");
                    getTableByConditions(1);
                }
            }
        });
        $(this).siblings().click();
        $("#warehouse").val("");
        $("#agent").val("");
        $("#source").val("");
        $("#updateModal").click();
    });
    $("#updateModal").on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('whatever');
        console.log("1234" + id);
        var modal = $(this);
        modal.find('.modal-body textarea').val(id);
    });
});

function setsession(data) {
    sessionStorage.setItem("pagedata", JSON.stringify(data));
    sessionStorage.setItem("total", data.total);
    sessionStorage.setItem("current", data.current);
    sessionStorage.setItem("pages", data.pages);
}

function loadTable() {
    $("tbody").empty();
    var pagedata = JSON.parse(sessionStorage.getItem("pagedata")).records;
    $.each(pagedata, function (index, value) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + (index + 1) + "</td>");
        tr.append("<td><a class='nav-link' role='button' " + "id=" + value.id + ">" + value.receiptNumber + "</a></td>");
        tr.append("<td>" + value.warehouse + "</td>");
        tr.append("<td>" + fmtDateTime(value.storageTime) + "</td>");
        // tr.append("<td>" + fmtDateTime(value.storageTime) + "</td>");
        tr.append("<td>" + value.agent + "</td>");
        tr.append("<td>" + value.source + "</td>");
        tr.append("<td>" + "<div class='row'>" +
            "<div class='col-md-3'><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#updateModal'" + "data-whatever='" + value.id + "'>修改</button></div>" +
            // "<div class='col-md-1'></div>" +
            "<div class='col-md-3'><button type='button' class='btn btn-danger' "+ "id='" + value.id + "'>删除</button></div></div></td>")
        $("tbody").append(tr);
    });
}

function fmtDateTime(obj) {
    var d = new Date(obj);
    var year = d.getFullYear();
    var month = "0" + (d.getMonth() + 1);
    var day = "0" + d.getDate();
    var time = year + '-' + month.substring(month.length - 2, month.length) + '-' + day.substring(day.length - 2, day.length);
    return time;
}

function getTableByConditions(current) {
    var receiptNumber = $("#getReceiptNumber").val();
    var warehouse = $("#getWarehouse").val();
    var agent = $("#getAgent").val();
    var source = $("#getSource").val();
    $.get({
        url: "/grn/get",
        data: {
            "current": current,
            "receiptNumber": receiptNumber,
            "warehouse": warehouse,
            "agent": agent,
            "source": source
        },
        success: function (data) {
            if (data.code === 20041) {
                setsession(data.data);
                loadTable();
                loadPagination();
            }
        }
    });
}

function loadPagination() {
    $(".yemabiaoqian").remove();
    var pages = sessionStorage.getItem("pages");
    var current = sessionStorage.getItem("current");
    for (var i = 1; i <= pages; i++) {
        var li = $("<li class='page-item yemabiaoqian'></li>");
        // li.append('<a class="page-link" href="javascript:;" onclick="page(' + i * MAXPAGE + ',' + (i * MAXPAGE + MAXPAGE - 1) + ')"' + 'index="' + i + '"' + i + '</a>');
        li.append('<a class="page-link" href="javascript:;"> ' + i + '</a>');
        li.attr("index", i);
        li.on('click',function () {
            getTableByConditions($(this).attr('index'));
        });
        if(i === parseInt(current)){
            li.addClass("active");
            li.attr("aria-current", "page");
        }
        $("#next").before(li);
    }
}