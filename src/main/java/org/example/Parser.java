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


public class Parser {
    private static final String API_URL = "https://api.kriptomat.io/public/prices";
    public static CryptoCurrency parseJsonString(String strJson, org.example.Currency currency) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(strJson);
        return objectMapper.treeToValue(rootNode.get("data").get(currency.getName()), CryptoCurrency.class);
//        CryptoCurrency btc = objectMapper.treeToValue(rootNode.get("data").get("btc"), CryptoCurrency.class);
//        CryptoCurrency usdt = objectMapper.treeToValue(rootNode.get("data").get("usdt"), CryptoCurrency.class);
//        CryptoCurrency xrp = objectMapper.treeToValue(rootNode.get("data").get("xrp"), CryptoCurrency.class);
//        CryptoCurrency ltc = objectMapper.treeToValue(rootNode.get("data").get("ltc"), CryptoCurrency.class);
//
//        Time time = new Time();
//
//        String nameOfCrypta = time.formatData() + "," + eth.getPrice() + "," + btc.getPrice() + "," + usdt.getPrice() + "," + xrp.getPrice() + "," + ltc.getPrice();
//        FileWriter fileWriter = new FileWriter();
//        fileWriter.appendNewLine(nameOfCrypta);
    }

    public static void runParse(int period) {
        FileWriter fileWriter = new FileWriter("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\CryptoCurrency.csv");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Future future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    String json = ApiRequest.doRequest(API_URL);
                    CryptoCurrency eth = parseJsonString(json, org.example.Currency.ETHERIUM);
                    CryptoCurrency btc = parseJsonString(json, org.example.Currency.BITKOIN);
                    CryptoCurrency usdt = parseJsonString(json, Currency.TETHER);
                    CryptoCurrency xrp = parseJsonString(json, Currency.RIPPLE);
                    CryptoCurrency ltc = parseJsonString(json, Currency.LITECOIN);

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


