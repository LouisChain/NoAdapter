package vn.tiki.noadapter.sample.custom_listener;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adaptercommands.AdapterCommandProcessor;
import com.hannesdorfmann.adaptercommands.ItemChangedDetector;
import com.hannesdorfmann.adaptercommands.command.AdapterCommand;
import com.hannesdorfmann.adaptercommands.command.DiffCommandsCalculator;

import java.util.Arrays;
import java.util.List;

import vn.tiki.noadapter.LayoutSelector;
import vn.tiki.noadapter.OnlyAdapter;
import vn.tiki.noadapter.sample.R;
import vn.tiki.noadapter.sample.entity.ItemValueViewModel;
import vn.tiki.noadapter.sample.entity.Option;

/**
 * Created by Giang Nguyen on 9/29/16.
 */

public class CustomListenerFragment extends Fragment {

  private static final String TAG = "CustomListenerFragment";

  private OnlyAdapter adapter;
  private AdapterCommandProcessor commandProcessor;
  private final DiffCommandsCalculator<Option> diffCommandsCalculator = new DiffCommandsCalculator<>(new ItemChangedDetector<Option>() {
    @Override public boolean hasChanged(Option oldItem, Option newItem) {
      return oldItem.isSelected() != newItem.isSelected();
    }
  });

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.list_fragment, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final RecyclerView rvList = (RecyclerView) view.findViewById(R.id.rvList);
    rvList.setLayoutManager(new LinearLayoutManager(getContext()));

    adapter = new OnlyAdapter.Builder()
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

  private void updateOptions(List<Option> options) {
    adapter.setItems(options);
    if (options == null) {
      return;
    }
    final List<AdapterCommand> commands = diffCommandsCalculator.diff(options);
    if (commands == null) {
      return;
    }
    for (AdapterCommand command : commands) {
      Log.d(TAG, "updateOptions: " + command);
    }
    commandProcessor.execute(commands);
  }
}
