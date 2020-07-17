//加载笔记列表
function loadNotes(){
	// 获取当前笔记本的id
	var bookid=$(this).data("bookId");
	// alert(bookid);
	
	// 移除上次被选中的笔记本的样式
	$("#notebooks li a").removeClass("checked");
	
	// 给选中的笔记本添加样式（$(this)指的是li)
	$(this).find("a").addClass("checked")
	
	// 发送ajax请求
	$.ajax({
 	       url:"http://localhost:8081/SN_cloudNote/note/loadnotes.do",
           type:"post",
           data:{"bookId":bookid},// bookId
           dataType:"json",
           success : function(res) {
            if(res.status==0){
            	
            // 清空原有的笔记信息
            $("#note_list").empty();
            	
          	var notes=res.data;  // 获取笔记信息
	        console.log(res.data); // 控制台查看是否有数据
	        // 循环获取每个笔记
	        for(var i=0;i<notes.length;i++){
	        	
	        	var notename=notes[i].cn_note_title; // 获取笔记标题
	        	var noteid=notes[i].cn_note_id; // 获取笔记的id
	        	// alert(notename);
	        	
	        	// 拼一个笔记字符串
				var s_li = "";
				s_li+='<li class="online">';
				s_li+='	<a>';
				s_li+='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				s_li+= notename;
				// s_li+='<i class="fa fa-sitemap"></i>';
				s_li+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				s_li+='	</a>';
				s_li+='	<div class="note_menu" tabindex="-1">';
				s_li+='		<dl>';
				s_li+='			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
				s_li+='			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
				s_li+='			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
				s_li+='		</dl>';
				s_li+='	</div>';
				s_li+='</li>';
	        	
	        	// 拼接后的字符串是dom格式，需要转换为jQuery格式
	        	var $s_li=$(s_li);
	        	// 给li添加上bookId
	        	$s_li.data("noteId",noteid);
	        	// 将字符串$s_li添加到ul列表中
	        	$("#note_list").append($s_li);
	        }	
            }
           }
})
}

// 弹出添加笔记的窗口
function showAddNoteWindow(){
	$("#can").load("alert/alert_note.html");// 加载弹出框
	$(".opacity_bg").show();
}

// 添加笔记
function addNotes(){
	// 获取数据
	var $li=$("#notebooks li a.checked").parent();
	var bookid=$li.data("bookId");// 获取bookid
	var notename= $("#input_note").val()||"";// 获取添加笔记的框的笔记名称
	
	 $.ajax({
		 url:"http://localhost:8081/SN_cloudNote/note/addnotes.do",
		 type:"post",
		 data:{"noteName":notename,"bookId":bookid,"userId":userId}, // 插入数据
		 dataType:"json",
		 success:function(result){
			 if(result.status==0){
				// 创建成功后关闭弹框及背景色
			        closeWindow();
					
		        	var noteid=result.data;// 获取添加笔记的id
					// 拼一个笔记字符串
					var s_li='<li class="online">';
						s_li+='<a>';
						s_li+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+notename+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
						s_li+='</a>';
						s_li+='<div class="note_menu" tabindex="-1">';
						s_li+='<dl>';
						s_li+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
						s_li+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
						s_li+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
						s_li+='</dl>';
						s_li+='</div>';
						s_li+='</li>';
					
					// 将笔记的id藏值
					var $li=$(s_li);
					$li.data("noteid",noteid);
					// alert($li.data("noteid"));
					$("#note_list").prepend($li);
		        	
			 }
			 else{
				 alert(result.msg);
			 }
		 } 
	 })
}

// 根据id编辑笔记
function findNoteById(){
	
	// 移除上次被选中的笔记本的样式
	$("#note_list li a").removeClass("checked");	
	// 给选中的笔记本添加样式（$(this)指的是li)
	$(this).find("a").addClass("checked");
	
	// 获取笔记id
	var noteId=$(this).data("noteId");
	// 发送ajax请求
	$.ajax({
		url:"http://localhost:8081/SN_cloudNote/note/findnote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				// alert(result.data.cn_note_title);
				// 将笔记标题放入编辑器标题中
				$("#input_note_title").val(result.data.cn_note_title);
				// 将笔记的内容放到编辑器文本框中
				um.setContent(result.data.cn_note_body); // 给文本框设置内容
			}
		}
			})
}

