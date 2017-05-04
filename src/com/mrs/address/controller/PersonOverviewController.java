package com.mrs.address.controller;

import com.mrs.address.MainApp;
import com.mrs.address.model.Person;
import com.mrs.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by marus505 on 2017. 4. 24..
 */
public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label streetLabel;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label birthdayLabel;

    private MainApp mainApp;

    private Person tempPerson;

    private LocalDateTime lastClickTime;

    public PersonOverviewController() {

    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            birthdayLabel.setText(DateUtil.format(person.getBirthDay()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");

            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            noSelected();
        }
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean onClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (onClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            noSelected();
        }
    }

    private void noSelected() {
        //아무것도 선택하지 않음
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }

    /**
     * 마우스를 클릭하면
     */
    @FXML
    private void handleRowSelect(MouseEvent mouseEvent) {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {

            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                boolean onClicked = mainApp.showPersonEditDialog(selectedPerson);
                if (onClicked) {
                    showPersonDetails(selectedPerson);
                }
            }

            /*
            if (selectedPerson != tempPerson) {
                tempPerson = selectedPerson;
                lastClickTime = LocalDateTime.now();
            } else if ( selectedPerson == tempPerson) {
                LocalDateTime now = LocalDateTime.now();
                long diff = lastClickTime.until(now, ChronoUnit.MILLIS);
                if (diff < 300) { // 더블클릭 속도를 300ms 로 설정함
                    boolean onClicked = mainApp.showPersonEditDialog(selectedPerson);
                    if (onClicked) {
                        showPersonDetails(selectedPerson);
                    }
                } else {
                    lastClickTime = LocalDateTime.now();
                }
            }
            */

        } else {
            noSelected();
        }
    }

    /**
     * 테이블 뷰 에서 키를 입력했을 경우 엔터만 받아 수정화면을 띄움
     * @param e
     */
    @FXML
    private void handleRowSelectByKeyPressed(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            handleEditPerson();
        }
    }
}
