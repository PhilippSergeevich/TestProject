package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BitflyerParser {
    private static final String API_URL = "https://bitflyer.com/api/web/home/market-stats2?marketplace=BTC_EUR,DOT_EUR,XTZ_EUR,XLM_EUR,BAT_EUR,ETC_EUR,LTC_EUR,BCH_EUR,MONA_EUR,LSK_EUR";
    public static BitflyerCryptoCurrency parseJsonString(String strJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(strJson);
        return objectMapper.treeToValue(rootNode.get("product_code").get("BTC_EUR"), BitflyerCryptoCurrency.class);

    }

    public static void runParse(int period) {
        FileWriter fileWriter = new FileWriter("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\CryptoCurrencyBitflyer.csv");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    String json = ApiRequest.doRequest(API_URL);
                    BitflyerCryptoCurrency eth = parseJsonString(json);
                    BitflyerCryptoCurrency btc = parseJsonString(json);
                    BitflyerCryptoCurrency usdt = parseJsonString(json);
                    BitflyerCryptoCurrency xrp = parseJsonString(json);
                    BitflyerCryptoCurrency ltc = parseJsonString(json);

                    String nameOfCrypta = Time.formatData(LocalDateTime.now()) + "," + eth.getPrice() + "," + btc.getPrice() + "," + usdt.getPrice() + "," + xrp.getPrice() + "," + ltc.getPrice();
                    fileWriter.appendNewLine(nameOfCrypta);

                } catch (URISyntaxException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }, 0, period, TimeUnit.SECONDS);

    }
}


