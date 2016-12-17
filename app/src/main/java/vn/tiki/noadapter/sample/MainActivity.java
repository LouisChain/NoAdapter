package vn.tiki.noadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import vn.tiki.noadapter.AbsViewHolder;
import vn.tiki.noadapter.DiffCallback;
import vn.tiki.noadapter.OnItemClickListener;
import vn.tiki.noadapter.OnlyAdapter;
import vn.tiki.noadapter.ViewHolderSelector;
import vn.tiki.noadapter.sample.entity.Color;
import vn.tiki.noadapter.sample.viewholder.ColorViewHolder;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  private static final Random RANDOM = new Random();
  private OnlyAdapter adapter;
  private List<Color> items;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final RecyclerView rvList = (RecyclerView) this.findViewById(R.id.rvList);

    findViewById(R.id.btAdd).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        addItem();
      }
    });
    findViewById(R.id.btClear).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        clearItems();
      }
    });
    findViewById(R.id.btChange).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        shuffleItems();
      }
    });
    rvList.setLayoutManager(new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false));
    rvList.setHasFixedSize(true);

    adapter = new OnlyAdapter.Builder()
        .viewHolderSelector(new ViewHolderSelector() {
          @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
            return ColorViewHolder.create(parent);
          }
        })
        .onItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, Object item, int position) {
            Toast.makeText(MainActivity.this, "Clicked on item" + item, Toast.LENGTH_SHORT).show();
          }
        })
        .diffCallback(new DiffCallback() {
          @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
            return ((Color) oldItem).getId() == ((Color) newItem).getId();
          }

          @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
            return oldItem.equals(newItem);
          }
        })
        .build();
    rvList.setAdapter(adapter);

    items = generateItems(4);
    adapter.setItems(items);
  }

  private void shuffleItems() {
    Collections.shuffle(items);
    adapter.setItems(items);
  }

  private void clearItems() {
    items.clear();
    adapter.setItems(items);
  }

  private void addItem() {
    items.add(new Color(items.size() + 1, randomColor()));
    adapter.setItems(items);
  }

  public List<Color> generateItems(int size) {
    final ArrayList<Color> colors = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      colors.add(new Color(i, randomColor()));
    }
    return colors;
  }

  private int randomColor() {
    int r = RANDOM.nextInt(256);
    int g = RANDOM.nextInt(256);
    int b = RANDOM.nextInt(256);

    return android.graphics.Color.rgb(r, g, b);
  }
}
