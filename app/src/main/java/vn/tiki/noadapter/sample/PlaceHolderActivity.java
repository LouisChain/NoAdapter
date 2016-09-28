package vn.tiki.noadapter.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.tiki.noadapter.sample.singlechoice.SingleChoiceFragment;

/**
 * Created by Giang Nguyen on 9/28/16.
 */

public class PlaceHolderActivity extends AppCompatActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, PlaceHolderActivity.class);
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_placeholder);

    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .add(R.id.fragmentContainer, new SingleChoiceFragment())
          .commit();
    }
  }
}
