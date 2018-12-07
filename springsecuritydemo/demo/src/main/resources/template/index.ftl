<!DOCTYPE html><!--登录页面 -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>home page</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-4 col-center-block">
            <form class="form-login" action="/login" method="POST">
                <h2 class="form-login-heading">请登录</h2>
                <label for="username" class="sr-only">用户名</label>
                <input type="text" id="username" class="form-control" name="username" placeholder="用户名" required autofocus>
                <label for="password" class="sr-only">密码</label>
                <input type="password" id="password" class="form-control" name="password" placeholder="密码" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me">
                        记住我 </label>
                </div>

                <button class="btn btn-lg btn-primary btn-block" type="submit" >登录</button>
            </form>
        </div>
    </div>
</div>
</body>


</html>
