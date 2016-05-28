/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    //以xia为修改个人资料
    //默认选中
    $("#all li").each(function(){
        if ($(this).text()===$("#operation").val()) {
            $(this).addClass("selected");
            $("#"+$(this).attr("id")+"1").css("display","block");//对应右框
        }
    });
    $("#all li:contains('头像设置')").click(function() {
        //取消原来选中
        $("#all li").each(function(){
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
                $("#"+$(this).attr("id")+"1").css("display","none");//对应右框
            }
        });
        $("#setPic1").css("display","block");
        $(this).addClass("selected");
    });
    $("#all li:contains('分类管理')").click(function() {
        //取消原来选中
        $("#all li").each(function(){
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
                $("#"+$(this).attr("id")+"1").css("display","none");//对应右框
            }
        });
        $("#blog1").css("display","block");
        $(this).addClass("selected");
    });
    //编辑分类
    var operation ="create";
    var sort;
    var crruent;
    $("ul li a.edit").click(function(){
        crruent = $(this);
        thisLi=$(this);
        sort = $(this).prev().prev().text();
        $("#createSort").val(sort);
        operation="edit";
    });
   // 删除分类
    $("ul li a.delete").click(function(){
        $(this).parent().hide();
        $.ajax({
            url: "http://localhost:8080/myblog3/DeleteSort",
            type: "post",
            data: "sort=" + $(this).prev().text(),
            success: function(data) {
            }
        });
    });
    //创建分类
    $("#createButton").click(function(){
        if (operation==="edit"){
                //编辑现有分类
                $.ajax({
                    url: "http://localhost:8080/myblog3/EditSort",
                    type: "post",
                    data: "sort=" + sort +"&newSort=" + $("#createSort").val(),
                    success: function(data) {
                        if(data==="ok") {
                            crruent.prev().prev().text($("#createSort").val());
                            operation ="create";
                        }
                    }
                });
        }
        else {
            $.ajax({
                    url: "http://localhost:8080/myblog3/CreateSort",
                    type: "post",
                    data: "newSort=" + $("#createSort").val(),
                    success: function(data) {
                        if(data==="ok") {
                            $("#sort").append("<li><span>"+$("#createSort").val()+"</span><a href=\"javascript:void(0)\" class=\"delete\">[删除]</a><a href=\"javascript:void(0)\" class=\"edit\">[编辑]</a></li>");
                            location.href="personalInformation.jsp?op=分类管理";
                        }
                        else
                            alert("该分类已存在");
                    }
                });
        }
    }); 
    //修改密码
    $("#all li:contains('修改密码')").click(function() {
        //取消原来选中
        $("#all li").each(function(){
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
                $("#"+$(this).attr("id")+"1").css("display","none");//对应右框
            }
        });
        $("#changepw1").css("display","block");
        $(this).addClass("selected");
    });
    //原密码判断
    $("#originalpw").blur(function(){
        $.ajax({
            url: "http://localhost:8080/myblog3/CheckPw",
            type: "post",
            data: "pw=" + $(this).val(),
            success: function(data) {
                if (data!=="") {
                    $("#originalpw").parent().append("<span class='red'> "+data+"</span>");
                    toWarn=true;
                }
            }
        });
    });
    //获得焦点时，移除错误信息
    $("#originalpw").focus(function(){
        $("span.red").remove();
        toWarn=false;
    });
    //确认修改
    $("#changeSubmit").click(function(){
        passwordRecheck("resurePassword1");
        passwordCheck("password");
        //愿密码判断
        $.ajax({
        url: "http://localhost:8080/myblog3/CheckPw",
        type: "post",
        data: "pw=" + $("#originalpw").val(),
        success: function(data) {
            if (data!=="") {
                $("span.red").remove();//有的话先一出
                $("#originalpw").parent().append("<span class='red'> "+data+"</span>");
                toWarn=true;
            }
        }
        });
        if($("#originalpw").val()===$("#password").val()) {
            $("span.red").remove();//有的话先一出
            $("#password").parent().append("<span class='red'> 新密码不应与原来相同</span>");
            toWarn=true;
        }
        if(!toWarn)
            document.phone_form.submit();
    }); 
});

