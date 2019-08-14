
    $(document).ready(function(){
        $('#sleep').click(function () {
            window.location.href="/time/sleep/1";//跳转
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
    $(document).ready(function(){
        $('#finance').click(function () {
            window.location.href="/time/savefinance/1";//跳转
        });
    });
    $(document).ready(function(){
        $('#red').click(function () {
            window.location.href="/time/redpage/1";//跳转
        });
    });
    $(document).ready(function(){
        $('#queryred').click(function () {
            window.location.href="showred";//跳转
        });
    });

    function getDateStr(date) {
        var month = date.getMonth() + 1;
        var strDate = date.getFullYear() + '-' + month + '-' + date.getDate();
        return strDate;
    }
    $(function(){
       //提交时间
        $("#button1").unbind("click").bind("click", function(){
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
                            alert("提交成功！")
                            window.location.href="/time/sleep/1";//跳转
                            // alert("跳转成功")
                        }else {
                            alert("错误代码：" + Result.code + Result.message);
                        }
                    }
                });
            }
        });
    });



    $(function(){
        //提交开销
        $("#onclick1").unbind("click").bind("click", function(){

            var purpose=$("#purpose").val();
            var money=$("input[name=money]").val();
            var time=$("input[name=appDateTime]").val();
            var name=$("#name").val();
            var remark=$("textarea[name=remark]").val();
            var status = $('#status').val();
            var vlue = $("#money").val();
            var str = vlue.indexOf(".");
            var numlength = 0;
            if (str != -1) {
                index = vlue.substring(str + 1, vlue.length);
                numlength = index.length;
            }
            var d2 = new Date($("#appDateTime").val());
            var d = new Date();
            if(d2 > d){
                alert('狗怂,不许作弊！咋,想穿越啊！');
                return false;
            }
            if(money==""||money==null){
                alert("没花钱？");
                return false;
            }else if(time==""||time==null){
                alert("把时间也记上吧");
                return false;
            }else if(remark==""||remark==null){
                alert("具体用途写一下吧");
                return false;
            }else if (isNaN(vlue) || (vlue <= 0) || (numlength > 2)){
                alert("金额必须大于0且最多有两位小数！");
                return false;
            } else{
                $.ajax({  // ajax登陆请求
                    url:"/time/finance",
                    dataType:'json',
                    contentType: "application/json;charset=utf-8",
                    type:"post",
                    data:JSON.stringify({"purpose":purpose,"money":money,"time":time,"remark":remark,"status":status,"tag1":name}),
                    async:false,
                    success:function(Result){
                        if(Result.flag == true){
                            alert("提交成功！");
                            window.location.href="/time/savefinance/1";//跳转
                        }else {
                            alert("错误代码：" + Result.code + Result.message);
                        }
                    }
                });
            }
        });
    });


    $(function(){
        //提交开销
        $("#onclick2").unbind("click").bind("click", function() {

            window.location.href="/time/showfinance";

        });
    });

    $(function(){
        //跳转睡眠视图
        $("#button2").unbind("click").bind("click", function() {

            window.location.href="/time/spline";

        });
    });

    function deletesleep(id) {
        $.ajax({
            url:"/time/deletesleep",
            dataType:'json',
            contentType: "application/json;charset=utf-8",
            type:"post",
            data:JSON.stringify({"id":id}),
            async:false,
            success:function(Result){
                if(Result.flag == true){
                    window.location.href="/time/sleep/1";//跳转
                }else {
                    alert("错误代码：" + Result.code + Result.message);
                }
            }
        });
    }

    function deletered(id) {
        $.ajax({
            url:"/time/deletered",
            dataType:'json',
            contentType: "application/json;charset=utf-8",
            type:"post",
            data:JSON.stringify({"id":id}),
            async:false,
            success:function(Result){
                if(Result.flag == true){
                    window.location.href="/time/redpage/1";//跳转
                }else {
                    alert("错误代码：" + Result.code + Result.message);
                }
            }
        });
    }

    $(function(){
        //提交开销
        $("#onclick3").unbind("click").bind("click", function(){


            var starttime=$("input[name=starttime]").val();
            var outtime=$("input[name=outtime]").val();
            var remark=$("textarea[name=remark]").val();

            var start = new Date($("#starttime").val());
            var end = new Date($("#outtime").val());
            if(end < start){
                alert('你确定时间是倒着的？');
                return false;
            }
            if(starttime==""||starttime==null){
                alert("把开始时间选上");
                return false;
            }else if(remark==""||remark==null){
                alert("具体用途写一下吧");
                return false;
            }else if (outtime==""||outtime==null){
                alert("把结束时间选上");
                return false;
            } else{
                $.ajax({  // ajax登陆请求
                    url:"/time/red",
                    dataType:'json',
                    contentType: "application/json;charset=utf-8",
                    type:"post",
                    data:JSON.stringify({"starttime":starttime,"outtime":outtime,"remark":remark}),
                    async:false,
                    success:function(Result){
                        if(Result.flag == true){
                            alert("提交成功！");
                            window.location.href="/time/redpage/1";//跳转
                        }else {
                            alert("错误代码：" + Result.code + Result.message);
                        }
                    }
                });
            }
        });
    });

    $(function(){
        //提交开销
        $("#onclick4").unbind("click").bind("click", function() {

            window.location.href="/time/showred";

        });
    });