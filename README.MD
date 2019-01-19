# U-short (API)  
A URL shortener in the likes of bit.ly, it uses Redis as storage and to control entries expiration date.


### Setup  
First, install [Redis](https://redis.io/).  
Then set the environment variables `REDIS_HOST`, `REDIS_PORT`, and `REDIS_PASS` with their respective values (use the `-e` flag on Docker).  
...or...   
Create a file called `application.properties` on the same directory as the executable with this structure:  
>redis.host=localhost
>redis.port=6379
>redis.password=


### Usage  
Path|Method|Description|Data
-|-|-|-
`/<short URL>`|GET|Redirects to the full URL using the `302 FOUND` code.  Returns `404 NOT FOUND` if there's no entry.|...
`/api/shorten?url=<full URL>`|GET|Returns the short version of the URL and its expiration date.|`{"fullUrl":"http://g1.globo.com","expires":"2019-01-20T16:45:02.832+0000","shortUrl":"ENoxr"}`
`/api/stats`|GET|Returns some statistics about data processing|`{"reducedUrls":6,"reducedLeters":115,"averageUrlSize":19,"reductionPercentage":79}`

For administrative functions (the default credential is '_admin/admin_'), use:  
Path|Method|Description|Data
-|-|-|-
`/api/admin/config`|GET|Returns the current configuration.  '_ttlHours_' stands for the time to live of a URL entry, after this time, it will be deleted.|`{"ttlHours":24}`
`/api/admin/config`|POST|Send a json to change settings.|_see get example_
`/api/admin/user`|GET|Returns the administrative user information (password will always come as _null_).|`{"username":"admin","password":null}`
`/api/admin/user`|POST|Send a json to change username and password.|`{"username":"admin","password":secret}`
`/api/admin/url?page=<number>&size=<number>`|GET|Pages through the entire URL database, use its various properties for control.|`{"content":[{"fullUrl":"http://g1.globo.com","expires":"2019-01-20T18:19:36.439+0000","shortUrl":"ENoxr"},{"fullUrl":"http://diariodonordeste.verdesmares.com.br/","expires":"2019-01-20T18:19:29.701+0000","shortUrl":"KLMaA"},{"fullUrl":"http://opovo.com.br","expires":"2019-01-20T18:19:41.864+0000","shortUrl":"anPLb"}],"totalElements":3,"totalPages":1,"size":10,"number":0,"first":true,"last":true,"empty":false}`
`/api/admin/url/search?query=<partial URL>`|GET|Returns a page containing the URLs tha matches the term (example uses _'query=povo'_).|`{"content":[{"fullUrl":"http://opovo.com.br","expires":"2019-01-20T18:19:41.864+0000","shortUrl":"anPLb"}],"totalElements":1,"totalPages":1,"size":1,"number":0,"first":true,"last":true,"empty":false}`
`/api/admin/url/<short URL>`|DELETE|Removes the designated URL from database.|...