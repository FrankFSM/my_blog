(function ($) {
  $.extend({
    tableUtil: {
      _option: {},
      init: function (options) {
        $.tableUtil._option = options;
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
          clickToSelect: true,                //是否启用点击选中行
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
    buttonUtil: {
      init: function (options) {
        /**
         * 修改
         */
        $("#tablelist").on('click', '.btn-update', function () {
          var $this = $(this);
          var userId = $this.attr("data-id");
          $.ajax({
            type: 'post',
            url: options.getInfoUrl.replace("{id}", userId),
            beforeSend: function (xhr) {
              xhr.setRequestHeader(header, token);
            },
            success: function (json) {
              var info = json.data;
              resetForm(info);
              initUpdateInfo(info);
              $("#addOrUpdateModal").modal('show');
              $("#addOrUpdateModal").find(
                  ".modal-dialog .modal-content .modal-header h4.modal-title").html("修改"
                  + options.modalName);
              if ($("#password") && $("#password")[0]) {
                $("#password").removeAttr("required");
              }
              if ($("#username") && $("#username")[0]) {
                $("#username").attr("readonly", "readonly");
              }
              bindSaveInfoEvent(options.updateUrl);
            }
          })
          ;
        });

        /**
         * 删除
         */
        $("#tablelist").on('click', '.btn-remove', function () {
          var $this = $(this);
          var userId = $this.attr("data-id");
          remove(userId);
        });

        /**
         * 添加
         */
        $("#btn_add").click(function () {
          resetForm();
          $("#addOrUpdateModal").modal('show');
          $("#addOrUpdateModal").find(
              ".modal-dialog .modal-content .modal-header h4.modal-title").html("添加"
              + options.modalName);

          $("#password").attr("required", "required");
          $("#username").attr("required", "required");
          $("#username").removeAttr("readonly");
          bindSaveInfoEvent(options.createUrl);

        });

        $("#btn_delete_ids").click(function () {
          var selectedId = getSelectedId();
          if (!selectedId || selectedId == '[]' || selectedId.length == 0) {
            $.tool.alertError("请最少选项一条记录");
            return;
          }
          remove(selectedId)
        });

        $('#tablelist').on('click', '.btn-allot', function () {
          console.log("分配权限");
          var $this = $(this);
          var userId = $this.attr("data-id");
          $.ajax({
            async: false,
            type: "POST",
            data: {uid: userId},
            url: '/role/rolesWithSelected',
            beforeSend: function (xhr) {
              xhr.setRequestHeader(header, token);
            },
            dataType: 'json',
            success: function (json) {
              var data = json.data;
              var setting = {
                check: {
                  enable: true,
                  chkboxType: {"Y": "ps", "N": "ps"},
                  chkStyle: "radio"
                },
                data: {
                  simpleData: {
                    enable: true
                  }
                },
                callback: {
                  onCheck: function (event, treeId, treeNode) {
                    console.log(treeNode.tId + ", " + treeNode.name + ","
                        + treeNode.checked);
                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                    var nodes = treeObj.getCheckedNodes(true);
                    var ids = new Array();
                    for (var i = 0; i < nodes.length; i++) {
                      //获取选中节点的值
                      ids.push(nodes[i].id);
                    }
                    $.ajax({
                      type: "post",
                      url: options.saveRolesUrl,
                      data: {"userId": userId, "roleIds": ids.join(",")},
                      dataType: 'json',
                      beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                      },
                      success: function (json) {
                        $.tool.ajaxSuccess(json);
                        $.tableUtil.refresh();
                      },
                      error: function () {
                        $.tool.ajaxError();
                      }
                    });
                  }
                }
              };
              var tree = $.fn.zTree.init($("#treeRole"), setting, data);
              tree.expandAll(true);//全部展开

              $('#selectRole').modal('show');
            }
          });
        });
        /**
         * 加载Icon
         */
        $('#show_icon_all').click(function () {
          $('#iconList').modal('show');

          $(".icon-list i").on('click', function () {
            $("#icon").val($(this).attr('class'));
            $('#iconList').modal('hide');
          });
        });

        /**
         * 加载父目录
         */
        $('#show_menu_all').click(function () {
          $.ajax({
            async: false,
            type: "POST",
            data: {},
            url: '/resources/tree',
            beforeSend: function (xhr) {
              xhr.setRequestHeader(header, token);
            },
            dataType: 'json',
            success: function (json) {
              var data = json.data;
              var setting = {
                check: {
                  enable: true,
                  chkboxType: {"Y": "ps", "N": "ps"},
                  chkStyle: "radio"
                },
                data: {
                  simpleData: {
                    enable: true
                  }
                },
                callback: {
                  onCheck: function (event, treeId, treeNode) {
                    console.log(treeNode.tId + ", " + treeNode.name + ","
                        + treeNode.checked);
                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                    var nodes = treeObj.getCheckedNodes(true);
                    var ids = new Array();
                    for (var i = 0; i < nodes.length; i++) {
                      //获取选中节点的值
                      ids.push(nodes[i].id);
                    }

                    $("#parentName").val(treeNode.name);
                    $("#parentId").val(ids[0]);
                    $('#selectRole').modal('hide');
                  }
                }
              };
              var tree = $.fn.zTree.init($("#treeRole"), setting, data);
              tree.expandAll(true);//全部展开

              $('#selectRole').modal('show');
            }
          });
        });

        /**
         * 删除
         * @param selectedId
         */
        function remove(selectedId) {
          $.tool.confirm("确定删除该" + options.modalName + "信息?", function () {
            $.ajax({
              type: "post",
              url: options.removeUrl,
              traditional: true,
              data: {'ids': selectedId},
              beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
              },
              success: function (json) {
                $.tool.ajaxSuccess(json);
                $.tableUtil.refresh();
              },
              error: function () {
                $.tool.ajaxError();
              }
            })

          }, function () {

          }, 5000)
        }

      }
    }
  });
})(jQuery);

