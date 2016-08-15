package vn.tale.simplyrecyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<SimpleViewHolder> implements Adapter {
  private List<?> items;
  private final ItemViewMapping itemViewMapping;

  public RecyclerViewAdapter(ItemViewMapping itemViewMapping) {
    this.itemViewMapping = itemViewMapping;
  }

  @Override
  public void setItems(List<?> items) {
    this.items = items;
  }

  @Override public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final int layoutId = itemViewMapping.layoutForViewType(viewType);
    final ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
    return new SimpleViewHolder(binding);
  }

  @Override public int getItemViewType(int position) {
    final Object item = items.get(position);
    return itemViewMapping.viewTypeForItem(item);
  }

  @Override public void onBindViewHolder(SimpleViewHolder holder, int position) {
    final Object item = items.get(position);
    holder.setItem(item);
  }

  @Override public int getItemCount() {
    return items == null ? 0 : items.size();
  }
}
