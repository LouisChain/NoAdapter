package vn.tale.simplyrecyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

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
        .renderer(new ItemViewMapping() {
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
        })
        .build();
//    rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//    final RecyclerViewAdapter adapter = new RecyclerViewAdapter(new Renderer() {
//      @Override public int layoutForViewType(int type) {
//        switch (type) {
//          case 1:
//            return R.layout.item_header;
//          default:
//            return R.layout.item_name;
//        }
//      }
//
//      @Override public int viewTypeForItem(Object item) {
//        if (item instanceof Header) {
//          return 1;
//        }
//        return 2;
//      }
//    });
//    rvList.setAdapter(adapter);
    adapter.setItems(Arrays.asList(
        new Header("Header 1"),
        "Item 1",
        "Item 2",
        "Item 3",
        new Header("Header 2"),
        "Item 5",
        "Item 6",
        "Item 7"
    ));
  }
}
