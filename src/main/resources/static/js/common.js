// startTimeにneedTimeを加算して時間を計算
function calculatTime(startTime, needTime) {
    // e.g 1200 + 90 = 13:30
    var resultTime = "";
    var hour;
    var tmpHour;
    var target;
    if (startTime !== "" && !isNaN(startTime) && needTime !== "" && !isNaN(needTime)) {
        hour = parseInt(startTime.substring(0, 2), 10);
        target = parseInt(needTime, 10) + parseInt(startTime.substring(2), 10);
        if (target >= 60) {
            tmpHour = Math.floor(target / 60);
            hour = hour + tmpHour;
            target = target - (tmpHour * 60);
        }
        resultTime = hour + ":" + ("0" + target).slice(-2);
    }
    return resultTime;
}
// jqueryでのsubmit
// e.g)
// <button id="cancel-submit" data-action="/client/">ボタン名</button>
// $('#cancel-submit').click(function () {
//     submit($(this));
// });
function submit(formThis) {
    formThis.parents('form').attr('action', formThis.data('action'));
    formThis.parents('form').submit();
}
