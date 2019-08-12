

$(function(){
    // 登陆
    $("#login").unbind("click").bind("click", function(){
        var username=$("input[name=username]").val();
        var password=$("input[name=password]").val();
        if(username==""||username==null){
            alert("用户名不能为空！");
            return false;
        }else if(password==""||password==null){
            alert("密码不能为空！");
            return false;
        }else{
            $.ajax({  // ajax登陆请求
                url:"/user/login",
                dataType:'json',
                contentType: "application/json;charset=utf-8",
                type:"post",
                data:JSON.stringify({"username":username,"password":password}),
                async:false,
                success:function(Result){
                    if(Result.flag == true){
                        window.location.href = '/arsenal/view';
                        // alert("跳转成功")
                    }else {
                        alert("错误代码：" + Result.code + Result.message);
                    }
                }
            });
        }
    });
});
