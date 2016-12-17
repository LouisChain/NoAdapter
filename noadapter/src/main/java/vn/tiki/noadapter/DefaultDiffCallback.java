package vn.tiki.noadapter;

/**
 * Default implementation of {@link DiffCallback}. This used {@link Object#equals(Object)} to check.
 */
class DefaultDiffCallback implements DiffCallback {
  @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
    return oldItem.equals(newItem);
  }

  @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
    return oldItem.equals(newItem);
  }

  @Override public boolean equals(Object o) {
    return this == o || !(o == null || getClass() != o.getClass());
  }

  @Override public int hashCode() {
    return 0;
  }
}
