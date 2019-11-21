document.write("<script type='text/javascript' th:src=@{/static/js/sweetalert-dev.js}'></script>");

/*
   登录请求
 */
function login() {
    var username=$("#username").val();
    var password=$("#password").val();

    $.ajax({  // ajax登陆请求
        url:"/user/login",
        dataType:'json',
        // contentType: "application/json;charset=utf-8",
        type:"POST",
        data: {
            "username":username,
            "password":password
        },
        success:function(Result){

            if(Result.flag){
                // swal("登录",Result.data.nickname,"success")
                headerSetup(Result.message,Result.data.photo,Result.data.nickname);
                // swal({
                //     title: "两秒后跳转主页",
                //     showConfirmButton: false,
                //     imageUrl: "https://finance-1257201044.cos.ap-chengdu.myqcloud.com/timg.gif",
                //     showCancelButton: false,
                //     timer:2000
                // },function () {
                //     window.location.href = '/user/index';
                // });
                swal("登录",Result.data.nickname,"success");
                window.location.href = '/user/index';

                // request.setRequestHeader("Authorization", "Bearer " +Result.data);
                // alert("看放进去没：" + window.localStorage.getItem("Authrorization"));
                // swal("测试阶段，看一下token！",window.localStorage.getItem("Authrorization"));
                // window.location.href="/pages/mainview/" + Result.data.id;//view

            }else {
                document.getElementById('password').value = '' ;
            }
        }
    });
}

/*
    保存token和基本信息
 */
function headerSetup(qq_token,qq_photo,qq_nickname)
{
    var storage = window.localStorage;
    if(!storage){
        // alert("浏览器不支持localstorage");
        swal("完蛋","您的浏览器不支持Storage","error");
        return false;
    }
    storage.setItem("Authrorization", "Bearer " +qq_token);	//保存token
    storage.setItem("user_login_time", new Date().getTime());//保存登录时间
    storage.setItem("photo", qq_photo);//保存头像
    storage.setItem("nickname", qq_nickname);//保存昵称

}
/*
    登出
 */
function log_out() {
    var storage = window.localStorage;
    var token = storage.getItem('Authrorization');
    if(token != null){
        // storage.clear();
        // swal("登出","登出成功","success");
        // window.location.href = '/user/index';
        swal(
            {title:"登出",
                text:"您确定要退出当前登录吗?",
                type:"warning",
                showCancelButton:true,
                confirmButtonText:"登出",
                cancelButtonText:"取消",
                closeOnConfirm:false,
                closeOnCancel:false
            },
            function(isConfirm)
            {
                if(isConfirm)
                {
                    swal({title:"登出成功",
                        text:"您已退出当前登录",
                        type:"success"},function(){storage.clear();window.location='/user/index'})
                }
                else{
                    swal({title:"登出",
                        text:"取消登出操作",
                        type:"success"})
                }
            }
        )
    }else {
        swal("登出","当前没有用户登录","info");
    }






}