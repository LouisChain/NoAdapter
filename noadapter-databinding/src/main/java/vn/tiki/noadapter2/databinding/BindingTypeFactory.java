package vn.tiki.noadapter2.databinding;

import android.support.annotation.NonNull;
import vn.tiki.noadapter2.TypeFactory;

/**
 * Created by Giang Nguyen on 1/13/17.
 */

class BindingTypeFactory implements TypeFactory {

  private final LayoutFactory layoutFactory;

  BindingTypeFactory(@NonNull LayoutFactory layoutFactory) {
    this.layoutFactory = layoutFactory;
  }

  @Override public int typeOf(Object item) {
    return layoutFactory.layoutOf(item);
  }
}
