package com.project;

import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class JsonDownloader {

    public JsonNode downloadFrom(String address) {

        GetRequest request = Unirest.get(address);
        HttpResponse<JsonNode> jsonResponse = request.asJson();

        return jsonResponse.getBody();
    }
}
