<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/public/head.jspf"%>
<style type="text/css">
	form div{
		margin: 5px;
	}
</style>
<script type="text/javascript">
 $(function(){
 	//ifram中dg 对象
 	var dg=parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg");
 	
 	 //获取管理员类别下拉类别值
    $('#cc').combobox({    
	    url:'account_query.action',    
	    valueField:'id',    
	    textField:'login',
	    panelHeight:'auto',
	    editable:false
	});  
 		//完成数据回显
 	var rows=dg.datagrid("getSelections"); 	 
 	console.info(rows.length);
 	
 	//给表单赋值
 	$('#ff').form('load',{
		id:rows[0].id,
		type:rows[0].type,
		hot:rows[0].hot,
		'account.id':rows[0].account.id			
   });
  

 
	//设置type框验证
	$("input[name=type]").validatebox({
		required:true,
		missingMessage:"请输入类别名称"
	});
	
	//窗体弹出默认禁用属性
	$("#ff").form("disableValidation");
	
	//注册button事件
	$("#btn").click(function(){
		//开启验证
		$("#ff").form("enableValidation");
		//如果验证成功,则提交数据
		if($("#ff").form("validate")){
			//提交数据
			$.messager.progress();	// 显示进度条
			$('#ff').form('submit', {
				url: "category_update.action",
	     		success: function(){
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
					//关闭当前窗体
					parent.$("#win").window("close");
					dg.datagrid("reload");
					
				}
			});			
		}else{
			alert("验证失败");
		}
		
	});
});
</script>
</head>

<body>
	<form id="ff" method="post">
		<div>
			<label for="type">类别名称:</label>
		   <input  type="text" name="type" />
		</div>
		<div>
			<label for="hot"></label>
			 热点<input type="radio" name="hot" value="true" />
			 非热点 <input type="radio" name="hot" value="false" checked="checked" />
		</div>
		<div>
		   <label for="account">所属管理员:</label>
		   <!-- 远程加载管理员类型 -->
		  
		  <input id="cc" name="account.id" >  
		  	
		</div>
		<div>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新</a>  
			<input type="hidden" name="id" />
		</div>
	</form>
</body>
</html>
