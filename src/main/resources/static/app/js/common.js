/**
 * 通用方法封装处理
 * Copyright (c) 2018 Barry
 */

$(function(){
	// 复选框事件绑定
	if ($.fn.select2 !== undefined) {
		$("select.form-control:not(.noselect2)").each(function () {
			$(this).select2().on("change", function () {
				$(this).valid();
			})
		})
	}
	if ($(".i-checks").length > 0) {
	    $(".i-checks").iCheck({
	        checkboxClass: "icheckbox_square-green",
	        radioClass: "iradio_square-green",
	    })
	}
	if ($(".time").length > 0) {
		layui.use('laydate', function() {
		    var laydate = layui.laydate;
		    laydate.render({ elem: '#startTime', theme: 'molv' });
		    laydate.render({ elem: '#endTime', theme: 'molv' });
		});
	}
});

/** 创建选项卡 */
function createMenuItem(dataUrl, menuName) {
    dataIndex = $.common.random(1,100),
    flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
    var topWindow = $(window.parent.document);
    // 选项卡菜单已存在
    $('.menuTab', topWindow).each(function() {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $('.page-tabs-content').animate({ marginLeft: ""}, "fast");
                // 显示tab对应的内容区
                $('.mainContent .app_iframe', topWindow).each(function() {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('.app_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });
    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab', topWindow).removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="app_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.mainContent', topWindow).find('iframe.app_iframe').hide().parents('.mainContent').append(str1);

        // 添加选项卡
        $('.menuTabs .page-tabs-content', topWindow).append(str);
    }
    return false;
}

/** 设置全局ajax超时处理 */
$.ajaxSetup({
    complete: function(XMLHttpRequest, textStatus) {
        if (textStatus == "parsererror") {
        	$.modal.confirm("登陆超时！请重新登陆！", function() {
        		window.location.href = ctx + "login";
        	})
        }
    }
});

/*用户管理-部门*/
function dept() {
    var url = ctx + "system/dept";
    createMenuItem(url, "部门管理");
}

/*用户管理-新增-选择部门树*/
function selectDeptTree() {
    var treeId = $("#treeId").val();
    var deptId = treeId == null || treeId == "" ? "100" : treeId;
    var url = ctx + "system/dept/selectDeptTree/" + deptId;
    $.modal.open("选择部门", url, '380', '380');
}

function queryDeptTreeDaTa() {
    // 树结构初始化加载
    var setting = {
        view: {selectedMulti: false}, data: {key: {title: "title"}, simpleData: {enable: true}},
        callback: {
            onClick: function (event, treeId, treeNode) {
                //tree.expandNode(treeNode);
                $("#deptId").val(treeNode.id);
                $("#parentId").val(treeNode.pId);
                $.table.search($('form').attr('id'));
            }
        }
    }, tree, loadTree = function () {
        $.get(ctx + "system/dept/treeData", function (data) {
            tree = $.fn.zTree.init($("#tree"), setting, data); //.expandAll(true);
            // 展开第一级节点
            var nodes = tree.getNodesByParam("level", 0);
            for (var i = 0; i < nodes.length; i++) {
                tree.expandNode(nodes[i], true, false, false);
            }
            // 展开第二级节点
            nodes = tree.getNodesByParam("level", 1);
            for (var i = 0; i < nodes.length; i++) {
                tree.expandNode(nodes[i], true, false, false);
            }
        }, null, null, "正在加载，请稍后...");
    };
    loadTree();

    $('#btnExpand').click(function () {
        tree.expandAll(true);
        $(this).hide();
        $('#btnCollapse').show();
    });
    $('#btnCollapse').click(function () {
        tree.expandAll(false);
        $(this).hide();
        $('#btnExpand').show();
    });
    $('#btnRefresh').click(function () {
        loadTree();
    });
}
