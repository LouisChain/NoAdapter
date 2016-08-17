package vn.tale.simplyrecyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.tale.simplyrecyclerview.Adapter;
import vn.tale.simplyrecyclerview.ItemViewMapping;
import vn.tale.simplyrecyclerview.R;
import vn.tale.simplyrecyclerview.RecyclerViewUtil;
import vn.tale.simplyrecyclerview.model.Header;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final RecyclerView rvList = (RecyclerView) this.findViewById(R.id.rvList);
    final Adapter adapter = RecyclerViewUtil.with(rvList)
        .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        .mapping(new ItemViewMapping() {
          @Override public int layoutForViewType(int viewType) {
            switch (viewType) {
              case 1:
                return R.layout.item_header;
              default:
                return R.layout.item_name;
            }
          }

          @Override public int viewTypeForItem(Object item) {
            if (item instanceof Header) {
              return 1;
            }
            return 2;
          }

          @Override public void onClick(View view, Object item) {
            Log.d(TAG, "onClick: View: " + view + ", item: " + item);
          }
        })
        .build();
    adapter.setItems(generate(100));

  }

  static List<Object> generate(int size) {
    final List<Object> result = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      if (i % 5 == 0) {
        result.add(new Header("Header " + i));
      } else {
        result.add("Item " + i);
      }
    }
    return result;
  }
}
