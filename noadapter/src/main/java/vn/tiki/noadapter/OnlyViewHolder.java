package vn.tiki.noadapter;

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

  OnlyViewHolder(ViewDataBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  void bind(Object item) {
    binding.setVariable(vn.tale.noadapter.BR.item, item);
    this.item = item;
  }

  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
    if (onItemClickListener == null) {
      return;
    }
    binding.setVariable(vn.tale.noadapter.BR.onClick, this);
  }

  @Override
  public void onClick(View view) {
    if (onItemClickListener != null) {
      onItemClickListener.onItemClick(view, item, getAdapterPosition());
    }
  }
}
