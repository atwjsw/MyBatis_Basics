/*
 * 
 * 调用后台批量删除方法
 */ 
function deleteBatch(basePath) {
	/*alert('submit form to deleteBatch from js');*/
	$("#mainForm").attr("action", basePath+"DeleteBatch.action");
	$("#mainForm").submit();	
}

/*
 * 调用后台单条删除方法
 */
function deleteOne(basePath, messageId) {
	/*alert('submit form to deleteOne from js: ' + messageId + '   ' + basePath+'DeleteOne.action');*/
	$("#messageId").attr("value", messageId);
	$("#mainForm").attr("action", basePath+"DeleteOne.action");
	$("#mainForm").submit();
	
}
/*
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();	
}