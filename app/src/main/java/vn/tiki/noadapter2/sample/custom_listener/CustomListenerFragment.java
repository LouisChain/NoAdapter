package vn.tiki.noadapter2.sample.custom_listener;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import vn.tiki.noadapter2.LayoutSelector;
import vn.tiki.noadapter2.OnlyAdapter;
import vn.tiki.noadapter2.sample.R;
import vn.tiki.noadapter2.sample.entity.ItemValueViewModel;

/**
 * Created by Giang Nguyen on 9/29/16.
 */

public class CustomListenerFragment extends Fragment {

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.list_fragment, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final RecyclerView rvList = (RecyclerView) view.findViewById(R.id.rvList);
    rvList.setLayoutManager(new LinearLayoutManager(getContext()));

    final OnlyAdapter adapter = new OnlyAdapter.Builder()
        .layoutSelector(new LayoutSelector() {
          @Override public int layoutForType(int type) {
            return R.layout.item_counter;
          }
        })
        .build();
    rvList.setAdapter(adapter);

    adapter.setItems(Arrays.asList(
        new ItemValueViewModel(),
        new ItemValueViewModel()
    ));
  }

}
