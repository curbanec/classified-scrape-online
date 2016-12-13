function initiateCrawler(){
			 $.ajax({
					url:"/api/main/initiateCrawler",
					type:'GET'
					});
				}

function sendCrawlerQuery() {
	$('#crawlerForm').submit(function(){
		var query = $('#query').val();
		var pages = $('#pages').val();			
		$.ajax({
				url:"/api/main/initiateCrawler/" + query + "/" + pages, 
				type:'GET'
			}); 
		});
	}
