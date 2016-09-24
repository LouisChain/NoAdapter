package vn.tiki.noadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class OnlyAdapter extends RecyclerView.Adapter<OnlyViewHolder> {
  private final TypeDeterminer typeDeterminer;
  private final LayoutSelector layoutSelector;
  private List<?> items;
  private ViewHolderCallback viewHolderCallback;
  private OnItemClickListener onItemClickListener;

  public OnlyAdapter(@NonNull TypeDeterminer typeDeterminer, @NonNull LayoutSelector layoutSelector) {
    this.typeDeterminer = typeDeterminer;
    this.layoutSelector = layoutSelector;
  }

  void setOnItemClickListener(@NonNull OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  void setViewHolderCallback(@NonNull ViewHolderCallback viewHolderCallback) {
    this.viewHolderCallback = viewHolderCallback;
  }

  public void setItems(List<?> items) {
    this.items = items;
  }

  @Override
  public OnlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final int layoutId = layoutSelector.layoutForType(viewType);
    final ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
    return new OnlyViewHolder(binding);
  }

  @Override
  public int getItemViewType(int position) {
    final Object item = items.get(position);
    return typeDeterminer.typeOfItem(item);
  }

  @Override
  public void onBindViewHolder(OnlyViewHolder holder, int position) {
    final Object item = items.get(position);
    holder.bind(item);
    holder.setOnItemClickListener(onItemClickListener);

    if (viewHolderCallback != null) {
      viewHolderCallback.onBind(position, item);
    }
  }

  @Override
  public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  public static class Builder {

    private LayoutSelector layoutSelector;
    private TypeDeterminer typeDeterminer;
    private OnItemClickListener onItemClickListener;

    public Builder typeDeterminer(TypeDeterminer typeDeterminer) {
      this.typeDeterminer = typeDeterminer;
      return this;
    }

    public Builder layoutSelector(LayoutSelector layoutSelector) {
      this.layoutSelector = layoutSelector;
      return this;
    }

    public Builder onItemClickListener(OnItemClickListener onItemClickListener) {
      this.onItemClickListener = onItemClickListener;
      return this;
    }

    public OnlyAdapter build() {
      final OnlyAdapter adapter = new OnlyAdapter(typeDeterminer, layoutSelector);
      if (onItemClickListener != null) {
        adapter.setOnItemClickListener(onItemClickListener);
      }
      return adapter;
    }

  }

}
