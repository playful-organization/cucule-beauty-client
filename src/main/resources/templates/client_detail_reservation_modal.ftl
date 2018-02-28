<script type="text/javascript">
    $(function () {
        $('.onlickDate').datetimepicker({
            locale: 'ja',
            format: 'YYYY年MM月DD日(dd)'
        });

        $('#startTime').change(function () {
            var startTime = $('#startTime option:selected').val().replace(":", "");
            var needTime = $('#needTime option:selected').val();
            var resultTime = calculatTime(startTime, needTime);
            $('#detail_endTime').val(resultTime);
        });
        $('#needTime').change(function () {
            var startTime = $('#startTime option:selected').val().replace(":", "");
            var needTime = $('#needTime option:selected').val();
            var resultTime = calculatTime(startTime, needTime);
            $('#detail_endTime').val(resultTime);
        });
        $('#edit-reservation').click(function () {
            $('#detailReservation').modal('toggle');
            initEditData();
            $('#editReservation').modal();
            // submit($(this));
            return false;
        });
        $('#cancel-submit').click(function () {
            $.confirm({
                title: '予約のキャンセル',
                // TODO 改行されない
                content: '予約をキャンセルしようとしてますが、よろしいですか？\nお客様にキャンセルメールが送信されます',
                type: 'orange',
                buttons: {
                    OK: {
                        text: 'OK',
                        btnClass: 'btn-red',
                        keys: ['enter', 'shift'],
                        action: function () {
                            submit($('#cancel-submit'));
                        }
                    },
                    閉じる: function () {
                        // TODO メッセージいる？?
                    },
                }
            });
            return false;
        });
        function initEditData() {
            $('#edit_reservationId').val($("#detail_reservationId").val());
            $('#edit_staffId').val($("#detail_staffId").val());
            $('#edit_raitenDate').val($("#detail_raitenDate").val());
            $("#edit_startTime").val($("#detail_startTime").val());
            $("#edit_endTime").val($("#detail_endTime").val());
            $('#edit_needTime').val($("#detail_needTimeRaw").val());
            $('#edit_lastNameKana').val($("#detail_userLastNameKana").val());
            $('#edit_firstNameKana').val($("#detail_userFirstNameKana").val());
            $('#edit_lastNameKanji').val($("#detail_userLastNameKanji").val());
            $('#edit_firstNameKanji').val($("#detail_userFirstNameKanji").val());
        }
    })
    ;
</script>
<form method="post">
    <div class="modal fade" id="detailReservation" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
                    <h4 class="modal-title">予約詳細</h4>
                </div>
                <div class="modal-body">
                    <pre>予約情報</pre>
                    <table class="table table-bordered">
                        <tr>
                            <th class="warning">スタイリスト</th>
                            <td>
                                <input type="text" class="none Width150" id="detail_staffName"
                                       readonly/>
                            </td>
                        </tr>
                        <tr>
                            <th class="warning" rowspan="2">来店日時</th>
                            <td>
                                <input type="text" class="none Width150" id="detail_raitenDate" readonly/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                開始時間&nbsp;:&nbsp;
                                <input type="text" class="none Width50" id="detail_startTime"
                                       readonly/>
                                &nbsp終了時間&nbsp;:&nbsp;<input type="text" class="none Width50"
                                                             id="detail_endTime"
                                                             readonly/>
                                &nbsp施術時間&nbsp;:&nbsp;
                                <input type="text" class="none Width50" id="detail_needTime"
                                       readonly/>
                            </td>
                        </tr>
                    </table>
                    <pre>お客様情報</pre>
                    <table class="table table-bordered">
                        <tr>
                            <th class="warning">氏名(カナ)</th>
                            <td>
                                <div class="form-group form-inline">
                                    <input type="text" class="none" id="detail_userLastNameKana"
                                           readonly>
                                    <input type="text" class="none" id="detail_userFirstNameKana"
                                           readonly>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th class="warning">氏名(漢字)</th>
                            <td>
                                <div class="form-group form-inline">
                                    <input type="text" class="none" id="detail_userLastNameKanji"
                                           readonly>
                                    <input type="text" class="none" id="detail_userFirstNameKanji"
                                           readonly>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" id="closeModal" class="btn btn-default" data-dismiss="modal">閉じる</button>
                    <button id="edit-reservation" class="btn btn-primary">編集する
                    </button>
                    <button id="cancel-submit" class="cancel-submit btn btn-danger"
                            data-action="/client/schedule/cancel?date=${clientScheduleModel.currentDateParam}">
                        キャンセルする
                    </button>
                    <input type="hidden" id="detail_reservationId" name="reservationId">
                    <input type="hidden" id="detail_needTimeRaw" name="needTimeRaw">
                    <input type="hidden" id="detail_staffId" name="staffId">

                </div>
            </div>
        </div>
    </div>
</form>
<#include "/client_edit_reservation_modal.ftl">
