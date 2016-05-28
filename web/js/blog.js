/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
     if ($("#commentName").val()==="") {
         $("#form1").hide();//评论表
     }
     else {
        $("#asset2").hide();//隐藏提醒登陆
          //点赞功能
        $("a#like1").click(function(){
            $.ajax({
                url: "http://localhost:8080/myblog3/Like",
                type: "get",
                data: "blogId=" + $("#blogId").val()+"&username=" +$("#username1").text()+"&user="+$("#commentName").val(),
                success: function(data) {
                    $("span#like2").text("("+data+")");
                    $("#like3").hide();
                    $("#cancelLike").show();
                    $("span#data").remove();
                    $("#cancelLike").append("<span id=\"data\">("+data+")</span>");
//                   
                }
            });
        });
        //如果是当前用户赞过，提供取消赞
        $.ajax({
            url: "http://localhost:8080/myblog3/CheckCancelLike",
            type: "post",
            data: "userblogId=" + $("#username").val()+$("#blogId").val() +"&user="+$("#commentName").val(),
            success: function(data) {
                if(data==="ok") {//表示赞过
                    $("#like3").hide();
                    $("#cancelLike").show();
                }
            }
        });
        //取消赞
        $("#cancelLike").click(function(){
            $.ajax({
                url: "http://localhost:8080/myblog3/CancelLike",
                type: "get",
                data: "blogId=" + $("#blogId").val()+"&username=" +$("#username1").text() +"&user="+$("#commentName").val(),
                success: function(data) {
                    $("#like3").show();
                    $("#cancelLike").hide();
                    $("span#like2").text("("+data+")");
                }
            });
        });
    }
    //如果是当前用户评论，提供删除键
    $("a.users").each(function() {
        if($(this).text()===$("#commentName").val()) {
            $(this).next().text("删除");
        }    
    });
    
//    //删除评论
//    $("a.float_right").contains("删除").click(function(){
//        $.ajax({
//            url: "http://localhost:8080/myblog3/DeleteComment",
//            type: "get",
//            data: "commentId=" + $("#commentId").val()+"&userblogId=" +$("#username1").text()+$("#blogId").val(),
//            success: function(data) {
//                if (data==="ok")
//                $("span#like2").text("("+data+")");
//            }
//        });
//    });
//    //评论者检查
//    $("#commentuser").blur(function(){
//        $.ajax({
//            url: "http://localhost:8080/myblog3/CheckUser",
//            type: "get",
//            data: "user=" + $(this).val(),
//            success: function(data) {
//               if (data!=="") {
//                    $("#commentuser").parent().append("<span class='red'> "+data+"</span>");
//                    toWarn=true;
//                }
//            }
//        });
//    });
//    //获得焦点时，移除错误信息
//    $("#commentuser").focus(function(){
//        $("span.red").remove();
//        toWarn=false;
//    });
//    $("#commentSubmit").click(function(){
//        //mailCheck();
////        //user Check
////        $.ajax({
////            url: "http://localhost:8080/myblog3/CheckUser",
////            type: "get",
////            data: "user=" + $(this).val(),
////            success: function(data) {
////               if (data!=="") {
////                    $("#commentuser").parent().append("<span class='red'> "+data+"</span>");
////                    toWarn=true;
////                }
////            }
////        });
////        if(!toWarn)
//            document.mail_form.submit();
//    });
});
    

