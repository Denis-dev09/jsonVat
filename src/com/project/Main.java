package com.project;

import kong.unirest.JsonNode;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        JsonNode vatAsJson = new JsonDownloader().downloadFrom("https://jsonvat.com/");
        List<Vat> vatList = new VatExtractor().extractStandardVats(vatAsJson);

        //Sorting list based on standard VAT
        vatList.sort(Comparator.comparing(Vat::getStandardRate));

        System.out.println("3 Countries with highest standard VAT");
        vatList.get(vatList.size()-1).printCountryVatDate();
        vatList.get(vatList.size()-2).printCountryVatDate();
        vatList.get(vatList.size()-3).printCountryVatDate();
        System.out.println();
        System.out.println("3 Countries with lowest standard VAT");
        vatList.get(0).printCountryVatDate();
        vatList.get(1).printCountryVatDate();
        vatList.get(2).printCountryVatDate();
    }
}
