//加载笔记本列表
function loadNoteBooks(){
    $.ajax({
     	url:"http://localhost:8081/SN_cloudNote/notebook/loadbooks.do",
       type:"post",
       data:{"userId":userId},//用户id
       dataType:"json",
       success : function(res) {
	        if (res.status == 0) {  //加载笔记本成功
	          	var notebooks=res.data;
		        console.log(res.data); //控制台查看是否有数据
		        //循环获取每个笔记本
		        for(var i=0;i<notebooks.length;i++){
		        	var bookname=notebooks[i].cn_notebook_name; //获取笔记本名字
		        	var bookId=notebooks[i].cn_notebook_id; //获取笔记本的id
		        	//alert(bookname);
		        	
		        	var s_li='<li class="online">';
		        	s_li+="<a>";
		        	s_li+='<i class="fa fa-book" title="online" rel="tooltip-bottom">;'
		        	s_li+='</i> '+bookname+'<button id="deleteBook" type="button" class="btn btn-default btn-xs btn_delete" title="删除" style="float:right;padding:3px"><i class="fa fa-times"></i></button>'
		            s_li+='</a></li>'
		        	
		        	//拼接后的字符串是dom格式，需要转换为jQuery格式
		        	var $s_li=$(s_li);
		        	//给li添加上bookId
		        	$s_li.data("bookId",bookId);
		        	//将字符串$s_li添加到ul列表中
		        	$("#notebooks").append($s_li);
		        }	
		       
		        /*
		      //加载笔记本成功的另一种写法：
		     if (result.status == 0) {
			     var notes=result.data
			     console.log(result.data);
		         var html="";
	             $.each(notebooks, function(k,v) {
			         html+='<li class="online"><a class="checked"> <i '+
				     'class="fa fa-book"'+ 'title="online" '+'rel="tooltip-bottom">'+' </i>'
			        +notebooks[k].cn_notebook_name+'</a></li>'
		   });
			     $("#notebooks").append(html); 
			     */
			     
       	}else {
		         console.log("失败");	
	        }
    },
            error : function(res) {
	            console.log(res);	
        	}
	});
}

//弹出添加笔记本的窗口
function showAddBookWindow(){
	$(".opacity_bg").show();
	$("#can").load("alert/alert_notebook.html"); //弹出新建笔记本的框
}

//关闭添加笔记本的框
function closeWindow(){
	$("#can").empty();
	$(".opacity_bg").hide(); //隐藏灰色背景
}

//给弹出的笔记本框的创建按钮添加事件
function  addNotebooks(){
	 //获取添加笔记本的框的笔记本名称
	var bookname= $("#input_notebook").val()||"";
	// alert(bookname);
	 $.ajax({
		 url:"http://localhost:8081/SN_cloudNote/notebook/addnotebook.do",
		 type:"post",
		 data:{"bookName":bookname,"userId":userId}, //插入数据（该用户，笔记本名）
		 dataType:"json",
		 success:function(result){
			 if(result.status==0){
		         //创建成功后关闭弹框及背景色
	             closeWindow();
			
			     var s_li='<li class="online">';
     	         s_li+="<a>";
     	         s_li+='<i class="fa fa-book" title="online" rel="tooltip-bottom">;'
     	         s_li+='</i> '+bookname+'<button id="deleteBook" type="button" class="btn btn-default btn-xs btn_delete" title="删除" style="float:right;padding:3px"><i class="fa fa-times"></i></button>'
     	         s_li+='</a></li>'
     	
     	         var bookid=result.data;
     	         //拼接后的字符串是dom格式，需要转换为jQuery格式
     	         var $s_li=$(s_li);
               	//给li添加上bookId
     	         $s_li.data("bookid",bookid);
     	        //将字符串$s_li添加到ul列表中
     	        $("#notebooks").prepend($s_li);
	       }else{
	    	   alert(result.msg);
	       }
			   
	        	
		 }
	 })
}

//弹出重命名笔记本对话框
function alertRenameBookWindow(){
 	$("#can").load("alert/alert_rename.html");
 	$(".opacity_bg").show();
 }

//重命名笔记本
function updateNoteBook(){
	var bookname= $("#input_notebook_rename").val()||"";
	var $li=$("#notebooks li a.checked").parent();
	var bookid=$li.data("bookId");
	$.ajax({
		type:"post",
		url:"http://localhost:8081/SN_cloudNote/notebook/updateNoteBookName.do",
		dataType:"json",
		data:{"bookId":bookid,"bookName":bookname},
		success:function(result) {
			if(result.status==0) {
				$li.children('a').html('<i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> '+bookname+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>');
				$("#can").empty();
				$(".opacity_bg").hide(); 
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

//删除笔记本
function deleteBook(){
	//获取笔记本id
	var li=$("#notebooks li a.checked").parent();
    var bookid=li.data("bookId");
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8081/SN_cloudNote/notebook/deletebook.do",
		type:"post",
		data:{"bookId":bookid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				li.remove();
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除失败")
		}
		
	});

}