package weomucat.duke.ui.message.element;

import javafx.scene.Node;
import weomucat.duke.ui.gui.message.MessageBoxOptions;

public interface MessageElement {
  String toCli();

  Node toGui(MessageBoxOptions options);
}
