package org.example.parser;

import org.example.util.FileWriter;
import org.example.util.Time;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BankirosHtmlParser implements Parser {
    private static final String URL_TO_HTML = "https://bankiros.ru/crypto/bitcoin?ysclid=lr9sthgprb997445006";
    public void runParse(int period,  Path path) {
        FileWriter fileWriter = new FileWriter(path, "Data-Time, btc " + "\n");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {

        try {
            Document doc = Jsoup.connect("https://bankiros.ru/crypto/bitcoin?ysclid=lr9sthgprb997445006").get();
            var elements = doc.getElementsByClass("crypto_curr_val");
            String nameOfCrypta = Time.formatData(LocalDateTime.now()) + "," + elements.text().replace("$","") + ",";
            fileWriter.appendNewLine(nameOfCrypta);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }, 0, period, TimeUnit.SECONDS);
    }


//    public void runParse(int period) {
//        Document document = null;
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch();
//            Page page = browser.newPage();
//
//            page.navigate("https://www.google.com/search?q=+%D0%BA%D1%83%D1%80%D1%81+%D0%B1%D0%B8%D1%82%D0%BA%D0%BE%D0%B8%D0%BD%D0%B0&sca_esv=597832100&sxsrf=ACQVn09ePVXI9dKEbPeaL_3ZHUgx5wNOdw%3A1705075430928&ei=5mKhZf2XOK2HwPAP4ty_8Ag&udm=&ved=0ahUKEwj9ooD7nNiDAxWtAxAIHWLuD44Q4dUDCBA&uact=5&oq=+%D0%BA%D1%83%D1%80%D1%81+%D0%B1%D0%B8%D1%82%D0%BA%D0%BE%D0%B8%D0%BD%D0%B0&gs_lp=Egxnd3Mtd2l6LXNlcnAiGiDQutGD0YDRgSDQsdC40YLQutC-0LjQvdCwMgoQIxiABBiKBRgnMgUQABiABDILEAAYgAQYsQMYgwEyCxAAGIAEGLEDGIMBMgUQABiABDILEAAYgAQYsQMYgwEyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAESJcIUABY8gVwAXgBkAEAmAFJoAGDAaoBATK4AQPIAQD4AQHiAwQYACBB&sclient=gws-wiz-serp");
//            System.out.println(page.locator(".pclqee").textContent());
//            System.out.println(page.title());
//        }
//
//    }


}
