<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <#include "style/style_homepage.css">
    </style>
    <script>
        <#include "script/script_homepage.js">
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<div id="block_information">
    <div id="block_reg">
        <div id="background_reg" onClick="closeFormReg()"></div>
        <div id="block_form_reg">
            <div id="div_form_reg">
                <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; margin-bottom: 10px;">Регистрация</p>
                <form action="/" id="form_reg" method="post">
                    <label>
                        <input type="text" name="first_name" class="inp_reg" placeholder="Имя">
                    </label>
                    <label>
                        <input type="text" name="last_name" class="inp_reg" placeholder="Фамилия">
                    </label>
                    <label>
                        <input type="text" name="phone" class="inp_reg" placeholder="Телефон">
                    </label>
                    <label>
                        <input type="email" name="email" class="inp_reg" placeholder="E-mail">
                    </label>
                    <label>
                        <input type="password" name="password" class="inp_reg" placeholder="Пароль">
                    </label>
                    <label>
                        <input type="password" class="inp_reg" placeholder="Повторите пароль">
                    </label>
                    <input type="submit" value="Зарегистироваться" class="inp_reg">
                </form>
            </div>
        </div>
    </div>
    <div id="block_auth">
        <div id="background_reg" onClick="closeFormAuth()">

        </div>
        <div id="block_form_reg">
            <div id="div_form_reg">
                <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; margin-bottom: 30px;">Вход</p>
                <form action="/user" id="form_reg" method="post">
                    <label>
                        <input type="email" name="email_login" class="inp_reg" placeholder="E-mail">
                    </label>
                    <label>
                        <input type="password" name="password_login" class="inp_reg" placeholder="Пароль">
                    </label>
                    <input type="submit" value="Войти" class="inp_reg">
                </form>
            </div>
        </div>
    </div>

    <div id="info_content">
        <div id="popular_flights" style="overflow: hidden;">
            <div id="main_block_navigation" style="width: 300%; height: 100%; position: absolute; right: -100%;">
                <div id="image_one"></div>
                <div id="image_two"></div>
                <div id="image_three"></div>
            </div>

            <div id="navigation_flight" style="width: 100%; height: 100%; opacity: 1; position: absolute;">
                <div id="last_flight" onClick="lastFlight()"></div>
                <div id="next_flight" onClick="nextFligth()"></div>
                <div id="select_popular_flight">
                    <div id="radio_block" style="margin: auto; width: 30%; text-align: center;">
                        <label class="rad_label">
                            <input type="radio" name="" id="first_radio" disabled="">
                            <div class="rad-design"></div>
                        </label>
                        <label class="rad_label">
                            <input type="radio" name="" id="second_radio" checked disabled="">
                            <div class="rad-design"></div>
                        </label>
                        <label class="rad_label">
                            <input type="radio" name="" id="third_radio" disabled="">
                            <div class="rad-design"></div>
                        </label>

                    </div>

                </div>
            </div>

        </div>
        <div id="special_offer">

        </div>
        <div id="question">
            <#if flights?has_content>
                <#list flights as flight>
                    <#list locations as location>
                        <div id="flight_div" class="${flight?index}${location?index}">
                            <form action="" style="width: 30%; border-right: 1px grey solid; display: flex;">
                                <div id="buy_ticket">
                                    <div id="center_div_buy_ticket">
                                        <div id="price"><p>${location.seat_price_per_kilometers * flight.distance}</p></div>
                                        <div id="location_type"><p>${location.place}</p></div>
                                        <input type="submit" id="input_buy_ticket" value="Купить билет">
                                    </div>
                                </div>
                            </form>
                            <div id="information_ticket">
                                <div id="carrier" class="${flight?index}">
                                    <div id="logo_carrier"></div>
                                    <#list carriers as carrier>
                                        <#if flight?index == carrier?index>
                                            <div id="name_carrier" class="${carrier?index}"><p>${carrier.company_name}</p></div>
                                        </#if>
                                    </#list>
                                </div>
                                <div id="info_flight">
                                    <div id="time_departure">
                                        <div id="time"><p>${flight.departure_time}</p></div>
                                        <div id="city"><p>${city_departure}</p></div>
                                        <div id="date"><p>${date_departure}</p></div>
                                    </div>
                                    <div id="total_time">
                                        <div id="time_flight"><p>В пути: ${time}</p></div>
                                        <div id="image_flight"></div>
                                    </div>
                                    <div id="time_arrival">
                                        <div id="time"><p>${flight.arrival_time}</p></div>
                                        <div id="city"><p>${city_arrival}</p></div>
                                        <div id="date"><p>${date_departure}</p></div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </#list>


                </#list>
            </#if>

        </div>
        <div id="reviews">
            <div id="your_reviews">
                <form action="" id="form_reviews">
                    <div id="body_reviews_ava">
                        <div id="reviews_ava"></div>
                        <label for="theme_reviews"></label><input type="text" id="theme_reviews">
                    </div>
                    <div id="body_form_reviews">
                        <label for="text_reviews"></label><textarea id="text_reviews"></textarea>
                    </div>
                </form>
            </div>
            <div id="all_reviews">

            </div>
        </div>
    </div>
    <div id="footer">

    </div>
