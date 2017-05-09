$.getScript("/js/regionsForStates.js", function(data, textStatus, jqxhr) {
	console.log('Load was performed for state dropdown.');
	});

$("#maxDepth").on("click", disable);
$("#alertForm").on("click", addToSearchList);

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

function addToSearchList(){
	/*var li = document.createElement("LI"); 
	li.innerHTML = "hey";
	var input = document.getElementById("alertsSection");
    document.getElementById("alertsSection").appendChild(li);*/
    
    var table = document.getElementById("tableBody");
    var row = table.insertRow(0);
   
    var cell2 = row.insertCell(1);
    
    //http://api.jquery.com/append/
    
    cell1.innerHTML = "<td class="a-center "><input type="checkbox" class="flat" name="table_records"></td>";
    cell2.innerHTML = "NEW CELL2";
    
}