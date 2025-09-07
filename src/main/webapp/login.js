const form = document.getElementById('form');
const rname = document.getElementById('rname');
const remail = document.getElementById('remail');
const rnumber = document.getElementById('rnumber');
const rpassword = document.getElementById('rpassword');
const rcpassword = document.getElementById('rcpassword');

const name_error = document.getElementById('name_error');
const email_error = document.getElementById('email_error');
const mobile_error = document.getElementById('mobile_error');
const phone_error = document.getElementById('phone_error');
const confirm_password_error = document.getElementById('confirm_password_error');
const password_match_error = document.getElementById('password_match_error');

form.addEventListener('submit', e =>{
    if(rname.value === '' || rname.value === null){
        e.preventDefault();
        name_error.innerText = "Please Enter Name";
    }else{
        rname.value.trim();
    }
    if(remail.value === '' || remail.value === null){
        e.preventDefault();
        email_error.innerText = "Please Enter Email";
    }
    else{
        rname.value.trim();
    }
    if(rnumber.value.length != 10){
        e.preventDefault();
        mobile_error.innerText = "Mobile Number must be 10 Digits";
    }
    if(rpassword.value != rcpassword.value){
        e.preventDefault();
        password_match_error.innerText = "Password and confirm password do not match";
    }
    else{
        rpassword.value.trim();
    }
});