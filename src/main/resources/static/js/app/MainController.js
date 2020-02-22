'use strict'

var module = angular.module('weissCinema.controllers', ['ngMaterial']);
module.controller("MainController", [ "$scope", "$http", '$mdDialog',
		function($scope, $http, $mdDialog) {

			var onError = function(reason) {
            $scope.error = "Could not fetch the data.";
        };
        var searchTitle = null;

        var wishlistIds = [];

        var getWishlistFromServer = function() {
            wishlistIds = getWishlist().then(onGetWishlistComplete, onError);
        };

        var onGetWishlistComplete = function(data) {
            if (data != null) {
                wishlistIds = data;
            }
        };

        var getWishlist = function(title) {
            return $http.get("/movies/wishlist")
                        .then(function(response){
                           return response.data;
                        });
        }

        getWishlistFromServer();

        $scope.search = function(title) {
            searchTitle = title;
            var movies = getMovies(title).then(onGetMoviesComplete, onError);
        };

        var onGetMoviesComplete = function(data) {
            if (data.movies == null) {
                $scope.result = "No results for: " + searchTitle;
                $scope.moviesResult = null;
            } else {
                $scope.moviesResult = data.movies;
                $scope.result = "Search Results for: " + searchTitle;
            }
        };

        var getMovies = function(title) {
            return $http.get("/movies/" + title + "/1")
                        .then(function(response){
                           return response.data;
                        });
        }


    $scope.showDialog = function showDialog($event, movie) {
        $mdDialog.show({
          controller: DialogController,
          template:
          '<md-dialog style=width:1000px>' +
          '<md-dialog-content>' +
          ' <h1>{{movie.Title}}</h1>' +
          ' <p>{{movie.Year}}</p>' +
          ' <img ng-src={{movie.Poster}}/>' +
          ' <p>Genre: {{movie.Genre}}</p>' +
          ' <p>Director: {{movie.Director}}</p>' +
          ' <p>Actors: {{movie.Actors}}</p>' +
          ' <p>Plot: {{movie.Plot}}</p>' +
          ' <p>IMDB Rating: {{movie.imdbRating}}</p>' +
          '<md-button class="md-raised" ng-click=handleWishlist(movie)>{{wishlistButton}} Wishlist</md-button>' +
          '</md-dialog-content>' +
          '</md-dialog>',
          parent: angular.element(document.body),
          targetEvent: event,
          clickOutsideToClose:true,
          locals: {movie: movie}
      })
      .then(function(answer) {
          $scope.status = 'You said the information was "' + answer + '".';
       }, function() {
          $scope.status = 'You cancelled the dialog.';
       });
    }

    function DialogController($scope, $mdDialog, $http, movie) {
        $scope.movie = movie;
        $scope.hide = function() {
          $mdDialog.hide();
        };
        $scope.cancel = function() {
          $mdDialog.cancel();
        };
        $scope.answer = function(answer) {
          $mdDialog.hide(answer);
        };
        $scope.addToWishlist = function(movie) {
        }

        var buttonTxt = function(movie) {
            if (wishlistIds.includes(movie.id + "")) {
                return "Remove from";
            } else {
                return "Add to"
            }
        }
        $scope.wishlistButton = buttonTxt(movie);

        $scope.handleWishlist = function(movie) {
            if (wishlistIds.includes(movie.id)) {
                const index = wishlistIds.indexOf(movie.id);
                if (index > -1) {
                    wishlistIds.splice(index, 1);
                    $http.delete("/movies/wishlist/" + movie.id)
                        .then(function(response){});
                    $scope.wishlistButton = "Add to";
                }
            } else {
                wishlistIds.push(movie.id)
                $http.post("/movies/wishlist/" + movie.id)
                        .then(function(response){});
                $scope.wishlistButton = "Remove from";
            }
        }
  }

    $scope.title = "";
} ]);