/***
 * 1.获得活动列表
 */
function getActivityList(){
	$.ajax({
		type:"post",
		url:"http://localhost:8081/SN_cloudNote/activity/getActionList.do",
		dataType:"json",
		data:{},
		success:function(result) {
			if(result.status==0) {
				var list = result.data;
				var html = "";
				$(list).each(function(i){
					var color;
					if(i%4==0){
						color='bg-primary';
					}else if(i%4==1){
						color='bg-danger';
					}
					else if(i%4==2){
						color='bg-inverse';
					}else{
						color='bg-warning';
					};
					
					var column=i%3;
					html='<div id="contentfeeds'+i+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+color+'"><p class="lead"><a href="activity_detail.html?activityid='+this.cn_activity_id+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">正文：'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>'
					var $html=$(html)
					$html.data("activityid",this.cn_activity_id)
					$('#col_'+column).append($html);
				});
			} else {
				alert(result.message);
			}
		},
		error:function(result) {
			alert("请求失败.");
		}
	});
}

/***
 * 2.查询指定活动下已参加活动的笔记列表
 */
function getNoteActivitys(){
	var activityId = getUrlParam('activityid');
	$.ajax({
		type:"post",
		url:"http://localhost:8081/SN_cloudNote/activity/getActivityNote.do",
		dataType:"json",
		data:{"activityid":activityId},
		success:function(result) {
			if(result.status==0) {
				var noteActivityList = result.data;
				console.log(noteActivityList);
				$(noteActivityList).each(function(){
					$("#first_action ul").append('<li class="online"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+this.cn_note_activity_title+'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button><button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button></a></li>');
					$("#first_action ul li:last").data("activityNoteId",this.cn_note_activity_id);
				});
			} else {
				alert(result.message);
			}
		},
		error:function(result) {
			alert("请求失败.");
		}
	});
	  
     /* 另一种写法 */
	 /*
	  * var action = getUrlParam('activityid');
     $.ajax({
			url : "http://localhost:8081/SN_cloudNote/activity/getActivityNote.do",
			data : {"activityid":action},
			type : "post",
			dataType : "json",
			success : function(res) {
				if (res.status == 0) {
					//alert(action)
					var avtivitynotes = res.data
					var html = "";
					$.each( avtivitynotes,function(k, v) {
						
						html='<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+avtivitynotes[k].cn_note_activity_title+'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button><button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button></a></li>'
						var $html=$(html)
						$html.data("activitynoteid", avtivitynotes[k].cn_note_activity_id)
							
						$("#acnotes").append($html);
					});
					
				}else{
					closeAddWindow()
					alert("失败")
				} 
			},
			error : function() {
				alert("error");
			}
		});
		*/
}

/***
 * 3.查询活动笔记内容
 */
function getActivityNoteDetail(){

	// 移除上次被选中的活动笔记的样式
	$("#acnotes li a").removeClass("checked");	
	// 给选中的活动笔记添加样式（$(this)指的是li)
	$(this).find("a").addClass("checked");
	
	var activityNoteId = $(this).data("activityNoteId");
	$.ajax({
		type:"post",
		url:"http://localhost:8081/SN_cloudNote/activity/getActivityNoteDetail.do",
		dataType:"json",
		data:{"activityNoteId":activityNoteId},
		success:function(result) {
			if(result.status==0) {
				alert(activityNoteId)
				var noteActivity = result.data;
				$("#content_body").empty();
				$("#content_body").append('<h4><strong>标题: </strong>'+noteActivity.cn_note_activity_title+'</h4>');
				$("#content_body").append(noteActivity.cn_note_activity_body);
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}