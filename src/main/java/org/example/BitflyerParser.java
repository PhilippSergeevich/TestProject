package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BitflyerParser {
    private static final String API_URL = "https://bitflyer.com/api/web/home/market-stats2?";
    public static BitflyerCryptoCurrency parseJsonString(String strJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(strJson);
        JsonNode marketsNode = rootNode.get("data").get("markets");

        List<BitflyerCryptoCurrency> markets = objectMapper.readValue(marketsNode.toString(), new TypeReference<List<BitflyerCryptoCurrency>>() {});

        return objectMapper.treeToValue(rootNode.get("data").get("markets").get("product_code"), BitflyerCryptoCurrency.class);

    }

    public static void runParse(int period) {
        FileWriter fileWriter = new FileWriter(Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\CryptoCurrencyBitflyer.csv"), "Data-Time, btc");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    String json = ApiRequest.doRequest(API_URL);

                    BitflyerCryptoCurrency btc_jpy = parseJsonString(json);

                    String nameOfCrypta = Time.formatData(LocalDateTime.now()) + "," + btc_jpy.getPrice() + ",";
                    System.out.println("Value to be written: " + nameOfCrypta); // Добавьте эту строку для проверки значения до записи

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


