# Fenrir-Friends
App that allows the user to view dog breads, search for specific dog breed and take a look into their details

## The Brief

The app get data from the [the Dog api](https://thedogapi.com/).
It showns the data using pagination and caches data in order to support offline mode. It also has "netflix like" search type.
The app is ready for multiple screen sizes and dark mode

It uses a clean achitecture (divided by layer) and MVI

## Build configuration
There are properties you can change for the available environments.
For this purpose it has build configurations, you need to add into `flavours.gradle` file.
```properties
# The path to a base URL 
BASE_URL

# The time in minutes the cached data will be maintained in the app database
CACHE_TIME_OUT

# The time in seconds for a connection time out
CONNECT_TIMEOUT

# The time in seconds for the read time out
READ_TIMEOUT

# Number of items to be shown per page requested
ITEMS_PER_PAGE

# The delay in miliseconds for the search to initiate
SEARCH_DELAY

```

## Architecture & Libraries
    - MVI
    - Retrofit
    - ROOM Database
    - Dependency Injection - Dagger-Hilt
    - Kotlin Coroutines
    - Offline Cache
    - Flow
    - Paging 3
    - Coil
    
## Preview:




Image #1            |                Image #2                 |  Image #3
:-------------------------:|:---------------------------------------:|:----------------------------:
<img src="images/Fenrir_Friends_1.jpg">    | <img src="images/Fenrir_Friends_2.jpg"> |  <img src="images/Fenrir_Friends_3.jpg"> 

