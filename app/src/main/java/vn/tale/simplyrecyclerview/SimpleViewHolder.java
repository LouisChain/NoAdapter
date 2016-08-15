package vn.tale.simplyrecyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {

  private final ViewDataBinding binding;

  public SimpleViewHolder(ViewDataBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void setItem(Object item) {
    binding.setVariable(vn.tale.simplyrecyclerview.BR.item, item);
  }
}
