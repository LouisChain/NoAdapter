package vn.tiki.noadapter2.sample.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import vn.tiki.noadapter2.AbsViewHolder;
import vn.tiki.noadapter2.sample.R;
import vn.tiki.noadapter2.sample.entity.Color;

/**
 * Created by Giang Nguyen on 12/15/16.
 */

public class ColorViewHolder extends AbsViewHolder {

  private ColorViewHolder(View view) {
    super(view);
    // Set "this" to clickListener then it'll be delegated to Adapter
    view.setOnClickListener(this);
  }

  public static ColorViewHolder create(ViewGroup parent) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_color, parent, false);
    return new ColorViewHolder(view);
  }

  @Override public void bind(Object item) {
    super.bind(item);
    // Set up display for data
    itemView.setBackgroundColor(((Color) item).getValue());
  }
}
