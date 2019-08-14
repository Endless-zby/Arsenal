$(document).ready(function() {
    $.ajax({
        type: "POST",
        url: "/time/queryred",
        dataType: "json",

        success: function (data) {

            var day = new Array(); //天数
            var date = new Array();//年份月份

            for (var i = 0; i < data.data.length; i++) {
                var days = (new Date(data.data[i].outtime).getTime() - new Date(data.data[i].starttime).getTime())/1000/3600/24;
                var dates = data.data[i].starttime;
                day.push(days);
                date.push(dates);
            }

            var chart = {
                type: 'column'
            };
            var title = {
                text: '每月持续天数'
            };
            var subtitle = {
                text: 'Source: 小鸹貔'
            };
            var xAxis = {
                categories: date,
                crosshair: true
            };
            var yAxis = {
                min: 0,
                title: {
                    text: 'day (天)'
                }
            };
            var tooltip = {
                headerFormat: '<span style="font-size:10px">开始日期：{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}</td>' +
                    '<td style="padding:0"><b>{point.y:.1f} 天</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            };
            var plotOptions = {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            };
            var credits = {
                enabled: false
            };

            var series= [{
                name: '天数',
                data: day
            }];

            var json = {};
            json.chart = chart;
            json.title = title;
            json.subtitle = subtitle;
            json.tooltip = tooltip;
            json.xAxis = xAxis;
            json.yAxis = yAxis;
            json.series = series;
            json.plotOptions = plotOptions;
            json.credits = credits;
            $('#container').highcharts(json);

        },
        error: function () {
            alert('哎呀！出错了！');
        }
    });

});