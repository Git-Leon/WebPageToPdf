package com.deanofwalls.webpagetopdf.selenium;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
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
            this.driver = browserHandlerFactory.getBrowserHandlerLogger();
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
