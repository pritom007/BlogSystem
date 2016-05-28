/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
//    var userRadio=$("input[name=user]");
//    userRadio.each().change(function(){
////        if(userRadio[index].attr("checked")){
//            alert(userRadio.val());
////        }
//    });
//    $("#alluser").click(function(){
//        $("#someuser").attr("checked",false);
 //       alert($("input[name=user]").val());
//    });
//    $("#someuser").click(function(){
//        $("#someuser").attr("checked",true);
//    });
    $("#search").click(function(){
        if($("#user").val()==="user") {
            if($("#username2").val()===""){
                $("#warn span.red").remove();
                $("#warn").append("<span class=\"red\">用户名不能为空</span>");
            }
            else{
                $.ajax({
                    url: "http://localhost:8080/14302016002_finalsubmit/CheckUser",
                    type: "get",
                    data: "user="+$("#username2").val(),
                    success: function(data) {
                        if(data!=="") {
                            $("#warn span.red").remove();
                            $("#warn").append("<span class=\"red\">"+data+"</span>");
                        }
                        else
                            $("#searchForm").submit();
                    }
                });
            }
        }
        else//Select all of the blog without judgment
            $("#searchForm").submit();
    });
});

