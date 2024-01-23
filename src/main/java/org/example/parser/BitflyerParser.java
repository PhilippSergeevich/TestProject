package org.example.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apiRequest.ApiRequest;
import org.example.currency.BitflyerCryptoCurrency;
import org.example.currency.CurrencyDTO;
import org.example.util.FileWriter;
import org.example.util.Time;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BitflyerParser implements ParserJson  {
    private static final String API_URL = "https://bitflyer.com/api/web/home/market-stats2?";
    private static ObjectMapper objectMapper = new ObjectMapper();

    public CurrencyDTO parseJsonString(String strJson, String currencyName) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(strJson);
            JsonNode marketsNode = rootNode.get("data").get("markets");

            List<BitflyerCryptoCurrency> markets = objectMapper.readValue(marketsNode.toString(), new TypeReference<List<BitflyerCryptoCurrency>>() {
            });
            BitflyerCryptoCurrency bitflyerCryptoCurrency = markets.get(0);
            return new CurrencyDTO("BTC_JPY", bitflyerCryptoCurrency.getPrice());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    public void runParse(int period, Path path) {
        FileWriter fileWriter = new FileWriter(path, "Data-Time, btc " + "\n");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                String json = ApiRequest.doRequest(API_URL);

                CurrencyDTO btcJpy = parseJsonString(json, "BTC_JPY");
                String nameOfCrypta = Time.formatData(LocalDateTime.now()) + "," + btcJpy.getPrice() + ",";

                fileWriter.appendNewLine(nameOfCrypta);
            } catch (IOException | URISyntaxException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, period, TimeUnit.SECONDS);
    }

    public String getApiUrl() {
        return API_URL;
    }
}
