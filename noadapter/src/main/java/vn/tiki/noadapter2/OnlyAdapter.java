package vn.tiki.noadapter2;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Giang Nguyen on 8/14/16.
 */
public class OnlyAdapter extends RecyclerView.Adapter<AbsViewHolder> {
  @VisibleForTesting final TypeFactory typeFactory;
  @VisibleForTesting final ViewHolderFactory viewHolderFactory;
  @VisibleForTesting final DiffCallback diffCallback;
  @VisibleForTesting OnItemClickListener onItemClickListener;
  private List<?> items = Collections.emptyList();
  private int dataVersion;
  private AsyncTask<Void, Void, DiffUtil.DiffResult> updateTask;

  private OnlyAdapter(
      @NonNull TypeFactory typeFactory,
      @NonNull ViewHolderFactory viewHolderFactory,
      @NonNull DiffCallback diffCallback) {
    this.typeFactory = typeFactory;
    this.viewHolderFactory = viewHolderFactory;
    this.diffCallback = diffCallback;
  }

  public static Builder builder() {
    return new Builder();
  }

  @NonNull
  private static DiffUtil.DiffResult calculateDiff(
      List<?> oldItems,
      List<?> update,
      DiffCallback diffCallback) {
    return DiffUtil.calculateDiff(new DiffUtilCallback(oldItems, update, diffCallback));
  }

  private void setOnItemClickListener(@NonNull OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public void setItems(final List<?> newItems) {
    cancelUpdateTask();
    dataVersion++;
    if (items == null) {
      if (newItems == null) {
        return;
      }
      items = newItems;
      notifyDataSetChanged();
    } else if (newItems == null) {
      int oldSize = items.size();
      items = null;
      notifyItemRangeRemoved(0, oldSize);
    } else {
      final List<?> oldItems = items;
      int maxItemCount = newItems.size() > oldItems.size() ? newItems.size() : oldItems.size();
      if (maxItemCount < 50) {
        // we don't need to calculate in background for less than 100 items.
        final DiffUtil.DiffResult diffResult = calculateDiff(oldItems, newItems, diffCallback);
        postUpdate(newItems, diffResult);
      } else {
        updateTask = new UpdateTask(oldItems, newItems, diffCallback, this).execute();
      }
    }
  }

  private void cancelUpdateTask() {
    if (updateTask != null) {
      updateTask.cancel(true);
    }
  }

  private void postUpdate(List<?> newItems, DiffUtil.DiffResult diffResult) {
    items = new ArrayList<>(newItems);
    diffResult.dispatchUpdatesTo(OnlyAdapter.this);
  }

  @Override
  public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return viewHolderFactory.viewHolderForType(parent, viewType);
  }

  @Override
  public void onBindViewHolder(AbsViewHolder holder, int position) {
    final Object item = items.get(position);
    holder.bind(item);
    holder.setOnItemClickListener(onItemClickListener);
  }

  @Override
  public int getItemViewType(int position) {
    final Object item = items.get(position);
    return typeFactory.typeOf(item);
  }

  @Override
  public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override public void onViewRecycled(AbsViewHolder holder) {
    super.onViewRecycled(holder);
    holder.unbind();
  }

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
    cancelUpdateTask();
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

  private static class UpdateTask extends AsyncTask<Void, Void, DiffUtil.DiffResult> {
    private final List<?> oldItems;
    private final List<?> newItems;
    private final DiffCallback diffCallback;
    private final WeakReference<OnlyAdapter> adapterRef;
    private final int dataVersion;

    UpdateTask(
        List<?> oldItems,
        List<?> newItems,
        DiffCallback diffCallback,
        OnlyAdapter adapter) {
      this.oldItems = oldItems;
      this.newItems = newItems;
      this.diffCallback = diffCallback;
      this.adapterRef = new WeakReference<>(adapter);
      dataVersion = adapter.dataVersion;
    }

    @Override protected DiffUtil.DiffResult doInBackground(Void... params) {
      return calculateDiff(oldItems, newItems, diffCallback);
    }

    @Override
    protected void onPostExecute(DiffUtil.DiffResult diffResult) {
      final OnlyAdapter onlyAdapter = adapterRef.get();
      if (onlyAdapter == null || onlyAdapter.dataVersion != dataVersion) {
        return;
      }
      onlyAdapter.postUpdate(newItems, diffResult);
    }
  }
}
