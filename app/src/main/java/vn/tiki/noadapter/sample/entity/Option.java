package vn.tiki.noadapter.sample.entity;

/**
 * Created by Giang Nguyen on 9/28/16.
 */

public class Option {
  private final String text;
  private final boolean selected;

  public Option(String text, boolean selected) {
    this.text = text;
    this.selected = selected;
  }

  public String getText() {
    return text;
  }

  public boolean isSelected() {
    return selected;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Option option = (Option) o;

    return getText() != null ? getText().equals(option.getText()) : option.getText() == null;

  }

  @Override public int hashCode() {
    return getText() != null ? getText().hashCode() : 0;
  }
}
