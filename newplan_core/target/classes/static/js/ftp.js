/**
 * 页面展示
 * @param page
 * @returns {boolean}
 */
var w;

function fileList(page) {

    var page = page;
    if (page > 10000) {
        return false;
    }

    var right = document.getElementById("right");
    var pages = document.getElementById("page");
    var rightcontext = '';

    var pagecon = '';
    $.ajax({
        url: "/FtpHandle/basefileList/" + page,
        dataType: 'json',
        // contentType: "application/json;charset=utf-8",
        type: "GET",
        success: function (Result) {
            if (Result.flag) {
                var fileList = Result.data.content;
                var num = Result.data.pageable.pageNumber;
                for (var i = 0; i < fileList.length; i++) {

                    var da = fileList[i].timestamp;
                    da = new Date(da);
                    var year = da.getFullYear() + '年';
                    var month = da.getMonth() + 1 + '月';
                    var date = da.getDate() + '日';


                    rightcontext += "<div class=\"author\">\n" +
                        "                <div class=\"content\">\n" +
                        "                    <div class=\"wrap-head\">\n" +
                        "                        <h4>" + fileList[i].filename + "</h4>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"wrap-price bg-blue\">\n" +
                        "                        <h4><sup>下载</sup>" + fileList[i].filedownnum + "<span>次</span></h4>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"wrap-list\">\n" +
                        "                        <ul>\n" +
                        "                            <li>" + Number(fileList[i].filesize / 1000).toFixed(3) + "Kb" + "</li>\n" +
                        "                            <li>" + [year, month, date].join('-') + "</li>\n" +
                        "                            <li>" + fileList[i].filepath + "</li>\n" +
                        "<button onclick='return delfile(\"" + fileList[i].filename + "\")' class=\"btn btn-danger\">删除</button>" +
                        "                        </ul>\n" +
                        "                    </div>\n" +
                        "                    <button class=\"button\" onclick='return download(\"" + fileList[i].filename + "\")'>下载</button>\n" +
                        "                </div>\n" +
                        "        </div>";
                }
                right.innerHTML = rightcontext;
                //page
                pagecon = "<li><a href=\"javascript:void(0);\" onclick=\"return fileList(" + num + ")\">" + num + "</a></li>\n" +
                    "\t\t\t\t<li class=\"disabled\"><a class=\"active\">" + (Number(num + 1)) + "</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return fileList(" + (Number(num + 2)) + ")\">" + (Number(num + 2)) + "</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return fileList(" + (Number(num + 3)) + ")\">" + (Number(num + 3)) + "</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return fileList(" + (Number(num + 4)) + ")\">" + (Number(num + 4)) + "</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return fileList(" + (Number(num + 5)) + ")\">" + (Number(num + 5)) + "</a></li>";
                pages.innerHTML = pagecon;

                return true;

            } else {
                swal("异常", Result.message, "error");
                return false;
            }
        }
    });
}

/**
 * 文件下载
 * @param filename
 */
function download(filename) {

    var filename = filename + "";
    $.ajax({
        url: "/FtpHandle/downFile",
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        type: "GET",
        async: 'false',
        data: {
            "fileName": filename,
            "localPath": '/storage/emulated/0/Download'
        },
        success: function (Result) {
            if (Result.flag) {
                swal("成功", "下载成功", "success");
                return false;
            } else {
                swal("提示", Result.message, "error");
                return false;
            }
        }

    });
}

/**
 * 文件删除
 * @param filename
 */
function delfile(filename) {
    var filename = filename + "";

    swal(
        {
            title: "提示",
            text: "您确定要删除文件【" + filename + "】?",
            type: "info",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                //删除
                $.ajax({
                    url: '/FtpHandle/delFile',
                    dataType: 'json',
                    // contentType: "application/json;charset=utf-8",
                    type: 'GET',
                    data: {
                        "fileName": filename
                    },
                    headers: {
                        'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
                    },
                    success: function (Result) {
                        if (Result.flag == true) {
                            swal({
                                title: "提示",
                                text: "删除成功",
                                type: "success"
                            }, function () {
                                fileList(1)
                            })
                        } else {
                            swal("提示", Result.message, "error")
                        }
                    }
                });

            } else {
                //取消
                swal({
                    title: "提示",
                    text: "取消删除",
                    type: "success"
                })
            }
        }
    )
}

/**
 * 文件上传
 */
function fileupload() {
    swal({
        title: "提示",
        text: "确认上传该文件？",
        type: "info",
        showCancelButton: true,
        confirmButtonColor: "#CD6600",
        confirmButtonText: "上传",
        cancelButtonText: "取消",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            var file = $('#multipartFile')[0].files[0];
            var formData = new FormData();
            formData.append("multipartFile", file);
            $.ajax({
                url: "/FtpHandle/upload",
                type: 'POST',
                data: formData,
                dataType: 'json',
                contentType: false,//告诉jquery不需要增加请求头对于contentType的设置
                processData: false,//告诉jquery要传输data对象
                headers: {
                    'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
                    // 'Content-Type': 'multipart/form-data'
                },
                success: function (Result) {
                    if (Result.flag == true) {
                        swal({
                            title: "提示",
                            text: "上传成功",
                            type: "success"
                        }, function () {
                            fileList(1)
                        })
                    } else {
                        swal("提示", Result.message, "error")
                    }
                },
                error: function (err) {
                    swal("提示", err.error, "error")
                }
            });


        } else {
            //取消
            swal({
                title: "提示",
                text: "取消上传",
                type: "success"
            })
        }
    })
}

