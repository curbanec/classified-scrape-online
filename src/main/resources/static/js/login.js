// The name of the application is 'hello'.
angular.module('hello', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
      templateUrl : 'home.html',
      controller : 'home',
      controllerAs: 'controller'
    }).when('/signup', {
    	templateUrl : 'signup.html', 
    	controller : 'signup', 
    	controllerAs : 'controller',
    }).when('/login', {
      templateUrl : 'login.html',
      controller : 'navigation',
      controllerAs: 'controller'
    }).otherwise('/login');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  }).service('sharedProperties', function(){
	  
	  var serviceDisplayName;
	
  }).controller('signup', function($rootScope, $http, $location) {
	  
	  var self = this;
	  
	  // callback function
	  self.registerNewUser = function() {
	        signUp(self.credentials, function() {
	        });
	    }; 
	  
	// local helper function
	// containing function
	var signUp = function(credentials, callback) {
		
		//var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
		// var headers = {}; 
		
		// do not add the HTTP Request Header Authorization : Basic 
		
	    $http.post('/api/registration/userSignup', {"username":credentials.username, "password":credentials.password})
	    	.success(function(data){
	    		console.log("success");
	    		self.registrationSuccess = true;
	    		self.error = false;
	    		})
	    		.error(function(err){
	    			console.log("error");
	    			self.error = true;
	    			self.registrationSuccess = false;
	    		}, function() {
	    			$rootScope.authenticated = false;
	    			callback && callback();
	    		});
		}
  }).controller('home', function($rootScope, $http, $location, sharedProperties) {
    
	var self = this;  

    self.displayName = sharedProperties.serviceDisplayName;
    
    $.getScript("../js/pageActions.js", function(data, textStatus, jqxhr) {
		console.log('Load was performed for pageActions.js.');
		});
    $.getScript("../js/pageSetup.js", function(data, textStatus, jqxhr) {
		console.log('Load was performed for pageSetup.js.');
		});
    self.logout = function() {
  	  $http.post('logout', {}).finally(function() {
  	    $rootScope.authenticated = false;
  	    $location.path("/");
  	  });
  	}
  }).controller('navigation',

		  function($rootScope, $http, $location, sharedProperties) {

	  		var self = this;
	  
	  		// local helper function
	  		var authenticate = function(credentials, callback) {
	  			
	  			var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
	  			
	  		    $http.get('user', {headers : headers}).then(function(response) {
	  		    	if (response.data.name) {
	  		    		console.log(response.data.name);
	  		    		sharedProperties.serviceDisplayName = response.data.name;
	  		    		$rootScope.authenticated = true;
	  		        } else {
	  		            $rootScope.authenticated = false;
	  		          }
	  		        callback && callback();
	  		        }, function() {
	  		          $rootScope.authenticated = false;
	  		          callback && callback();
	  		        });
	  		}
	  	authenticate();
	  	self.credentials = {};
	  	
	  	self.login = function() {
	        authenticate(self.credentials, function() {
	          if ($rootScope.authenticated) {
	            $location.path("/");
	            self.error = false;
	          } else {
	            $location.path("/login");
	            self.error = true;
	          }
	        });
	    };  
	    self.logout = function() {
	    	  $http.post('logout', {}).finally(function() {
	    	    $rootScope.authenticated = false;
	    	    $location.path("/");
	    	  });
	    	}
  });