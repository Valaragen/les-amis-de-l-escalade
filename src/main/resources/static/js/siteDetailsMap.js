var mapId = "mapid";
var mapElem = $("#" + mapId);
var lat = mapElem.data("lat");
var lon = mapElem.data("lon");

var mymap = L.map(mapId).setView([lat, lon], 13);

// Nous ajoutons un marqueur
var marker = L.marker([lat, lon]).addTo(mymap);
marker.bindTooltip("<b>" + mapElem.data("name") + "</b>");

L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - Rendu <a href="//openstreetmap.fr">OSM Humanitaire</a>',
    minZoom: 1,
    maxZoom: 20
}).addTo(mymap);

var popup = L.popup();
function onMapClick(e) {
    popup
        .setLatLng(e.latlng)
        .setContent("You clicked the map at " + e.latlng.toString())
        .openOn(mymap);
}

mymap.on('click', onMapClick);