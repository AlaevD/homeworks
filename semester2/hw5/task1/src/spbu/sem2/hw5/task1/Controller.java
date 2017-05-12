package spbu.sem2.hw5.task1;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /** first operand spinner */
    @FXML
    private Spinner<Integer> firstOperand;

    /** second operand spinner */
    @FXML
    private Spinner<Integer> secondOperand;

    /** operation choice box */
    @FXML
    private ChoiceBox<String> operation;

    /** result label */
    @FXML
    private Label result;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operation.setItems(FXCollections.observableArrayList("+", "-", "*", "/"));
        operation.getSelectionModel().select(0);

        firstOperand.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Calculator.getMin(), Calculator.getMax()));
        secondOperand.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Calculator.getMin(), Calculator.getMax()));

        firstOperand.getValueFactory().setValue(0);
        secondOperand.getValueFactory().setValue(0);

        firstOperand.valueProperty().addListener(((observable, oldValue, newValue) -> updateResult()));
        secondOperand.valueProperty().addListener(((observable, oldValue, newValue) -> updateResult()));

        operation.valueProperty().addListener((observable, oldValue, newValue) -> updateResult());

        updateResult();
    }

    /** updates label result */
    public void updateResult() {
        Integer a = firstOperand.getValue();
        Integer b = secondOperand.getValue();
        char oper = operation.getValue().charAt(0);
        if (b.equals(0) && oper == '/') {
            result.setText("Division by zero");
        }
        else {
            result.setText(Integer.toString(Calculator.calculate(a, b, oper)));
        }
    }
}
