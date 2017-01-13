package vn.tiki.noadapter2.databinding;

import android.databinding.ViewDataBinding;

import vn.tiki.noadapter2.AbsViewHolder;
import vn.tiki.noadapter2.OnItemClickListener;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
class OnlyViewHolder extends AbsViewHolder {

  private final ViewDataBinding binding;
  private ExtraBinding extraBinding;

  OnlyViewHolder(ViewDataBinding binding, ExtraBinding extraBinding) {
    super(binding.getRoot());
    this.binding = binding;
    this.extraBinding = extraBinding;
  }

  @Override
  public void bind(Object item) {
    super.bind(item);
    binding.setVariable(vn.tiki.noadapter2.databinding.BR.item, item);
    if (extraBinding != null) {
      extraBinding.onBind(binding, item, getAdapterPosition());
    }
  }

  @Override
  protected void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    super.setOnItemClickListener(onItemClickListener);
    if (onItemClickListener == null) {
      return;
    }
    binding.setVariable(vn.tiki.noadapter2.databinding.BR.onClick, this);
  }
}
