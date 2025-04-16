import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JFX_Display extends Application {
    static Library lib = new Library();

    static void saveLibrary() throws IOException {
        FileOutputStream libOut = new FileOutputStream("library");
        ObjectOutputStream libOOS = new ObjectOutputStream(libOut);
        libOOS.writeObject(lib);
        libOut.close();
    }

    static void loadLibrary() throws IOException, ClassNotFoundException {
        FileInputStream libIn = new FileInputStream ("library");
        ObjectInputStream checkOIS = new ObjectInputStream(libIn);
        lib = (Library)checkOIS.readObject();
        libIn.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library System");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);

        Label welcome = new Label ("Welcome To Our Library Management System. What Do You Want to Edit?");
        welcome.setFont(new Font("Arial", 24));
        welcome.setLayoutX(80);
        welcome.setLayoutY(150);

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
        root.getChildren().addAll(but_user, but_book, but_cd, but_close, welcome);

        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    private void openUserScene(Stage primaryStage) {
        Pane userPane = new Pane();

        Label welcome = new Label ("How Would You Like To Manage Users?");
        welcome.setFont(new Font("Arial", 24));
        welcome.setLayoutX(280);
        welcome.setLayoutY(150);

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
        removeUser.setOnAction(e -> removeUser(primaryStage));

        Button userHistory = new Button("View User Checkouts");
        userHistory.setLayoutX(750);
        userHistory.setLayoutY(300);
        userHistory.setOnAction(e -> userCheckout(primaryStage));
 
        userPane.getChildren().addAll(backButton, addUser, removeUser, userHistory, welcome);
        Scene userScene = new Scene(userPane, 1000, 700);
        primaryStage.setScene(userScene);
    }

    private void openBookScene(Stage primaryStage) {
        Pane bookPane = new Pane();

        Label welcome = new Label ("How Would You Like to Manage Books?");
        welcome.setFont(new Font("Arial", 24));
        welcome.setLayoutX(280);
        welcome.setLayoutY(150);

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
        removeBook.setOnAction(e -> removeBook(primaryStage));

        Button checkoutBook = new Button("Check Out Book");
        checkoutBook.setLayoutX(550);
        checkoutBook.setLayoutY(300);
        checkoutBook.setOnAction(e -> checkoutBook(primaryStage));

        Button returnBook = new Button("Return Book");
        returnBook.setLayoutX(750);
        returnBook.setLayoutY(300);
        returnBook.setOnAction(e -> returnBook(primaryStage));
 
        bookPane.getChildren().addAll(backButton, addBook, removeBook, checkoutBook, returnBook, welcome);
        Scene userScene = new Scene(bookPane, 1000, 700);
        primaryStage.setScene(userScene);
    }

    private void openCDScene(Stage primaryStage) {
        Pane cdPane = new Pane();

        Label welcome = new Label ("How Would You Like to Manage CD's / DVD's?");
        welcome.setFont(new Font("Arial", 24));
        welcome.setLayoutX(250);
        welcome.setLayoutY(150);

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
        removeCD.setOnAction(e -> removeCD(primaryStage));

        Button checkoutCD = new Button("Check Out CD");
        checkoutCD.setLayoutX(550);
        checkoutCD.setLayoutY(300);
        checkoutCD.setOnAction(e -> checkoutCD(primaryStage));

        Button returnCD = new Button("Return CD");
        returnCD.setLayoutX(750);
        returnCD.setLayoutY(300);
        returnCD.setOnAction(e -> returnCD(primaryStage));
 
        cdPane.getChildren().addAll(backButton, addCD, removeCD, checkoutCD, returnCD, welcome);
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
        
                if (lib.items.findItem(title) == null) {
                    lib.items.addItem(Library.ItemType.BOOK, title).setQuantity(quantity);
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Book Information");
                    infoAlert.setHeaderText("New Book Added:");
                    infoAlert.setContentText("Title: " + title + "\n" + "Author: " + author + "\n" + "Page Count: " + pageCount + "\n" + "Quantity: " + quantity + "\n" + "Publish Date: " + publishDate + "\n" + "Publisher: " + publisher);
                infoAlert.showAndWait();    
                } else {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("Book Already Exists");
                    aAlert.setHeaderText("The Book Title You Inputted Already Exists");
                    aAlert.showAndWait();
                }
                
            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });

        Scene scene = new Scene(addBookGrid, 400, 300);
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

                if (lib.items.findItem(title) == null) {
                    lib.items.addItem(Library.ItemType.CD, title).setQuantity(quantity);
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("CD / DVD Information");
                    infoAlert.setHeaderText("New CD / DVD Added:");
                    infoAlert.setContentText(
                    "Title: " + title + "\n" +
                    "Author: " + author + "\n" +
                    "Time Count: " + timeCount + " minutes" +"\n" +
                    "Quantity: " + quantity + "\n" +
                    "Publish Date: " + publishDate + "\n" +
                    "Product Company: " + productionCompany
                );
                infoAlert.showAndWait();
                } else {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("CD / DVD Already Exists");
                    aAlert.setHeaderText("The CD / DVD Title You Inputted Already Exists");
                    aAlert.showAndWait();
                }
                

            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });

        Scene scene = new Scene(addCDGrid, 400, 300);
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

        Label nameLabel = new Label ("Name (First and Last) : ");
        TextField nameField = new TextField();

        Label ageLabel = new Label ("Birth Year: ");
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

                if (lib.users.findUser(name) == null) {
                    int id = lib.users.addUser(name, age).id;
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("User Information");
                    infoAlert.setHeaderText("New User Added:");
                    infoAlert.setContentText("Name: " + name + "\n" + "Birth Year: " + age + "\n" + "ID: " + id + "\n");
                    infoAlert.showAndWait();
                } else {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("User Already Exists");
                    aAlert.setHeaderText("The Users Name You Inputted Already Exists");
                    aAlert.showAndWait();
                }

                
            

            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });

        Scene scene = new Scene(addUserGrid, 400, 200);
        addUserStage.setScene(scene);
        addUserStage.show();
    }

    private void removeUser(Stage primaryStage) {
        Stage removeUserStage = new Stage();
        removeUserStage.setTitle("Remove User Info");
        GridPane removeUserGrid = new GridPane();

        removeUserGrid.setVgap(20);
        removeUserGrid.setHgap(10);
        removeUserGrid.setPadding(new javafx.geometry.Insets(10));

        Label nameLabel = new Label ("Name (First and Last) : ");
        TextField nameField = new TextField();
        removeUserGrid.add(nameLabel, 0, 0);
        removeUserGrid.add(nameField, 1, 0);

        Button finishButton = new Button ("Finish");
        removeUserGrid.add(finishButton, 1, 1);
        finishButton.setOnAction (e -> {
            try {
                removeUserStage.close();
                String name = nameField.getText();

                if (lib.users.findUser(name) != null) {
                    lib.users.removeUser(lib.users.findUser(name).id);
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Successful");
                    infoAlert.setHeaderText("You Successfully Removed the User: " + name);
                    infoAlert.showAndWait();
                } else {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("User Doesn't Exist");
                    aAlert.setHeaderText("The User You Inputted Does Not Exist, and Thus Cannot Be Deleted.");
                    aAlert.showAndWait();
                }

            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
        });
        

        Scene scene = new Scene(removeUserGrid, 400, 100);
        removeUserStage.setScene(scene);
        removeUserStage.show();
    }

    private void removeBook(Stage primaryStage) {
        Stage removeBookStage = new Stage();
        removeBookStage.setTitle("Remove Book Info");
        GridPane removeBookGrid = new GridPane();

        removeBookGrid.setVgap(20);
        removeBookGrid.setHgap(10);
        removeBookGrid.setPadding(new javafx.geometry.Insets(10));

        Label nameLabel = new Label ("Title : ");
        TextField nameField = new TextField();
        removeBookGrid.add(nameLabel, 0, 0);
        removeBookGrid.add(nameField, 1, 0);

        Button finishButton = new Button ("Finish");
        removeBookGrid.add(finishButton, 1, 1);
        finishButton.setOnAction (e -> {
            try {
                removeBookStage.close();
                String title = nameField.getText();
                if (lib.items.findItem(title) != null) {
                    lib.items.removeItem(lib.items.findItem(title).id);
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Successful");
                    infoAlert.setHeaderText("You Successfully Removed the Item: " + title);
                    infoAlert.showAndWait();
                } else {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("Item Doesn't Exist");
                    aAlert.setHeaderText("The Item Title You Inputted Does Not Exist, and Thus Cannot Be Deleted.");
                    aAlert.showAndWait();
                }
            
            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
            });

        Scene scene = new Scene(removeBookGrid, 400, 100);
        removeBookStage.setScene(scene);
        removeBookStage.show();
    }

    private void removeCD(Stage primaryStage) {
        Stage removeCDStage = new Stage();
        removeCDStage.setTitle("Remove CD Info");
        GridPane removeCDGrid = new GridPane();

        removeCDGrid.setVgap(20);
        removeCDGrid.setHgap(10);
        removeCDGrid.setPadding(new javafx.geometry.Insets(10));

        Label nameLabel = new Label ("Title : ");
        TextField nameField = new TextField();
        removeCDGrid.add(nameLabel, 0, 0);
        removeCDGrid.add(nameField, 1, 0);

        Button finishButton = new Button ("Finish");
        removeCDGrid.add(finishButton, 1, 1);
        finishButton.setOnAction (e -> {
            try {
                removeCDStage.close();
                String title = nameField.getText();
                if (lib.items.findItem(title) != null) {
                    lib.items.removeItem(lib.items.findItem(title).id);
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Successful");
                    infoAlert.setHeaderText("You Successfully Removed the Item: " + title);
                    infoAlert.showAndWait();
                } else {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("Item Doesn't Exist");
                    aAlert.setHeaderText("The Item Title You Inputted Does Not Exist, and Thus Cannot Be Deleted.");
                    aAlert.showAndWait();
                }
            
            } catch (Exception ex) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setHeaderText("Number Format Error");
                errorAlert.setContentText("You have entered some information improperly.");
                errorAlert.showAndWait();
            } 
            });

        Scene scene = new Scene(removeCDGrid, 400, 100);
        removeCDStage.setScene(scene);
        removeCDStage.show();
    }

    private void userCheckout(Stage primaryStage) {
        Stage userCheckoutStage = new Stage();
        userCheckoutStage.setTitle("User Information: ");
        GridPane userCheckoutGrid = new GridPane();

        userCheckoutGrid.setVgap(20);
        userCheckoutGrid.setHgap(10);
        userCheckoutGrid.setPadding(new javafx.geometry.Insets(10));

        Label nameLabel = new Label ("Name (First and Last) : ");
        TextField nameField = new TextField();
        userCheckoutGrid.add(nameLabel, 0, 0);
        userCheckoutGrid.add(nameField, 1, 0);

        Button finishButton = new Button ("Finish");
        userCheckoutGrid.add(finishButton, 1, 1);
        finishButton.setOnAction(e -> {
            userCheckoutStage.close();
            String name = nameField.getText();

            if (lib.users.findUser(name) == null) {
                Alert aAlert = new Alert(Alert.AlertType.ERROR);
                aAlert.setTitle("User Doesn't Exist");
                aAlert.setHeaderText("The Users Name You Inputted Does Not Currently Exist");
                aAlert.showAndWait();
            } else {
                if (lib.checkouts.findUserCheckouts(lib.users.findUser(name)) == null) {
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Current Checkouts");
                    infoAlert.setHeaderText("No User Checkouts: ");
                    infoAlert.setContentText("The User You Inputted Currently Has No Checked Out Books or CD's.");
                    infoAlert.showAndWait();
                } else {
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Current Checkouts");
                    infoAlert.setHeaderText("Displaying " + name + "'s Checkouts: ");
                    
                    ArrayList<Library.Checkout> checkouts = lib.checkouts.findUserCheckouts(lib.users.findUser(name));
                    System.out.println(lib.users.findUser(name).name);
                    System.out.println(lib.checkouts.findCheckout(lib.users.findUser(name), lib.items.findItem("book")).item.title);
                    String content = "";
                    if (checkouts.size() == 0) {
                        content = "No checkouts could be found";
                    } else {
                        for (Library.Checkout check : checkouts) {
                            content += check.item.title + "\n";
                        }
                    }
                    infoAlert.setContentText(content); // fix this
                    infoAlert.showAndWait();
                }
            }
            
        });

        Scene scene = new Scene(userCheckoutGrid, 400, 100);
        userCheckoutStage.setScene(scene);
        userCheckoutStage.show();
    }

    private void checkoutBook(Stage primaryStage) {
        Stage checkoutBookStage = new Stage();
        checkoutBookStage.setTitle("Checkout Information: ");
        GridPane checkoutBookGrid = new GridPane();

        checkoutBookGrid.setVgap(20);
        checkoutBookGrid.setHgap(10);
        checkoutBookGrid.setPadding(new javafx.geometry.Insets(10));

        Label titleLabel = new Label ("Title : ");
        TextField titleField = new TextField();
        checkoutBookGrid.add(titleLabel, 0, 0);
        checkoutBookGrid.add(titleField, 1, 0);

        Label nameLabel = new Label ("Name of User: ");
        TextField nameField = new TextField();
        checkoutBookGrid.add(nameLabel, 0, 1);
        checkoutBookGrid.add(nameField, 1, 1);

        Button finishButton = new Button ("Finish");
        checkoutBookGrid.add(finishButton, 1, 2);
        finishButton.setOnAction(e -> {
            checkoutBookStage.close();
            String name = nameField.getText();
            String title = titleField.getText();

            if (lib.users.findUser(name) == null || lib.items.findItem(title) == null) {
                Alert aAlert = new Alert(Alert.AlertType.ERROR);
                aAlert.setTitle("User / Title Doesn't Exist");
                aAlert.setHeaderText("The User or Book Title You Inputted Doesn't Exist. Please Ensure Your Entering Proper Information.");
                aAlert.showAndWait();
            } else {
                try {
                    lib.checkouts.checkoutItem(lib.users.findUser(name), lib.items.findItem(title));
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Book Checkout");
                    infoAlert.setHeaderText(name + " Checked Out " + title);
                    infoAlert.setContentText("The User: " + name + " successfully checked out a book: " + title + ".");
                    infoAlert.showAndWait();
                } catch (Exception ex) {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("Failed To Checkout");
                    aAlert.setHeaderText("Error: The User Was Unable To Checkout Book.");
                    aAlert.showAndWait();
                }
            }
        });

        Scene scene = new Scene(checkoutBookGrid, 400, 200);
        checkoutBookStage.setScene(scene);
        checkoutBookStage.show();
    }

    private void checkoutCD(Stage primaryStage) {
        Stage checkoutCDStage = new Stage();
        checkoutCDStage.setTitle("Checkout Information: ");
        GridPane checkoutCDGrid = new GridPane();

        checkoutCDGrid.setVgap(20);
        checkoutCDGrid.setHgap(10);
        checkoutCDGrid.setPadding(new javafx.geometry.Insets(10));

        Label titleLabel = new Label ("Title : ");
        TextField titleField = new TextField();
        checkoutCDGrid.add(titleLabel, 0, 0);
        checkoutCDGrid.add(titleField, 1, 0);

        Label nameLabel = new Label ("Name of User: ");
        TextField nameField = new TextField();
        checkoutCDGrid.add(nameLabel, 0, 1);
        checkoutCDGrid.add(nameField, 1, 1);

        Button finishButton = new Button ("Finish");
        checkoutCDGrid.add(finishButton, 1, 2);
        finishButton.setOnAction(e -> {
            checkoutCDStage.close();
            String name = nameField.getText();
            String title = titleField.getText();

            if (lib.users.findUser(name) == null || lib.items.findItem(title) == null) {
                Alert aAlert = new Alert(Alert.AlertType.ERROR);
                aAlert.setTitle("User / Title Doesn't Exist");
                aAlert.setHeaderText("The User or CD Title You Inputted Doesn't Exist. Please Ensure Your Entering Proper Information.");
                aAlert.showAndWait();
            } else {
                try {
                    lib.checkouts.checkoutItem(lib.users.findUser(name), lib.items.findItem(title));
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("CD Checkout");
                    infoAlert.setHeaderText(name + " Checked Out " + title);
                    infoAlert.setContentText("The User: " + name + " successfully checked out a CD: " + title + ".");
                    infoAlert.showAndWait();
                } catch (Exception ex) {
                    Alert aAlert = new Alert(Alert.AlertType.ERROR);
                    aAlert.setTitle("Failed To Checkout");
                    aAlert.setHeaderText("Error: The User Was Unable To Checkout CD.");
                    aAlert.showAndWait();
                }
            }
        });
        

        Scene scene = new Scene(checkoutCDGrid, 400, 200);
        checkoutCDStage.setScene(scene);
        checkoutCDStage.show();
    }

    private void returnBook(Stage primaryStage) {
        Stage returnBookStage = new Stage();
        returnBookStage.setTitle("Return Information: ");
        GridPane returnBookGrid = new GridPane();

        returnBookGrid.setVgap(20);
        returnBookGrid.setHgap(10);
        returnBookGrid.setPadding(new javafx.geometry.Insets(10));

        Label titleLabel = new Label ("Title : ");
        TextField titleField = new TextField();
        returnBookGrid.add(titleLabel, 0, 0);
        returnBookGrid.add(titleField, 1, 0);

        Label nameLabel = new Label ("Name of User: ");
        TextField nameField = new TextField();
        returnBookGrid.add(nameLabel, 0, 1);
        returnBookGrid.add(nameField, 1, 1);

        Button finishButton = new Button ("Finish");
        returnBookGrid.add(finishButton, 1, 2);

        Scene scene = new Scene(returnBookGrid, 400, 200);
        returnBookStage.setScene(scene);
        returnBookStage.show();
    }

    private void returnCD(Stage primaryStage) {
        Stage returnCDStage = new Stage();
        returnCDStage.setTitle("Return Information: ");
        GridPane returnCDGrid = new GridPane();

        returnCDGrid.setVgap(20);
        returnCDGrid.setHgap(10);
        returnCDGrid.setPadding(new javafx.geometry.Insets(10));

        Label titleLabel = new Label ("Title : ");
        TextField titleField = new TextField();
        returnCDGrid.add(titleLabel, 0, 0);
        returnCDGrid.add(titleField, 1, 0);

        Label nameLabel = new Label ("Name of User: ");
        TextField nameField = new TextField();
        returnCDGrid.add(nameLabel, 0, 1);
        returnCDGrid.add(nameField, 1, 1);

        Button finishButton = new Button ("Finish");
        returnCDGrid.add(finishButton, 1, 2);

        Scene scene = new Scene(returnCDGrid, 400, 200);
        returnCDStage.setScene(scene);
        returnCDStage.show();
    }
}
