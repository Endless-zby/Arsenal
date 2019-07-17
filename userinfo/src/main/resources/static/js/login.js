$(function(){

    $("table").css({"border":"1px solid red","margin":"0px auto"});

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
                url:"/UserController/login",
                dataType:'json',
                contentType: "application/json;charset=utf-8",
                type:"post",
                data:JSON.stringify({"username":username,"password":password}),
                async:false,
                success:function(Result){
                    if(Result.flag == true){
                        window.location.href = '/UserController/index1';
                    }else {
                        alert(Result.code + Result.message);
                    }
                }
            });
        }
    });

});
