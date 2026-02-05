package iris;

import iris.components.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Iris iris;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/earth-profile-photo.jpg"));
    private Image irisImage = new Image(this.getClass().getResourceAsStream("/images/moon-profile-photo.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Iris instance.
     * @param iris Iris to be injected.
     */
    public void setIris(Iris iris) {
        this.iris = iris;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Iris's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = iris.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getIrisDialog(response, irisImage)
        );
        userInput.clear();
    }
}
