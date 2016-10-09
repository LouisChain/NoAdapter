package vn.tiki.noadapter2.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.tiki.noadapter2.sample.custom_listener.CustomListenerFragment;
import vn.tiki.noadapter2.sample.singlechoice.SingleChoiceFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void showSingleChoice(View view) {
    PlaceHolderActivity.start(this, SingleChoiceFragment.class);
  }

  public void showCustomListener(View view) {
    PlaceHolderActivity.start(this, CustomListenerFragment.class);
  }
}
