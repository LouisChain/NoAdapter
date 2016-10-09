package vn.tiki.noadapter2;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
class OnlyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private final ViewDataBinding binding;
  private Object item;
  private OnItemClickListener onItemClickListener;
  private CustomBinding customBinding;

  OnlyViewHolder(ViewDataBinding binding, CustomBinding customBinding) {
    super(binding.getRoot());
    this.binding = binding;
    this.customBinding = customBinding;
  }

  void bind(Object item) {
    this.item = item;
    binding.setVariable(vn.tiki.noadapter2.BR.item, item);
    if (customBinding != null) {
      customBinding.onBind(binding, item, getAdapterPosition());
    }
  }

  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
    if (onItemClickListener == null) {
      return;
    }
    binding.setVariable(vn.tiki.noadapter2.BR.onClick, this);
  }

  @Override
  public void onClick(View view) {
    if (onItemClickListener != null) {
      onItemClickListener.onItemClick(view, item, getAdapterPosition());
    }
  }
}
