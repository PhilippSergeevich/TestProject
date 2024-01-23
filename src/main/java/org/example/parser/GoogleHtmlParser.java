package org.example.parser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.example.util.FileWriter;
import org.example.util.Time;

import javax.swing.text.Document;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GoogleHtmlParser implements Parser {
    private static final String URL_TO_HTML = "https://www.google.com/search?q=%D0%BA%D1%83%D1%80%D1%81+%D0%B1%D0%B8%D1%82%D0%BA%D0%BE%D0%B8%D0%BD%D0%B0+%D0%B2+%D0%B4%D0%BE%D0%BB%D0%BB%D0%B0%D1%80%D0%B0%D1%85&sca_esv=597832100&sxsrf=ACQVn0-Xq8_lJC8p31PA-swA5FZIcsQR5g%3A1706035313301&ei=cQiwZfH9EZSvwPAPwu-c0Ao&udm=&oq=%D0%BA%D1%83%D1%80%D1%81+%D0%B1%D0%B8%D1%82%D0%BA%D0%BE%D0%B8%D0%BD%D0%B0+d+&gs_lp=Egxnd3Mtd2l6LXNlcnAiHNC60YPRgNGBINCx0LjRgtC60L7QuNC90LAgZCAqAggAMgkQABiABBgKGCoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGApIxyNQxwxY9Q9wAXgBkAEAmAFUoAHtAaoBATO4AQPIAQD4AQHCAgoQABhHGNYEGLADwgINEAAYgAQYigUYQxiwA8ICBRAAGIAEwgILEAAYgAQYsQMYgwHiAwQYACBBiAYBkAYK&sclient=gws-wiz-serp";

    public void runParse(int period, Path path) {
        Document document = null;
        FileWriter fileWriter = new FileWriter(path, "Data-Time, btc " + "\n");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch();
                Page page = browser.newPage();
                page.navigate("https://www.google.com/search?q=%D0%BA%D1%83%D1%80%D1%81+%D0%B1%D0%B8%D1%82%D0%BA%D0%BE%D0%B8%D0%BD%D0%B0+%D0%B2+%D0%B4%D0%BE%D0%BB%D0%BB%D0%B0%D1%80%D0%B0%D1%85&sca_esv=597832100&sxsrf=ACQVn0-Xq8_lJC8p31PA-swA5FZIcsQR5g%3A1706035313301&ei=cQiwZfH9EZSvwPAPwu-c0Ao&udm=&oq=%D0%BA%D1%83%D1%80%D1%81+%D0%B1%D0%B8%D1%82%D0%BA%D0%BE%D0%B8%D0%BD%D0%B0+d+&gs_lp=Egxnd3Mtd2l6LXNlcnAiHNC60YPRgNGBINCx0LjRgtC60L7QuNC90LAgZCAqAggAMgkQABiABBgKGCoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGAoyBxAAGIAEGApIxyNQxwxY9Q9wAXgBkAEAmAFUoAHtAaoBATO4AQPIAQD4AQHCAgoQABhHGNYEGLADwgINEAAYgAQYigUYQxiwA8ICBRAAGIAEwgILEAAYgAQYsQMYgwHiAwQYACBBiAYBkAYK&sclient=gws-wiz-serp");
                String nameOfCrypta = Time.formatData(LocalDateTime.now()) + "," + (page.locator(".pclqee")).textContent().replaceAll(" ","") + ",";
                fileWriter.appendNewLine(nameOfCrypta);

            }
        }, 0, period, TimeUnit.SECONDS);

    }
}