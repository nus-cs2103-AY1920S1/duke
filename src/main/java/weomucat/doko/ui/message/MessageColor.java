package weomucat.doko.ui.message;

import javafx.scene.paint.Color;

public class MessageColor {
  private String hexadecimal;
  private Color color;

  public MessageColor(String hexadecimal) {
    this.hexadecimal = hexadecimal;
    this.color = Color.valueOf(hexadecimal);
  }

  public Color toGui() {
    return color;
  }

  @Override
  public String toString() {
    return this.hexadecimal;
  }
}
