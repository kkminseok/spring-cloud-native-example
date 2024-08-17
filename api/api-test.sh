http POST :9001/books author="Lyra Silverstar" \
title="Northern Lights" isbn="1234567891" price=9.98

HTTP/1.1 201
Connection: keep-alive
Content-Type: application/json
Date: Thu, 04 Jul 2024 13:59:50 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "author": "Lyra Silverstar",
    "isbn": "1234567891",
    "price": 9.98,
    "title": "Northern Lights"
}



http POST :9001/books author="Jon Snow" title="" isbn="123ABC4562" price=9.98
http POST :9002/orders isbn=123456891 quantity=3


# Chapter 10 message order test

http POST :9001/books author="kms" \
title="Rabbit MQ Message Test" isbn="1234567896" \
price=9.90 publisher="minseok-kang"

http POST :9002/orders isbn=1234567896 quantity=3

http :9002/orders