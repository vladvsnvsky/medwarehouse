package com.mipapp;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.SQLException;


public class UI extends Application {

    Stage currentStage = new Stage();

    Scene loginScene = null;
    Scene registerScene = null;
    User current = null;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage currentStage) {
        setRegisterScene();
        setLoginScene();
        currentStage.setScene(this.loginScene);
        currentStage.show();
    }

    public void setRegisterScene(){
        currentStage.setTitle("Registration Form JavaFX Application");
        // Create the registration form pane
        GridPane gridPane = createRegistrationFormPane();
        // Create a scene with the registration form gridPane as the root node.
        this.registerScene = new Scene(gridPane, 800, 500);
    }

    public void setLoginScene(){
        // Create the registration form pane
        GridPane gridPane = createLoginFormPane();
        // Create a scene with the registration form gridPane as the root node.
        this.loginScene = new Scene(gridPane, 800, 500);
    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        Label headerLabel = new Label("Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        // Add Username Label
        Label usernameLabel = new Label("Username : ");
        gridPane.add(usernameLabel, 0, 2);

        // Add Email Text Field
        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        Hyperlink goToLoginLabel = new Hyperlink("Already have an account? Log In!");
        goToLoginLabel.setOnAction((param)->{
            this.currentStage.setScene(loginScene);
            this.currentStage.show();
        });
        gridPane.add(goToLoginLabel,0,5, 2, 1);
        GridPane.setHalignment(goToLoginLabel, HPos.CENTER);
        GridPane.setMargin(goToLoginLabel, new Insets(20, 0,20,0));

        submitButton.setOnAction(event -> {
            if(nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                return;
            }
            if(usernameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your username");
                return;
            }
            if(passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                return;
            }
            User user = new User(nameField.getText(), usernameField.getText(), passwordField.getText());
            DatabaseController dbc = new DatabaseController();
            try {
                dbc.insertUser(user);
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Cannot create user with this credentials!");
                throw new RuntimeException(e);
            }
            showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText());
        });

        return gridPane;
    }

    private GridPane createLoginFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        Label headerLabel = new Label("Log In");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label usernameLabel = new Label("Username : ");
        gridPane.add(usernameLabel, 0,1);

        // Add Name Text Field
        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        Hyperlink goToRegisterLabel = new Hyperlink("Already have an account? Go to login!");
        goToRegisterLabel.setOnAction((param)-> {
            this.currentStage.setScene(registerScene);
            this.currentStage.show();
        });
        gridPane.add(goToRegisterLabel,0,5, 2, 1);
        GridPane.setHalignment(goToRegisterLabel, HPos.CENTER);
        GridPane.setMargin(goToRegisterLabel, new Insets(20, 0,20,0));

        submitButton.setOnAction(event -> {
            if(usernameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your username");
                return;
            }
            if(passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                return;
            }

            String _username = usernameField.getText();
            String _password = passwordField.getText();
            DatabaseController dbc = new DatabaseController();

            current = dbc.login(_username, _password);

            if(current != null){
                //go to the crud page
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Login Successful!", "Welcome " + usernameField.getText());
                Scene crudScene;
                crudScene = buildCrudScene(current);
                this.currentStage.setScene(crudScene);
                this.currentStage.show();

                return;
            }

            showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Login Failed!", "Try again!");
        });

        return gridPane;
    }

    Scene buildCrudScene(User u){
        TableView<Product> table = new TableView<>();
        TextField idField;
        TextField pznField;
        TextField supplierField;
        TextField productNameField;
        TextField strengthField;
        TextField packageSizeField;
        TextField unitField;
        Button addButton;
        Button deleteButton;
        Button updateButton;

        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, Integer> pznColumn = new TableColumn<>("PZN");
        pznColumn.setMinWidth(100);
        pznColumn.setCellValueFactory(new PropertyValueFactory<>("pzn"));

        TableColumn<Product, String> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setMinWidth(200);
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setMinWidth(200);
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, String> strengthColumn = new TableColumn<>("Strength");
        strengthColumn.setMinWidth(100);
        strengthColumn.setCellValueFactory(new PropertyValueFactory<>("strength"));

        TableColumn<Product, Integer> packageSizeColumn = new TableColumn<>("Package Size");
        packageSizeColumn.setMinWidth(100);
        packageSizeColumn.setCellValueFactory(new PropertyValueFactory<>("packageSize"));

        TableColumn<Product, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setMinWidth(100);
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

        // Create table
//        table.setItems(DatabaseController.getAllProducts());
//        table.getColumns().addAll(idColumn, pznColumn, supplierColumn, productNameColumn, strengthColumn, packageSizeColumn, unitColumn);

        // Create input fields and buttons
        idField = new TextField();
        idField.setPromptText("ID");
        idField.setMinWidth(100);

        pznField = new TextField();
        pznField.setPromptText("PZN");
        pznField.setMinWidth(100);

        supplierField = new TextField();
        supplierField.setPromptText("Supplier");
        supplierField.setMinWidth(200);

        productNameField = new TextField();
        productNameField.setPromptText("Product Name");
        productNameField.setMinWidth(200);

        strengthField = new TextField();
        strengthField.setPromptText("Strength");
        strengthField.setMinWidth(100);

        packageSizeField = new TextField();
        packageSizeField.setPromptText("Package Size");
        packageSizeField.setMinWidth(100);

        unitField = new TextField();
        unitField.setPromptText("Unit");
        unitField.setMinWidth(100);

        addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Product p = new Product();
            p.setName(productNameField.getText());
            p.setPzn(pznField.getText());
            p.setSupplier(supplierField.getText());
            p.setStrength(strengthField.getText());
            p.setPackageSize(Integer.parseInt(packageSizeField.getText()));
            p.setUnit(unitField.getText());
            try {
                DatabaseController.insertProduct(p);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            table.getItems().add(p);
            idField.clear();
            pznField.clear();
            supplierField.clear();
            productNameField.clear();
            strengthField.clear();
            packageSizeField.clear();
            unitField.clear();
        });
        deleteButton = new Button("Delete");
//        deleteButton.setOnAction(e -> deleteButtonClicked());
        updateButton = new Button("Update");
//        updateButton.setOnAction(e -> updateButtonClicked());

        // Create layout
        HBox inputFields = new HBox();
        inputFields.setPadding(new Insets(10, 10, 10, 10));
        inputFields.setSpacing(10);
        inputFields.getChildren().addAll(idField, pznField, supplierField, productNameField, strengthField, packageSizeField, unitField);

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(10, 10, 10, 10));
        buttons.setSpacing(10);
        buttons.getChildren().addAll(addButton, deleteButton, updateButton);

        VBox layout = new VBox();
        layout.getChildren().addAll(table, inputFields, buttons);

        // Create scene and stage
        Scene scene = new Scene(layout);

        return scene;
    }

    // Add button clicked


    private void showAlert(Alert.AlertType error, Window owner, String alertTitle, String message) {
        Alert alert = new Alert(error);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
