<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/public/head.jspf"%>
<style type="text/css">
body {
	margin: 1px;
}
.searchbox {
  margin: -3;
}
</style>
<script type="text/javascript">
$(function() {
	// 吧当前dom-->query-->easyui对象
	$('#dg').datagrid({
		// 通过后台获取相应的数据,仅仅支持json格式
		// url:'datagrid_data.json',
		url: 'category_queryJoinAccount.action',
		//fitColumns:true,  /* 真正的自动展开/收缩列的大小 */
		striped: true,
		/* 显示 奇偶行变色 */
		nowrap: true,
		/* 则在同一行中显示数据 */
		loadMsg: '加载数据的提示信息',
		remoteSort: false,
		/* 必须禁止使用远程排序 */
		singleSelect: false,
		checkOnSelect: true,
		queryParams: {
			type: ''
		},
		//设置分页
		pagination: true,
		pageSize: 5,
		pageList: [5, 10, 15, 20],
		//指定id字段为标识字段，在删除更行时有用，如果配置此字段，在翻页的时候不会丢失
		idField: 'id',
		//工具条
		toolbar: [
		//linkbutton
		{
			iconCls: 'icon-add',
			text: '添加类别',
			handler: function() {
				parent.$('#win').window({    
					title:'添加类别',
					width:280,
					height:165,
				    content:'<iframe src="send_category_save.action" frameborder="0" width="100%" height="100%"/>'
				});  
			}
		},
		'-', {
			iconCls: 'icon-edit',
			text: '更新类别',
			handler: function() {
			   //1.判断是否有选中行数据
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length != 1) {
					//弹出提示信息
					$.messager.show({
						title: '错误提示',
						msg: '一次只能更新一条记录。',
						timeout: 2000,
						showType: 'slide'
					});

				} else {
				//1.弹出更新页面
				parent.$('#win').window({    
					title:'更新类别',
					width:280,
					height:250,
				    content:'<iframe src="send_category_update.action" frameborder="0" width="100%" height="100%"/>'
				}); 

				}
			}
		},
		'-', {
			iconCls: 'icon-remove',
			text: '删除类别',
			handler: function() {

				//1.判断是否有选中行数据
				var rows = $('#dg').datagrid('getSelections');
				//rows返回被选中的行，没有任何行被选中，则返回空数组
				if (rows.length == 0) {
					//弹出提示信息
					$.messager.show({
						title: '错误提示',
						msg: '至少选中一条记录。',
						timeout: 2000,
						showType: 'slide'
					});

				} else {
					//  提示是否确认删除，如果确认则执行删除逻辑
					$.messager.confirm('确认对话框', '您确认想要删除选中的记录吗？',
					function(r) {
						if (r) {
							//执行删除逻辑
							//1.获取被选中的记录，然后从记录获取相应的id
							var ids = "";
							for (var i = 0; i < rows.length; i++) {
								ids += rows[i].id + ",";
							}
							//2.拼接id值，发送给后台
							ids = ids.substring(0, ids.lastIndexOf(","));
							console.info(ids);
							//3.发送ajax 请求
							$.post("category_deleteByIds.action", {
								ids: ids
							},
							function(result) {
								console.info(result);
								//成功回调
								if (result == "true") {
								//取消选中的所有行
								$('#dg').datagrid('uncheckAll');
									$('#dg').datagrid('reload');

								} else {
									$.messager.show({
										title: '删除异常',
										msg: '删除失败,请检查操作。',
										timeout: 2000,
										showType: 'slide'
									});
								}

							},
							"text");
						}
					});

				}

			}
		},
		'-', {
			text: "<input id='ss' name='search' /> ",
		}],

		/*冻结列,适合列比较多的情况*/
		frozenColumns: [[{
			field: 'xyz',
			width: 50,
			checkbox: true
		},
		{
			field: 'id',
			title: '编码',
			width: 100
		},
		]],
		// 用来设置列的参数
		columns: [[
		/*  field设置字段的名称,与json中的key捆绑 ,title: 列标题   */
		{
			field: 'type',
			title: '类别名称',
			width: 100,
			formatter: function(value, row, index) {
				return "<span title=" + value + ">" + value + "</span>"
			}
		},
		{
			field: 'hot',
			title: '热点',
			width: 100,
			align: 'right',
			formatter: function(value, row, index) {
				//console.info(typeof(value));
				if (value) {
					return "<input type='checkbox' checked='checked' disabled='disable'></input>";
				} else {
					return "<input type='checkbox'  disabled='true' '></input>";
				}

			},
			styler: function(value, row, index) {
				if (value) {
					return 'background-color:#ffee00;color:red;';
				}
			}
		},
		{
			field: 'account.login',
			title: '所属管理员',
			width: 100,
			align: 'right',
			formatter: function(value, row, index) {
				//  console.info(row.type+","+row.account.login);
				if (row.account != null && row.account.login != null) {
					//return "<span>"+row.account.login+"</span>";
					return row.account.login;
				}
			}
		}]]
	});
	//把普通的文本框转化为搜索文本框
	$('#ss').searchbox({
		//触发查询事件
		searcher: function(value, name) {
			//获取当前查询
			$('#dg').datagrid('load', {
				type: value,
			});

			alert(value + "," + name)
		},
		prompt: 'Please Input Value'
	});
});
</script>
</head>
<body>
	<table id="dg"></table>	
</body>
</html>
