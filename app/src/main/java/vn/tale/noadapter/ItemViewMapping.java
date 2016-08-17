package vn.tale.noadapter;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public interface ItemViewMapping {

  @LayoutRes int layoutForViewType(int type);

  int viewTypeForItem(Object item);

  void onClick(View view, Object item);
}
