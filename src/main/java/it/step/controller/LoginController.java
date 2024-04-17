package it.step.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


public class LoginController {

    public TextField usernameTxtField;

    public void onLogin(ActionEvent event) {
        /*
        * 0. Adaugam ID pentru campurile username si password
        * 1. Citim datele introduse in text field-uri
        * 2. Validam datele - daca sunt complete
        * 3. Daca datele nu sunt corecte - afisam o alerta (#see main scene controller - delete)
        * 4. Cream o clasa noua: User (campuri: firstName/lastname/username/password/boolean isActive)
        * 5. SELECT user din baza de date unde username = usernameTxtField.getText() si parola = password.getText()
        * 6. Daca utilizatorul a fost gasit - deschidem fereastra principala
        * 6.2 Daca utilizatorul nu a fost gasit - afisam un mesaj de eroare
        * */
    }

    public void onRegister(ActionEvent event) {
        /*
        * 1. De adaugat FXML pentru o fereastra noua - register.fxml
        * 2. De afisat campurile necesare pentru clasa User.
        * 3. Cand se da click pe register - de salvat in baza de date utilizatorul nou.
        * 4. Inchidem dialogul de inregistrare si afisam login-ul.
        * */
    }
}
