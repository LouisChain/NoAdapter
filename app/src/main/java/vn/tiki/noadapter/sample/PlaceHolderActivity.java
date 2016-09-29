package vn.tiki.noadapter.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Giang Nguyen on 9/28/16.
 */

public class PlaceHolderActivity extends AppCompatActivity {

  public static void start(Context context, Class<? extends Fragment> fragmentToOpen) {
    Intent starter = new Intent(context, PlaceHolderActivity.class);
    starter.putExtra("fragmentClass", fragmentToOpen);
    context.startActivity(starter);
  }

  @SuppressWarnings("unchecked")
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_placeholder);

    if (savedInstanceState == null) {
      final Class<? extends Fragment> fragmentClass = (Class<? extends Fragment>) getIntent().getSerializableExtra("fragmentClass");
      try {
        final Fragment fragment = fragmentClass.newInstance();
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }
}
