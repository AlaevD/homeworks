package spbu.sem2.hw5.task2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.*;

public class Controller implements Initializable {
    /** left operand i.e. first number or calculated result of previous operations */
    private String firstOperand = "";
    /** right operand */
    private String secondOperand = "";
    /** operation symbol */
    private String operation;

    /** text field for showing result */
    @FXML
    private TextField display;

    /** current calculator state */
    private State state = State.TYPING_FIRST;

    /** possible calculator states */
    private enum State {
        TYPING_FIRST,
        TYPING_SECOND,
        TYPING_AFTER_EQUAL_SIGN
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        display.setText("0");
    }

    /**
     * processes entered digit. Depending on current state adds it to the end of proper operand
     */
    public void digitClicked(ActionEvent actionEvent) {
        Button b = (Button)actionEvent.getSource();

        switch (state) {
            case TYPING_FIRST:
                firstOperand += b.getText();
                printOperand(firstOperand);
                break;
            case TYPING_SECOND:
                secondOperand += b.getText();
                printOperand(secondOperand);
                break;
            case TYPING_AFTER_EQUAL_SIGN:
                resetState();
                firstOperand += b.getText();
                printOperand(firstOperand);
                break;
        }
    }

    /**
     * processes entered operation. Changes state and calculates result if needed.
     */
    public void operationClicked(ActionEvent actionEvent) {
        Button b = (Button)actionEvent.getSource();

        switch (state) {
            case TYPING_SECOND:
                if (zeroDivision()) {
                    processZeroDivision();
                    break;
                }
                updateDisplay(getResult());
                firstOperand = display.getText();
                secondOperand = "";
            default:
                state = State.TYPING_SECOND;
                operation = b.getText();
        }
        updateDisplay(display.getText() + " " + operation);
    }

    /** calculates result */
    public void equalSignClicked(ActionEvent actionEvent) {
        switch (state) {
            case TYPING_FIRST:
                break;
            case TYPING_SECOND:
                if (secondOperand.isEmpty()) {
                    return;
                }
                if (zeroDivision()) {
                    processZeroDivision();
                    break;
                }

                String result = getResult();
                updateDisplay(result);
                firstOperand = result;
                state = State.TYPING_FIRST;
                secondOperand = "";
                break;
        }
        state = State.TYPING_AFTER_EQUAL_SIGN;
    }

    /** processes C(clear) button. Clears display and resets state */
    public void clearSignClicked(ActionEvent actionEvent) {
        clearDisplay();
    }

    /** Clears display and resets state */
    private void clearDisplay() {
        updateDisplay("0");
        resetState();
    }

    /** processes zero division by showing an appropriate message */
    private void processZeroDivision() {
        updateDisplay("Do not divide by zero");
        resetState();
    }

    /**
     * checks second operand and operation for zero division
     * @return true if zero division was found
     */
    private boolean zeroDivision() {
        Pattern p = Pattern.compile("[0]+");
        Matcher m = p.matcher(secondOperand);
        return operation.equals("/") && m.matches();
    }

    /**
     * calculates result of expression Operand1 operation Operand2
     * @return result of expression
     */
    private String getResult() {
        return (new Calculator().calculate(new BigDecimal(firstOperand), new BigDecimal(secondOperand), operation.charAt(0))).toString();
    }

    /** resets calculator state */
    private void resetState() {
        state = State.TYPING_FIRST;
        firstOperand = "";
        secondOperand = "";
    }

    /**
     * prints operand to display
     * @param operand operand to be printed
     */
    private void printOperand(String operand) {
        display.setText(operand);
    }

    /**
     * updates display with given message
     * @param newText message to be shown on the display
     */
    private void updateDisplay(String newText) {
        display.setText(newText);
    }
}
