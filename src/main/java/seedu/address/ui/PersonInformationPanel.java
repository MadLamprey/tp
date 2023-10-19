package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusTypes;

/**
 * An UI component that displays information of a {@code Person}
 */
public class PersonInformationPanel extends UiPart<Region> {
    private static final String FXML = "PersonInformationPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final Person person;

    @FXML
    private Label name;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private ToggleButton preliminary;
    @FXML
    private ToggleButton interviewed;
    @FXML
    private ToggleButton status;
    @FXML
    private Label linkedIn;
    @FXML
    private Label github;


    /**
     * Creates a {@code PersonInformationPanel} with the given {@code Person}.
     * @param person the person to display
     */
    public PersonInformationPanel(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        remark.setText(person.getRemark().value);
        person.getTags().stream()
                .sorted(java.util.Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        linkedIn.setText(person.getLinkedIn().value);
        github.setText(person.getGithub().value);
        setResultButton(person.getStatus(), status);
        setButton(person.getStatus());
    }


    private static void setResultButton(Status status, ToggleButton statusButton) {
        StatusTypes statusType = status.getStatusType();
        if (statusType == StatusTypes.OFFERED) {
            statusButton.setText("Offered");
        } else if (statusType == StatusTypes.REJECTED) {
            statusButton.setText("Rejected");
        } else {
            statusButton.setText("Pending");
        }
    }

    private void setButton(Status currentStatus) {
        StatusTypes statusType = currentStatus.getStatusType();
        if (statusType == StatusTypes.OFFERED) {
            status.getStyleClass().clear();
            status.getStyleClass().add("offered-button");

            preliminary.getStyleClass().clear();;
            preliminary.getStyleClass().add("offered-button");

            interviewed.getStyleClass().clear();
            interviewed.getStyleClass().add("offered-button");
        } else if (statusType == StatusTypes.REJECTED) {
            status.getStyleClass().clear();
            status.getStyleClass().add("rejected-button");
        } else if (statusType == StatusTypes.INTERVIEWED) {
            preliminary.getStyleClass().clear();;
            preliminary.getStyleClass().add("offered-button");

            interviewed.getStyleClass().clear();
            interviewed.getStyleClass().add("offered-button");
        } else {
            preliminary.getStyleClass().clear();;
            preliminary.getStyleClass().add("offered-button");

        }
    }






}
