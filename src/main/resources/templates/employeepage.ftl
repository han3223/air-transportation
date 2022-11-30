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
        <form action="/user/employee_name" method="post">
            <input type="text" name="brand" id="" placeholder="Бренд">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="div_airport">
        <p>Аэропорт</p>
        <form action="/user/employee_name" method="post">
            <input type="text" name="city" id="" placeholder="Город">
            <input type="text" name="airport_name" id="" placeholder="Название аэропорта">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="div_carrier">
        <p>Перевозчик</p>
        <form action="/user/employee_name" method="post">
            <input type="text" name="carrier" id="" placeholder="Перевозчик">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="div_locationtype">
        <p>Место</p>
        <form action="/user/employee_name" method="post">
            <input type="text" name="place" id="" placeholder="Тип места">
            <input type="text" name="seat_price" id="" placeholder="Цена за км">
            <input type="submit" name="" id="">
        </form>
    </div>
    <div id="flight">
        <button>Заполнить рейс</button>
    </div>
</div>
</body>
</html>