package com.clearpath.tweetpipe.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TweetMapper implements MapFunction<String, Tweet> {

    static private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Tweet map(String tweetJsonStr) throws Exception {
        JsonNode tweetJson = mapper.readTree(tweetJsonStr);
        JsonNode textNode = tweetJson.get("text");
        JsonNode langNode = tweetJson.get("lang");

        String text = textNode == null ? "" : textNode.textValue();
        String lang = langNode == null ? "" : langNode.textValue();
        List<String> tags = new ArrayList<>();

        JsonNode entities = tweetJson.get("entities");
        if (entities != null) {
            JsonNode hashtags = entities.get("hashtags");

            for (Iterator<JsonNode> iter = hashtags.elements(); iter.hasNext(); ) {
                JsonNode node = iter.next();
                String hashtag = node.get("text").textValue();
                tags.add(hashtag);
            }
        }

        return new Tweet(lang, text, tags);
    }
}
