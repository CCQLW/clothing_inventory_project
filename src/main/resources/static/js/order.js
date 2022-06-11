var MAXPAGE = 5;
$(function () {
    fuzzyOrder(1);
    $("#pre").click(function () {
        var current = sessionStorage.getItem('current');
        if (current > 1) {
            current--;
            getTable(current);
        }
    });
    $("#next").click(function () {
        var current = sessionStorage.getItem('current');
        var pages = sessionStorage.getItem('pages');
        if (current < pages) {
            current++;
            getTable(current);
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
    // $("#search").click(fuzzyOrder(1));
    // $("#search").click(function () {
    //     fuzzyOrder(1);
    // });
    $("#search").on('click', function () {
        sessionStorage.setItem("a", $("#receiptNumber").val());
        fuzzyOrder(1);
        sessionStorage.setItem("a", "");
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
        buttenDelete = "<button type='button' class='btn btn-danger' onclick='deleteOrder(" + value.id + ")'>删除</button>";
        buttenUpdate = "<button type='button' class='btn btn-primary' onclick='updateOrder(" + value.id + ")'>修改</button>";
        tr.append("<td>" + buttenUpdate + buttenDelete + "</td>");
        $("tbody").append(tr);
    });
}

function getTable(current) {
    $.get({
        url: "/delivery_order/pageorder",
        data: {
            "current": current,
            "size": MAXPAGE
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
        // li.append('<a class="page-link" href="javascript:;" onclick="page(' + i * MAXPAGE + ',' + (i * MAXPAGE + MAXPAGE - 1) + ')"' + 'index="' + i + '"' + i + '</a>');
        li.append('<a class="page-link" href="javascript:;"> ' + i + '</a>');
        li.attr("index", i);
        li.on('click', function () {
            getTable($(this).attr('index'));
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
        url: "/delivery_order/" + id,
        type: "DELETE",
        success: function (data) {
            if (data.result === "success") {
                getTable(1);
            }
        }
    });
}
function updateOrder(id) {
    $.put({
        url: "/delivery_order/",
        data: {
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
function fuzzyOrder(page) {
    console.log($(".receiptNumber").val());
    // console.log($(".form-control.receiptNumber").text());
    // console.log($(".form-control.receiptNumber").html());
    $.get({
        url: "/delivery_order/fuzzy",
        data: {
            "page": page,
            "size": MAXPAGE,
            "receiptNumber": sessionStorage.getItem("a"),
            // "receiptNumber": "1",
            "warehouse": $(".warehouse").val(),
            // "storageTime": $("#storageTime").val(),
            "agent": $(".agent").val(),
            "whereabouts": $(".whereabouts").val()
        },
        success: function (data) {
            console.log(data);
            if (data.result === "success") {
                setsession(data.data);
                loadTable();
                loadPagination();
            }
        }
    });
}