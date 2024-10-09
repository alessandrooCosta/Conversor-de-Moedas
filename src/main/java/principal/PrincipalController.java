package principal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrincipalController {
    @FXML
    private Label resultado;

    @FXML
    protected void onHelloButtonClick() {
        resultado.setText("0.92 EUR");
    }
}