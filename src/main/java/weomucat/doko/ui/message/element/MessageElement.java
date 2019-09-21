package weomucat.doko.ui.message.element;

import javafx.scene.Node;
import weomucat.doko.ui.gui.message.MessageBoxOptions;

public interface MessageElement {
  String toCli();

  Node toGui(MessageBoxOptions options);
}
