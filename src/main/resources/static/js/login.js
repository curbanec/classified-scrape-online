function login(){
    $('#login').submit(function(){
        var user = "guy";
        var pass = "";
        
        /*$('#user').val();
        $('#pass').val();*/
        
		$.ajax({
				type:'GET',
				url:"/api/login/" + user + "-" + pass, 
				headers: { 'Content-Type': 'application/json' },
				dataType:'json',  
				/*mimeType:'json', */
				contentType:'application/json',
				statusCode: {
					200: function(){
					    console.log("success login");
					    alert("success login");
					    
	      			},
					500: function(){
					    alert("bad login info");
					}
				}
			}); 
    });
}