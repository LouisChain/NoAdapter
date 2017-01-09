package vn.tiki.noadapter2.sample.entity;

/**
 * Created by Giang Nguyen on 12/15/16.
 */

public class Color {
  private final int id;
  private final int value;

  public Color(int id, int value) {
    this.id = id;
    this.value = value;
  }

  public int getId() {
    return id;
  }

  public int getValue() {
    return value;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Color color = (Color) o;

    if (getId() != color.getId()) return false;
    return getValue() == color.getValue();

  }

  @Override public int hashCode() {
    int result = getId();
    result = 31 * result + getValue();
    return result;
  }

  @Override public String toString() {
    return "Color{"
        + "id="
        + id
        + '}';
  }
}
