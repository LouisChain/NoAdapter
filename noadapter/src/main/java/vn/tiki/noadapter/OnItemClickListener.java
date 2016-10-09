package vn.tiki.noadapter;

import android.view.View;

/**
 * Created by Giang Nguyen on 9/24/16.
 */

public interface OnItemClickListener {
  /**
   * Callback that will be called when item clicked.
   *
   * @param view     view that was clicked.
   * @param item     data of item
   * @param position position of the item
   */
  void onItemClick(View view, Object item, int position);
}
