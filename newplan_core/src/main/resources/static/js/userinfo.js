function update() {
    var storage = window.localStorage;
    var username = $("#username").val();
    var nickname = $("#nicknames").val();
    var gender = $("#gender").val();
    var phone = $("#phone").val();
    var email = $("#email").val();



    if (phone != username) {
        swal({
                title: "提示",
                text: "检测到该账号绑定的手机号已改变，请输入新的号码",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                showCloseButton: false,
                allowOutsideClick:true,
                animation: "slide-from-top",
                inputPlaceholder: "Write something"
            },
            function (inputValue) {
                if (inputValue === false)
                    return false;
                if (!(/^1[3456789]\d{9}$/.test(inputValue))) {
                    swal.showInputError("这玩意不是手机号吧！");
                    return false;
                }
                if (inputValue === "") {
                    swal.showInputError("手机号不能为空");
                    return false;
                }
                phone = inputValue;
                $.ajax({
                    url: '/userinfo/checkNewPhone/' + inputValue,
                    type: 'get',
                    dataType: 'json',
                    headers: {
                        'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
                    },
                    success: function (Result) {
                        if (Result.flag == true) {
                            swal("Nice!", "短信验证码已经发送至您的手机：" + inputValue, "success");
                            swal({
                                title: "号码验证",
                                text: "请输入4位验证码",
                                type: "input",
                                showCancelButton: true,
                                closeOnConfirm: false,
                                animation: "slide-from-top",
                                inputPlaceholder: "Write something"
                            }, function (inputValue) {
                                var reg = /^\d{4}$/;

                                if (!reg.test(inputValue)) {
                                    swal.showInputError("验证码是四位的！");
                                    return false;
                                }
                                if (inputValue === "") {
                                    swal.showInputError("验证码不能为空");
                                    return false;
                                }
                                $.ajax({
                                    url: '/userinfo/updatainfo/' + inputValue,
                                    dataType: 'json',
                                    type: "POST",
                                    contentType: "application/json;charset=utf-8",
                                    data: JSON.stringify({
                                        "nickname": nickname,
                                        "gender": gender,
                                        "phone": phone,
                                        "username": username,
                                        "email": email,
                                    }),
                                    headers: {
                                        'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
                                    },
                                    success: function (res) {
                                        if (res.flag == true) {

                                            swal({
                                                title: "NICE!",
                                                text: "新的手机绑定成功！",
                                                type: "success"
                                            }, function () {
                                                storage.clear();    //清除localStorage
                                                window.location = '/user/login' //重定向到登录页login，重新登录
                                            })

                                        } else {
                                            swal("ERROR", res.message, "error");
                                        }
                                    }
                                })
                            });
                        } else {
                            swal("ERROR", Result.message, "error");
                            return false;
                        }
                    }
                })
            });
    } else {
        $.ajax({
            url: '/userinfo/updatainfo',
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            type: "post",
            data: JSON.stringify({
                "nickname": nickname,
                "gender": gender,
                "phone": phone,
                "username": username,
                "email": email,
            }),
            headers: {
                'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
            },
            success: function (res) {
                if (res.flag == true) {
                    swal("NICE!", res.message, "success");

                } else {
                    swal("ERROR", res.message, "error");
                }
            }
        })
    }
}