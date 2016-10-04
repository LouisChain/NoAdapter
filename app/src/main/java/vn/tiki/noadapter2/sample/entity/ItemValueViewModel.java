package vn.tiki.noadapter2.sample.entity;

import android.databinding.ObservableInt;

/**
 * Created by Giang Nguyen on 9/29/16.
 */

public class ItemValueViewModel {

  private final ObservableInt value = new ObservableInt(0);

  public ObservableInt getValue() {
    return value;
  }

  public void increase() {
    final int newValue = value.get() + 1;
    value.set(newValue);
  }

  public void decrease() {
    final int newValue = value.get() - 1;
    value.set(newValue);
  }
}
