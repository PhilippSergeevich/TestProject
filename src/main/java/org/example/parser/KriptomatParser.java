package org.example.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apiRequest.ApiRequest;
import org.example.currency.CurrencyDTO;
import org.example.currency.CurrencyName;
import org.example.currency.KriptomatCurrency;
import org.example.util.FileWriter;
import org.example.util.Time;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class KriptomatParser implements ParserJson {
    private static final String API_URL = "https://api.kriptomat.io/public/prices";
    private static ObjectMapper objectMapper = new ObjectMapper();

    public CurrencyDTO parseJsonString(String strJson, String currencyName) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(strJson);
            KriptomatCurrency kriptomatCurrency = objectMapper.treeToValue(rootNode.get("data").get(currencyName), KriptomatCurrency.class);

            return new CurrencyDTO(kriptomatCurrency.getName(), kriptomatCurrency.getPrice());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void runParse(int period, Path path) {
        FileWriter fileWriter = new FileWriter(path, "Data-Time,eth,btc,usdt,xrp,ltc" + "\n");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                String json = ApiRequest.doRequest(API_URL);
                CurrencyDTO eth = parseJsonString(json, "eth");
                CurrencyDTO btc = parseJsonString(json, "btc");
                CurrencyDTO usdt = parseJsonString(json, "usdt");
                CurrencyDTO xrp = parseJsonString(json, "xrp");
                CurrencyDTO ltc = parseJsonString(json, "ltc");
                String nameOfCrypta = Time.formatData(LocalDateTime.now()) + "," + eth.getPrice() + "," + btc.getPrice() + "," + usdt.getPrice() + "," + xrp.getPrice() + "," + ltc.getPrice();
                fileWriter.appendNewLine(nameOfCrypta);

            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, period, TimeUnit.SECONDS);
    }

    public String getApiUrl() {
        return API_URL;
    }

}



