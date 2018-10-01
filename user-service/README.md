# User Service

The user service is responsible for being the authentication gateway for the online store application. Backend microservices that require authentication will attempt to authorize the user's session through this OAuth2 authorization server.


mysql -uroot -h127.0.0.1
USE mysql;
CREATE DATABASE  IF NOT EXISTS dev  DEFAULT CHARACTER SET utf8 ;
GRANT ALL ON *.* TO 'root'@'%' IDENTIFIED BY 'dbpass';
GRANT ALL ON *.* TO 'root'@'localhost' IDENTIFIED BY 'dbpass';
GRANT ALL ON *.* TO 'root'@'127.0.0.1' IDENTIFIED BY 'dbpass';
FLUSH PRIVILEGES;
SELECT host,user FROM user;
SHOW VARIABLES LIKE 'character%';
SHOW VARIABLES LIKE '%collation%';
QUIT


## How to test to generate Token (acme:acmesecret BASE64 is YWNtZTphY21lc2VjcmV0)
In browser input the below URL to wait for callbacking URL(We need code=e6k9k2):

http://localhost:8181/uaa/oauth/authorize?response_type=code&scope=openid&client_id=acme&redirect_uri=http://www.baidu.com/&state=x1y1

wait for callbacking URL   https://www.baidu.com/?code=e6k9k2&state=x1y1

curl -i -X POST http://localhost:8181/uaa/oauth/token \
-H 'authorization: Basic YWNtZTphY21lc2VjcmV0' \
-H "Accept: application/json" \
-d "client_id=acme&client_secret=acmesecret&grant_type=authorization_code&code=NCo27x&redirect_uri=http://www.baidu.com/"
HTTP/1.1 200 
Cache-Control: no-store
Pragma: no-cache
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 29 Sep 2018 02:46:37 GMT

{"access_token":"d051ca18-a1c0-4239-b178-14a4558ad339","token_type":"bearer","refresh_token":"c18ce723-81d6-4c72-b498-7d356baa6466","expires_in":7199,"scope":"openid"}

http://192.168.10.200:8787/api/user/uaa/v1/me

curl -i -X GET http://192.168.10.200:8181/uaa/v1/me \
-H 'Authorization: Bearer 31bfc8e5-4055-4001-b6b9-1696eb226c01'
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 29 Sep 2018 14:45:41 GMT

{
    "createdAt": 1538230778, 
    "lastModified": 1538230778, 
    "id": 0, 
    "username": "user", 
    "firstName": "John", 
    "lastName": "Doe", 
    "email": "john.doe@example.com"
}


curl -i -X GET http://192.168.10.200:8181/uaa/user \
-H 'Authorization: Bearer 8392f41b-30b4-4a4e-bfa6-2d95a1776255'
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 29 Sep 2018 15:15:51 GMT

{
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ], 
    "details": {
        "remoteAddress": "192.168.10.200", 
        "sessionId": null, 
        "tokenValue": "d051ca18-a1c0-4239-b178-14a4558ad339", 
        "tokenType": "Bearer", 
        "decodedDetails": null
    }, 
    "authenticated": true, 
    "userAuthentication": {
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ], 
        "details": null, 
        "authenticated": true, 
        "principal": {
            "password": null, 
            "username": "user", 
            "authorities": [
                {
                    "authority": "ROLE_USER"
                }
            ], 
            "accountNonExpired": true, 
            "accountNonLocked": true, 
            "credentialsNonExpired": true, 
            "enabled": true
        }, 
        "credentials": null, 
        "name": "user"
    }, 
    "oauth2Request": {
        "clientId": "acme", 
        "scope": [
            "openid"
        ], 
        "requestParameters": {
            "user_oauth_approval": "true", 
            "code": "e6k9k2", 
            "grant_type": "authorization_code", 
            "response_type": "code", 
            "redirect_uri": "http://www.baidu.com/", 
            "state": "x1y1", 
            "client_secret": "acmesecret", 
            "client_id": "acme"
        }, 
        "resourceIds": [ ], 
        "authorities": [
            {
                "authority": "ROLE_USER"
            }, 
            {
                "authority": "ROLE_ADMIN"
            }
        ], 
        "approved": true, 
        "refresh": false, 
        "redirectUri": "http://www.baidu.com/", 
        "responseTypes": [
            "code"
        ], 
        "extensions": { }, 
        "grantType": "authorization_code", 
        "refreshTokenRequest": null
    }, 
    "principal": {
        "password": null, 
        "username": "user", 
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ], 
        "accountNonExpired": true, 
        "accountNonLocked": true, 
        "credentialsNonExpired": true, 
        "enabled": true
    }, 
    "clientOnly": false, 
    "credentials": "", 
    "name": "user"
}

https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/#token-authentication-provider
https://github.com/jloisel/securing-rest-api-spring-security
