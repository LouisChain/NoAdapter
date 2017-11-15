package vn.tiki.noadapter2;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.view.View;
import android.view.ViewGroup;
import org.junit.*;

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
  public void shouldBuildAdapter() throws Exception {
    final ViewHolderFactory viewHolderFactory = new ViewHolderFactory() {
      @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return new AbsViewHolder(parent);
      }
    };
    final DiffCallback diffCallback = new DiffCallback() {
      @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
        return false;
      }

      @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
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
    assertEquals(diffCallback, adapter.diffCallback);
    assertEquals(onItemClickListener, adapter.onItemClickListener);
  }

  @Test
  public void shouldBuildAdapterWithDefaultDiffCallback() throws Exception {
    final OnlyAdapter adapter = builder.viewHolderFactory(new ViewHolderFactory() {
      @Override
      public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return new AbsViewHolder(parent);
      }
    }).build();
    assertTrue(adapter.diffCallback instanceof DefaultDiffCallback);
  }

  @Test
  public void shouldBuildAdapterWithDefaultTypeDeterminer() throws Exception {
    final ViewHolderFactory viewHolderFactory = new ViewHolderFactory() {
      @Override
      public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return new AbsViewHolder(parent);
      }
    };
    final DiffCallback diffCallback = new DiffCallback() {
      @Override
      public boolean areContentsTheSame(Object oldItem, Object newItem) {
        return false;
      }

      @Override
      public boolean areItemsTheSame(Object oldItem, Object newItem) {
        return false;
      }
    };
    final OnlyAdapter adapter = builder
        .viewHolderFactory(viewHolderFactory)
        .diffCallback(diffCallback)
        .build();

    assertTrue(adapter.typeFactory instanceof DefaultTypeFactory);
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

}