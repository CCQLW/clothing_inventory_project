var MAXPAGE = 5;
$(function () {
    getTable(1, MAXPAGE);
    $("#pre").click(function () {
        var current = sessionStorage.getItem('current');
        if (current > 1) {
            current--;
            getTable(current, MAXPAGE);
        }
    });
    $("#next").click(function () {
        var current = sessionStorage.getItem('current');
        var pages = sessionStorage.getItem('pages');
        if (current < pages) {
            current++;
            getTable(current, MAXPAGE);
        }
    });
});


function loadTable() {
    $("tbody").empty();
    var pagedata = JSON.parse(sessionStorage.getItem("pagedata")).records;
    $.each(pagedata, function (index, value) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + (index + 1) + "</td>");
        tr.append("<td>" + value.receiptNumber + "</td>");
        tr.append("<td>" + value.warehouse + "</td>");
        tr.append("<td>" + value.storageTime + "</td>");
        tr.append("<td>" + value.agent + "</td>");
        tr.append("<td>" + value.whereabouts + "</td>");
        tr.append("<td>" + "<button type='button' class='btn-danger'>删除</button> " + "</td>");
        $("tbody").append(tr);
    });
}

function getTable(current, size) {
    $.get({
        url: "/delivery_order/pageorder",
        data: {
            "current": current,
            "size": size
        },
        success: function (data) {
            if (data.result === "success") {
                sessionStorage.setItem("pagedata", JSON.stringify(data.data));
                sessionStorage.setItem("total", data.data.total);
                sessionStorage.setItem("current", data.data.current);
                sessionStorage.setItem("pages", data.data.pages);
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
            getTable($(this).attr('index'), MAXPAGE);
        });
        if(i === parseInt(current)){
            li.addClass("active");
            li.attr("aria-current", "page");
        }
        $("#next").before(li);
    }
}