</div>
<div id="block_buy_ticket">
    <div id="content_buy_ticket">
        <div id="body_prof_setting" onClick="closeProfBlock()">
            <div id="profile_setting">
                <div id="content_prof">
                    <div id="my_prof" class="btns_prof_setting">
                        <button>Мой профиль</button>
                    </div>
                    <div id="setting" class="btns_prof_setting">
                        <button>Настройки</button>
                    </div>
                    <div id="notifications" class="btns_prof_setting">
                        <button>Уведомления</button>
                    </div>
                    <div id="enter" class="btns_prof_setting" style="margin-top: 30px; margin-bottom: 5px;">
                        <button style="background-color: #587ca9; margin-bottom: 5px;" onClick="openFormAuth()">Вход</button>
                        <button style="background-color: #e87f2c;" onClick="openFormReg()">Регистрация</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="logo_profile">
            <div id="company" style="width: 70%; float: right; min-width: 215px;">
                <a href="homepage.html"><div id="logo"></div></a>
                <div id="name_firm">
                    <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; user-select: none;"><a href="homepage.html">FastFlights</a><p>
                </div>
            </div>

            <div id="profile">
                <div id="ava" onClick="openProfBlock()">
                </div>
                <div id="name_acc">
                    <div id="first_name">
                        <p class="prof" style="font-family: Biennale,serif; font-size: 100%; float: inherit;">
                            <#if first_name?has_content>
                                ${first_name}
                            <#else>
                                Твой
                            </#if>
                        </p>
                    </div>
                    <div id="last_name">
                        <p class="prof" style="font-family: Biennale,serif; font-size: 100%; float: inherit;">
                            <#if last_name?has_content>
                                ${last_name}
                            <#else>
                                Профиль
                            </#if>
                        </p>
                    </div>
                </div>
            </div>

        </div>
        <form <#if first_name?has_content && last_name?has_content> action="/${first_name}/${last_name}/buy_flight" <#else> action="/buy_flight"</#if> id="form_search_ticket" method="post">
            <div class="form_div">
                <label>
                    <input type="text" name="departure" class="input_form" placeholder="Откуда">
                </label>
            </div>
            <div class="form_div">
                <label>
                    <input type="text" name="arrival" class="input_form" placeholder="Куда">
                </label>
            </div>
            <div id="select_date" class="form_div">
                <label for="input_select_date"></label><input type="date" name="date" id="input_select_date" class="input_form" placeholder="Дата отправления" style="cursor: pointer;">
            </div>
            <div id="search_div">
                <input type="submit" id="search_ticket" value="Найти билет">
            </div>
        </form>
        <div id="your_flight"></div>
    </div>
</div>

</body>
</html>