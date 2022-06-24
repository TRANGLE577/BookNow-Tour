var geocoder;
var map;
var marker;
window.onload = function () {
	var latlng = new google.maps.LatLng(-33.897, 150.099);
	var myOptions = {
		zoom: 9,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.TERRAIN
	};
	map = new google.maps.Map(document.getElementById("map2"), myOptions);
	var rendererOptions = {
		map: map
	};

	map3 = new google.maps.Map(document.getElementById("map3"), myOptions);
	var rendererOptions = {
		map3: map3
	};

	map4 = new google.maps.Map(document.getElementById("map4"), myOptions);
	var rendererOptions = {
		map4: map4
	};

	map5 = new google.maps.Map(document.getElementById("map5"), myOptions);
	var rendererOptions = {
		map5: map5
	};

};
