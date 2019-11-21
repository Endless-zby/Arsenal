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
                        "                            <li>上传用户:" + fileList[i].nickname + "</li>\n" +
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
            "localPath": 'E:\\ddd'
        },
        headers: {
            'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
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
            swal({
                title: "<small>上传中。。。</small>",
                text: "<progress id=\"progressBar\" value=\"0\" max=\"100\" style=\"width: 240px;\"></progress>\n" +
                    "            <span id=\"percentage\"></span><span id=\"time\"></span>",
                html: true
            });
            var file = $('#multipartFile')[0].files[0];
            var url = "/FtpHandle/upload"; // 接收上传文件的后台地址 /这里在后台做了跨域处理

            var formData = new FormData();// FormData 对象
            formData.append("multipartFile", file);// 文件对象

            xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
            xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
            xhr.onload = uploadComplete; //请求完成
            xhr.onerror = uploadFailed; //请求失败
            xhr.setRequestHeader("Authrorization",window.localStorage.getItem("Authrorization"));
            xhr.upload.onprogress = progressFunction;//【上传进度调用方法实现】
            xhr.upload.onloadstart = function () {//上传开始执行方法
                ot = new Date().getTime();   //设置上传开始时间
                oloaded = 0;//设置上传开始时，以上传的文件大小为0
            };

            xhr.send(formData); //开始上传，发送form数据
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

//上传成功响应
function uploadComplete(evt) {
    //服务断接收完文件返回的结果

    var data = JSON.parse(evt.target.responseText);
    if (data.flag == true) {
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

}

//上传失败
function uploadFailed(evt) {
    swal("提示", "请重试", "error")
}

//取消上传
function cancleUploadFile() {
    xhr.abort();
}


//上传进度实现方法，上传过程中会频繁调用该方法
function progressFunction(evt) {
    var progressBar = document.getElementById("progressBar");
    var percentageDiv = document.getElementById("percentage");
    // event.total是需要传输的总字节，event.loaded是已经传输的字节。如果event.lengthComputable不为真，则event.total等于0
    if (evt.lengthComputable) {//
        progressBar.max = evt.total;
        progressBar.value = evt.loaded;
        percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
    }
    var time = document.getElementById("time");
    var nt = new Date().getTime();//获取当前时间
    var pertime = (nt - ot) / 1000; //计算出上次调用该方法时到现在的时间差，单位为s
    ot = new Date().getTime(); //重新赋值时间，用于下次计算
    var perload = evt.loaded - oloaded; //计算该分段上传的文件大小，单位b
    oloaded = evt.loaded;//重新赋值已上传文件大小，用以下次计算
    //上传速度计算
    var speed = perload / pertime;//单位b/s
    var bspeed = speed;
    var units = 'b/s';//单位名称
    if (speed / 1024 > 1) {
        speed = speed / 1024;
        units = 'k/s';
    }
    if (speed / 1024 > 1) {
        speed = speed / 1024;
        units = 'M/s';
    }
    speed = speed.toFixed(1);
    //剩余时间
    var resttime = ((evt.total - evt.loaded) / bspeed).toFixed(1);
    time.innerHTML = '，速度：' + speed + units + '，剩余时间：' + resttime + 's';
    if (bspeed == 0) time.innerHTML = '上传已取消';
}



function getfilename() {
    var file = $("#multipartFile").val();
    var pos = file.lastIndexOf("\\");
    var _name  = file.substring(pos + 1);
    $('.upload span').html(_name);
}