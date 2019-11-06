/*
init qq登录的请求地址
 */
$(document).ready(function () {

    $.ajax({
        url:"/user/getCode",
        type:"get",
        dataType:"text",
        success:function(code){
            if(code != null){
                document.getElementById("qqcode").href = code;
            }else{
                document.getElementById("qqcode").href = "www.zby123.club";
            }
        }
    })
});

/*
    登录请求
 */
function login() {
    document.write("<script language=javascript src='http://www.huangwx.cn/js/sweetalert-dev.js'></script>");
    var username=$("#username").val();
    var password=$("#password").val();

    $.ajax({  // ajax登陆请求
        url:"/user/login",
        dataType:'json',
        // contentType: "application/json;charset=utf-8",
        type:"post",
        data: {
            "username":username,
            "password":password
        },
        success:function(Result){
            alert(Result);
            if(Result.flag){
                headerSetup(Result.message);
                // request.setRequestHeader("Authorization", "Bearer " +Result.data);
                // alert("看放进去没：" + window.localStorage.getItem("Authrorization"));
                swal("测试阶段，看一下token！",window.localStorage.getItem("Authrorization"));
                // window.location.href="/pages/mainview/" + Result.data.id;//view

            }else {
                document.getElementById('password').value = '' ;
            }
        }
    });
}

/*
    保存token
 */
function headerSetup(token)
{
    document.write("<script language=javascript src='http://www.huangwx.cn/js/sweetalert-dev.js'></script>");
    var storage = window.localStorage;
    if(!storage){
        // alert("浏览器不支持localstorage");
        swal("完蛋","您的浏览器不支持Storage","error");
        return false;
    }
    storage.setItem("Authrorization", "Bearer " +token);
    storage.setItem("user_login_time", new Date().getTime());//保存登录时间
}

