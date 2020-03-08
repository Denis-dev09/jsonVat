package com.project;

import kong.unirest.JsonNode;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JsonNode vatAsJson = new JsonDownloader().downloadFrom("https://jsonvat.com/");
        List<Vat> vatList = new VatExtractor().extractStandardVats(vatAsJson);

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
        System.out.println(vatList.get(index[0]).getCountry() + " VAT: " + vatList.get(index[0]).vatStandard());
        System.out.println(vatList.get(index[1]).getCountry() + " VAT: " + vatList.get(index[1]).vatStandard());
        System.out.println(vatList.get(index[2]).getCountry() + " VAT: " + vatList.get(index[2]).vatStandard());

        System.out.println();

        System.out.println("3 countries with lowest standard VAT");
        System.out.println(vatList.get(index[vatList.size()-1]).getCountry() + " VAT: " + vatList.get(index[vatList.size()-1]).vatStandard());
        System.out.println(vatList.get(index[vatList.size()-2]).getCountry() + " VAT: " + vatList.get(index[vatList.size()-2]).vatStandard());
        System.out.println(vatList.get(index[vatList.size()-3]).getCountry() + " VAT: " + vatList.get(index[vatList.size()-3]).vatStandard());

    }
}
