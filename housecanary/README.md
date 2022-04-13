This does what HouseCanary asked for, hopefully. It was written in a big hurry.

- Written with java 13 or so but will probably work on java 8.

- Implements a *fixed* window, not a *sliding* one. The latter is more "fair", but more overhead: The only way I know to do that is with an array, long[10000] to track 10000 req/interval. Then again this is for the entire service and not a specific user, so maybe it would be worth it to do sliding.

How to run it:

- build.sh just does a build
- test.sh does a command-line test of the rate limiter (no http parts)
- web.sh starts up a web server on port 80



Requirements below
-----------------------------------------------


API Rate Limiter
================

Languages choices: Golang, Python, Javascript, Java

Problem statement:

In the language of your choice, design a class that can be used as an API rate limiter. A rate limiter is a tool that monitors the number of requests per a window of time a service agrees to allow. If the request count exceeds the number agreed by the service owner in a decided window time, the rate limiter will reject subsequent calls.

Acceptance criteria:

-Build a class that can be used by an API to rate limit incoming requests.
-It should have a function, constructor, or factory method to instantiate an instance that takes an interval in milliseconds and a count that specifies the numbers of calls allowed.
-A function that checks to see if the limit has been reached.
-The rate limiter should support any number of clients.
-You can share the project however you would like (zip file, github, other public git repo).

Bonus:

-Build a simple http server (or use a framework) that serves up a GET request that returns the current time in a JSON response and uses the rate limiter you created above, if the rate limit is exceeded return the proper HTTP response code. This can be as simple as using a language's builtin http server.