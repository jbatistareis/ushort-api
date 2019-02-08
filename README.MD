# U-short (API)  
A URL shortener in the likes of bit.ly, it uses Redis as storage and to control entries expiration date.


### Demo
The API can be accessed [here](https://ushort-api.herokuapp.com/), but without any user related functions.  
A basic interface can be accessed [here](https://github.com/jbatistareis/ushort-ui).


### Setup  
First, install [Redis](https://redis.io/).  
Then set the environment variables `REDIS_URL` with the connection string (use the `-e` or `--env-file` flags on Docker).  
...or...   
Create a file called `application.properties` on the same directory as the executable with the `redis.url` variable containing the connection string.  
>redis.url=redis://[USER]:[PASS]@[HOST]:[PORT]

Optional: set the server port (default 8080) with the argument `--server.port=<port>` when calling `java -jar`, or setting the variable `server.port` on the `application.properties` file.  
Docker example:
>docker build -t ushort .  
>docker run -p 8080:8080 -e REDIS_URL=redis://localhost:6379 ushort


### Usage  
Path|Method|Description
-|-|-
`/<short URL>`|GET|Redirects to the full URL using the `302 FOUND` code.  Returns `404 NOT FOUND` if there's no entry.
`/api/shorten?url=<full URL>`|GET|Returns the short version of the URL and its expiration date.
`/api/stats`|GET|Returns some statistics about data processing.

For administrative functions (the default credential is '_admin/admin_'), use:  

Path|Method|Description
-|-|-
`/api/admin/config`|GET|Returns the current configuration. '_ttlHours_' stands for the time to live of a URL entry, after this time, it will be deleted.
`/api/admin/config`|POST|Send a json to change settings.
`/api/admin/user`|GET|Returns the administrative user information (password will always come as _null_).
`/api/admin/user`|POST|Send a json to change username and password.
`/api/admin/url?page=<number>&size=<number>`|GET|Pages through the entire URL database, use its various properties for control.
`/api/admin/url/search?query=<partial URL>`|GET|Returns a page containing the URLs that matches the term.
`/api/admin/url/<short URL>`|DELETE|Removes the designated URL from database.
