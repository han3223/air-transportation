<!doctype html>
<html lang="en">
<head>
    <style>
        <#include "style/style_adminpage.css">
    </style>
    <script>
        <#include "script/script_admin.js">
    </script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div id="main_div">
        <form action="/user/admin_name" method="post">
            <label>
                <input type="text" style="display: none" value="${status}" name="status">
            </label>
            <p>${status}</p>
            <label for=""></label><input type="email" name="email" id="" placeholder="Email">
            <label>
                <select name="select_role">
                    <#if admin?has_content>
                        <option>${admin}</option>
                    </#if>
                    <option>Сотрудник</option>
                    <option>Обычный пользователь</option>
                </select>
            </label>
            <input type="submit" name="" id="">
            <input type="button" value="Показать только админов" onClick="openOnlyAdmin()">
            <input type="button" value="Показать только сотрудников" onClick="openOnlyEmployee()">
            <input type="button" value="Показать только пользователей" onClick="openOnlyUser()">
            <input type="button" value="Показать всех пользователей" onClick="openAllUsers()">
            <input type="button" value="Показать количество админов и пользователей" onClick="getCountAdminUser()">
            <input type="button" value="Показать перевозчика и рейс" onClick="getCarrierFlight()">
            <input type="button" value="Показать билет и пассажиров" onClick="ticketPassenger()">
            <input type="button" value="Показать номер билета и бренд" onClick="getTicketBrand()">
            <input type="button" value="Показать среднюю цену билетов" onClick="getAvgTicket()">
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
    <div id="list_only_user">
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
            </tr>
            <#list only_users as only_user>
                <tr>
                    <th>${only_user.id}</th>
                    <th>${only_user.firstName}</th>
                    <th>${only_user.lastName}</th>
                    <th>${only_user.email}</th>
                    <th>${only_user.phone}</th>
                    <th>${only_user.role}</th>
                </tr>
            </#list>
        </table>
    </div>
    <div id="list_only_admin">
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
            </tr>
            <#list only_admins as only_admin>
                <tr>
                    <th>${only_admin.id}</th>
                    <th>${only_admin.firstName}</th>
                    <th>${only_admin.lastName}</th>
                    <th>${only_admin.email}</th>
                    <th>${only_admin.phone}</th>
                    <th>${only_admin.role}</th>
                </tr>
            </#list>
        </table>
    </div>
    <div id="list_only_employee">
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
            </tr>
            <#list only_employes as only_employee>
                <tr>
                    <th>${only_employee.id}</th>
                    <th>${only_employee.firstName}</th>
                    <th>${only_employee.lastName}</th>
                    <th>${only_employee.email}</th>
                    <th>${only_employee.phone}</th>
                    <th>${only_employee.role}</th>
                </tr>
            </#list>
        </table>
    </div>
    <div id="list_admin_user">
        <table>
            <tr>
                <th>Count Admin</th>
                <th>Count User</th>
            </tr>
            <#list counts as count>
                <tr>
                    <th>${count.countUser}</th>
                    <th>${count.countAdmin}</th>
                </tr>
            </#list>
        </table>
    </div>

    <div id="carrier_flight">
        <table>
            <tr>
                <th>flightNumber</th>
                <th>departure</th>
                <th>arrival</th>
                <th>departure_time</th>
                <th>arrival_time</th>
                <th>flight_time</th>
                <th>distance</th>
                <th>carrier</th>
                <th>brand</th>
            </tr>
            <#list flights_carriers as flight_carrier>
                <tr>
                    <th>${flight_carrier.flight_number}</th>
                    <th>${flight_carrier.point_of_departure}</th>
                    <th>${flight_carrier.point_of_arrival}</th>
                    <th>${flight_carrier.departure_time}</th>
                    <th>${flight_carrier.arrival_time}</th>
                    <th>${flight_carrier.time}</th>
                    <th>${flight_carrier.distance}</th>
                    <th>${flight_carrier.carrier_id}</th>
                    <th>${flight_carrier.brand_id}</th>
                </tr>
            </#list>
        </table>
    </div>
    <div id="ticket_passenger">
        <table>
            <tr>
                <th>number_of_ticket</th>
                <th>flightNumber</th>
                <th>seat_category_code</th>
                <th>id_passanger</th>
                <th>id_brand</th>
                <th>id_carrier</th>
                <th>ticket_price</th>
                <th>distance</th>
                <th>surname</th>
                <th>name</th>
                <th>passport_series</th>
                <th>passport_id</th>
            </tr>
            <#list tickets as ticket>
                <tr>
                    <th>${ticket.number_of_ticket}</th>
                    <th>${ticket.flightNumber}</th>
                    <th>${ticket.seat_category_code}</th>
                    <th>${ticket.id_passanger}</th>
                    <th>${ticket.id_brand}</th>
                    <th>${ticket.id_carrier}</th>
                    <th>${ticket.ticket_price}</th>
                    <th>${ticket.distance}</th>
                    <th>${ticket.surname}</th>
                    <th>${ticket.name}</th>
                    <th>${ticket.passport_series}</th>
                    <th>${ticket.passport_id}</th>
                </tr>
            </#list>
        </table>
    </div>
    <div id="ticket_brand">
        <table>
            <tr>
                <th>number_of_ticket</th>
                <th>brand</th>
            </tr>
            <#list brands as brand>
                <tr>
                    <th>${brand.number_of_ticket}</th>
                    <th>${brand.brand}</th>
                </tr>
            </#list>
        </table>
    </div>
    <div id="avg_ticket">
        <table>
            <tr>
                <th>avg</th>
            </tr>
            <tr>
                <th>${avg}</th>
            </tr>
        </table>
    </div>
</body>
</html>