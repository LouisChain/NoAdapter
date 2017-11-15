package vn.tiki.noadapter2.databinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
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
    binding.executePendingBindings();
    final int position = getAdapterPosition();
    if (extraBinding != null && position > RecyclerView.NO_POSITION) {
      extraBinding.onBind(binding, item, position);
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