//修改笔记
function modifyNote(){
	// 获取当前笔记的id——重要
	var noteId=$("#note_list li a.checked").parent().data("noteId");
	// 获取笔记title
	var noteTitle=$("#input_note_title").val();
	// 获取笔记body
	var noteBody=um.getContent();
	
	$.ajax({
		url:"http://localhost:8081/SN_cloudNote/note/savenote.do",
           type:"post",
           data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},// bookId
           dataType:"json",
           success:function(res){
        	   if(res.status==0){
        		   // 判断标题有没有改
        		 var now_title=$("#note_list li a.checked").text()||"";
        		 if(now_title!=noteTitle){// 现在获取的标题和笔记列表中的标题不一致
        			 var s='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';// 标题的<a></a>
        			 $("#note_list li a.checked").html(s);
        		 }
        	   }
           }
	})
}

//删除笔记（放入回收站）
function deleteNote(){
	//获取笔记id
	var li=$("#note_list a.checked").parent();
	var noteId=li.data("noteId");
	//alert(noteId);
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8081/SN_cloudNote/note/deletenote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//清除笔记li
				li.remove();
				//清除编辑区
				$("#input_note_title").val("");//清空标题
				um.setContent("");//清空内容
				//弹框提示删除笔记成功
				alert(result.msg);
				$(".opacity_bg").hide();//删除背景色
				$("#can").html("");
			}
		},
		error:function(){
			alert("删除失败")
		}
		
	});

}

//转移笔记弹出框
function moveNoteWindow(){
	    $(".opacity_bg").show();
		$("#can").load("alert/alert_move.html",function(){
			//为alert_move.html中<select>加载数据
			var books = $("#notebooks li");//获取笔记本列表
			//循环book列表数据
			for(var i=0;i<books.length;i++){
				var $li = $(books[i]);//获取li元素并转为jQuery对象
				var bookId = $li.data("bookId");//获取笔记本id
				var bookName = $li.text()||"";//获取笔记本名
				//alert(bookId);
				//创建一个option元素
				var sopt = '';
				sopt+='<option value="'+bookId+'">';
				sopt+= bookName;
				sopt+='</option>';
				//添加到select中
				$("#moveSelect").append(sopt);
			}
		});
}

//确认移动笔记
function moveNote(){
		 //获取要转移的笔记ID
		 var $li = $("#note_list a.checked").parent();
		 var noteId = $li.data("noteId");
		 //获取要转入的笔记本ID
		 var bookId = $("#moveSelect").val();
		 //发送Ajax请求
		 $.ajax({
			 url:"http://localhost:8081/SN_cloudNote/note/movenote.do",
			 type:"post",
			 data:{"noteId":noteId,"bookId":bookId},
			 dataType:"json",
			 success:function(result){
				 if(result.status==0){
					 //移除笔记li
					 $li.remove();
					 //提示成功
					 alert(result.msg);
					 $(".opacity_bg").hide();//删除背景色
					 $("#can").html("");
				 }
			 },
			 error:function(){
				 alert("转移笔记异常");
			 }
		 });
}

//分享笔记
function shareNote(){
	//获取笔记id
	var li=$("#note_list a.checked").parent();
	var noteId=li.data("noteId");
	//alert(noteId);
	$.ajax({
    	 url:"http://localhost:8081/SN_cloudNote/note/sharenote.do",
         type:"post",
         data:{"noteId":noteId},
         dataType:"json",
         success:function(result){
        	 if(result.status==0){
 				alert(result.msg);
 			}
 			if(result.status==1){
 				alert(result.msg);
 			}
         }
    })
}

//搜索框中搜索分享笔记
function searchNote(){
	//清除原有搜索的笔记
	$("#share_list").empty();
	var code = event.keyCode;//获取按下键的ASCII值
	if(code==13){//回车键的ASCII值为13
		//获取输入框的值
		var value=$("#search_note").val();
		
		$.ajax({
			url:"http://localhost:8081/SN_cloudNote/note/searchNote.do",
			type:"post",
			data:{"value":value},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var notes=result.data;
					for(var i=0;i<notes.length;i++){
						var title=notes[i].cn_share_title;//获取标题
						var shareid=notes[i].cn_share_id;//获取id
						//拼一个li
						var s_li = "";
							s_li+='<li class="online">';
							s_li+='<a>';
							s_li+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
							s_li+= title;
							//s_li+='<i class="fa fa-sitemap"></i>';
							s_li+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
							s_li+='	</a>';
							s_li+='</li>';
						
						//将shareid藏值
						var $s_li=$(s_li);
						$s_li.data("shareId",shareid)
						
						//添加到pc_part_6中
						$("#share_list").append($s_li);
						
						//pc_part_6显示,pc_part_2隐藏
						$("#pc_part_6").show();//搜索笔记区
						$("#pc_part_2").hide();//笔记列表区
						$("#pc_part_5").show();//预览笔记区
						$("#pc_part_3").hide();//编辑笔记区
						$("#pc_part_4").hide();//回收站笔记区
					}
				}
			}
		});
	}
}

