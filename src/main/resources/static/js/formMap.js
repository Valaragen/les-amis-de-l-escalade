var lat = 46.190901;
var lon = 6.236045;

var mymap = L.map('mapid').setView([lat, lon], 13);

// Nous ajoutons un marqueur
L.marker([lat, lon]).addTo(mymap);

L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - Rendu <a href="//openstreetmap.fr">OSM Humanitaire</a>',
    minZoom: 1,
    maxZoom: 20
}).addTo(mymap);