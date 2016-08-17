package vn.tale.noadapter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Giang Nguyen on 8/17/16.
 */
public class CustomViewGroup extends LinearLayout {

  private static final String TAG = "CustomViewGroup";

  public CustomViewGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    final CharSequence name = ((TextView) getChildAt(0)).getText();
    Log.d(TAG, "onDetachedFromWindow: " + name);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    final CharSequence name = ((TextView) getChildAt(0)).getText();
    Log.d(TAG, "onAttachedToWindow: " + name);
  }
}
