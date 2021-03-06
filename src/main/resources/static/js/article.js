var MAXPAGE = 5;
$(function () {
    getTableByConditions(1);
    loadSelect();
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
        if(cals === 'btn btn-danger'){
            $.ajax({
                url: "/article_number/delete/" + id, //请求地址
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
    $("#save").on("click", function () {
        var tradeName = $("#tradeName").val();
        var colorNo = $("#colorNo").val();
        var size = $("#size").val();
        if(tradeName === ''||colorNo === ''||size ===  ''){
            alert('输入不能为空');
            return ;
        }
        $.ajax({
            url: "/article_number/save", //请求地址
            type: "POST", //请求方式
            contentType: "application/json",
            data: JSON.stringify({
                tradeName: tradeName,
                colorNo: colorNo,
                size: size
            }),
            success: function (data) {
                if (data.code === 20011) {
                    alert("新增成功");
                    getTableByConditions(1);
                }
            }
        });
        $(this).siblings().click();
        $("#tradeName").val("");
        $("#colorNo").val("");
        $("#size").val("");
        $("#myModal").click();
    });
    $("#get").click(function () {
        getTableByConditions(1);
    });
    $("#update").on("click", function () {
        var tradeName = $("#UtradeName").val();
        var colorNo = $("#UcolorNo").val();
        var size = $("#Usize").val();
        var id = $("#uid").val();
        // console.log(id);
        if(tradeName === ''&&colorNo === ''&&size === ''){
            alert("未修改值");
            return;
        }
        $.ajax({
            url: "/article_number/update", //请求地址
            type: "PUT", //请求方式
            contentType: "application/json",
            data: JSON.stringify({
                id: id,
                tradeName: tradeName,
                colorNo: colorNo,
                size: size
            }),
            success: function (data) {
                if (data.code === 20031) {
                    alert("修改成功");
                    getTableByConditions(1);
                }
            }
        });
        $(this).siblings().click();
        $("#UtradeName").val("");
        $("#UcolorNo").val("");
        $("#Usize").val("");
        $("#updateModal").click();

    });
    $("#updateModal").on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('whatever');
        // console.log("1234" + id);
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

function loadSelect(){
    var html = '';
    html += '<option>' + 'T恤' + '</option>';
    html += '<option>' + '长T' + '</option>';
    html += '<option>' + 'POLO衫' + '</option>';
    html += '<option>' + '棒球衫' + '</option>';
    html += '<option>' + '背心' + '</option>';
    html += '<option>' + '衬衫' + '</option>';
    html += '<option>' + '卫衣' + '</option>';
    html += '<option>' + '其他' + '</option>';
    $('#tradeName').append(html);
    $('#UtradeName').append(html);
}

function loadTable() {
    $("tbody").empty();
    var pagedata = JSON.parse(sessionStorage.getItem("pagedata")).records;
    $.each(pagedata, function (index, value) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + (index + 1) + "</td>");
        tr.append("<td>" + value.articleNumber + "</td>");
        tr.append("<td>" + value.tradeName + "</td>");
        tr.append("<td>" + value.colorNo + "</td>");
        tr.append("<td>" + value.size + "</td>");
        tr.append("<td>" + value.number + "</td>");
        tr.append("<td>" +
            "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#updateModal'" + "data-whatever='" + value.id + "'>修改</button>" +
            "&emsp;" +
            "<button type='button' class='btn btn-danger' "+ "id='" + value.id + "'>删除</button>" +
            "</td>")
        $("tbody").append(tr);
    });
}

function getTableByConditions(current) {
    var articleNumber = $("#getArticleNumber").val();
    var tradeName = $("#getTradeName").val();
    var colorNo = $("#getColorNo").val();
    var size = $("#getSize").val();
    $.get({
        url: "/article_number/get",
        data: {
            "current": current,
            "articleNumber": articleNumber,
            "tradeName": tradeName,
            "colorNo": colorNo,
            "size": size
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