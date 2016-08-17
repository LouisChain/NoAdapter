package vn.tale.simplyrecyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private final ViewDataBinding binding;
  private ItemViewMapping itemViewMapping;
  private Object item;

  public SimpleViewHolder(ViewDataBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(ItemViewMapping itemViewMapping, Object item) {
    this.itemViewMapping = itemViewMapping;
    this.item = item;
    binding.setVariable(vn.tale.simplyrecyclerview.BR.item, item);
    binding.setVariable(vn.tale.simplyrecyclerview.BR.onClickListener, this);
  }

  @Override public void onClick(View view) {
    itemViewMapping.onClick(view, item);
  }
}
