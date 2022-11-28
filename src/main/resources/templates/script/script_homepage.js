
const btnProfile = document.querySelector('button');

function openProfBlock() {
    const profBlock = document.querySelector('#body_prof_setting');
    profBlock.classList = 'open';
}
function closeProfBlock() {
    const profBlock = document.querySelector('#body_prof_setting');
    profBlock.classList = '';

}
function openFormReg() {
    const formReg = document.querySelector('#block_reg');
    formReg.classList.add('open');
}
function closeFormReg() {
    const formReg = document.querySelector('#block_reg');
    formReg.classList = '';
}
function closeFormAuth() {
    const formAuth = document.querySelector('#block_auth');
    formAuth.classList = '';
}
function openFormAuth() {
    const formAuth = document.querySelector('#block_auth');
    formAuth.classList.add('open');
}


function nextFligth() {
    const navigation = document.querySelector('#main_block_navigation');
    const first_radio = document.querySelector('#first_radio');
    const second_radio = document.querySelector('#second_radio');
    const third_radio = document.querySelector('#third_radio');
    if (navigation.style.right == '-100%') {
        navigation.style.right = '0%';
        navigation.style.transition = '1000ms';
        second_radio.checked = false;
        third_radio.checked = true;
    }
    else if (navigation.style.right == '0%') {
        navigation.style.right = '-200%';
        third_radio.checked = false;
        first_radio.checked = true;
    }
    else {
        navigation.style.right = '-100%';
        first_radio.checked = false;
        second_radio.checked = true;
    }
}

function lastFlight() {
    const navigation = document.querySelector('#main_block_navigation');
    const first_radio = document.querySelector('#first_radio');
    const second_radio = document.querySelector('#second_radio');
    const third_radio = document.querySelector('#third_radio');
    if (navigation.style.right == '-100%') {
        navigation.style.right = '-200%';
        second_radio.checked = false;
        first_radio.checked = true;
    }
    else if (navigation.style.right == '-200%') {
        navigation.style.right = '0%';
        first_radio.checked = false;
        third_radio.checked = true;
    }
    else {
        navigation.style.right = '-100%';
        third_radio.checked = false;
        second_radio.checked = true;
    }
}


var next = setInterval(nextFligth, 7000);
