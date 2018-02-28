<#if errorMessageList??>
<div id="error-message">
    <#list errorMessageList as errorMessage>
        <li>${errorMessage}</li>
    </#list>
</div>
</#if>
