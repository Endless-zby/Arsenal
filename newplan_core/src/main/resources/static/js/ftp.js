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
                    var year = da.getFullYear()+'年';
                    var month = da.getMonth()+1+'月';
                    var date = da.getDate()+'日';


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
                        "                            <li>" + Number(fileList[i].filesize / 1000) + "Mb" + "</li>\n" +
                        "                            <li>" + [year,month,date].join('-') + "</li>\n" +
                        "                            <li>" + fileList[i].filepath + "</li>\n" +
                        "                        </ul>\n" +
                        "                    </div>\n" +
                        "                    <button class=\"button\">下载</button>\n" +
                        "                </div>\n" +
                        "        </div>" +
                        "</<br>";
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