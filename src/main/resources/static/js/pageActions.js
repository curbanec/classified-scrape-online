$.getScript("/js/regionsForStates.js", function(data, textStatus, jqxhr) {
	console.log('Load was performed for state dropdown.');
	});
$.getScript("/js/dateFormat.js", function(data, textStatus, jqxhr) {
	console.log('Load was performed for dateFormat.');
	});

$("#maxDepth").on("click", disable);
$("#alertForm").on("click", addToSearchList);

count = 0;

function cancel(queryId){

	var button = $("#" + queryId);
	button.html('Disabled');
	button.removeClass("btn-success").addClass("btn-danger");

	$.ajax({
		url:'/api/main/cancel?queryId='+queryId, 
		type:'GET',
	});
}

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


    function getCookie(c_name) {
        if(document.cookie.length > 0) {
            c_start = document.cookie.indexOf(c_name + "=");
            if(c_start != -1) {
                c_start = c_start + c_name.length + 1;
                c_end = document.cookie.indexOf(";", c_start);
                if(c_end == -1) c_end = document.cookie.length;
                return unescape(document.cookie.substring(c_start,c_end));
            }
        }
        return "";
    }

$(document).ready(function () {
    $('input.flat').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
    });
});

function addToSearchList(){
	
	var area = $('#area').val();
	var submissionTime = new Date(); // May 23, 2014 11:47:56 PM
	var submissionTimeDate = dateFormat(submissionTime, "yyyy-mm-d HH:MM");
	console.log(submissionTimeDate);
	var queryId = Math.floor((Math.random() * 1000000) + 1);
	// var listenerDuration = '';
	var query = $('#query').val();
	var notifyAddress = $('#notifyAddress').val();
	// var active = '';
	
	/*var secs = 60 * 1000;
	setInterval(function() {
	    var $badge = $('#nhb_01');
	    $badge.text((parseFloat($badge.text())+0.17).toFixed(2));
	}, secs);*/
	
	var json = {"submissionTimeDate":submissionTimeDate, "area":area, "query":query, "notifyAddress":notifyAddress, "queryId":queryId};
	
	$('#tableBody').append('<tr class="odd pointer"> <td class="a-center "> <input type="checkbox" class="flat" name="table_records"> </td>  <td class=" ">'+ area +'</td><td class=" ">'+ submissionTime +'</td><td class=" ">1</td><td class=" ">'+ query +'</td><td class=" ">'+ notifyAddress +'</td><td class="a-right a-right "><button id='+ queryId +' class="btn btn-success" type="button" onclick="cancel('+ queryId +')">'+ 'Active' +'</button></td></tr>');
	    
	var newTableRow = $('input.flat');
	
	newTableRow.iCheck({
		checkboxClass: 'icheckbox_flat-green', 
		radioClass: 'iradio_flat-green'
		});
	
	    $('table input').on('ifChecked', function () {
	        checkState = '';
	        $(this).parent().parent().parent().addClass('selected');
	        countChecked();
	    });
	    $('table input').on('ifUnchecked', function () {
	        checkState = '';
	        $(this).parent().parent().parent().removeClass('selected');
	        countChecked();
	    });

	    var checkState = '';

	    $('.bulk_action input').on('ifChecked', function () {
	        checkState = '';
	        $(this).parent().parent().parent().addClass('selected');
	        countChecked();
	    });
	    $('.bulk_action input').on('ifUnchecked', function () {
	        checkState = '';
	        $(this).parent().parent().parent().removeClass('selected');
	        countChecked();
	    });
	    $('.bulk_action input#check-all').on('ifChecked', function () {
	        checkState = 'all';
	        countChecked();
	    });
	    $('.bulk_action input#check-all').on('ifUnchecked', function () {
	        checkState = 'none';
	        countChecked();
	    });

	    function countChecked() {
	        if (checkState === 'all') {
	            $(".bulk_action input[name='table_records']").iCheck('check');
	        }
	        if (checkState === 'none') {
	            $(".bulk_action input[name='table_records']").iCheck('uncheck');
	        }

	        var checkCount = $(".bulk_action input[name='table_records']:checked").length;

	        if (checkCount) {
	            $('.column-title').hide();
	            $('.bulk-actions').show();
	            $('.action-cnt').html(checkCount + ' Records Selected');
	        } else {
	            $('.column-title').show();
	            $('.bulk-actions').hide();
	        }
	    }  
	    
	    var x = getCookie("XSRF-TOKEN");
	    
	    
	    $.ajax({
	    	headers: { 'Content-Type': 'application/json', 'X-XSRF-TOKEN': getCookie("XSRF-TOKEN")},
			url:"/api/main/createAlert", 
			type:'POST',
			dataType:'json', 
			contentType:'application/json',
			data:JSON.stringify(json)
		});     	    
}