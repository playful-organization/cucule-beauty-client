<script type="text/javascript">
    $(function () {
        $('.onlickDate').datetimepicker({
            locale: 'ja',
            format: 'YYYY年MM月DD日(dd)'
        });

        $('#edit_startTime').change(function () {
            var startTime = $('#edit_startTime option:selected').val().replace(":", "");
            var needTime = $('#edit_needTime option:selected').val();
            var resultTime = calculatTime(startTime, needTime);
            $('#edit_endTime').val(resultTime);
        });
        $('#edit_needTime').change(function () {
            var startTime = $('#edit_startTime option:selected').val().replace(":", "");
            var needTime = $('#edit_needTime option:selected').val();
            var resultTime = calculatTime(startTime, needTime);
            $('#edit_endTime').val(resultTime);
        });
    });
</script>
<form action="/client/schedule/edit?date=${clientScheduleModel.currentDateParam}" method="post">
    <div class="modal fade" id="editReservation" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
                    <h4 class="modal-title">予約編集</h4>
                </div>
            <#include "/error_message.ftl">
                <div class="modal-body">
                    <pre>予約情報</pre>
                    <table class="table table-bordered">
                        <tr>
                            <th class="warning">スタイリスト<span id="required">※</span></th>
                            <td>
                                <select id="edit_staffId" name="staffId">
                                <#list clientScheduleModel.reservationList as reservation>
                                    <option value="${reservation.staffId}">${reservation.staffName}</option>
                                </#list>
                                    <option value="T000395273">Pursuit</option>
                                    <option value="0000000000">フリー</option>
                                </select>
                                <input type="checkbox" value="on" checked="checked"/>
                                <label>&nbsp;指名予約の場合チェックを付けてください</label>
                            </td>
                        </tr>
                        <tr>
                            <th class="warning" rowspan="2">来店日時<span id="required">※</span></th>
                            <td>
                                <div class="row">
                                    <div class='col-sm-6'>
                                        <div class="form-group">
                                            <div class="input-group date onlickDate">
                                                <input type="text" name="raitenDate" id="edit_raitenDate"
                                                       class="form-control onlickDate"/>
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                開始時間&nbsp;:&nbsp;
                                <!-- TODO DBから取得したい -->
                                <select name="startTime" id="edit_startTime">
                                    <option value="09:00">09:00</option>
                                    <option value="09:30">09:30</option>
                                    <option value="10:00">10:00</option>
                                    <option value="10:30">10:30</option>
                                    <option value="11:00">11:00</option>
                                    <option value="11:30">11:30</option>
                                    <option value="12:00">12:00</option>
                                    <option value="12:30">12:30</option>
                                    <option value="13:00">13:00</option>
                                    <option value="13:30">13:30</option>
                                    <option value="14:00">14:00</option>
                                    <option value="14:30">14:30</option>
                                    <option value="15:00">15:00</option>
                                    <option value="15:30">15:30</option>
                                    <option value="16:00">16:00</option>
                                    <option value="16:30">16:30</option>
                                    <option value="17:00">17:00</option>
                                    <option value="17:30">17:30</option>
                                    <option value="18:00">18:00</option>
                                    <option value="18:30">18:30</option>
                                    <option value="19:00">19:00</option>
                                    <option value="19:30">19:30</option>
                                    <option value="20:00">20:00</option>
                                    <option value="20:30">20:30</option>
                                    <option value="21:00">21:00</option>
                                </select>
                                &nbsp終了時間&nbsp;:&nbsp;<input type="text" name="endTime" class="none Width40"
                                                             id="edit_endTime" readonly/>
                                &nbsp施術時間&nbsp;:&nbsp;
                                <select name="needTime" id="edit_needTime">
                                    <!-- TODO DBから取得したい -->
                                    <option value="30" selected="selected">0:30</option>
                                    <option value="60">1:00</option>
                                    <option value="90">1:30</option>
                                    <option value="120">2:00</option>
                                    <option value="150">2:30</option>
                                    <option value="180">3:00</option>
                                    <option value="210">3:30</option>
                                    <option value="240">4:00</option>
                                    <option value="270">4:30</option>
                                    <option value="300">5:00</option>
                                    <option value="330">5:30</option>
                                    <option value="360">6:00</option>
                                    <option value="390">6:30</option>
                                    <option value="420">7:00</option>
                                    <option value="450">7:30</option>
                                    <option value="480">8:00</option>
                                    <option value="510">8:30</option>
                                    <option value="540">9:00</option>
                                    <option value="570">9:30</option>
                                    <option value="600">10:00</option>
                                    <option value="630">10:30</option>
                                    <option value="660">11:00</option>
                                    <option value="690">11:30</option>
                                    <option value="720">12:00</option>
                                    <option value="750">12:30</option>
                                    <option value="780">13:00</option>
                                    <option value="810">13:30</option>
                                    <option value="840">14:00</option>
                                    <option value="870">14:30</option>
                                    <option value="900">15:00</option>
                                    <option value="930">15:30</option>
                                    <option value="960">16:00</option>
                                    <option value="990">16:30</option>
                                    <option value="1020">17:00</option>
                                    <option value="1050">17:30</option>
                                    <option value="1080">18:00</option>
                                    <option value="1110">18:30</option>
                                    <option value="1140">19:00</option>
                                    <option value="1170">19:30</option>
                                    <option value="1200">20:00</option>
                                    <option value="1230">20:30</option>
                                    <option value="1260">21:00</option>
                                    <option value="1290">21:30</option>
                                    <option value="1320">22:00</option>
                                    <option value="1350">22:30</option>
                                    <option value="1380">23:00</option>
                                    <option value="1410">23:30</option>
                                    <option value="1440">24:00</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <pre>お客様情報</pre>
                    <table class="table table-bordered">
                        <tr>
                            <th class="warning">氏名(カナ)</th>
                            <td>
                                <div class="form-group form-inline">
                                    <input type="text" name="lastNameKana" class="form-control" id="edit_lastNameKana"
                                           placeholder="シ" readonly>
                                    <input type="text" name="firstNameKana" class="form-control" id="edit_firstNameKana"
                                           placeholder="メイ" readonly>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th class="warning">氏名(漢字)</th>
                            <td>
                                <div class="form-group form-inline">
                                    <input type="text" name="lastNameKanji" class="form-control" id="edit_lastNameKanji"
                                           placeholder="氏" readonly>
                                    <input type="text" name="firstNameKanji" class="form-control"
                                           id="edit_firstNameKanji"
                                           placeholder="名" readonly>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" id="closeModal" class="btn btn-default" data-dismiss="modal">閉じる</button>
                    <button type="submit" class="btn btn-primary">編集内容を反映する</button>
                    <input type="hidden" id="edit_reservationId" name="reservationId">
                    <input type="hidden" id="edit_needTimeRaw" name="needTimeRaw">
                </div>
            </div>
        </div>
    </div>
</form>
