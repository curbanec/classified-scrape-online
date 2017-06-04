function login(){
    
        var user = $('#user').val();
        var pass = $('#pass').val();
        
		$.ajax({
				type:'GET',
				url:"/api/login/" + user + "/" + pass, 
				headers: { 'Content-Type': 'application/json' },
				dataType:'json',
				contentType:'application/json',
				statusCode: {
					200: function(){
					    console.log("success login");
					   /* alert("success login");*/
					    document.cookie = 'username=' + user +'; path=/';
					    window.location.href = 'home.html';
	      			},
					500: function(){
					    alert("bad login info");
					}
				}
			}); 
}