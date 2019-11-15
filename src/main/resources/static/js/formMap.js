var lat = 46.430455;
var lon = 2.491927;

var mapId = "mapid";
var mapElem = $("#" + mapId);
if (mapElem.data("lat") && mapElem.data("lon")) {
    lat = mapElem.data("lat");
    lon = mapElem.data("lon");
}

var mymap = L.map(mapId).setView([lat, lon], 5);

L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - Rendu <a href="//openstreetmap.fr">OSM Humanitaire</a>',
    minZoom: 1,
    maxZoom: 20
}).addTo(mymap);

var popup = L.popup()
    .setContent("<button id='addSiteMarkerBtn' class='btn btn-lightblue d-block mx-auto' type='button'>Placer le site d'escalade</button>" +
        "<button id='addParkingMarkerBtn' class='btn btn-lightblue d-block mx-auto mt-2' type='button'>Placer le parking le plus proche</button>");

var carIcon = L.icon({
    iconUrl: '../image/sports-car-blue.png',
    shadowUrl: '../image/sports-car-shadow.png',

    iconSize:     [32, 32], // size of the icon
    shadowSize:   [32, 32], // size of the shadow
    iconAnchor:   [12.5, 25], // point of the icon which will correspond to marker's location
    shadowAnchor: [12.5, 25],  // the same for the shadow
    popupAnchor:  [0, -40] // point from which the popup should open relative to the iconAnchor

});

var siteMarker = L.marker();
var parkingMarker = L.marker().setIcon(carIcon);

function onMapClick(e) {
    popup
        .setLatLng(e.latlng)
        .openOn(mymap);
}

function onAddSiteMarkerBtnClick() {
    siteMarker.setLatLng(popup.getLatLng()).addTo(mymap);
    console.log(popup);
    popup.removeFrom(mymap);
}

function onAddParkingMarkerBtnClick() {
    parkingMarker.setLatLng(popup.getLatLng()).addTo(mymap);
    console.log(popup);
    popup.removeFrom(mymap);
}


mymap.on('click', onMapClick);
$(document).on('click', '#addSiteMarkerBtn', onAddSiteMarkerBtnClick);
$(document).on('click', '#addParkingMarkerBtn', onAddParkingMarkerBtnClick);