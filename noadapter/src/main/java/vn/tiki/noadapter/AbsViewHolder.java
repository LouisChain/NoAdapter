package vn.tiki.noadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class AbsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private Object item;
  private OnItemClickListener onItemClickListener;

  public AbsViewHolder(View view) {
    super(view);
  }

  public void bind(Object item) {
    this.item = item;
  }

  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  @Override
  public void onClick(View view) {
    final int position = getAdapterPosition();
    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
      onItemClickListener.onItemClick(view, item, position);
    }
  }
}