function bindSaveInfoEvent(url) {
  $(".addOrUpdateBtn").unbind('click');
  $(".addOrUpdateBtn").click(function () {
    if (validator.checkAll($('#addOrUpdateForm'))) {
      $.ajax({
        type: 'post',
        url: url,
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, token);
        },
        data: $("#addOrUpdateForm").serialize(),
        success: function (json) {
          $.tool.ajaxSuccess(json);
          $('#addOrUpdateModal').modal('hide');
          $.tableUtil.refresh();
        },
        error: function () {
          $.tool.ajaxError();
        }

      })
      ;
    }
  });
}

function resetForm(info) {
  $("#addOrUpdateModal form input,#addOrUpdateModal form select,#addOrUpdateModal form textarea").each(
      function () {
        var $this = $(this);
        clearText($this, this.type, info);
      });
}

function clearText($this, type, info) {
  var $div = $this.parents(".item");
  if ($div.hasClass("bad")) {
    $div.toggleClass("bad");
    $div.find("div.alert").remove();
  }
  if (info) {
    var thisName = $this.attr("name");
    var thisValue = info[thisName];
    if (type == 'radio') {
      if ((thisValue && thisValue == $this.val())) {
        $this.iCheck('check');
      } else {
        $this.iCheck('uncheck');
      }
      return;
    } else if (type == 'checkbox') {
      if ((thisValue || thisValue == 1)) {
        $this.iCheck('check');
      } else {
        $this.iCheck('uncheck');
      }
    } else {
      if (thisValue && thisName != 'password') {
        $this.val(thisValue);
      }
    }
  } else {
    if (type == 'radio' || type == 'checkbox') {
      $this.iCheck('uncheck');
    } else {
      $this.val('');
    }
  }
}

/**
 * 获取选中ID
 */
function getSelectedId() {
  var sele = $("#tablelist input[type='checkbox']");
  console.log(sele);

  var selectedJson = $("#tablelist").bootstrapTable('getAllSelections');
  console.log(selectedJson);
  var ids = [];
  $.each(selectedJson, function (i) {
    ids.push(selectedJson[i].id);
  });
  console.log("ids[{}]", ids);
  return ids;
}