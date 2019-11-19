

function timedCount()
{

    setInterval(function () {
        $.ajax({
            url: "127.0.0.1:10003/FtpServer/size",
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            type: "GET",
            success: function (Result) {
                if (Result) {
                    postMessage(Result.data);
                }
            }
        });

    }, 1000);
}

timedCount();

