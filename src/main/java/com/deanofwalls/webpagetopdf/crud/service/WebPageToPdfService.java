package com.deanofwalls.webpagetopdf.crud.service;

import com.deanofwalls.webpagetopdf.selenium.PrintableWebPage;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class WebPageToPdfService {

    @Value("${pdf.storage.directory}")
    private String pdfDirectory;

    public Path convertWebPageToPdf(String url, String outputFileName) {
        return this.convertWebPageToPdf(BrowserHandlerFactory.PHANTOMJS, url, outputFileName);
    }


    public Path convertWebPageToPdf(BrowserHandlerFactory factory, String url, String outputFileName) {
        // Setup Firefox WebDriver using WebDriverManager
        final PrintableWebPage printableWebPage = new PrintableWebPage(factory, url);
        return printableWebPage.printToFile(pdfDirectory, outputFileName);
    }
}
