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
		url: 'product_queryJoinCategory.action',
		queryParams: {
			name: ''
		},
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
			text: '添加商品',
			handler: function() {
				parent.$('#win').window({    
					title:'添加商品',
					width:500,
					height:600,
				    content:'<iframe src="send_product_save.action" frameborder="0" width="100%" height="100%"/>'
				});  
			}
		},
		'-', {
			iconCls: 'icon-edit',
			text: '更新商品',
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
					title:'更新商品',
					width:280,
					height:250,
				    content:'<iframe src="send_product_update.action" frameborder="0" width="100%" height="100%"/>'
				}); 

				}
			}
		},
		'-', {
			iconCls: 'icon-remove',
			text: '删除商品',
			handler: function() {
               alert("==自己实现");
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
			title: '商品编码',
			width: 100
		},
		]],
		// 用来设置列的参数
		columns: [[
		/*  field设置字段的名称,与json中的key捆绑 ,title: 列标题   */
		{
			field: 'name',
			title: '商品名称',
			width: 100},
		{
			field: 'remark',
			title: '简单介绍',
			width: 300,
			align: 'right'
		},
		{
			field: 'category。type',
			title: '所属类别',
			width: 100,
			align: 'right',
			formatter: function(value, row, index) {
				//  console.info(row.type+","+row.account.login);
				if (row.category != null && row.category.type != null) {
					//return "<span>"+row.account.login+"</span>";
					return row.category.type;
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
