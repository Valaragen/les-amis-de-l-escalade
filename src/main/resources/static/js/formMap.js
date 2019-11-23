var lat = 46.430455;
var lon = 2.491927;

var mapId = "mapid";
var mapElem = $("#" + mapId);
var latitudeInput = $("#latitude");
var longitudeInput = $("#longitude");
var parkingLatitudeInput = $("#parkingLatitude");
var parkinglongitudeInput = $("#parkingLongitude");

var mymap = L.map(mapId).setView([lat, lon], 5);

L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - Rendu <a href="//openstreetmap.fr">OSM Humanitaire</a>',
    minZoom: 1,
    maxZoom: 20
}).addTo(mymap);

var popup = L.popup()
    .setContent("<button id='addSiteMarkerBtn' class='btn btn-lightblue d-block mx-auto' type='button'>Placer le site d'escalade</button>" +
        "<button id='addParkingMarkerBtn' class='btn btn-lightblue d-block mx-auto mt-2' type='button'>Placer le parking le plus proche</button>");


var carMarkerIcon = L.icon({
    iconUrl: '../image/automotive.png',

    iconSize:     [33, 44], // size of the icon
    iconAnchor:   [16.5, 44], // point of the icon which will correspond to marker's location
    popupAnchor:  [0, -40] // point from which the popup should open relative to the iconAnchor

});

var siteMarker = L.marker([0,0], {draggable: true});
var parkingMarker = L.marker([0,0], {draggable: true}).setIcon(carMarkerIcon);

if(mapElem.data("lat") &&  mapElem.data("lon")) {
    siteMarker.setLatLng([mapElem.data("lat"), mapElem.data("lon")]).addTo(mymap).bindTooltip("Site");
}
if(mapElem.data("plat") &&  mapElem.data("plon")) {
    parkingMarker.setLatLng([mapElem.data("plat"), mapElem.data("plon")]).addTo(mymap).bindTooltip("Parking");
}

var DELAY = 300, clicks = 0, timer = null;

function onMapClick(e) {
    clicks++;  //count clicks

    if(clicks === 1) {

        timer = setTimeout(function() {
            popup
                .setLatLng(e.latlng)
                .openOn(mymap);
            clicks = 0;             //after action performed, reset counter

        }, DELAY);

    } else {
        clearTimeout(timer);    //prevent single-click action
        clicks = 0;             //after action performed, reset counter
    }

}

function onAddSiteMarkerBtnClick() {
    var selectedLatLng = popup.getLatLng();
    siteMarker.setLatLng(selectedLatLng).addTo(mymap).bindTooltip("Site");
    popup.removeFrom(mymap);
    latitudeInput.attr("value", selectedLatLng.lat);
    longitudeInput.attr("value", selectedLatLng.lng);
    $("#addMarkerToMapInfo").addClass("d-none");
}

function onAddParkingMarkerBtnClick() {
    var selectedLatLng = popup.getLatLng();
    parkingMarker.setLatLng(selectedLatLng).addTo(mymap).bindTooltip("Parking");
    popup.removeFrom(mymap);
    parkingLatitudeInput.attr("value", selectedLatLng.lat);
    parkinglongitudeInput.attr("value", selectedLatLng.lng);
}

function onSiteMarkerDrag(event){
    var marker = event.target;
    var position = marker.getLatLng();
    marker.setLatLng(new L.LatLng(position.lat, position.lng));
    mymap.panTo(new L.LatLng(position.lat, position.lng));
    latitudeInput.attr("value", position.lat);
    longitudeInput.attr("value", position.lng);
}

function onParkingMarkerDrag(event){
    var marker = event.target;
    var position = marker.getLatLng();
    marker.setLatLng(new L.LatLng(position.lat, position.lng));
    mymap.panTo(new L.LatLng(position.lat, position.lng));
    parkingLatitudeInput.attr("value", position.lat);
    parkinglongitudeInput.attr("value", position.lng);
}

mymap.on('click', onMapClick);
$(document).on('click', '#addSiteMarkerBtn', onAddSiteMarkerBtnClick);
siteMarker.on('dragend', onSiteMarkerDrag);
$(document).on('click', '#addParkingMarkerBtn', onAddParkingMarkerBtnClick);
parkingMarker.on('dragend', onParkingMarkerDrag);

//SELECT2
$(document).ready(function() {
    $('.js-example-basic').select2({
        theme: "bootstrap"
    });
    $('.js-example-basic-multiple').select2({
        theme: "bootstrap"
    });
});
