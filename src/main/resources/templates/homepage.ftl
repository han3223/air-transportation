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
                <p style="font-family: Biennale; font-size: 207%; float: inherit; margin-bottom: 30px;">Регистрация</p>
                <form action="/" id="form_reg" method="post">
                    <input type="text" name="phone" class="inp_reg" placeholder="Телефон">
                    <input type="email" name="email" class="inp_reg" placeholder="E-mail">
                    <input type="password" name="password" class="inp_reg" placeholder="Пароль">
                    <input type="password" class="inp_reg" placeholder="Повторите пароль">
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
                <p style="font-family: Biennale; font-size: 207%; float: inherit; margin-bottom: 30px;">Вход</p>
                <form action="/user" id="form_reg" method="post">
                    <input type="email" name="email_login" class="inp_reg" placeholder="E-mail">
                    <input type="password" name="password_login" class="inp_reg" placeholder="Пароль">
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

        </div>
        <div id="reviews">
            <div id="your_reviews">
                <form action="" id="form_reviews">
                    <div id="body_reviews_ava">
                        <div id="reviews_ava"></div>
                        <input type="text" id="theme_reviews">
                    </div>
                    <div id="body_form_reviews">
                        <textarea id="text_reviews"></textarea>
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
                    <p style="font-family: Biennale; font-size: 207%; float: inherit; user-select: none;"><a href="homepage.html">FastFlights</a><p>
                </div>
            </div>

            <div id="profile">
                <div id="ava" onClick="openProfBlock()">
                </div>
                <div id="name_acc">
                    <div id="first_name">
                        <p class="prof" style="font-family: Biennale; font-size: 100%; float: inherit;">Твой</p>
                    </div>
                    <div id="last_name">
                        <p class="prof" style="font-family: Biennale; font-size: 100%; float: inherit;">Профиль</p>
                    </div>
                </div>
            </div>

        </div>
        <form action="" id="form_search_ticket">
            <div class="form_div">
                <input type="text" name="" class="input_form" placeholder="Откуда">
            </div>
            <div class="form_div">
                <input type="text" class="input_form" placeholder="Куда">
            </div>
            <div id="select_date" class="form_div">
                <input type="date" name="" id="input_select_date" class="input_form" placeholder="Дата отправления" style="cursor: pointer;">
                <!-- 	<div id="block_date">
                        <div id="text_select_date">
                            <span>Выберите дату отправления</span>
                        </div>
                        <div id="calendar_block">
                            <div id="block_name_mount">
                                <p></p>
                            </div>
                            <div id="calendar_weekdays" role="rowgroup">
                                <div id="calendar_weekdays_row" role="row">
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Понедельник" class="day">Пн</abbr>
                                    </div>
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Вторник" class="day">Вт</abbr>
                                    </div>
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Среда" class="day">Ср</abbr>
                                    </div>
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Четверг" class="day">Чт</abbr>
                                    </div>
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Пятница" class="day">Пт</abbr>
                                    </div>
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Суббота" class="day">Сб</abbr>
                                    </div>
                                    <div class="calendar_weekday" role="columnheader">
                                        <abbr title="Воскресенье" class="day">Вс</abbr>
                                    </div>
                                </div>
                            </div>
                            <div id="calendar_week_body">
                                <div class="calendar_week"></div>
                                <div class="calendar_week"></div>
                                <div class="calendar_week"></div>
                                <div class="calendar_week"></div>
                                <div class="calendar_week"></div>
                            </div>
                        </div>
                    </div> -->
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