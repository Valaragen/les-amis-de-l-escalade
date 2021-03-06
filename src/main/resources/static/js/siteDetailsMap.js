var mapId = "mapid";
var mapElem = $("#" + mapId);
var lat = mapElem.data("lat");
var lon = mapElem.data("lon");

var mymap = L.map(mapId).setView([lat, lon], 13);

// Nous ajoutons un marqueur
var marker = L.marker([lat, lon]).addTo(mymap);
marker.bindTooltip("<b>" + mapElem.data("name") + "</b>");
var carMarkerIcon = L.icon({
    iconUrl: '/image/automotive.png',

    iconSize:     [33, 44], // size of the icon
    iconAnchor:   [16.5, 44], // point of the icon which will correspond to marker's location
    popupAnchor:  [0, -40] // point from which the popup should open relative to the iconAnchor

});
if(mapElem.data("plat") &&  mapElem.data("plon")) {
    L.marker([mapElem.data("plat"), mapElem.data("plon")]).setIcon(carMarkerIcon).addTo(mymap).bindTooltip("Parking");
}

L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - Rendu <a href="//openstreetmap.fr">OSM Humanitaire</a>',
    minZoom: 1,
    maxZoom: 20
}).addTo(mymap);

//remove btns
if ($('#infoViewModify label').length === 0) {
    $('#infoViewContributionBtn').remove();
    $('#infoViewModify').remove();
}

if ($('#descViewModify label').length === 0) {
    $('#descViewContributionBtn').remove();
    $('#descViewModify').remove();
}

$(document).on('click', '.contribute-desc', function() {
    $("#descView").toggleClass("d-none");
    $("#descViewModify").toggleClass("d-none");
});

$(document).on('click', '.contribute-info', function() {
    $("#infoView").toggleClass("d-none");
    $("#infoViewModify").toggleClass("d-none");
});

//SELECT2
$(document).ready(function() {
    $('.js-example-basic').select2({
        theme: "bootstrap"
    });
    $('.js-example-basic-multiple').select2({
        theme: "bootstrap"
    });
});