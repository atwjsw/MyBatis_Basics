function deleteBatch(basePath) {
	/*alert('submit form to deleteBatch from js');*/
	$("#mainForm").attr("action", basePath+"DeleteBatch.action");
	$("#mainForm").submit();	
}

function deleteOne(basePath, messageId) {
	/*alert('submit form to deleteOne from js: ' + messageId + '   ' + basePath+'DeleteOne.action');*/
	$("#messageId").attr("value", messageId);
	$("#mainForm").attr("action", basePath+"DeleteOne.action");
	$("#mainForm").submit();
	
}