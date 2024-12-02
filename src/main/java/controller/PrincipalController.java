package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import dao.DadosMoeda;
import model.Moedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

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
    private Label atualizacao;

    @FXML
    private void initialize() throws IOException, InterruptedException {
        btnLimpar.setDisable(true);
        String linkApi = "https://v6.exchangerate-api.com/v6/183f02f3f6692bccf61e29fd/latest/USD";
        carregarMoedas(linkApi);
    }

    public void carregarMoedas(String linkApi) throws IOException, InterruptedException {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(linkApi)).build();
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
            Set<String> moedas = conversionRates.keySet();
            cbxMoedaDe.getItems().addAll(moedas);
            cbxMoedaPara.getItems().addAll(moedas);
        }catch (Exception e){
            e.printStackTrace();
        }
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
        atualizacao.setText(moedas.dataUltimaAtualização());
    }

    @FXML
    private void onBtnLimparClick(){
        cbxMoedaPara.setValue("");
        cbxMoedaDe.setValue("");
        txtQuantidade.setText("");
        resultado.setText("");
        atualizacao.setText("");
    }
}