<#include "/header.ftl">
<title>Cucule Top</title>
</head>
<body>
<div class="container">
    <pre>
        必要なもの箇条書き

        ・Admin login    Admin auth
        ・Client login   Client auth
        ・User login     Uses  auth
        ・Session time
        ・普通のトップ総合　まずはビューティかな?
        ・地域一覧
        ・Client用スケジュール確認画面　伸ばせたりするやつ
        ・User用　○✖で予約できるやつ
        ・Client登録編集画面
        ・集計画面
        ・クレジット
        ・請求
        ・リマインド
        ・メール
      </pre>
    <form action="/client/login" method="post">
    <#include "/error_message.ftl">
        <div class="Height200" id="login">
            <div class="form-group">
                <label>ID</label>
                <input type="text" class="form-control" name="loginId"
                       value="<#if form??>${form.loginId}</#if>"
                       placeholder="IDを入力して下さい。">
            </div>
            <div class="form-group">
                <label>パスワード</label>
                <input type="password" class="form-control" name="loginPassword" placeholder="パスワードを入力して下さい。">
            </div>
            <button type="submit" class="btn btn-info">ログイン</button>
        </div>
    </form>
</div>

<#include "/footer.ftl">
