
    $(document).ready(function(){
        $('#sleep').click(function () {
            window.location.href="/time/sleep";//跳转
        });
    });

    $(document).ready(function(){
        $('#spline').click(function () {
            window.location.href="spline";//跳转
        });
    });
    $(document).ready(function(){
        $('#queryfinance').click(function () {
            window.location.href="showfinance";//跳转
        });
    });
    function getDateStr(date) {
        var month = date.getMonth() + 1;
        var strDate = date.getFullYear() + '-' + month + '-' + date.getDate();
        return strDate;
    }
    $(function(){
       //提交时间
        $("#button").unbind("click").bind("click", function(){
            var appDate=$("input[name=appDate]").val();
            var appTime=$("input[name=appTime]").val();
            var d2 = new Date($("#appDate").val() + ' ' + $("#appTime").val());
            var d = new Date();
            if(d2 > d){
                alert('狗怂,不许作弊！咋,想穿越啊！');
                return false;
            }
            if(appDate==""||appDate==null){
                alert("日期不能为空！");
                return false;
            }else if(appTime==""||appTime==null){
                alert("时间不能为空！");
                return false;
            }else{
                $.ajax({  // ajax登陆请求
                    url:"/time/submit",
                    dataType:'json',
                    contentType: "application/json;charset=utf-8",
                    type:"post",
                    data:JSON.stringify({"appDate":appDate,"appTime":appTime}),
                    async:false,
                    success:function(Result){
                        if(Result.flag == true){
                            alert();
                            // alert("跳转成功")
                        }else {
                            alert("错误代码：" + Result.code + Result.message);
                        }
                    }
                });
            }
        });
    });