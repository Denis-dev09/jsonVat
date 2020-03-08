package com.project;

import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class VatExtractor {

    public List<Vat> extractStandardVats(JsonNode json) {

        List<Vat> vatList = new ArrayList<>();
        List inputList = json.getArray().toList();

        for (Object element : inputList) {
            JSONObject jsonObject = (JSONObject) element;
            JSONArray rates = jsonObject.getJSONArray("rates");
            for (Object element1 : rates) {
                JSONObject jsonObject1 = (JSONObject) element1;
                String name = jsonObject1.getString("name");
                JSONArray periods = jsonObject1.getJSONArray("periods");
                for (Object element2 : periods) {
                    JSONObject jsonObject2 = (JSONObject) element2;
                    JSONObject rates1 = jsonObject2.getJSONObject("rates");
                    double standard = rates1.getDouble("standard");
                    Vat myVats = new Vat(name,standard);
                    vatList.add(myVats);
                }
            }
        }
        return vatList;
    }




}











