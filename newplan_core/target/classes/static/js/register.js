document.write("<script type='text/javascript' th:src=@{/static/js/sweetalert-dev.js}'></script>");

function send(obj) {
    var phone = $("#phone").val();
    if(phone==""||phone==null){
        swal("手机号不能为空！");
        return false;
    }
    $.ajax({
        url:"/check/sms/" + phone,
        dataType:'text',
        contentType: "application/json;charset=utf-8",
        type:"get",
        success:function(Result){
            if (!Result){
                swal("发送失败");
                return false;
            }
        }
    });
    setTime(obj);//开始倒计时
}


var countdown = 60;
function setTime(obj) {
    if (countdown == 1) {
        // obj.text = '点击重试！';
        document.getElementById("settime").disabled=false;
        document.getElementById("settime").innerText= '重试！';
        // obj.onclick = send(this);
        countdown = 60;//60秒过后button上的文字初始化,计时器初始化;
        return false;
    } else {
        // obj.text = "("+countdown+"s)" ;
        document.getElementById("settime").disabled=true;
        document.getElementById("settime").innerText= "("+countdown+"s)";
        countdown--;
    }
    setTimeout(function() { setTime(obj) },1000) //每1000毫秒执行一次
}



function register() {
    //跳转到注册界面register.html进行注册
    // window.open("register.html", "_blank");  //_self,_parent,_top,_blank
    var phone=$("#phone").val();
    var smsCode=$("#smsCode").val();
    var type=$("#type").val();
    var password=$("#password").val();
    var passwords=$("#passwords").val();
    var email=$("#email").val();

    if(password == passwords){
    }else {
        document.getElementById('passwords').value = '';
        swal("提示","两次密码输入不一致！","error");
        return false;
    }

    $.ajax({  // ajax登陆请求
        url:"/user/register/" + smsCode,
        dataType:'json',
        contentType: "application/json;charset=utf-8",
        type:"post",
        data:JSON.stringify({
            "phone":phone,
            "email":email,
            "type":type,
            "password":password
        }),
        success:function(register){
            if(register.flag){
                swal({title:"注册成功",
                    text:"即将跳转",
                    type:"success"},function(){window.location='/user/login'})
            }else {
                swal("提示","验证码失效！","error");
                return false;
            }
        }

    });
}

function bluree(phone) {
    if(phone != ''){
    $.ajax({  // ajax登陆请求
        url:"/check/username/" + phone,
        dataType:'text',
        contentType: "application/json;charset=utf-8",
        type:"get",
        // async:false,
        success:function(Result){
            if(Result == 'true'){
                document.getElementById('phone').value = '' ;
                swal("提示","当前手机号已存在","info");
            }else {

            }

        }
    });
    }
}


