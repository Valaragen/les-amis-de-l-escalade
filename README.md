# Les amis de l'escalade
Site permettant à la communauté de référencer, donner des infos et commenter des sites d'escalade.

## Comment faire tourner le site en local ?
### Prérequis
1. [apache tomcat](https://tomcat.apache.org/download-90.cgi)
2. [postgresql](https://www.postgresql.org/download/)

### Installation
1. Téléchargez [la dernière version du site](https://github.com/Valaragen/les-amis-de-l-escalade/releases)
2. Extrayez le contenu du dossier .war dans /webapp/escalade de apache tomcat
3. Depuis votre console de commande, placez vous dans le dossier /bin de apache-tomcatet executez
    * catalina.bat run (on Windows)
    * catalina.sh run (on Unix-based systems)
4. Depuis votre navigateur connectez vous à "localhost:8080/escalade"
