<#import "parts/templadmin.ftl" as p>
<@p.pages>

    <h1> Login </h1>

    <hr>
    <form action="/login" method="post">

        <label for="username">Username</label>
        <input type="text" name="username" placeholder="user"><br>

        <label for="password">Password</label>
        <input type="password" name="password" placeholder="pass"><br>

        <input type="submit" value="Submit">

    </form>
    <a href="/registration">перехід на сторінку реєстрації</a>

</@p.pages>
