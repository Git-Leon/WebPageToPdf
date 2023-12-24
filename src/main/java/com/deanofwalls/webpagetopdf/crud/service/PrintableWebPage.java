package com.deanofwalls.webpagetopdf.crud.service;

import com.github.git_leon.extentreporting.ExtentTestLoggerFactory;
import com.github.git_leon.extentreporting.ExtentTestLoggerFactoryManager;
import com.github.git_leon.extentreporting.ExtentTestLoggerInterface;
import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.github.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;

import java.io.File;
import java.nio.file.Path;

public class PrintableWebPage {
    private BrowserHandlerFactory browserHandlerFactory;
    private String url;
    private BrowserHandlerLoggerInterface driver;

    public PrintableWebPage(BrowserHandlerFactory browserHandlerFactory, String url) {
        this.browserHandlerFactory = browserHandlerFactory;
        this.url = url;
    }

    public BrowserHandlerLoggerInterface getBrowser() {
        if (driver == null) {
            final String description = "Attempting to create an account on automationpractice.com";
            final String email = Long.toHexString(System.nanoTime()) + "@leonium.com";
            final ExtentTestLoggerFactory extentTestLoggerFactory = ExtentTestLoggerFactoryManager.TEST_REPORT_DIRECTORY.getExtentTestLoggerFactory();
            final ExtentTestLoggerInterface extentTestLogger = extentTestLoggerFactory.getExtentTestLoggerTimer(email, description);
            final BrowserHandlerLayeredLogger browserHandler = BrowserHandlerFactory.CHROME.getBrowserHandlerLayeredLogger(extentTestLogger);
            this.driver = browserHandler;
        }
        return driver;
    }

    public Path printToFile(String outputDirectory, String outputFileName) {
        final DirectoryReference directoryReference = DirectoryReference.TARGET_DIRECTORY;
        final File directory = directoryReference.getFileFromDirectory("/screenshot/" + outputDirectory);
        getBrowser().getOptions().SCREENSHOT_DIRECTORY.setValue(directory.toString());
        return getBrowser().screenshot().getFile().toPath();
    }
}
