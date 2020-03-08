package com.project;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class VatExtractorTest {

    @Test
    public void testExtractionSize() {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://jsonvat.com/"));

        Assert.assertEquals(vatList.size(), 39);
    }

    @Test
    public void testVatStandard() {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://jsonvat.com/"));

        Assert.assertEquals(vatList.get(1).vatStandard(), 20.0, 0.01);
    }

    @Test
    public void testCountryName() {

        JsonDownloader downloader = new JsonDownloader();
        VatExtractor extractor = new VatExtractor();
        List<Vat> vatList = extractor.extractStandardVats(downloader.downloadFrom("https://jsonvat.com/"));

        Assert.assertEquals(vatList.get(0).getCountry(), "Spain");
    }


}
