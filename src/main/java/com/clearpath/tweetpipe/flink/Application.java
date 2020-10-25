package com.clearpath.tweetpipe.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Application {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> wordDataStream = env.socketTextStream("localhost", 9000, "\n")
                .flatMap(new Splitter());
        wordDataStream.print();
        env.execute("Tweet Pipe Flink Analyser");
    }

    public static class Splitter implements FlatMapFunction<String, String> {
        @Override
        public void flatMap(String s, Collector<String> collector) {
            for (String word : s.split(" ")) {
                collector.collect(word);
            }
        }
    }
}
