package controller;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.DadosMoeda;
import model.Moedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PrincipalController {
    @FXML
    private TextField txtQuantidade;

    @FXML
    private ComboBox<String> cbxMoedaDe;

    @FXML
    private ComboBox<String> cbxMoedaPara;

    @FXML
    private Button btnConverter;

    @FXML
    private Button btnLimpar;

    @FXML
    private Label resultado;

    @FXML
    private void initialize() throws IOException, InterruptedException {
        btnLimpar.setDisable(true);

        cbxMoedaDe.getItems().addAll("USD","AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN",
                "BHD","BIF","BMD","BND","BOB","BRL","BSD","BTN","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY","COP","CRC", "CUP", "CVE","CZK",
                "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB","EUR","FJD","FKP","FOK","GBP","GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ",
                "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY","KES", "KGS",
                "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL","MGA", "MKD", "MMK", "MNT",
                "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP",
                "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD",
                "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "UYU", "UZS", "VES",
                "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL");

        cbxMoedaPara.getItems().addAll("USD","AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN",
                "BHD","BIF","BMD","BND","BOB","BRL","BSD","BTN","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY","COP","CRC", "CUP", "CVE","CZK",
                "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB","EUR","FJD","FKP","FOK","GBP","GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ",
                "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY","KES", "KGS",
                "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL","MGA", "MKD", "MMK", "MNT",
                "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP",
                "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD",
                "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "UYU", "UZS", "VES",
                "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL");
    }

    @FXML
    private void onBtnConverterClick() throws IOException, InterruptedException {
        double quantidade = Double.parseDouble(txtQuantidade.getText());
        Moedas moeda = new Moedas(quantidade);
        String moedaDe = cbxMoedaDe.getValue();
        String moedaPara = cbxMoedaPara.getValue();
        String linkApi = "https://v6.exchangerate-api.com/v6/183f02f3f6692bccf61e29fd/pair/" + moedaDe+ "/" + moedaPara + "/" + moeda.getQuantidade();
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(linkApi)).build();
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new Gson();
        DadosMoeda record = gson.fromJson(json, DadosMoeda.class);
        Moedas moedas = new Moedas(quantidade,record);
        btnLimpar.setDisable(false);
        resultado.setText(moedas.toString());
    }

    @FXML
    private void onBtnLimparClick(){
        cbxMoedaPara.setValue("");
        cbxMoedaDe.setValue("");
        txtQuantidade.setText("");
        resultado.setText("");
    }
}