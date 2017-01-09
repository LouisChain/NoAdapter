package vn.tiki.noadapter2;

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

  /**
   * Called to update view
   * @param item data
   */
  public void bind(Object item) {
    this.item = item;
  }

  /**
   * Called when a view created by adapter has been recycled. This may be
   * a good place to release expensive data or resources.
   */
  public void unbind() {
    // NoOp
  }

  protected void setOnItemClickListener(OnItemClickListener onItemClickListener) {
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
