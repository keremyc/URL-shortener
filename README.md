URL Shortener
=============

A RESTful based microservice that shortens a given long url using CLEAN
URI.

SHORTEN A URL

Request Fields:

Path    Type       Description

url   String   The long URL

One showing how to make a request using cURL:

``` {.highlight}
$ curl 'http://localhost:8080/api/v1/shorten' -i -X POST \
    -H 'Content-Type: application/json' \
    -d '{"url":"https://docs.oracle.com/javase/tutorial/"}'
```

One showing the HTTP request:

``` {.highlight .nowrap}
POST /api/v1/shorten HTTP/1.1
Content-Type: application/json
Content-Length: 50
Host: localhost:8080

{"url":"https://docs.oracle.com/javase/tutorial/"}
```

And one showing the HTTP response:

``` {.highlight .nowrap}
HTTP/1.1 200 OK
```

To run: 

$ mvn clean install

$ docker-compose up
