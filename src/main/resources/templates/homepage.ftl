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
    <div id="body_form_reg">
        <div id="background_reg" onClick="closeFormReg()"></div>
        <div id="block_form_reg">
            <form action="/" style="text-align: center;" method="post">
                <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; margin-bottom: 10px;">Регистрация</p>
                <label>
                    <input type="text" name="first_name" class="inp_reg" placeholder="Имя" pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Поле должно быть заполнено латинскими символами!')">
                </label>
                <label>
                    <input type="text" name="last_name" class="inp_reg" placeholder="Фамилия" pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Поле должно быть заполнено латинскими символами!')">
                </label>
                <label>
                    <input type="tel" name="phone" class="inp_reg" placeholder="Телефон" pattern="^+[0-9]" minlength="12" maxlength="12" oninvalid="setCustomValidity('Телефон должен быть заполнен в международном формате!')">
                </label>
                <label>
                    <input type="email" name="email" class="inp_reg" placeholder="E-mail">
                </label>
                <label>
                    <input type="password" id="new_password" name="password" class="inp_reg" placeholder="Пароль" onkeyup="checkPasswordMatch()" minlength="8" pattern="[A-Za-z0-9!@#$%^&*()_+=-]{8,16}" oninvalid="setCustomValidity('Пароль должен включать: Минимум восемь символов, по крайней мере, одна буква, одна цифра!')">
                </label>
                <label>
