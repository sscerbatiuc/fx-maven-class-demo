package it.step.controller;

import it.step.db.EmployeeManagerDB;
import it.step.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    public TableColumn<Person, Integer> idCol;
    public TableColumn<Person, String> nameCol;
    public TableColumn<Person, String> surnameCol;
    public TableColumn<Person, LocalDate> birthdateCol;

    public TableView<Person> table;

    private EmployeeManagerDB db = new EmployeeManagerDB();

    public void onAdd(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
        AnchorPane parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Add employee");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        // handle close without data
        AddDialogController dialogController = loader.getController();
        String name = dialogController.nameTxtField.getText();
        LocalDate birthdate = dialogController.birthdatePicker.getValue();
        // db.edit()
        table.getItems().add(new Person(1, name, birthdate));

    }

    public void onDelete(ActionEvent event) {
        int idx = table.getSelectionModel().getSelectedIndex();
        if(idx != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("This is a header: please confirm");
            alert.setContentText("Are you sure you want to delete? \n"
                    + table.getItems().get(idx).toString());
            alert.setTitle("Title dialog");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if(result.get() == ButtonType.OK){
                    // db.delete()
                    table.getItems().remove(idx);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Selection required");
            alert.setContentText("Please select something!");
            alert.setTitle("Title dialog");
            alert.show();
        }
        // get selected index
        // delete selected index
    }

    public void onEdit(ActionEvent event) throws IOException {
        int idx = table.getSelectionModel().getSelectedIndex();
        if(idx != -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit.fxml"));
            AnchorPane parent = loader.load();

            EditDialogController controller = loader.getController();
            controller.setPerson(table.getItems().get(idx));

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Edit employee");
            stage.setScene(scene);
            stage.showAndWait();

            String name = controller.nameTxtField.getText();
            Person person = controller.getResult();
            // db.edit()
            table.getItems().set(idx, person);

            table.refresh();
        }
//        table.getItems().get(idx).setName("EDITED NAME");
//        table.getItems().get(idx).setId(999);
//        table.getItems().get(idx).setBirthdate(LocalDate.MIN);
//        table.refresh();
    }

    public void onClose(ActionEvent event){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create person list
        ObservableList<Person> list = FXCollections.observableArrayList();
        List<Person> persons = db.read();
        for(Person p: persons) {
            list.add(p);
        }
//        list.add(new Person(1, "John Smith", LocalDate.of(1980, 10, 10)));
//        list.add(new Person(2, "Jane Smith", LocalDate.of(1980, 10, 10)));
//        list.add(new Person(3, "Peter Smith", LocalDate.of(1980, 10, 10)));
//        list.add(new Person(4, "Max Smith", LocalDate.of(1980, 10, 10)));
        // Link person list with table
        table.setItems(list);
        // Link id with idCol
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        // Link name with nameCol
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        // Link birthdate with birthdateCol
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    }
}