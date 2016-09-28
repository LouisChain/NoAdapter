package vn.tiki.noadapter.sample.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.tiki.noadapter.sample.R;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void showSingleChoice(View view) {
    PlaceHolderActivity.start(this);
  }
}
