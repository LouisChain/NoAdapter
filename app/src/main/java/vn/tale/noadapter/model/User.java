package vn.tale.noadapter.model;

/**
 * Created by Giang Nguyen on 8/17/16.
 */
public class User {
  private final String name;
  private final String link;

  public User(String name, String link) {
    this.name = name;
    this.link = link;
  }

  public String getName() {
    return name;
  }

  public String getLink() {
    return link;
  }

  @Override public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", link='" + link + '\'' +
        '}';
  }
}
