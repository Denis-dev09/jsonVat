package com.project;

import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    GetRequest request = Unirest.get("https://jsonvat.com/");
    HttpResponse<JsonNode> jsonResponse = request.asJson();
    JsonNode json = jsonResponse.getBody();
    List list = json.getArray().toList();
    List<Vat> vatList = new ArrayList<>();

    for (Object element : list) {
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

    //Creating field of numbers which will refer to sorted values from highest to lowest
    int[] index = new int[vatList.size()];
    for (int i = 0; i < vatList.size(); i++) {
        index[i] = i;
    }
    for (int i = 0; i < vatList.size() -1; i++ ) {
        int maxIndex = index[i];
        double maxVat = vatList.get(maxIndex).vatStandard();
        for (int j = i + 1; j < vatList.size(); j++) {
            int nextIndex = index[j];
            double nextVat = vatList.get(nextIndex).vatStandard();
            if (maxVat < nextVat) {
                maxVat = nextVat;
                maxIndex = j;
            }
        }
        int tempIndex = index[i];
        index[i] = index[maxIndex];
        index[maxIndex] = tempIndex;
    }

    System.out.println("3 countries with highest standard VAT");
    System.out.println(vatList.get(index[0]).getName() + " VAT: " + vatList.get(index[0]).vatStandard());
    System.out.println(vatList.get(index[1]).getName() + " VAT: " + vatList.get(index[1]).vatStandard());
    System.out.println(vatList.get(index[2]).getName() + " VAT: " + vatList.get(index[2]).vatStandard());
    System.out.println();
    System.out.println("3 countries with lowest standard VAT");
    System.out.println(vatList.get(index[vatList.size()-1]).getName() + " VAT: " + vatList.get(index[vatList.size()-1]).vatStandard());
    System.out.println(vatList.get(index[vatList.size()-2]).getName() + " VAT: " + vatList.get(index[vatList.size()-2]).vatStandard());
    System.out.println(vatList.get(index[vatList.size()-3]).getName() + " VAT: " + vatList.get(index[vatList.size()-3]).vatStandard());

    }
}
