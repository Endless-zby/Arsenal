document.write("<script type='text/javascript' th:src=@{/static/js/sweetalert-dev.js}'></script>");
function selFinance(page) {

    var page = page;
    if(page > 10000){
        return false;
    }
    var content = document.getElementById("context");
    var pages = document.getElementById("page");

    var res = '';
    var pagecon = '';
    $.ajax({
        url:"/FinanceHandle/selfinance/" + page,
        dataType:'json',
        // contentType: "application/json;charset=utf-8",
        type:"GET",
        headers:{
            'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
        },
        success:function(Result){
            if(Result.flag){
                var financeList = Result.data.content;
                var num = Result.data.pageable.pageNumber;
                var pagenum = Result.data.pageable.totalPages;
                for (var i = 0; i < financeList.length; i++) {
                    var photo = '';
                    if(financeList[i].purpose == '交通'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/car.png';
                    }else if(financeList[i].purpose == '零食'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/snack.png';
                    }else if(financeList[i].purpose == '饮食'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/eating.png';
                    }else if(financeList[i].purpose == '娱乐'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/game.png';
                    }else if(financeList[i].purpose == '约会'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/love.png';
                    }else if(financeList[i].purpose == '医疗'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/medical.png';
                    }else if(financeList[i].purpose == '其他'){
                        photo = 'https://finance-1257201044.cos.ap-chengdu.myqcloud.com/apps.png';
                    }

                    res += "<div class=\"author\">\n" +
                        "                    <img src=" + photo + " alt=\"\">\n" +
                        "                    <div class=\"text\">\n" +
                        "                    <h5>" + financeList[i].purpose + "</h5>\n" +
                        "                    <span>" + "￥ " + financeList[i].money + "</span>\n" +
                        "                <div class=\"wrap-social\">\n" +
                        "                    <ul>\n" +
                        "                    <li><a href=\"\"><i class=\"fab fa-facebook-f\"></i></a></li>\n" +
                        "                <li><a href=\"\"><i class=\"fab fa-instagram\"></i></a></li>\n" +
                        "                <li><a href=\"\"><i class=\"fab fa-twitter\"></i></a></li>\n" +
                        "                <li><a href=\"\"><i class=\"fab fa-google\"></i></a></li>\n" +
                        "                </ul>\n" +
                        "                </div>\n" +
                        "                </div>\n" +
                        "                </div>";
                }
                content.innerHTML=res;
                //page
                pagecon = "<li><a href=\"javascript:void(0);\" onclick=\"return selFinance("+ num +")\">"+ num +"</a></li>\n" +
                    "\t\t\t\t<li class=\"disabled\"><a class=\"active\">"+ (Number(num + 1)) +"</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return selFinance("+ (Number(num + 2)) +")\">"+(Number(num + 2))+"</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return selFinance("+ (Number(num + 3)) +")\">"+(Number(num + 3))+"</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return selFinance("+ (Number(num + 4)) +")\">"+(Number(num + 4))+"</a></li>\n" +
                    "\t\t\t\t<li><a href=\"javascript:void(0);\" onclick=\"return selFinance("+ (Number(num + 5)) +")\">"+(Number(num + 5))+"</a></li>";
                pages.innerHTML=pagecon;

                return true;

            }else {
                swal("异常",Result.message,"error");
                return false;
            }
        }
    });
}






















