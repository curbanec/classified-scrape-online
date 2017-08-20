// The name of the application is 'hello'.
angular.module('hello', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
      templateUrl : 'home.html',
      controller : 'home',
      controllerAs: 'controller'
    }).when('/login', {
      templateUrl : 'login.html',
      controller : 'navigation',
      controllerAs: 'controller'
    }).otherwise('/login');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  }).service('sharedProperties', function(){
	  
	  var serviceDisplayName;
	
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
	  			
	  			var headers = credentials ? {authorization : "Basic "
	  		        + btoa(credentials.username + ":" + credentials.password)
	  		    	} : {};
	  			
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