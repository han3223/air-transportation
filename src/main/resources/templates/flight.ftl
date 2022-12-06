<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <#include "style/style_flight.css">
    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<div id="main_block">
    <div id="form_div">
        <form action="/user/employee_name/add_new_flight" method="post">
            <select name="Point_of_departure" id="">
                <#list data as list>
                    <option value="${list?index}" name=""><h2>${list}</h2></option>
                </#list>
            </select>
            <select name="Point_of_arrival" id="">
                <#list data as list>
                    <option value="${list?index}" name=""><h2>${list}</h2></option>
                </#list>
            </select>
            <select name="Carrier_name" id="">
                <#list carrier as list>
                    <option value="${list?index}"><h2>${list}</h2></option>
                </#list>
            </select>
            <input type="time" name="Departure_time" id="">
            <input type="time" name="Arrival_time" id="">
            <input type="text" name="Distance">
            <select name="Brand_name" id="">
                <#list brands as brand>
                    <option value="${brand?index}"><h2>${brand}</h2></option>
                </#list>
            </select>
            <input type="submit" name="" id="">
        </form>
    </div>
</div>
</body>
</html>