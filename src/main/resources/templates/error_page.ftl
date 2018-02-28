<#include "/header.ftl">
<title>エラーページ</title>
</head>
<body>
<div>
      <pre>
      <#list stackTrace as trace>
        ${trace}
</#list>
      </pre>
</div>
<#include "/footer.ftl">
