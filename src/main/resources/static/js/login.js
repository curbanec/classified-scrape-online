// The name of the application is 'web-lite'.
angular.module('web-lite', [ 'ngRoute' ])
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
	
  }).controller('signup', function($rootScope, $http, $location,  $scope) {
	  
	  var self = this;
	  
	 /* $.getScript("../vendors/validator/validator.js", function(data, textStatus, jqxhr) {
			console.log('Load was performed for validator.js');
			});*/
	  
	  $.getScript("../js/pageSetup.js", function(data, textStatus, jqxhr) {
			console.log('Load was performed for pageSetup.js.');
			});
	  
	  // callback function
	  self.registerNewUser = function() {
	        signUp(self.credentials, function() {  
	        });
	    }; 
	// local helper function
	// "containing" function of the callback

	    var signUp = function(credentials, callback) {
			
		// do not add the HTTP Request Header Authorization : Basic + btoa(credentials.username + ":" + credentials.password)
		
	    $http.post('/api/registration/userSignup', {"username":credentials.username, "password":credentials.password})
	    	.success(function(data){
	    		self.registrationSuccess = true;
	    		self.error = false;
	    		})
	    		.error(function(err){
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
  	    $location.path("/login");
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
  });