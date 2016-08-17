package vn.tale.noadapter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by Giang Nguyen on 8/17/16.
 */
public class CustomFrameLayout extends FrameLayout {

  private static final String TAG = "CustomFrameLayout";

  public CustomFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    Log.d(TAG, "onDetachedFromWindow: ");
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    Log.d(TAG, "onAttachedToWindow: ");
  }
}
