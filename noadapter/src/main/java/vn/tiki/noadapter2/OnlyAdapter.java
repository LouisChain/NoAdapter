package vn.tiki.noadapter2;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class OnlyAdapter extends RecyclerView.Adapter<AbsViewHolder> {
  final TypeFactory typeFactory;
  final ViewHolderFactory viewHolderFactory;
  final DiffUtilCallback diffUtilCallback;
  OnItemClickListener onItemClickListener;
  private List<?> items = Collections.emptyList();

  private OnlyAdapter(@NonNull TypeFactory typeFactory,
                      @NonNull ViewHolderFactory viewHolderFactory,
                      DiffCallback diffCallback) {
    this.typeFactory = typeFactory;
    this.viewHolderFactory = viewHolderFactory;
    this.diffUtilCallback = new DiffUtilCallback(diffCallback);
  }

  private void setOnItemClickListener(@NonNull OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public void setItems(final List<?> newItems) {
    diffUtilCallback.setItems(items);
    diffUtilCallback.setNewItems(newItems);

    final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

    items.clear();
    items = new ArrayList<>(newItems);

    diffResult.dispatchUpdatesTo(this);
  }

  @Override
  public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return viewHolderFactory.viewHolderForType(parent, viewType);
  }

  @Override
  public int getItemViewType(int position) {
    final Object item = items.get(position);
    return typeFactory.typeOf(item);
  }

  @Override
  public void onBindViewHolder(AbsViewHolder holder, int position) {
    final Object item = items.get(position);
    holder.bind(item);
    holder.setOnItemClickListener(onItemClickListener);
  }

  @Override public void onViewRecycled(AbsViewHolder holder) {
    super.onViewRecycled(holder);
    holder.unbind();
  }

  @Override
  public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private ViewHolderFactory viewHolderFactory;
    private TypeFactory typeFactory;
    private OnItemClickListener onItemClickListener;
    private DiffCallback diffCallback;


    public Builder typeFactory(TypeFactory typeFactory) {
      this.typeFactory = typeFactory;
      return this;
    }

    public Builder viewHolderFactory(ViewHolderFactory viewHolderFactory) {
      this.viewHolderFactory = viewHolderFactory;
      return this;
    }

    public Builder onItemClickListener(OnItemClickListener onItemClickListener) {
      this.onItemClickListener = onItemClickListener;
      return this;
    }

    public Builder diffCallback(DiffCallback diffCallback) {
      this.diffCallback = diffCallback;
      return this;
    }

    public OnlyAdapter build() {
      if (viewHolderFactory == null) {
        throw new NullPointerException("Null viewHolderFactory");
      }
      if (typeFactory == null) {
        typeFactory = new DefaultTypeFactory();
      }
      if (diffCallback == null) {
        diffCallback = new DefaultDiffCallback();
      }
      final OnlyAdapter adapter = new OnlyAdapter(typeFactory, viewHolderFactory, diffCallback);
      if (onItemClickListener != null) {
        adapter.setOnItemClickListener(onItemClickListener);
      }
      return adapter;
    }

  }

}
