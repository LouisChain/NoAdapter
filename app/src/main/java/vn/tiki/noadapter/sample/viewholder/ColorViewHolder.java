package vn.tiki.noadapter.sample.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.tiki.noadapter.AbsViewHolder;
import vn.tiki.noadapter.sample.R;
import vn.tiki.noadapter.sample.entity.Color;

/**
 * Created by Giang Nguyen on 12/15/16.
 */

public class ColorViewHolder extends AbsViewHolder {
  private ColorViewHolder(View view) {
    super(view);
    view.setOnClickListener(this);
  }

  public static ColorViewHolder create(ViewGroup parent) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_color, parent, false);
    return new ColorViewHolder(view);
  }

  @Override public void bind(Object item) {
    super.bind(item);
    itemView.setBackgroundColor(((Color) item).getValue());
  }
}
