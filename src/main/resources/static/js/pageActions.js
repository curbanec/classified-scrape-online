$("#maxDepth").on("click", disable);

count = 0;

function disable(event) {
	count++;
	if (count % 2 != 0) {
		$('#pages').attr("disabled", true);
		$('#pages').val("0");
	} else {
		$('#pages').attr("disabled", false);
		$('#pages').val("");
	}
}