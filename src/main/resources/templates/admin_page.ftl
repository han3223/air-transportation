<!doctype html>
<html lang="en">
<head>
    <style>
        <#include "style/style_adminpage.css">
    </style>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

    <div id="main_div">
        <form action="/user/admin_name" method="post">
            <input type="email" name="email" id="" placeholder="Email">
            <select name="select_role">
                <option>Администратор</option>
                <option>Сотрудник</option>
                <option>Обычный пользователь</option>
            </select>
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="list_user">
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
            </tr>
            <#list users as user>
                <tr>
                    <th>${user.id}</th>
                    <th>${user.firstName}</th>
                    <th>${user.lastName}</th>
                    <th>${user.email}</th>
                    <th>${user.phone}</th>
                    <th>${user.role}</th>
                </tr>
            </#list>
        </table>
    </div>
</body>
</html>