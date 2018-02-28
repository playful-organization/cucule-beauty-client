<#include "/header.ftl">
<title>Cucule Top</title>
<script type="text/javascript">
    $(function () {
        $("#summaryTable th, td").bind("click", function (e) {
            location.href = "/client/schedule/?date=" + this.id.substr(3);
            return true;
        });

        var hoveringId = null;
        $("#summaryTable").find("th, td").hover(
                function (e) {
                    hoveringId = this.id.substr(3);
                    $(this).addClass("hover");
                    getPair(this).addClass("hover");
                    return true;
                },
                function (e) {
                    var myself = this;

                    hoveringId = null;
                    setTimeout(
                            function () {
                                if (myself.id.substr(3) != hoveringId) {
                                    $(myself).removeClass("hover");
                                    getPair(myself).removeClass("hover");
                                }
                            },
                            0);
                    return true;
                }
        );
    });
    function getPair(elem) {
        return $("#" + ((elem.tagName.toLowerCase() == 'th') ? "sd_" : "sh_") + elem.id.substr(3));
    }
</script>
</head>
<body>
<#include "/client_navigation_var.ftl">
<div class="container">
    <form action="/client/schedule" method="get">
        <table class="table table-bordered table-condensed Font8" id="summaryTable">
            <tr>
            <#list output.oneWeekScheduleList as oneWeekSchedule>
                <th class="${oneWeekSchedule["weekType"]}" id="sh_${oneWeekSchedule["timestamp"]}">
                ${oneWeekSchedule["monthDate"]}
                </th>
            </#list>
            </tr>
            <tr>
            <#list output.oneWeekScheduleList as oneWeekSchedule>
                <td id="sd_${oneWeekSchedule["timestamp"]}">
                ${oneWeekSchedule["numOfReservation"]}
                </td>
            </#list>
            </tr>
            <tr>
            <#list output.twoWeekScheduleList as twoWeekSchedule>
                <th class="${twoWeekSchedule["weekType"]}" id="sh_${twoWeekSchedule["timestamp"]}">
                ${twoWeekSchedule["monthDate"]}
                </th>
            </#list>
            </tr>
            <tr>
            <#list output.twoWeekScheduleList as twoWeekSchedule>
                <td id="sd_${twoWeekSchedule["timestamp"]}">
                ${twoWeekSchedule["numOfReservation"]}
                </td>
            </#list>
            </tr>
        </table>
    </form>
</div>

<#include "/footer.ftl">
