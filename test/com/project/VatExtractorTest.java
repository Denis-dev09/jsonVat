package com.project;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class VatExtractorTest {

    @Test
    public void testExtractionSize() throws ParseException {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://my-json-server.typicode.com/Denis-dev09/jsonVat/db"));

        Assert.assertEquals(vatList.size(), 3);
    }

    @Test
    public void testVatStandard() throws ParseException {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://my-json-server.typicode.com/Denis-dev09/jsonVat/db"));

        Assert.assertEquals(vatList.get(1).getStandardRate(), 20.0, 0.01);
    }

    @Test
    public void testCountryName() throws ParseException {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://my-json-server.typicode.com/Denis-dev09/jsonVat/db"));

        Assert.assertEquals(vatList.get(0).getCountryName(), "Spain");
    }

    @Test
    public void testEffectiveFromDate() throws ParseException {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://my-json-server.typicode.com/Denis-dev09/jsonVat/db"));

        Assert.assertEquals(vatList.get(0).getEffectiveFromDate(), new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"));
    }


}
