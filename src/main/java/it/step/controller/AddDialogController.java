package it.step.controller;

import it.step.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddDialogController implements Initializable {

    public TextField nameTxtField;
    public DatePicker birthdatePicker;

    public ChoiceBox choiceBox;

    public RadioButton maleRadioBtn;

    public RadioButton femaleRadioBtn;

    public void onAdd(ActionEvent event) {
        String name = nameTxtField.getText();
        LocalDate birthDate = birthdatePicker.getValue();
        if(!name.isEmpty() && birthDate != null){
            // save the data
            // close
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().add("Item 1");
        choiceBox.getItems().add("Item 2");
        choiceBox.getItems().add("Item 3");

    }

    public void onMaleSelect(ActionEvent event) {
        this.femaleRadioBtn.setSelected(false);
    }

    public void onFemaleSelect(ActionEvent event) {
        this.maleRadioBtn.setSelected(false);
    }
}
