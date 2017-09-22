package vn.tiki.noadapter2.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import vn.tiki.noadapter2.AbsViewHolder;
import vn.tiki.noadapter2.DiffCallback;
import vn.tiki.noadapter2.OnItemClickListener;
import vn.tiki.noadapter2.OnlyAdapter;
import vn.tiki.noadapter2.TypeFactory;
import vn.tiki.noadapter2.ViewHolderFactory;
import vn.tiki.noadapter2.sample.entity.Color;
import vn.tiki.noadapter2.sample.viewholder.ColorViewHolder;
import vn.tiki.noadapter2.sample.viewholder.TextViewHolder;

public class MainActivity extends AppCompatActivity {
  private static final Random RANDOM = new Random();
  private OnlyAdapter adapter;
  private List<Object> items;

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
    findViewById(R.id.btAdd100).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        add100Items();
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

    adapter = OnlyAdapter.builder()
        .typeFactory(new TypeFactory() {
          @Override public int typeOf(Object item) {
            return item instanceof Color ? 1 : 0;
          }
        })
        .viewHolderFactory(new ViewHolderFactory() {
          @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
            switch (type) {
              case 1:
                return ColorViewHolder.create(parent);
              default:
                return TextViewHolder.create(parent);
            }
          }
        })
        .onItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, Object item, int position) {
            Toast
                .makeText(MainActivity.this, "Clicked on item: " + item, Toast.LENGTH_SHORT)
                .show();
          }
        })
        .diffCallback(new DiffCallback() {
          @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
            if (oldItem instanceof Color) {
              return newItem instanceof Color
                  && ((Color) oldItem).getId() == ((Color) newItem).getId();
            } else {
              return oldItem.equals(newItem);
            }
          }

          @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
            return oldItem.equals(newItem);
          }
        })
        .build();
    rvList.setAdapter(adapter);

    items = generateItems(0, 4);
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
    items.add(randomItem(items.size()));
    adapter.setItems(items);
  }

  private void add100Items() {
    items.addAll(generateItems(items.size(), 100));
    adapter.setItems(items);
  }

  public List<Object> generateItems(int startIndex, int size) {
    final ArrayList<Object> colors = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      final Object item;
      item = randomItem(i + startIndex);
      colors.add(item);
    }
    return colors;
  }

  @NonNull private Object randomItem(int i) {
    Object item;
    if (i % 3 == 0) {
      item = "Text #" + i;
    } else {
      item = new Color(i, randomColor());
    }
    return item;
  }

  private int randomColor() {
    int r = RANDOM.nextInt(256);
    int g = RANDOM.nextInt(256);
    int b = RANDOM.nextInt(256);

    return android.graphics.Color.rgb(r, g, b);
  }
}
