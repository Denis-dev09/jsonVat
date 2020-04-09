package com.project;

import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VatExtractor {

    public List<Vat> extractStandardVats(JsonNode json) throws ParseException {

        List<Vat> vatList = new ArrayList<>();
        List inputList = json.getArray().toList();

        for (Object element : inputList) {
            JSONObject ratesObject = (JSONObject) element;
            JSONArray rates = ratesObject.getJSONArray("rates");

            for (Object elementInRates : rates) {
                JSONObject countriesObject = (JSONObject) elementInRates;
                String countryName = countriesObject.getString("name");
                JSONArray periods = countriesObject.getJSONArray("periods");

                Date actualDate = new SimpleDateFormat("yyyy-MM-dd").parse("0000-00-00");
                Vat actualVat = null;
                for (Object elementInPeriods : periods) {
                    JSONObject periodObject = (JSONObject) elementInPeriods;
                    Date effectiveFromDate = new SimpleDateFormat("yyyy-MM-dd").parse(periodObject.getString("effective_from"));
                    JSONObject ratesInPeriods = periodObject.getJSONObject("rates");
                    double standardRate = ratesInPeriods.getDouble("standard");
                    if(effectiveFromDate.after(actualDate)) {
                        actualDate = effectiveFromDate;
                        actualVat = new Vat(countryName, standardRate, effectiveFromDate);
                    }
                }
                if (actualVat != null) {
                    vatList.add(actualVat);
                }
            }
        }
        return vatList;
    }
}










