document.write("<script type='text/javascript' th:src=@{/static/js/sweetalert-dev.js}'></script>");
document.write("<script type='text/javascript' th:src='@{/static/js/highcharts.js}'></script>");

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

                    var ids =  financeList[i].id + "";

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
                        "                <li><a href=\"javascript:void(0);\" onclick='return delfinance(\""+ids + "\")'><i class=\"fab fa-google\"></i></a></li>\n" +
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


function reqfinance() {
    document.getElementById("sendfinance").disabled = 'disabled';//失效按钮
    var purpose=$("#purpose").val();
    var money=$("#money").val();
    var remark=$("#remark").val();
    $.ajax({
        url:'/FinanceHandle/savefinance',
        dataType:'json',
        contentType: "application/json;charset=utf-8",
        type:"POST",
        data:JSON.stringify({"purpose":purpose,"money":money,"remark":remark}),
        headers:{
            'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
        },
        success:function(Result){
            if(Result.flag == true){
                document.getElementById("sendfinance").removeAttribute("disabled");//恢复按钮
                selFinance(1);
                document.getElementById("purpose").value = '';
                document.getElementById("money").value = '';
                document.getElementById("remark").value = '';
                swal("提示","提交成功","success")
            }else {
                swal("提示",Result.data.message,"error")
            }
        }
    });

}

function delfinance(id) {
    swal(
        {title:"提示",
            text:"您确定要删除当前记录吗?",
            type:"info",
            showCancelButton:true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText:"删除",
            cancelButtonText:"取消",
            closeOnConfirm:false,
            closeOnCancel:false
        },
        function(isConfirm)
        {
            if(isConfirm)
            {
                //删除
                $.ajax({
                    url:'/FinanceHandle/delfinance/' + id,
                    dataType:'json',
                    // contentType: "application/json;charset=utf-8",
                    type:'GET',
                    // data:JSON.stringify({"_method":"DELETE"}),
                    headers:{
                        'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
                    },
                    success:function(Result){
                        if(Result.flag == true){
                            swal({title:"提示",
                                text:"您已删除此记录",
                                type:"success"},function(){selFinance(1)})
                        }else {
                            swal("提示",Result.data.message,"error")
                        }
                    }
                });

            }
            else{
                //取消
                swal({title:"提示",
                    text:"取消删除",
                    type:"success"})
            }
        }
    )

}

function view() {

    $.ajax({
        url:'/FinanceHandle/financeview',
        type:'get',
        dataType:'json',
        headers:{
            'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
        },
        success:function(result){
            if(result.flag){
                //数据处理
                var list = new Array();
                for(var key in result.data){

                    var pop = new Array();
                    pop.push(key);
                    pop.push(result.data[key]);
                    list.push(pop);
                }

                var chartzby = {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                };
                var titlezby = {
                    text: '总花销' + result.message
                };
                var tooltipzby = {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                };
                var plotOptionszby = {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}%</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                };
                var serieszby= [{
                    type: 'pie',
                    name: '占比',
                    data: list

                }];

                var json = {};
                json.chart = chartzby;
                json.title = titlezby;
                json.tooltip = tooltipzby;
                json.series = serieszby;
                json.plotOptions = plotOptionszby;

                swal({
                    title: "<small>视图</small>",
                    text: "<div id='zby' style='float: left; width: 350px; height: 350px; margin: 0 auto'></div>",
                    html: true
                });
                $('#zby').highchartss(json);
            }else {
                swal("异常",result.message,"info");

            }
        }


    });

}















