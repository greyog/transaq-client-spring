<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Organizer</title>
      <link rel="stylesheet" type="text/css" href="/css/style.x87534.css"/>
</head>
<body>
<h1>Transaq connector</h1>
<form method="post">
    <button type="submit" name="login">Login</button>
    <br>
    <button type="submit" name="disconnect">Disconnect</button>
    <br>
    <button type="submit" name="server_status">Server Status</button>
    <br>
    <button type="submit" name="fetch_data">Fetch data</button>
</form>
<br>
<#if result??>
    <h2>Request result: ${result}</h2>
<#else>
    <h2>Request result is empty.</h2>
</#if>
</body>
</html>