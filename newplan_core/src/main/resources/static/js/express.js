function sel() {
    var shipSn = $("#shipSn").val();
    $.ajax({
        url:'/ExperssHandle/getOrderShipperCode',
        type:'get',
        dataType:'json',
        // contentType: "application/json;charset=utf-8",
        data: {"shipSn":shipSn},
        headers:{
            'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
        },
        success:function(result){
            if(result.flag){
                if(result.data.Success){
                    document.getElementById("showshipChannel").value = result.data.Shippers[0].ShipperName;
                    document.getElementById("shipChannel").value = result.data.Shippers[0].ShipperCode;
                }else {
                    document.getElementById("shipChannel").value = '';
                }
            }else {
                if(result.code == '20002'){
                    swal("异常",result.message,"warning");
                }else {
                    swal("异常",result.message,"error");
                }
            }
        }
    })
}


function Status() {
    var shipSn = $("#shipSn").val();
    var shipChannel = $("#shipChannel").val();
    $.ajax({
        url:'/ExperssHandle/getOrderTracesByJson',
        type:'get',
        dataType:'json',
        // contentType: "application/json;charset=utf-8",
        data: {"shipSn":shipSn,
            "shipChannel":shipChannel},
        headers:{
            'Authrorization': window.localStorage.getItem("Authrorization")//将token放到请求头中
        },
        success:function(result){
            var traces = result.data.Traces;
            var ul = document.getElementById("ul");
            var res = '';
            if(result.data.Success){
                for (var i = 0; i < traces.length; i++) {

                    res += "<li>\n" +
                        "            <div class=\"tl-circ\"></div>\n" +
                        "            <div class=\"timeline-panel\">\n" +
                        "                <div class=\"tl-heading\">\n" +
                        "                    <h4>当前第"+ (Number(i + 1)) +"站</h4>\n" +
                        "                    <p><small class=\"text-muted\"><i class=\"glyphicon glyphicon-time\"></i>"+ traces[i].AcceptTime +"</small></p>\n" +
                        "                </div>\n" +
                        "                <div class=\"tl-body\">\n" +
                        "                    <p>"+ traces[i].AcceptStation +"</p>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </li>";
                }
                ul.innerHTML=res;

            }else {
                if(result.code == '20002'){
                    swal("异常",result.message,"warning");
                }else {
                    swal("异常",result.message,"error");
                }
            }
        }
    })
}