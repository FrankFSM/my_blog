(function ($) {
  $.extend({
    tableUtil: {
      _option: {},
      init: function (options) {
        $.tableUtil._option = options;
        console.log(options.token);
        $('#tablelist').bootstrapTable({
          url: options.url,
          method: 'post',                      //请求方式（*）
          toolbar: '#toolbar',                //工具按钮用哪个容器
          striped: true,                      //是否显示行间隔色
          cache: true,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
          contentType: "application/x-www-form-urlencoded", // 发送到服务器的数据编码类型, application/x-www-form-urlencoded为了实现post方式提交
          sortable: false,                     //是否启用排序
          sortOrder: "asc",                   //排序方式
          sortStable: true,                   // 设置为 true 将获得稳定的排序
          queryParams: $.tableUtil.queryParams,//传递参数（*）
          queryParamsType: '',
          pagination: true,                   //是否显示分页（*）
          sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
          pageNumber: 1,                       //初始化加载第一页，默认第一页
          pageSize: 10,                       //每页的记录行数（*）
          pageList: [10, 20, 30, 50, 100],        //可供选择的每页的行数（*）
          search: true,                       //是否启用搜索框 根据sidePagination选择从前后台搜索
          strictSearch: true,                 //设置为 true启用 全匹配搜索，否则为模糊搜索
          searchOnEnterKey: true,            // 设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
          minimumCountColumns: 1,             //最少允许的列数
          showColumns: true,                  //是否显示 内容列下拉框
          showRefresh: true,                  //是否显示刷新按钮
          showToggle: true,                  //是否显示详细视图和列表视图的切换按钮
          ajaxOptions: {
            headers: {"X-CSRF-TOKEN": options.token}
          },
          // detailView: true,                   //是否显示父子表
          // showExport: true,                   //是否显示导出
          // exportDataType: "basic",              //basic', 'all', 'selected'.
          // clickToSelect: true,                //是否启用点击选中行
          // singleSelect: true,
          // height: 505,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
          onEditableSave: function (field, row, oldValue, $el) {
            if (options.updateUrl) {
              $.ajax({
                type: "post",
                url: options.updateUrl,
                data: {strJson: JSON.stringify(row)},
                success: function (json) {
                  if (json.status == 200) {
                    $.tool.alert(json.message);
                  } else {
                    $.tool.alertError(json.message);
                  }
                },
                error: function () {
                  $.tool.alertError("网络超时！");
                }
              });
            } else {
              $.tool.alertError("无效的请求地址！");
              return;
            }
          },
          rowStyle: options.rowStyle || function (row, index) {
            return {};
          },
          columns: options.columns
        });
      },
      queryParams: function (params) {
        params = $.extend({}, params);
        return params;
      },
      refresh: function () {
        $("#tablelist").bootstrapTable('refresh',
            {url: $.tableUtil._option.url});
      }
    },
  });
})(jQuery);
