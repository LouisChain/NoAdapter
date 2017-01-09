package vn.tiki.noadapter2;

/**
 * Created by Giang Nguyen on 12/17/16.
 */

class DefaultTypeDeterminer implements TypeDeterminer {
  @Override public int typeOf(Object item) {
    return 0;
  }

  @Override public boolean equals(Object o) {
    return this == o || !(o == null || getClass() != o.getClass());
  }

  @Override public int hashCode() {
    return 0;
  }
}
