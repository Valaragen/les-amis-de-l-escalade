# Les amis de l'escalade
Site permettant à la communauté de référencer, donner des infos et commenter des sites d'escalade.

## Comment faire tourner le site en local ?
### Prérequis
1. [Apache tomcat](https://tomcat.apache.org/download-90.cgi)
2. [Postgresql](https://www.postgresql.org/download/)

### Installation
1. **Téléchargez** [la dernière version du site](https://github.com/Valaragen/les-amis-de-l-escalade/releases) et [le jeu de données](https://github.com/Valaragen/les-amis-de-l-escalade/releases)
2. Lancez pgAdmin et créez une nouvelle base de données nommée **LADL_DB** appartenant à l'utilisateur **postgres**.
3. Déposez le fichier .war dans **/webapps** de apache tomcat
4. Depuis votre **console de commande**, placez vous dans le dossier **/bin** de apache-tomcatet executez
    - **catalina.bat** run (sur Windows)
    - **catalina.sh** run (sur Linux)
5. Depuis votre navigateur connectez vous à **localhost:8080/ladl-{version_du_site}**