//查看分享笔记
function showShareNote(){
	//获取被分享笔记的id
	var id=$(this).data("shareId");
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8081/SN_cloudNote/note/showShareNote.do",
		type:"post",
		dataType:"json",
		data:{"shareId":id},
		success:function(result){		
			var share=result.data;
		
			//显示预览区(预览笔记），隐藏编辑区
			$("#noput_note_title").html(share.cn_share_title);
			var body=share.cn_share_body;
			$("#noput_note_body").html(body);
		}
	});
}

//弹出恢复笔记本的窗口
function replayNoteWindow(){  
	      //获取选中笔记的noteId
	      var noteId = $(this).parents("li").data("noteId");
	      //alert(noteId);
	      $(".opacity_bg").show();//显示背景色
	      $("#can").load("alert/alert_replay.html",function(){//发送ajax请求01——加载页面
	    	  
	      $("#sureReplay").data("noteId",noteId);//将noteId绑定至恢复按钮,新的方法！
		   
		   //发送ajax请求02——加载笔记本信息，生成下拉单选择项（必须在页面加载完毕后才可发送请求）
	 	   $.ajax({                   
	 		  url:"http://localhost:8081/SN_cloudNote/notebook/loadbooks.do",
	 	  	  type:"post",
	 	      data:{"userId":userId},
	 	  	  dataType:"json",
	 	  	  success:function(result){
	 	  		  if(result.status == 0){
	 	  			 var noteBooks = result.data;
	 	  			 for(var i=0;i<noteBooks.length;i++){
	 	  				 var bookId = noteBooks[i].cn_notebook_id;
	 	  				 var bookName = noteBooks[i].cn_notebook_name;
	 	  				 //拼一个option,option里面的value属性可以存储bookId,所以不用将option对象转换成jQuery对象再藏值。
	 	  				 var s_opt='<option value="'+bookId+'">-'+bookName+'-</option>';
	 	  				 $("#replaySelect").append(s_opt);
	 	  			 }
	 	  		  }
	 	  	  }
	 	   });
	   });       	            	   
}

//显示回收站笔记列表
function loadRecycleNotes(){
	var userId=getCookie("uid");
	
	//显示回收站笔记列表	
	$("#pc_part_4").show();//回收站
	$("#pc_part_2").hide();//全部笔记
	$("#pc_part_6").hide();//分享笔记
	$("#pc_part_7").hide();//收藏笔记
	$("#pc_part_8").hide();//参加笔记活动
	//alert(userId)
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8081/SN_cloudNote/note/rollback.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			
			var notes=result.data;
			//循环生成回收站笔记li
			for(var i=0;i<notes.length;i++){
				var title=notes[i].cn_note_title;//获取笔记标题
				//拼成一个li列表
				var li='<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
				var noteid=notes[i].cn_note_id;//获取笔记id
				var $li=$(li);
				$li.data("noteId",noteid);
				//追加到回收站列表
				$("#rollback_note").append($li);
			}
			
		}
	});
}

//点击"恢复"按钮恢复笔记
function replayNote(){
	   var $li = $("#note_list a.checked").parent();
	   var noteId = $("#sureReplay").data("noteId");//noteId已经被藏到恢复按钮中
	   var bookId = $("#replaySelect").val();//获取下拉框选中option的val
	   $.ajax({
		  url:"http://localhost:8081/SN_cloudNote/note/replayNote.do",
	  	  type:"post",
	      data:{"bookId":bookId,"noteId":noteId},
	  	  dataType:"json",
	  	  success:function(result){
	  		if(result.status == 0){    	  		   
	  		   closeWindow();//关闭对话框   
	  		   $("#rollback_note").empty();
	  		   $("#rollback_button").click() //刷新回收站列表显示,等价于用户自己点一下
	  		   alert("恢复笔记成功")
	  		}
	  	  }
	   });
}

//彻底删除笔记
function sureDeleteNote(){
	   var noteId=$(this).parents("li").data("noteId");
	   $("#can").load("alert/alert_delete_rollback.html",function(){//使用load("xxx.html",function)的形式，否则后面有绑不上的风险！
		   $("#sure_deleteNote").click(function(){
 		     $.ajax({
 		        url:"http://localhost:8081/SN_cloudNote/note/sureDelete.do",      
 		        Type:"post",
 		        data:{"noteId":noteId},
 		        dataType:"json",
 		        success:function(result){
 		          if(result.status == 0){
 		        	closeWindow();
 		        	$("#rollback_note").empty();
 		        	$("#rollback_button").click();
 		            alert(result.msg);
 		          }
 		        },
 		        error:function(){
 		        	alert("系统异常，删除失败！");
 		        }
 		     });
		   });
	   });     	   
}