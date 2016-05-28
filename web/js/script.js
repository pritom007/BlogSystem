		
document.getElementById("foot").innerHTML ="<p>Copyright &copy; " + new Date().getFullYear() + " xxx, All Rights Reserved</p>"+"<p>Images by @google search</p>"+"<p>14SS Fudan University</p>";

//this is for username valid
function username_valid(){
    var x=document.getElementById("login").value;
    if(x.length<6){
       
        
        //document.getElementById("user_valid_pic").src="error.jpg";
        //document.getElementById("user_valid_pic").style.visibility="visible";
        document.getElementById("errname").innerHTML="*username must be at least 6 character";
        document.getElementById("login").style.background="red";
        document.getElementById("login").value="";
        
    }
    else if((x%1==0)){
       // window.alert("*username must cotain alphabetic character or symble!");
        //document.getElementById("user_valid_pic").src="error.jpg";
        //document.getElementById("user_valid_pic").style.visibility= "visible";
        document.getElementById("login").style.background="red";
        document.getElementById("errname").innerHTML="*username must cotain alphabetic character or symble!";
        document.getElementById("login").value="";
    }
    else{
        //document.getElementById("user_valid_pic").src="th.jpg";
        //document.getElementById("user_valid_pic").style.visibility="visible";
        document.getElementById("login").style.background="green";
        document.getElementById("errname").innerHTML="";
    }
    
}


//this is for checking the confirm password

function password_valid() {
            var x = document.getElementById("pass").value;
            var y = document.getElementById("pass2").value;
            if((x=="") || (y=="")){
            document.getElementById("pass").value="";
            //document.getElementById("pass_valid_pic").src="error.jpg";
            //document.getElementById("pass_valid_pic").style.margin-bottom="-5px";
           // document.getElementById("pass_valid_pic").style.visibility="visible";
        }
            else if(x==y){
            document.getElementById("pass2").style.background="green";
            //document.getElementById("pass_valid_pic").src="th.jpg";
            //document.getElementById("pass_valid_pic").style.margin-bottom="-5px";
            //document.getElementById("pass_valid_pic").style.visibility="visible";
        }
            else{
           document.getElementById("pass2").value="";
           document.getElementById("pass2").style.background="red";
           //document.getElementById("pass_valid_pic").src="error.jpg";
           //document.getElementById("pass_valid_pic").style.margin-bottom="-5px";
           document.getElementById("pass_valid_pic").style.visibility="visible";
        }
        }



//this is for checking the password
////this is for checking the password        
        function password_valid1(){
            var x=document.getElementById("pass").value;
            if(x.length<6){
                //window.alert("*password must be at least 6 character");
                document.getElementById("pass").value="";
                document.getElementById("errpass").innerHTML="*password must be at least 6 character";
                //document.getElementById("pass1_valid_pic").src="error.jpg";
                //document.getElementById("pass1_valid_pic").style="margin-bottom:-5px";
                //document.getElementById("pass1_valid_pic").style.visibility="visible";
                
            }
            else if((x%1==0)){
                //window.alert("*password must cotain alphabetic character or symble!");
                document.getElementById("pass").value="";
                document.getElementById("errpass").innerHTML="*password must cotain alphabetic character or symble!";
                //document.getElementById("pass1_valid_pic").src="error.jpg";
                //document.getElementById("pass1_valid_pic").style="margin-bottom:-5px";
                //document.getElementById("pass1_valid_pic").style.visibility="visible";
            }
            else{
                document.getElementById("pass").style.background="green";
               // document.getElementById("pass1_valid_pic").src="th.jpg";
               //document.getElementById("pass1_valid_pic").style.margin-bottom="-5px";
               //document.getElementById("pass1_valid_pic").style.visibility="visible";
               document.getElementById("errpass").innerHTML="";
            }
        }

        
//this is for the Title check
//
//this is for already read part
//this is to validate the tag
function checktag(){
    var x=document.getElementById("pass").value;
    if(x==""){
        document.getElementById("tag1").innerHTML="*please write something!!!";
    }
}
//canle the div
function cancletagdiv(){
    
       document.getElementById("tag1").innerHTML="";
    
}
function checktag2(){
    var x=document.getElementById("deletetag").value;
    if(x==""){
        document.getElementById("tag2").innerHTML="*please write something!!!";
    }
}
//canle the div
function cancletagdiv2(){
    
       document.getElementById("tag2").innerHTML="";
    
}
//
