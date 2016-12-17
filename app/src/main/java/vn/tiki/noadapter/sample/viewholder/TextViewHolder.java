package vn.tiki.noadapter.sample.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.tiki.noadapter.AbsViewHolder;
import vn.tiki.noadapter.sample.R;

/**
 * Created by Giang Nguyen on 12/15/16.
 */

public class TextViewHolder extends AbsViewHolder {
  private final TextView text;

  private TextViewHolder(View view) {
    super(view);
    text = ((TextView) view.findViewById(R.id.text));
    view.setOnClickListener(this);
  }

  public static TextViewHolder create(ViewGroup parent) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_text, parent, false);
    return new TextViewHolder(view);
  }

  @Override public void bind(Object item) {
    super.bind(item);
    text.setText((String) item);
  }
}
