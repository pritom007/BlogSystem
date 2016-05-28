/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Go to new blog/edit blog page
function edit() {
    location.href="newblogpost.jsp#content";
}
//Go to the registration page
function register(){
    location.href="register.jsp";
}
function changeToMailRegister() {
    document.getElementById("phone").style.display="none";
    document.getElementById("mail").style.display="block";
}
function changeToPhoneRegister() {
    document.getElementById("phone").style.display="block";
    document.getElementById("mail").style.display="none";
}
//Registration check
    var toWarn = false;
    var para = document.createElement("span");
    para.style.color="red";
    var node = document.createTextNode("");//Early warning information
    para.appendChild(node);//
//Mobile registration check(not included)
function phoneCheck() {
    var partten = /^1[358]([0-9]{9})$/;
    if (!partten.test(document.phone_form.phone.value)) {
        para.removeChild(node);
        node=document.createTextNode(" Phone number is not correct!");
        para.appendChild(node); 
        document.getElementById("1").appendChild(para);
        toWarn = true;
    }
}
function phone_register() {
    passwordRecheck("resurePassword1");
    passwordCheck("phone_password");
    check("phone_username");
    phoneCheck();
    if(!toWarn)
        document.phone_form.submit();
}
//mail check
function mailCheck() {
    var apos = document.mail_form.mail.value.indexOf("@");
    var dotpos = document.mail_form.mail.value.indexOf(".");
    if (apos<1||dotpos-apos<2) {
        para.removeChild(node);
        node=document.createTextNode(" mail is not correct!");
        para.appendChild(node); 
        document.getElementById("a").appendChild(para);
        toWarn = true;
    }
}
//userd id check
function check(id) {
    //var partten = /[^0-9a-z_]{1,}/i;
    var name =document.getElementById(id).value;
    if(name==="") {
        para.removeChild(node);
        node=document.createTextNode(" fill the username!");
        para.appendChild(node); 
        document.getElementById(id).parentNode.appendChild(para);
        toWarn = true;
    }

}
//password check
function passwordCheck(id) {
    var partten = /[^0-9a-z_\-+=*/!@#$%^&*()~`,.;'"￥]{1,}/i;
    var partten2 = /[^0-9]{1,}/i;//check if that is number
    if (partten.test(document.getElementById(id).value)) {
        para.removeChild(node);
        node=document.createTextNode(" Only numbers, letters, symbols"); 
        para.appendChild(node);
        document.getElementById(id).parentNode.appendChild(para);//Password field
        toWarn=true;
    }
    else if(document.getElementById(id).value==="") {
        para.removeChild(node);
        node=document.createTextNode(" fill the passsword!"); 
        para.appendChild(node);
        document.getElementById(id).parentNode.appendChild(para);//Password field
        toWarn=true;
    }
    else if (!partten2.test(document.getElementById(id).value)) {
        para.removeChild(node);
        node=document.createTextNode(" use some characters for strong password!"); 
        para.appendChild(node);
        document.getElementById(id).parentNode.appendChild(para);//Password field
        toWarn=true;
    }
    else if(document.getElementById(id).value.length<6) {
        para.removeChild(node);
        node=document.createTextNode(" password length can't be less than 6 "); 
        para.appendChild(node);
        document.getElementById(id).parentNode.appendChild(para);//Password field
        toWarn=true;
    }
    
}
function passwordRecheck(id) {
    if(id==="resurePassword1") {//mobile register
        if(document.getElementById(id).value !== document.phone_form.password.value) {
            para.removeChild(node);
            node=document.createTextNode(" Type correct password!");
            para.appendChild(node); 
            document.getElementById(id).parentNode.appendChild(para);
            toWarn = true;
        }
    }
    else {//mail register
        if(document.getElementById(id).value !== document.mail_form.password.value) {
            para.removeChild(node);
            node=document.createTextNode(" Type correct password!");
            para.appendChild(node); 
            document.getElementById(id).parentNode.appendChild(para);
            toWarn = true;
        }
    }
}
function clearError(id) {
    toWarn=false;
    document.getElementById(id).parentNode.removeChild(para);
}
function mail_register() {
    passwordRecheck("resurePassword2");
    passwordCheck("mailPw");
    check("mail_username");
    mailCheck();
    if(!toWarn)
        document.mail_form.submit();
}
//login check
function loginCheck() {
    if (document.login.username.value==="") {
        para.removeChild(node);
        node=document.createTextNode("Can not be empty!");
        para.appendChild(node); 
        document.login.username.parentNode.appendChild(para);
    }
    else if(document.login.password.value==="") {
        para.removeChild(node);
        node=document.createTextNode("Can not be empty!");
        para.appendChild(node); 
        document.login.password.parentNode.appendChild(para);
    }
    else
        document.login.submit();
}
//Signature function(havn't done)
function sign(position) {
    position.style.display="none";
    document.getElementById("signature_form").style.display="block";
}
function checkTitle(){
    var x = document.getElementById("tittle").value;
    if(x==""){
        document.getElementById("alert").innerHTML="*Title can't be empty!";
        document.login.username.parentNode.appendChild(para);
    }
    if(x.length>30){
      document.getElementById("alert").innerHTML="*Title is too big";
        document.getElementById("tittle").value="";
        
    }
}
//for title
function checkTitle2(){

        document.getElementById("alert").innerHTML="";
}
  