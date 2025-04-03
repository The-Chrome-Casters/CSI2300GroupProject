import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFX_Display extends Application {
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
}