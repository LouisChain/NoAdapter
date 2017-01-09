package vn.tiki.noadapter2;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Giang Nguyen on 12/15/16.
 */

class DiffUtilCallback extends DiffUtil.Callback {

  private final DiffCallback diffCallback;
  private List<?> items;
  private List<?> newItems;

  DiffUtilCallback(@NonNull DiffCallback diffCallback) {
    this.diffCallback = diffCallback;
  }

  void setItems(List<?> items) {
    this.items = items;
  }

  void setNewItems(List<?> newItems) {
    this.newItems = newItems;
  }

  @Override public int getOldListSize() {
    return items == null ? 0 : items.size();
  }

  @Override public int getNewListSize() {
    return newItems == null ? 0 : newItems.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    final Object oldItem = items.get(oldItemPosition);
    final Object newItem = newItems.get(newItemPosition);
    return diffCallback.areItemsTheSame(oldItem, newItem);
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    final Object oldItem = items.get(oldItemPosition);
    final Object newItem = newItems.get(newItemPosition);
    return diffCallback.areContentsTheSame(oldItem, newItem);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DiffUtilCallback that = (DiffUtilCallback) o;

    return diffCallback.equals(that.diffCallback);

  }

  @Override public int hashCode() {
    return diffCallback.hashCode();
  }
}
