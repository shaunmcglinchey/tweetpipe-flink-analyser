package com.clearpath.tweetpipe.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;

public class Application {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, System.getenv("TWITTER_CONSUMER_KEY"));
        props.setProperty(TwitterSource.CONSUMER_SECRET, System.getenv("TWITTER_CONSUMER_SECRET"));
        props.setProperty(TwitterSource.TOKEN, System.getenv("TWITTER_ACCESS_TOKEN"));
        props.setProperty(TwitterSource.TOKEN_SECRET, System.getenv("TWITTER_ACCESS_TOKEN_SECRET"));

        env.addSource(new TwitterSource(props))
                .map(new TweetMapper())
                .print();
        env.execute("Tweet Pipe Flink Analyser");
    }
}
