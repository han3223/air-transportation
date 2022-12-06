<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <#include "style/style_employeepage.css">
    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<div id="main_div">
    <div id="div_brand">
        <p>Бренд</p>
        <form action="/user/employee_name/add_brand" method="post">
            <input type="text" name="brand" id="" placeholder="Бренд">
            <input type="text" name="coast_factor" placeholder="Коэфицент стоимости">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="div_airport">
        <p>Аэропорт</p>
        <form action="/user/employee_name/add_airport" method="post">
            <input type="text" name="city" id="" placeholder="Город">
            <input type="text" name="airport_name" id="" placeholder="Название аэропорта">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="div_carrier">
        <p>Перевозчик</p>
        <form action="/user/employee_name/add_carrier" method="post">
            <input type="text" name="carrier" id="" placeholder="Перевозчик">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="div_locationtype">
        <p>Место</p>
        <form action="/user/employee_name/add_place" method="post">
            <input type="text" name="place" id="" placeholder="Тип места">
            <input type="text" name="seat_price" id="" placeholder="Цена за км">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="flight">
        <form action="/user/employee_name/next" method="post">
            <input type="submit" value="Заполнить рейс">
        </form>
    </div>
</div>
</body>
</html>