package vn.tiki.noadapter2.databinding;

import vn.tiki.noadapter2.DiffCallback;
import vn.tiki.noadapter2.OnItemClickListener;
import vn.tiki.noadapter2.OnlyAdapter;

/**
 * Created by Giang Nguyen on 1/13/17.
 */

public class BindingBuilder {
  private OnlyAdapter.Builder builder = new OnlyAdapter.Builder();
  private LayoutFactory layoutFactory;
  private ExtraBinding extraBinding;

  public BindingBuilder layoutFactory(LayoutFactory layoutFactory) {
    this.layoutFactory = layoutFactory;
    return this;
  }

  public BindingBuilder extraBinding(ExtraBinding extraBinding) {
    this.extraBinding = extraBinding;
    return this;
  }

  public BindingBuilder onItemClickListener(OnItemClickListener onItemClickListener) {
    builder = builder.onItemClickListener(onItemClickListener);
    return this;
  }

  public BindingBuilder diffCallback(DiffCallback diffCallback) {
    builder = builder.diffCallback(diffCallback);
    return this;
  }

  public OnlyAdapter build() {
    if (layoutFactory == null) {
      throw new NullPointerException("layoutFactory must not be null");
    }

    return builder
        .typeFactory(new BindingTypeFactory(layoutFactory))
        .viewHolderFactory(new BindingViewHolderFactory(extraBinding))
        .build();
  }
}
