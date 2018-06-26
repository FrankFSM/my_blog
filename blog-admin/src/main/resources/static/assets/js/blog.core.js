/**
 * MIT License
 *
 * Copyright (c) 2018 yadong.zhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * 项目页面模板核心JS
 * blog.core
 * @author: ralap
 * @date: created at 2018/6/26 14:21
 */

function countChecked() {
  "all" === checkState && $(".bulk_action input[name='table_records']").iCheck(
      "check"), "none" === checkState && $(
      ".bulk_action input[name='table_records']").iCheck("uncheck");
  var a = $(".bulk_action input[name='table_records']:checked").length;
  a ? ($(".column-title").hide(), $(".bulk-actions").show(), $(
      ".action-cnt").html(a + " Records Selected")) : ($(
      ".column-title").show(), $(".bulk-actions").hide())
}

$(document).ready(function () {
  $("input[type=checkbox], input[type=radio]").iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue',
    increaseArea: '20%' // optional
  });
}), $("table input").on("ifChecked", function () {
  checkState = "", $(this).parent().parent().parent().addClass(
      "selected"), countChecked()
}), $("table input").on("ifUnchecked", function () {
  checkState = "", $(this).parent().parent().parent().removeClass(
      "selected"), countChecked()
});
var checkState = "";
$(".bulk_action input").on("ifChecked", function () {
  checkState = "", $(this).parent().parent().parent().addClass(
      "selected"), countChecked()
}), $(".bulk_action input").on("ifUnchecked", function () {
  checkState = "", $(this).parent().parent().parent().removeClass(
      "selected"), countChecked()
}), $(".bulk_action input#check-all").on("ifChecked", function () {
  checkState = "all", countChecked()
}), $(".bulk_action input#check-all").on("ifUnchecked", function () {
  checkState = "none", countChecked()
})