# WChallenge 

This a simple application for consume the users, albums, photos and comments from [JSONPlaceholder](https://jsonplaceholder.typicode.com), adding a permission management to share albums with other users. 

# Requeriments
For build and run the application, you need:
* [JDK 8](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven 4](https://maven.apache.org)

Run local database with doker
```sh
$ docker pull postgres:9.6.6-alpine
$ docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=wolox 
```

# HTTP Authentication
Authenticate with whatever username from [JSONPlaceholder/users](https://jsonplaceholder.typicode.com/users)
Example
```sh
$ curl --user Bret:Bret http://localhost:8081/wchallenge
$ curl --user Antonette:Antonette http://localhost:8081/wchallenge 
$ curl --user Samantha:Samantha http://localhost:8081/wchallenge 
```

# Test Rest API
### All users
```sh
$ curl -X GET --basic --user Bret:Bret http://localhost:8081/wchallenge/users/all
```
### All albums
```sh
$ curl -X GET --basic --user Bret:Bret http://localhost:8081/wchallenge/albums/all
```

### All photos
```sh
$ curl -X GET --basic --user Bret:Bret http://localhost:8081/wchallenge/photos/all
```

### Photos by user
```sh
$ curl -X GET --basic --user Bret:Bret http://localhost:8081/wchallenge/photos/{idUser}
```
### Comments by name
```sh
$ curl -X GET --basic --user Bret:Bret http://localhost:8081/wchallenge/comments/{name}
```
### Share album with users
URL : http://localhost:8081/wchallenge/access/shared
Method: POST
Only give access to another user when the authenticated user:
* Owns the album
* has "WRITE" permissions on the album to share
```json
{
    "idAlbum": 1,
    "idUser": 9,
    "permissions": [
        "READ"
    ]
}
```
### Update permissions
URL : http://localhost:8081/wchallenge/access/permission
Method: PUT
Only give access to another user when the authenticated user:
* Owns the album
* has "WRITE" permissions on the album to share
```json
{
    "idAlbum": 1,
    "idUser": 9,
    "permissions": [
        "READ"
        "WRITE"
    ]
}
```
### User/Permission on an album
```sh
$ curl -X GET --basic --user Bret:Bret http://localhost:8081/wchallenge/access/"/{idAlbum}/{permission}"
``` 