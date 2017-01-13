package vn.tiki.noadapter2.databinding;

import android.support.annotation.LayoutRes;

/**
 * Created by Giang Nguyen on 9/24/16.
 */
public interface LayoutFactory {

  /**
   * Select layout for item
   *
   * @param item them item
   * @return {@link LayoutRes} id of layout
   */
  @LayoutRes int layoutOf(Object item);
}
