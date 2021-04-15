package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private final BaseConnection baseConnection = new BaseConnection();
    public TextField text1;

    Double result;
    int operation = 0;

    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button six;
    @FXML
    private Button five;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button zero;
    @FXML
    private Button comma;
    @FXML
    private Button equally;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button multiply;
    @FXML
    private Button division;
    @FXML
    private Button exponentiation;
    @FXML
    private Button root;
    @FXML
    private Button AC;
    @FXML
    private Button history;
    @FXML
    private TextField display;
    @FXML
    private Button plus_minus;

    @FXML
    private TextField first;
    @FXML
    private TextField second;
    @FXML
    private TextField third;
    @FXML
    private TextField fourth;

    @FXML
    public Pane pane;

    private boolean isVisible;

    @FXML
    private TextArea textHistory;


    @FXML
    private void num(ActionEvent event) {

        if (operation == 7) {
            String regex = "\\d+";
            if (((Button) event.getSource()).getText().matches(regex)) {
                display.setText(((Button) event.getSource()).getText());
                operation = 0;
            }
        } else if (event.getSource() == one) {
            display.setText(display.getText() + "1");
        } else if (event.getSource() == two) {
            display.setText(display.getText() + "2");
        } else if (event.getSource() == three) {
            display.setText(display.getText() + "3");
        } else if (event.getSource() == four) {
            display.setText(display.getText() + "4");
        } else if (event.getSource() == five) {
            display.setText(display.getText() + "5");
        } else if (event.getSource() == six) {
            display.setText(display.getText() + "6");
        } else if (event.getSource() == seven) {
            display.setText(display.getText() + "7");
        } else if (event.getSource() == eight) {
            display.setText(display.getText() + "8");
        } else if (event.getSource() == nine) {
            display.setText(display.getText() + "9");
        } else if (event.getSource() == zero) {
            display.setText(display.getText() + "0");
        } else if (event.getSource() == AC) {
            display.setText("");
        } else if (event.getSource() == comma) {
            if (display.getText().isEmpty()) display.setText("0,");
            else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (event.getSource() == plus_minus) {
            if (display.getText().contains("-")) {
                display.setText(display.getText().replace("-", ""));
            } else display.setText("-" + display.getText());
        } else if (event.getSource() == plus) {
            result = Double.parseDouble(display.getText());
            display.setText("");
            operation = 1;
        } else if (event.getSource() == minus) {
            result = Double.parseDouble(display.getText());
            display.setText("");
            operation = 2;
        } else if (event.getSource() == multiply) {
            result = Double.parseDouble(display.getText());
            display.setText("");
            operation = 3;
        } else if (event.getSource() == division) {
            result = Double.parseDouble(display.getText());
            display.setText("");
            operation = 4;
        } else if (event.getSource() == exponentiation) {
            result = Double.parseDouble(display.getText());
            display.setText("");
            operation = 5;
        } else if (event.getSource() == root) {
            result = Double.parseDouble(display.getText());
            display.setText("");
            operation = 6;
        } else if (event.getSource() == equally) {
            Double secondOperand = Double.parseDouble(display.getText());
            switch (operation) {
                case 1:
                    double ans = result + secondOperand;
                    display.setText(String.valueOf(ans));
                    baseConnection.addCalc(result + " + " + secondOperand + " = " + ans);
                    operation = 7;
                    break;
                case 2:
                    ans = result - secondOperand;
                    baseConnection.addCalc(result + " + " + secondOperand + " = " + ans);
                    display.setText(String.valueOf(ans));
                    operation = 7;
                    break;
                case 3:
                    ans = result * secondOperand;
                    baseConnection.addCalc(result + " + " + secondOperand + " = " + ans);
                    display.setText(String.valueOf(ans));
                    operation = 7;
                    break;
                case 4:
                    if (secondOperand == 0) display.setText("Error");
                    else {
                        ans = result / secondOperand;
                        baseConnection.addCalc(result + " + " + secondOperand + " = " + ans);
                        display.setText(String.valueOf(ans));
                        operation = 7;
                    }
                    break;
                case 5:
                    ans = Math.pow(result, secondOperand);
                    baseConnection.addCalc(result + " + " + secondOperand + " = " + ans);
                    display.setText(String.valueOf(ans));
                    operation = 7;
                    break;
                case 6:
                    ans = Math.pow(result, 1.0 / secondOperand);
                    baseConnection.addCalc(result + " + " + secondOperand + " = " + ans);
                    display.setText(String.valueOf(ans));
                    operation = 7;
                    break;
            }
        }
        if (event.getSource() == history) {
            if (isVisible) {
                pane.setVisible(false);
                isVisible = false;
                textHistory.setText("");
            } else {
                pane.setVisible(true);
                isVisible = true;
                ResultSet resultSet = baseConnection.getCalc();
                try {
                    while (resultSet.next()) {
                        textHistory.appendText(resultSet.getString("name") + "\n");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
