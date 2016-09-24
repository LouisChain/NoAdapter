package vn.tiki.noadapter.sample.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.tiki.noadapter.LayoutSelector;
import vn.tiki.noadapter.OnItemClickListener;
import vn.tiki.noadapter.OnlyAdapter;
import vn.tiki.noadapter.TypeDeterminer;
import vn.tiki.noadapter.sample.sample.model.Header;
import vn.tiki.noadapter.sample.sample.model.User;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  static List<Object> generate(int size) {
    final List<Object> result = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      if (i % 5 == 0) {
        result.add(new Header("Header " + i));
      } else if (i == 22) {
        result.add(new User("User " + i, "Link: " + i));
      } else {
        result.add("Item " + i);
      }
    }
    return result;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(vn.tiki.noadapter.sample.R.layout.activity_main);
    final RecyclerView rvList = (RecyclerView) this.findViewById(vn.tiki.noadapter.sample.R.id.rvList);
    rvList.setLayoutManager(new LinearLayoutManager(this));
    final OnlyAdapter adapter = new OnlyAdapter.Builder()
        .layoutSelector(new LayoutSelector() {
          @Override public int layoutForType(int type) {
            switch (type) {
              case 1:
                return vn.tiki.noadapter.sample.R.layout.item_header;
              case 2:
                return vn.tiki.noadapter.sample.R.layout.item_user;
              default:
                return vn.tiki.noadapter.sample.R.layout.item_name;
            }
          }
        })
        .typeDeterminer(new TypeDeterminer() {
          @Override public int typeOfItem(Object item) {
            if (item instanceof Header) {
              return 1;
            } else if (item instanceof User) {
              return 2;
            }
            return 0;
          }
        })
        .onItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, Object o, int position) {
            Log.d(TAG, "onClick: View: " + view + ", item: " + o);

          }
        })
        .build();

    rvList.setAdapter(adapter);

    adapter.setItems(generate(100));

  }
}