<#--                    <input type="password" id="confirm_password" class="inp_reg" placeholder="Повторите пароль" >-->
                </label>
                <input type="submit" value="Зарегистироваться" class="inp_reg">
            </form>
        </div>
    </div>

    <div id="body_form_auth">
        <div id="background_reg" onClick="closeFormAuth()"></div>
        <div id="block_form_auth">
            <form action="/user" style="text-align: center;" method="post">
                <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; margin-bottom: 10px;">Вход</p>
                <label>
                    <input type="email" name="email" class="inp_reg" placeholder="E-mail">
                </label>
                <label>
                    <input type="password" name="password" class="inp_reg" placeholder="Пароль">
                </label>
                <input type="submit" value="Войти" class="inp_reg">
            </form>
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

        <div id="content">
            <div id="special_offer">
                <div style="width: 100%; text-align: center;">
                    <h2>Частые вопросы</h2>
                </div>
                <div style="margin-top: 15px; margin-left: 5px; display: flex; flex-wrap: wrap">
                    <div class="div_question">
                        <a><h3>Мне не пришёл билет. Что делать?</h3></a>
                    </div>
                    <div class="arrow arrow-bottom" onclick="openQuestion1()"></div>
                    <div id="arrow_1"><p>Письмо с маршрутной квитанцией (билетом) отправляет продавец в течение 3 часов на почту, которую вы указали при бронировании. Если на почте письма нет, проверьте папку «Спам» в своём почтовом ящике — часто потерянные письма оказываются именно там.</p></div>
                </div>
                <div style="margin-top: 15px; margin-left: 5px; display: flex; flex-wrap: wrap">
                    <div class="div_question">
                        <a><h3>Я хочу вернуть билет. Как мне это сделать?</h3></a>
                    </div>
                    <div class="arrow arrow-bottom" onclick="openQuestion2()"></div>
                    <div id="arrow_2"><p>Возможность возврата зависит от тарифа билета и правил авиакомпании. В некоторых тарифах вернуть билет получится только за дополнительную плату, в других же это будет бесплатно, а некоторые билеты вернуть не получится.</p></div>
                </div>
                <div style="margin-top: 15px; margin-left: 5px; display: flex; flex-wrap: wrap">
                    <div class="div_question">
                        <a><h3>Мне нужно поменять дату вылета. Что делать?</h3></a>
                    </div>
                    <div class="arrow arrow-bottom" onclick="openQuestion3()"></div>
                    <div id="arrow_3"><p>Возможность обмена зависит от тарифа билета и правил авиакомпании. В некоторых тарифах обмен включён только за дополнительную плату, в других же он будет бесплатным, а некоторые билеты обменять не получится.</p></div>
                </div>
                <div style="margin-top: 15px; margin-left: 5px; display: flex; flex-wrap: wrap">
                    <div class="div_question">
                        <a><h3>Допущена ошибка в данных при бронировании. Как исправить?</h3></a>
                    </div>
                    <div class="arrow arrow-bottom" onclick="openQuestion4()"></div>
                    <div id="arrow_4"><p>Некоторые авиакомпании допускают до трёх опечаток в фамилии, имени или отчестве, но лучше уточнить необходимость исправлений у продавца. Изменение данных может быть платным. Чтобы исправить ошибку, нужно обратиться к продавцу. Посмотрите его название в билете.</p></div>
                </div>
                <div style="margin-top: 15px; margin-left: 5px; display: flex; margin-bottom: 10px; flex-wrap: wrap">
                    <div class="div_question">
                        <a><h3>Как добавить багаж?</h3></a>
                    </div>
                    <div class="arrow arrow-bottom" onclick="openQuestion5()"></div>
                    <div id="arrow_5"><p>Порядок действий зависит от того, купили вы билет или ещё нет. Если нет, вы можете воспользоваться фильтрами при поиске на нашем сайте, и мы будем показывать только предложения с багажом. А если уже купили — тогда добавить багаж получится через продавца или авиакомпанию.</p></div>
                </div>
            </div>
            <div id="question">
                <#if flights?has_content>
                    <#assign x = 1>
                    <#list flights as flight>
                        <#if 0 <= flight?index>
                            <#list locations as location>
                                <#list carriers as carrier>
                                    <#list brands as brand>
                                        <#if flight?index == carrier?index && flight?index == brand?index>
                                            <div id="flight_div" class="flight" <#if 2 <= flight?index>style="display: none"</#if>>
                                                <form action="/add_ticket_to_the_database" style="width: 30%; border-right: 1px grey solid; display: flex; text-align: center" method="post">
                                                    <div id="buy_ticket">
                                                        <div id="center_div_buy_ticket">
                                                            <div id="price" class="${brand?index}">
                                                                <p>${(location.seat_price_per_kilometers * flight.distance * brand.cost_factor)?long?c} ₽</p>
                                                            </div>
                                                            <div id="location_type"><p>${location.place}</p></div>
                                                            <input type="button" id="input_buy_ticket" onclick="openFormBuyTicket()" value="Купить билет"/>
                                                        </div>
                                                    </div>
                                                    <div id="body_form_ticket">
                                                        <div id="background_reg" onClick="closeFormBuyTicket()"></div>
                                                        <div id="block_form_ticket">
                                                            <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; margin-bottom: 10px;">Заполните данные</p>
                                                            <label>
                                                                <input type="text" name="flight_number" value="${flight.flight_number}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="seat_category_code" value="${location.seat_category_code}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="brand_id" value="${brand.id}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="carrier_id" value="${carrier.id}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="ticket_price" value="${location.seat_price_per_kilometers * flight.distance * brand.cost_factor}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="departure_date" value="${date_departure}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="arrival_date" value="${date_departure}" style="display: none">
                                                            </label>
                                                            <label>
                                                                <input type="text" name="distance" value="${flight.distance}" style="display: none">
                                                            </label>
                                                            <input type="text" name="last_name" id="" class="inp_reg"
                                                                   placeholder="Фамилия"
                                                                   style="text-transform:uppercase">
                                                            <label for=""></label><input type="text" name="first_name" id="" class="inp_reg" placeholder="Имя" style="text-transform:uppercase">
                                                            <label for=""></label><input type="text" name="middle_name" id="" class="inp_reg" placeholder="Отчество" style="text-transform:uppercase">
                                                            <label for=""></label><input type="text" name="passport_series" id="" class="inp_reg" placeholder="Серия паспорта" minlength="4" maxlength="4">
                                                            <label for=""></label><input type="text" name="passport_id" id="" class="inp_reg" placeholder="Номер паспорта" minlength="6" maxlength="6">
                                                            <input type="submit" name="" id="" class="inp_reg"
                                                                   value="Купить билет">
                                                        </div>
                                                    </div>
                                                </form>
                                                <div id="information_ticket">
                                                    <div id="carrier" class="${flight?index}">
                                                        <div id="logo_carrier"></div>
                                                        <div id="name_carrier" class="${carrier?index}"><p>${carrier.company_name}</p></div>
                                                    </div>
                                                    <div id="info_flight">
                                                        <div id="time_departure">
                                                            <div id="time"><p>${flight.departure_time}</p></div>
                                                            <div id="city"><p>${city_departure}</p></div>
                                                            <div id="date"><p>${date_departure}</p></div>
                                                        </div>
                                                        <div id="total_time">
                                                            <div id="time_flight">
                                                                <div id="image_flight">
                                                                    <p>В пути: ${flight.time}</p>
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <div id="time_arrival">
                                                            <div id="time"><p>${flight.arrival_time}</p></div>
                                                            <div id="city"><p>${city_arrival}</p></div>
                                                            <div id="date"><p>${date_departure}</p></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </#if>
                                    </#list>
                                </#list>
                            </#list>
                        </#if>
                        <#if flight?index == 2>
                            <div id="flight_div" class="more_ticket" style="flex-wrap: wrap; justify-content: space-between; margin-bottom: 10px" >
                                <div style="width: 15%; height: 40px;"></div>
                                <div style="width: 60%; height: 60px">
                                    <button type="button" id="input_buy_ticket" style="margin-top: 10px;" onclick="openFlight()">Показать ещё</button>
                                </div>
                                <div style="width: 15%; height: 40px;"></div>
                            </div>
                        </#if>
                    </#list>
                <#else>
                    <style>
                        #question {
                            width: 550px;
                        }
                    </style>
                    <#assign x = 0>
                    <div id="your_flight" style="width: 100%; margin-top: 0; border: none; box-shadow: none"></div>
                </#if>
            </div>
        </div>
        <div id="reviews">
            <div id="your_reviews">
                <form <#if first_name?has_content && last_name?has_content> action="/${first_name}/${last_name}/add_review" <#else> action="/add_review"</#if> id="form_reviews" method="post">
                    <div id="body_reviews_ava">
                        <div id="reviews_ava"></div>
                        <label for="theme_reviews"></label><input type="text" id="theme_reviews" name="theme" placeholder="Тема комментария">
                    </div>
                    <div id="body_form_reviews">
                        <label for="text_reviews"></label><textarea id="text_reviews" name="text" placeholder="Текст комментария"></textarea>
                    </div>
                    <#if first_name?has_content>
                        <label>
                            <input type="text" value="${first_name}" style="display: none">
                        </label>
                        <label>
                            <input type="text" value="${last_name}" style="display: none">
                        </label>
                    </#if>
                    <input type="submit" id="input_buy_ticket" value="Отправить коментарий" style="width: 80%">
                </form>
            </div>
            <div id="all_reviews">
                <#list reviews as review>
                    <div style="margin-top: 23px; <#if 1 < review?index>display: none;</#if> <#if review?is_last>margin-bottom: 10px;</#if>" class="review">
                        <div id="body_reviews_ava">
                            <div id="reviews_ava" style="margin-right: 20px;"></div>
                            <div>
                                <div style="display: inline-block; margin-top: 25px; height: 20px; width: 70%"><h4>${review.first_name}</h4></div>
                                <div style="width: 70%"><h4>${review.last_name}</h4></div>
                            </div>
                            <div style="width: 100%; margin-top: 10px"><h3>${review.theme}</h3></div>
                        </div>
                        <div id="body_form_reviews" style="margin-top: 20px">
                            <h4>${review.text}</h4>
                        </div>
                    </div>
                    <#if review?index == 2>
                        <div style="width: 80%; margin: 0 auto">
                            <input type="button" id="input_buy_ticket" class="more_reviews" value="Показать еще" onclick="openReviews()" style="width: 100%;">
                        </div>
                    </#if>
                </#list>
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
                <a href="http://localhost:8080"><div id="logo"></div></a>
                <div id="name_firm">
                    <p style="font-family: Biennale,serif; font-size: 207%; float: inherit; user-select: none;"><a href="http://localhost:8080">FastFlights</a><p>
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
        <#if x == 0>
        <#else>
            <div id="your_flight"></div>
        </#if>
    </div>
</div>

</body>
</html>