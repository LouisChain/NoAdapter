package vn.tale.simplyrecyclerview;

import android.support.annotation.LayoutRes;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public interface ItemViewMapping {

  @LayoutRes int layoutForViewType(int type);

  int viewTypeForItem(Object item);
}
