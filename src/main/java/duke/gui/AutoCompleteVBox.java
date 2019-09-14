package duke.gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * VBox container for auto completion options.
 */
public class AutoCompleteVBox extends VBox {
    private LinkedList<AutoCompleteNode> autoCompleteNodeLinkedList = new LinkedList<>();
    private HashMap<String, AutoCompleteNode> displayValAutoCompleteNodeHashMap = new HashMap<>();

    private AutoCompleteNode selectedNode = null;
    private int selectedIndex = -1;
    private Label selectedLabel = null;

    /**
     * Initializes the autocomplete vbox and its options. Begins expanded.
     */
    public AutoCompleteVBox() {
        super();
        initAutoCompleteOptions();
        updateList("");
        toggleVisibilityByActiveness();
    }

    private void initAutoCompleteOptions() {
        //Note the space behind some of the actual values
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("list", "list"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("help", "help"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("clear", "clear"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("done ", "done {index}"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("delete ", "delete {index}"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("todo ", "todo {description}"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("event ", "event {description} /at {date}"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("deadline ", "deadline {description} /by {date}"));
        autoCompleteNodeLinkedList.add(new AutoCompleteNode("bye", "bye"));

        for (AutoCompleteNode node : autoCompleteNodeLinkedList) {
            displayValAutoCompleteNodeHashMap.put(node.getDisplayValue(), node);
        }
    }

    /**
     * Update list of auto complete options based on cli text.
     *
     * @param text cli text
     */
    public void updateList(String text) {
        //Inefficient but sufficient way
        this.getChildren().clear();
        for (AutoCompleteNode node : autoCompleteNodeLinkedList) {
            //We want to autocorrect it to the actual value while preventing it from blocking functionality
            if (node.getActualValue().startsWith(text) && !node.getActualValue().equals(text)) {
                addListItem(node);
            }
        }
        toggleVisibilityByActiveness();
        updateSelectedNodeAndIndex();
    }

    /**
     * Automatically sort list based on frequency of use and return string to autocomplete.
     * Also hides auto complete.
     *
     * @return String to autocomplete.
     */
    public String requestAutoCompleteResult() {
        if (selectedIndex >= 0 && selectedIndex < this.getChildren().size()) {
            AutoCompleteNode node = getNodeFromChildrenIndex(selectedIndex);
            autoCompleteNodeLinkedList.remove(node);
            autoCompleteNodeLinkedList.addFirst(node);
            return node.getActualValue();
        }
        return null;
    }

    /**
     * Return if auto completion box is active.
     *
     * @return boolean isActive
     */
    public boolean isActive() {
        return this.getChildren().size() > 0;
    }

    public void upButtonPressed() {
        int newIndex = (selectedIndex - 1 + this.getChildren().size()) % this.getChildren().size();
        changeSelectedIndex(newIndex);
    }

    public void downButtonPressed() {
        int newIndex = (selectedIndex + 1) % this.getChildren().size();
        changeSelectedIndex(newIndex);
    }

    private void updateSelectedNodeAndIndex() {
        //If no node selected but there are children
        if (this.getChildren().size() > 0) {
            if (selectedNode == null) {
                changeSelectedIndex(0);
            } else {
                int nodeIndex = getIndexOfNodeInChildren(selectedNode);
                if (nodeIndex == -1) {
                    changeSelectedIndex(0);
                } else {
                    changeSelectedIndex(nodeIndex);
                }
            }
        }
    }

    private AutoCompleteNode getNodeFromChildrenIndex(int index) {
        String textSelected = ((Label) this.getChildren().get(index)).getText();
        return displayValAutoCompleteNodeHashMap.get(textSelected);
    }

    /**
     * Find index of AutoCompleteNode in children; -1 if not found.
     *
     * @param node node to search for
     * @return index
     */
    private int getIndexOfNodeInChildren(AutoCompleteNode node) {
        if (node != null) {
            List<Node> children = this.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Label label = (Label) children.get(i);
                if (label.getText().equals(node.getDisplayValue())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Add list item to VBox children based on cli text.
     *
     * @param node auto complete node that is to be added
     */
    private void addListItem(AutoCompleteNode node) {
        Label label = new Label(node.getDisplayValue());
        this.getChildren().add(label);
    }

    /**
     * Show if active, hide if inactive.
     */
    private void toggleVisibilityByActiveness() {
        this.setVisible(isActive());
    }

    /**
     * The currently selected index will be highlighted. This changes the highlighted label.
     *
     * @param newIndex new index to highlight
     */
    private void changeSelectedIndex(int newIndex) {
        if (newIndex >= 0 && newIndex < this.getChildren().size()) {
            //If can find selected node, then selected label still exists, thus we need to clear it
            if (selectedLabel != null) {
                selectedLabel.setStyle("-fx-text-fill: black");
            }

            //Apply new changes
            selectedIndex = newIndex;
            selectedNode = getNodeFromChildrenIndex(newIndex);
            selectedLabel = (Label) this.getChildren().get(selectedIndex);
            selectedLabel.setStyle("-fx-text-fill: red");
        }
    }
}
