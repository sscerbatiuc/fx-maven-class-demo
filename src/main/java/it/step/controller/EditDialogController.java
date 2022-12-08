package it.step.controller;

import it.step.model.Person;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;


public class EditDialogController {

    private Person copy;

    public TextField nameTxtField;
    public DatePicker birthdatePicker;
    public void setPerson(Person person) {
        copy = person;
        // read name
        nameTxtField.setText(person.getName());
        // read birthdate
        birthdatePicker.setValue(person.getBirthdate());
    }

    public Person getResult() {
        return new Person(copy.getId(), nameTxtField.getText(), birthdatePicker.getValue());
    }

    public void onEdit(ActionEvent event) {
        if(nameTxtField.getText().isEmpty()) {
           nameTxtField.setText(copy.getName());
        }
        LocalDate birthdate = birthdatePicker.getValue();
        if(birthdate != null) {
            birthdatePicker.setValue(copy.getBirthdate());
        }
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
