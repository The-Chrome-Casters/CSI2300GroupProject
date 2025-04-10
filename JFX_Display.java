import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JFX_Display extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library System");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);

        Button but_user = new Button("Manage Users");
        but_user.setLayoutX(150);
        but_user.setLayoutY(300);
        but_user.setOnAction(e -> openUserScene(primaryStage));

        Button but_book = new Button("Manage Books");
        but_book.setLayoutX(450);
        but_book.setLayoutY(300);
        but_book.setOnAction(e -> openBookScene(primaryStage));

        Button but_cd = new Button("Manage CDs");
        but_cd.setLayoutX(750);
        but_cd.setLayoutY(300);
        but_cd.setOnAction(e -> openCDScene(primaryStage));

        Button but_close = new Button("Close");
        but_close.setLayoutX(920);
        but_close.setLayoutY(20);
        but_close.setOnAction(e -> Platform.exit());

        Pane root = new Pane();
        root.getChildren().addAll(but_user, but_book, but_cd, but_close);

        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    private void openUserScene(Stage primaryStage) {
        Pane userPane = new Pane();
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(primaryStage));
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);

        Button addUser = new Button("Add New User");
        addUser.setLayoutX(150);
        addUser.setLayoutY(300);
        addUser.setOnAction(e -> addUser(primaryStage));

        Button removeUser = new Button("Remove Existing User");
        removeUser.setLayoutX(450);
        removeUser.setLayoutY(300);

        Button userHistory = new Button("View User Checkouts");
        userHistory.setLayoutX(750);
        userHistory.setLayoutY(300);
 
        userPane.getChildren().addAll(backButton, addUser, removeUser, userHistory);
        Scene userScene = new Scene(userPane, 1000, 700);
        primaryStage.setScene(userScene);
    }

    private void openBookScene(Stage primaryStage) {
        Pane bookPane = new Pane();
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(primaryStage));
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);

        Button addBook = new Button("Add Book");
        addBook.setLayoutX(150);
        addBook.setLayoutY(300);
        addBook.setOnAction(e -> addBook(primaryStage));

        Button removeBook = new Button("Remove Book");
        removeBook.setLayoutX(350);
        removeBook.setLayoutY(300);

        Button checkoutBook = new Button("Check Out Book");
        checkoutBook.setLayoutX(550);
        checkoutBook.setLayoutY(300);

        Button returnBook = new Button("Return Book");
        returnBook.setLayoutX(750);
        returnBook.setLayoutY(300);
 
        bookPane.getChildren().addAll(backButton, addBook, removeBook, checkoutBook, returnBook);
        Scene userScene = new Scene(bookPane, 1000, 700);
        primaryStage.setScene(userScene);
    }

    private void openCDScene(Stage primaryStage) {
        Pane cdPane = new Pane();
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(primaryStage));
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);

        Button addCD = new Button("Add CD");
        addCD.setLayoutX(150);
        addCD.setLayoutY(300);
        addCD.setOnAction(e -> addCD(primaryStage));

        Button removeCD = new Button("Remove CD");
        removeCD.setLayoutX(350);
        removeCD.setLayoutY(300);

        Button checkoutCD = new Button("Check Out CD");
        checkoutCD.setLayoutX(550);
        checkoutCD.setLayoutY(300);

        Button returnCD = new Button("Return CD");
        returnCD.setLayoutX(750);
        returnCD.setLayoutY(300);
 
        cdPane.getChildren().addAll(backButton, addCD, removeCD, checkoutCD, returnCD);
        Scene userScene = new Scene(cdPane, 1000, 700);
        primaryStage.setScene(userScene);
    }

    private void addBook(Stage primaryStage) {
        Stage addBookStage = new Stage();
        addBookStage.setTitle("Add Book Info");

        GridPane addBookGrid = new GridPane();
        addBookGrid.setVgap(10);
        addBookGrid.setHgap(10);
        addBookGrid.setPadding(new javafx.geometry.Insets(10));

        Label titleLabel = new Label ("Title: ");
        TextField titleField = new TextField();

        Label authorLabel = new Label ("Author: ");
        TextField authorField = new TextField();

        Label pageLabel = new Label ("Page Count: ");
        TextField pageField = new TextField();

        Label quantityLabel = new Label ("Quantity: ");
        TextField quantityField = new TextField();

        Label publishDateLabel = new Label ("Publish Date: ");
        TextField publishDateField = new TextField();

        Label publisherLabel = new Label ("Publisher: ");
        TextField publisherField = new TextField();

        addBookGrid.add(titleLabel, 0, 0);
        addBookGrid.add(titleField, 1, 0);

        addBookGrid.add(authorLabel, 0, 1);
        addBookGrid.add(authorField, 1, 1);

        addBookGrid.add(pageLabel, 0, 2);
        addBookGrid.add(pageField, 1, 2);

        addBookGrid.add(quantityLabel, 0, 3);
        addBookGrid.add(quantityField, 1, 3);

        addBookGrid.add(publishDateLabel, 0, 4);
        addBookGrid.add(publishDateField, 1, 4);

        addBookGrid.add(publisherLabel, 0, 5);
        addBookGrid.add(publisherField, 1, 5);

        Button finishButton = new Button ("Finish");
        addBookGrid.add(finishButton, 1, 6);
        finishButton.setOnAction (e -> {
            try {
                addBookStage.close();
                String title = titleField.getText();
                String author = authorField.getText();
                int pageCount = Integer.parseInt(pageField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String publishDate = publishDateField.getText();
                String publisher = publisherField.getText();

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Book Information");
                infoAlert.setHeaderText("New Book Added:");
                infoAlert.setContentText(
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Page Count: " + pageCount + "\n" +
                "Quantity: " + quantity + "\n" +
                "Publish Date: " + publishDate + "\n" +
                "Publisher: " + publisher
            );
            infoAlert.showAndWait();

            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });

        Scene scene = new Scene(addBookGrid, 300, 300);
        addBookStage.setScene(scene);
        addBookStage.show();
    }

    private void addCD(Stage primaryStage) {
        Stage addCDStage = new Stage();
        addCDStage.setTitle("Add CD Info");

        GridPane addCDGrid = new GridPane();
        addCDGrid.setVgap(10);
        addCDGrid.setHgap(10);
        addCDGrid.setPadding(new javafx.geometry.Insets(10));

        Label titleLabel = new Label ("Title: ");
        TextField titleField = new TextField();

        Label authorLabel = new Label ("Author: ");
        TextField authorField = new TextField();

        Label timeLabel = new Label ("Time Length (In Minutes): ");
        TextField timeField = new TextField();

        Label quantityLabel = new Label ("Quantity: ");
        TextField quantityField = new TextField();

        Label publishDateLabel = new Label ("Publish Date: ");
        TextField publishDateField = new TextField();

        Label productionCompanyLabel = new Label ("Production Company : ");
        TextField productionCompanyField = new TextField();

        addCDGrid.add(titleLabel, 0, 0);
        addCDGrid.add(titleField, 1, 0);

        addCDGrid.add(authorLabel, 0, 1);
        addCDGrid.add(authorField, 1, 1);

        addCDGrid.add(timeLabel, 0, 2);
        addCDGrid.add(timeField, 1, 2);

        addCDGrid.add(quantityLabel, 0, 3);
        addCDGrid.add(quantityField, 1, 3);

        addCDGrid.add(publishDateLabel, 0, 4);
        addCDGrid.add(publishDateField, 1, 4);

        addCDGrid.add(productionCompanyLabel, 0, 5);
        addCDGrid.add(productionCompanyField, 1, 5);

        Button finishButton = new Button ("Finish");
        addCDGrid.add(finishButton, 1, 6);
        finishButton.setOnAction (e -> {
            try {
                addCDStage.close();
                String title = titleField.getText();
                String author = authorField.getText();
                int timeCount = Integer.parseInt(timeField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String publishDate = publishDateField.getText();
                String productionCompany = productionCompanyField.getText();

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("CD Information");
                infoAlert.setHeaderText("New CD Added:");
                infoAlert.setContentText(
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Time Count: " + timeCount + " minutes" +"\n" +
                "Quantity: " + quantity + "\n" +
                "Publish Date: " + publishDate + "\n" +
                "Product Company: " + productionCompany
            );
            infoAlert.showAndWait();

            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });

        Scene scene = new Scene(addCDGrid, 300, 300);
        addCDStage.setScene(scene);
        addCDStage.show();
    }

    private void addUser(Stage primaryStage) {
        Stage addUserStage = new Stage();
        addUserStage.setTitle("Add User Info");

        GridPane addUserGrid = new GridPane();
        addUserGrid.setVgap(10);
        addUserGrid.setHgap(10);
        addUserGrid.setPadding(new javafx.geometry.Insets(10));

        Label nameLabel = new Label ("Name(First and Last) : ");
        TextField nameField = new TextField();

        Label ageLabel = new Label ("Age: ");
        TextField ageField = new TextField();

        addUserGrid.add(nameLabel, 0, 0);
        addUserGrid.add(nameField, 1, 0);

        addUserGrid.add(ageLabel, 0, 1);
        addUserGrid.add(ageField, 1, 1);

        Button finishButton = new Button ("Finish");
        addUserGrid.add(finishButton, 1, 2);
        finishButton.setOnAction (e -> {
            try {
                addUserStage.close();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("User Information");
                infoAlert.setHeaderText("New User Added:");
                infoAlert.setContentText(
                "Name: " + name + "\n" +
                "Age: " + age + "\n"
            );
            infoAlert.showAndWait();

            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });

        Scene scene = new Scene(addUserGrid, 300, 200);
        addUserStage.setScene(scene);
        addUserStage.show();
    }
}
