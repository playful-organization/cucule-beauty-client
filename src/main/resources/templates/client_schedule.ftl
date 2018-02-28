<#include "/header.ftl">
<title>Cucule : スケジュール</title>
<script type="text/javascript">
    jQuery(document).ready(function () {
        var $sc = jQuery("#schedule").timeSchedule({
            startTime: "09:00", // schedule start time(HH:ii)
            endTime: "19:00",   // schedule end time(HH:ii)
            widthTime: 60 * 30,  // cell timestamp example 10 minutes
            timeLineY: 60,       // height(px)
            verticalScrollbar: 20,   // scrollbar (px)
            timeLineBorder: 2,   // border(top and bottom)
            debug: "#debug",     // debug string output elements
            rows: {
            ${clientScheduleModel}},
            change: function (node, data) {
                $.confirm({
                    title: '予約時間変更',
                    // TODO 改行されない
                    content: '予約時間をしようとしてますが、よろしいですか？\nお客様に予約時間変更のメールが送信されます',
                    type: 'orange',
                    buttons: {
                        OK: {
                            text: 'OK',
                            btnClass: 'btn-red',
                            keys: ['enter', 'shift'],
                            action: function () {
                                var dataList = data['data'].split(',')
                                var reservationId = dataList[0];
                                var priority = data['timeline'] + 1;
                                var startTime = data['start'];
                                var endTime = data['end'];
                                var needTime = endTime - startTime;

                                $("#change_reservationId").val(reservationId);
                                $("#change_priority").val(priority);
                                $("#change_startTime").val(startTime);
                                $("#change_endTime").val(endTime);
                                $("#change_needTime").val(needTime);

                                // dataの中のidと新しいstartとendくらいでいいかな渡すの、今日の日付はどっちからとってもいい
                                submit($('#change-submit'));
                            }
                        },
                        変更を中止: function () {
                            // TODO trueかfalseかどっちの方がいいのか検討の価値あり
                            // reloadで元の状態を読み込む true:serverから取得 false:キャッシュから取得
                            location.reload(false)
                        },
                    }
                });
                return false;
            },
            init_data: function (node, data) {
            },
            click: function (node, data) {
                var dataList = data['data'].split(',')
                var reservationId = dataList[0];
                var staffName = dataList[1];
                var userLastNameKana = dataList[2];
                var userFirstNameKana = dataList[3];
                var userLastNameKanji = dataList[4];
                var userFirstNameKanji = dataList[5];
                var yearMonthDate = dataList[6];
                var startTime = dataList[7];
                var endTime = dataList[8];
                var needTime = dataList[9];
                var needTimeRaw = dataList[10];
                var staffId = dataList[11];

                $("#detail_reservationId").val(reservationId);
                $("#detail_staffName").val(staffName);
                $("#detail_staffId").val(staffId);
                $("#detail_raitenDate").val(yearMonthDate);
                $("#detail_startTime").val(startTime);
                $("#detail_endTime").val(endTime);
                $("#detail_needTime").val(needTime);
                $("#detail_needTimeRaw").val(needTimeRaw);
                $("#detail_userLastNameKana").val(userLastNameKana);
                $("#detail_userFirstNameKana").val(userFirstNameKana);
                $("#detail_userLastNameKanji").val(userLastNameKanji);
                $("#detail_userFirstNameKanji").val(userFirstNameKanji);

                $('#detailReservation').modal();
            }
            ,
            append: function (node, data) {
            }
            ,
            time_click: function (time, data, priority) {
                clearRegistData();
                $("#startTime").val(data);
                var startTime = $('#startTime option:selected').val().replace(":", "");
                var needTime = $('#needTime option:selected').val();
                var resultTime = calculatTime(startTime, needTime);
                $('#endTime').val(resultTime);
                $("#staffId").val(priority);
                $('#error-message').remove();
                $('#registerReservation').modal();
            }
            ,
        });
        $('#reservation-navi').addClass('active');
    <#if errorMessageList??>
        <#if form.displayName == "register">
            //登録のvalidationに引っかかった時は内容を保持して登録画面でエラーメッセージを出力する
            $('#startTime').val('${form.startTime}');
            $('#endTime').val('${form.endTime}');
            $('#needTime').val('${form.needTime}');
            $('#lastNameKana').val('${form.lastNameKana}');
            $('#firstNameKana').val('${form.firstNameKana}');
            $('#lastNameKanji').val('${form.lastNameKanji}');
            $("#staffId").val('${form.staffId}');
            $('#firstNameKanji').val('${form.firstNameKanji}');
            $('#raitenDate').val('${form.raitenDate}');
            $('#registerReservation').modal();
        <#elseif form.displayName == "edit">
            $('#edit_startTime').val('${form.startTime}');
            $('#edit_endTime').val('${form.endTime}');
            $('#edit_needTime').val('${form.needTime}');
            $('#edit_lastNameKana').val('${form.lastNameKana}');
            $('#edit_firstNameKana').val('${form.firstNameKana}');
            $('#edit_lastNameKanji').val('${form.lastNameKanji}');
            $("#edit_staffId").val('${form.staffId}');
            $('#edit_firstNameKanji').val('${form.firstNameKanji}');
            $('#edit_raitenDate').val('${form.raitenDate}');
            $('#editReservation').modal();
        </#if>;
    </#if>
    });
</script>
</head>
<body>
<#include "/client_navigation_var.ftl">
<div class="container">
    <div class="hero-unit" id=unit>
        <h1 id="home">${clientScheduleModel.currentDate}</h1>
    </div>
    <div style="padding: 0 0 40px;">
        <!--スケジュールインクルード -->
        <div id="schedule"></div>
    </div>
</div><!-- /container -->
<form method="post">
    <button id="change-submit" class=""
            data-action="/client/schedule/change?date=${clientScheduleModel.currentDateParam}">
    </button>
    <input type="hidden" id="change_reservationId" name="reservationId">
    <input type="hidden" id="change_priority" name="priority">
    <input type="hidden" id="change_startTime" name="startTime">
    <input type="hidden" id="change_endTime" name="endTime">
    <input type="hidden" id="change_needTime" name="needTime">
</form>

<!--予約登録画面Modalインクルード -->
<#include "/client_register_reservation_modal.ftl">
<#include "/client_detail_reservation_modal.ftl">
<#include "/footer.ftl">
