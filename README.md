TweetPipe-Flink-Analyser
==============
Java Flink application with Twitter hosepipe integration. Functionality - WIP.

This application forms an alternative Flink-based analysis tier for the TweetPipe Data Pipeline (see other repositories). Once started the application will connect to the Twitter stream, mapping each raw JSON tweet to a Tweet object via the Flink mapping interface, and finally printing out those tweets. Functionality is a WIP.

Technologies used
--------------
* Java 8
* Apache Flink
* Maven
* Twitter Flink Connector
* Lombok


Running instructions
--------------------

## Setup
Ensure Twitter API credentials have been exported as properties in your environment before running:

```TWITTER_CONSUMER_KEY```

```TWITTER_CONSUMER_SECRET```

```TWITTER_ACCESS_TOKEN```

```TWITTER_ACCESS_TOKEN_SECRET```


## Running the application
The project can be run from within an IDE (such as IntelliJ) or directly from the command-line.

