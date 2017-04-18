$.getScript("/js/regionsForStates.js", function(data, textStatus, jqxhr) {
console.log('Load was performed for ajax requests.');
});

function sendStateCrawlerQuery() {
	$('#crawlerForm').off('submit').submit(function(event){
		var typeOf = "craigsList";
		var state = $('#state').val();
		var regions = stateObject[state];
		var searchQuery = $('#query').val();
		var pages = $('#pages').val();
		if ($("#maxDepth").is(":checked")){var maxDepth = "true";}else{var maxDepth = "false";}
		var json = {"pages":pages, "searchQuery":searchQuery, "regions":regions, "maxDepth":maxDepth, "typeOf":typeOf};
		var valid = JSON.stringify(json);
		function IsJsonString(json) {try {JSON.parse(json);} catch (e) {return false; } return true;}
		console.log(IsJsonString(valid));
		console.log(json);
		event.preventDefault();
		$.ajax({
				type:'POST',
				url:"/api/main/initiateStateCrawler", 
				headers: { 'Content-Type': 'application/json' },
				dataType:'json',  
				mimeType:'json', 
				contentType:'application/json',
				data:valid,
				statusCode: {
					500: function(){alert("not enough pages exist for your search query");}, 
					200: function(){console.log("search worked \"correctly\"");}
				}
			}); 
		});
	}

function activateAlert() {
	$('#alertForm').off('submit').submit(function(event){
		
		var region = $('#ChicagoRegion').val();
		var query = $('#query1').val();
		
		event.preventDefault();
		
		$.ajax({
				type:'GET',
				url:"/api/main/createAlert/" + region + "/" + query, 
				headers: { 'Content-Type': 'application/json' },
				dataType:'json',  
				mimeType:'json', 
				contentType:'application/json',
				statusCode: {
					200: function(){console.log("search worked \"correctly\"");}
				}
			}); 
		});
}