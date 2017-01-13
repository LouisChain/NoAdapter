package vn.tiki.noadapter2;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Giang Nguyen on 12/17/16.
 */
public class BuilderTest {
  private OnlyAdapter.Builder builder;

  @Before
  public void setUp() throws Exception {
    builder = new OnlyAdapter.Builder();
  }

  @Test
  public void shouldVerifyViewHolderFactory() throws Exception {
    try {
      builder.build();
    } catch (Exception e) {
      assertTrue(e instanceof NullPointerException);
      assertEquals("Null viewHolderFactory", e.getMessage());
    }
  }

  @Test
  public void shouldBuildAdapterWithDefaultDiffCallback() throws Exception {
    final OnlyAdapter adapter = builder.viewHolderFactory(new ViewHolderFactory() {
      @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return new AbsViewHolder(parent);
      }
    }).build();
    assertEquals(new DiffUtilCallback(new DefaultDiffCallback()), adapter.diffUtilCallback);
  }

  @Test
  public void shouldBuildAdapterWithDefaultTypeDeterminer() throws Exception {
    final ViewHolderFactory viewHolderFactory = new ViewHolderFactory() {
      @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return new AbsViewHolder(parent);
      }
    };
    final DiffCallback diffCallback = new DiffCallback() {
      @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
        return false;
      }

      @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
        return false;
      }
    };
    final OnlyAdapter adapter = builder
        .viewHolderFactory(viewHolderFactory)
        .diffCallback(diffCallback)
        .build();

    assertEquals(new DefaultTypeFactory(), adapter.typeFactory);
  }

  @Test
  public void shouldBuildAdapter() throws Exception {
    final ViewHolderFactory viewHolderFactory = new ViewHolderFactory() {
      @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return new AbsViewHolder(parent);
      }
    };
    final DiffCallback diffCallback = new DiffCallback() {
      @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
        return false;
      }

      @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
        return false;
      }
    };
    final TypeFactory typeFactory = new TypeFactory() {
      @Override public int typeOf(Object item) {
        return 0;
      }
    };
    final OnItemClickListener onItemClickListener = new OnItemClickListener() {
      @Override public void onItemClick(View view, Object item, int position) {

      }
    };
    final OnlyAdapter adapter = builder
        .typeFactory(typeFactory)
        .viewHolderFactory(viewHolderFactory)
        .diffCallback(diffCallback)
        .onItemClickListener(onItemClickListener)
        .build();

    assertEquals(typeFactory, adapter.typeFactory);
    assertEquals(viewHolderFactory, adapter.viewHolderFactory);
    assertEquals(new DiffUtilCallback(diffCallback), adapter.diffUtilCallback);
    assertEquals(onItemClickListener, adapter.onItemClickListener);
  }

}