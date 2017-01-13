package vn.tiki.noadapter2;

import android.view.ViewGroup;

/**
 * Created by Giang Nguyen on 9/24/16.
 */
public interface ViewHolderFactory {

  /**
   * Create viewHolder for view type
   *
   * @param parent parent view
   * @param type view type
   * @return {@link AbsViewHolder} object
   */
  AbsViewHolder viewHolderForType(ViewGroup parent, int type);
}
