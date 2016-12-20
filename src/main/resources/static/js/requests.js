function initiateCrawler(){
			 $.ajax({
					url:"/api/main/initiateCrawler",
					type:'GET'
					});
				}

function sendCrawlerQuery() {
	$('#crawlerForm').submit(function(event){
		var query = $('#query').val();
		var pages = $('#pages').val();			
		event.preventDefault();
		$.ajax({
				url:"/api/main/initiateCrawler/" + query + "/" + pages, 
				type:'GET'
			}); 
		});
	}
