/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    $("#createSort1").click(function(){
        $(this).hide();
        $("#createSort2").show();
    });
    //创建分类
    $("#createButton1").click(function(){
        $.ajax({
            url: "http://localhost:8080/myblog3/CreateSort",
            type: "post",
            data: "newSort=" + $("#createSort3").val(),
            success: function(data) {
                if(data==="ok") {
                    $("#sort").append("<option value=\""+$("#createSort3").val()+"\">"+$("#createSort3").val()+"</option>");
                    $("#createSort2").hide();
                    $("#createSort1").show();
                }
                else {
                    alert("该分类已存在");
                    $("#createSort2").hide();
                    $("#createSort1").show();
                }
            }
        });
    });
    //表单检查
//    $("input.tittle").blur(function() {
//        if ($(this).val()==="")
//            $(this).attr("placeholder","标题不能为空");
//    });
    $("#submitButton").click(function(){
        toWarn = false;
        if($("input.tittle").val()==="") {
            $("input.tittle").attr("placeholder","标题不能为空");
            toWarn=true;
        }
        if($("#content1").val()==="") {
            $("p.red").remove();//有的话先移除
            $("#content2").append("<p class=\"red\">正文内容不能为空</p>");
            toWarn=true;
        }
        else {
            $("p.red").remove();
        }
        var tag = $("input.tag").val();
        if(tag==="")
            $("#tag1").append("<span class=\"red\">标签不能为空</span>");
        var tagArray = tag.split(" ");
        var a = false;
        for (var i=0;i<tagArray.length;i++){
            for(var j=i+1;j<tagArray.length;j++) {
                if(tagArray[i]===tagArray[j]) {
                    a=true;
                    toWarn=true;
                }
            }
        }
        if (a){
            $("#tag1").append("<span class=\"red\"> 标签重复，还要注意只能用一个空格隔开</span>");
            a=false;
        }
        if(!toWarn)
            $("#editBlog").submit();
    });
    $("input.tag").focus(function(){
        $("span.red").remove();
    });
//    $("#content1").focus(function(){
//        $("p.red").remove();
//    });
});

