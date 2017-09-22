package vn.tiki.noadapter2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * Created by Giang Nguyen on 12/15/16.
 */

class DiffUtilCallback extends DiffUtil.Callback {

  private final List<?> oldItems;
  private final List<?> newItems;
  private final DiffCallback diffCallback;

  DiffUtilCallback(List<?> oldItems, List<?> newItems, @NonNull DiffCallback diffCallback) {
    this.oldItems = oldItems;
    this.newItems = newItems;
    this.diffCallback = diffCallback;
  }

  @Override public int getOldListSize() {
    return oldItems == null ? 0 : oldItems.size();
  }

  @Override public int getNewListSize() {
    return newItems == null ? 0 : newItems.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    final Object oldItem = getOldItem(oldItemPosition);
    final Object newItem = getNewItem(newItemPosition);
    if (oldItem == null) {
      return newItem == null;
    } else {
      return newItem != null && diffCallback.areItemsTheSame(oldItem, newItem);
    }
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    final Object oldItem = getOldItem(oldItemPosition);
    final Object newItem = getNewItem(newItemPosition);
    if (oldItem == null) {
      return newItem == null;
    } else {
      return newItem != null && diffCallback.areContentsTheSame(oldItem, newItem);
    }
  }

  @Nullable private Object getNewItem(int newItemPosition) {
    return newItemPosition >= newItems.size() ? null : newItems.get(newItemPosition);
  }

  @Nullable private Object getOldItem(int oldItemPosition) {
    return oldItemPosition >= oldItems.size() ? null : oldItems.get(oldItemPosition);
  }

  @Override public int hashCode() {
    return diffCallback.hashCode();
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DiffUtilCallback that = (DiffUtilCallback) o;

    return diffCallback.equals(that.diffCallback);
  }
}
