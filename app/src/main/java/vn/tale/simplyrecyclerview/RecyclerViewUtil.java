package vn.tale.simplyrecyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Giang Nguyen on 8/15/16.
 */
public final class RecyclerViewUtil {
  private final RecyclerView recyclerView;
  private RecyclerView.LayoutManager layoutManager;
  private ItemViewMapping itemViewMapping;

  private RecyclerViewUtil(RecyclerView recyclerView) {
    //no instance
    this.recyclerView = recyclerView;
  }

  public static RecyclerViewUtil with(RecyclerView recyclerView) {
    return new RecyclerViewUtil(recyclerView);
  }

  public RecyclerViewUtil layoutManager(RecyclerView.LayoutManager layoutManager) {
    this.layoutManager = layoutManager;
    return this;
  }

  public RecyclerViewUtil renderer(ItemViewMapping itemViewMapping) {
    this.itemViewMapping = itemViewMapping;
    return this;
  }

  public Adapter build() {
    recyclerView.setLayoutManager(layoutManager);
    final RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemViewMapping);
    recyclerView.setAdapter(adapter);
    return adapter;
  }
}
