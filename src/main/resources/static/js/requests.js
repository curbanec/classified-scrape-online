function initiateCrawler(){
			 $.ajax({
					url:"/api/main/initiateCrawler",
					type:'GET'
					});
				}

function sendCrawlerQuery() {
	$('#crawlerForm').unbind('submit').submit(function(event){
		var query = $('#query').val();
		var pages = $('#pages').val();			
		event.preventDefault();
		$.ajax({
				url:"/api/main/initiateCrawler/" + query + "/" + pages, 
				type:'GET', 
				statusCode: {
					500: function(){alert("not enough pages exist for your search query");}, 
					200: function(){console.log("search worked \"correctly\"");}
				}
			}); 
		});
	}
