window.onload = reClick()

//防止用户重复点击提交按钮
function reClick() {
    $.get("/Hzforum/user/reClick.action", function (data) {
        document.getElementById("reClick").value = data;
    })
}

function usernameCheck() {
    var username = document.getElementById("username").value;
    $("#uSpan").load("/Hzforum/user/checkUsername.action", {username: username});
}

function checkPassword() {
    var p1 = document.getElementById("pass1").value;
    var p2 = document.getElementById("pass2").value;
    if (p1 != p2) {
        document.getElementById("pSpan").innerHTML = "两次密码输入不一致".fontcolor("RED");
    } else {
        document.getElementById("pSpan").innerHTML = "";
    }
}

function emailCheck() {
    var email = document.getElementById("email").value;
    var reg = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    if (!reg.test(email)) {
        document.getElementById("eSpan").innerHTML = "邮箱格式不对".fontcolor("red");
    } else {
        document.getElementById("eSpan").innerHTML = "";
    }
}

function phoneCheck() {
    var phone = document.getElementById("cellphone").value;
    var reg = new RegExp("^1(3|4|5|7|8)[0-9]\\d{8}$");
    if (!reg.test(phone)) {
        document.getElementById("cSpan").innerHTML = "号码格式不对".fontcolor("red");
    } else {
        document.getElementById("cSpan").innerHTML = "";
    }
}

// 表单提交前检验
function beforeSubmit(form){
    if(form.username.value==""){
        alert('用户名不能为空！');
        form.username.focus();
        return false;
    }
    if(form.nickname.value==""){
        alert('昵称不能为空！');
        form.nickname.focus();
        return false;
    }
    var regPhone = new RegExp("^1(3|4|5|7|8)[0-9]\\d{8}$");
    if(!regPhone.test(form.cellphone.value)){
        alert("手机号码格式不对");
        form.cellphone.focus();
        return false;
    }
    var regEmail = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    if(!regEmail.test(form.email.value)){
        alert("邮箱格式不对");
        form.email.focus();
        return false;
    }
    if(form.pass1.value==''){
        alert('密码不能为空！');
        form.pass1.focus();
        return false;
    }
    if(form.pass1.value.length<6){
        alert('密码至少为6位，请重新输入！');
        form.pass1.focus();
        return false;
    }
    if(form.pass1.value != form.pass2.value) {
        alert('你两次输入的密码不一致，请重新输入！');
        form.pass2.focus();
        return false;
    }
    return true;
}


