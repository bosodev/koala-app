function AppListCtrl($scope, $http, $templateCache) {
	$scope.listApps = function() {
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/koala-webpack/rest/historic/allRaffles',
					cache : $templateCache
				}).success(function(data, status, headers, config) {
			$scope.apps = data; // set view model
			$scope.view = 'list.html'; // set to list view
		}).error(function(data, status, headers, config) {
			$scope.apps = data || "Request failed";
			$scope.status = status;
			$scope.view = 'list.html';
		});
	}

	$scope.showApp = function(id) {
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/koala-webpack/rest/historic/allRaffles',
					cache : $templateCache
				}).success(function(data, status, headers, config) {
			$scope.appDetail = data; // set view model
			$scope.view = 'detail.html'; // set to detail view
		}).error(function(data, status, headers, config) {
			$scope.appDetail = data || "Request failed";
			$scope.status = status;
			$scope.view = 'detail.html';
		});
	}

	$scope.view = 'list.html'; // set default view
	$scope.listApps();
}
AppListCtrl.$inject = [ '$scope', '$http', '$templateCache' ];