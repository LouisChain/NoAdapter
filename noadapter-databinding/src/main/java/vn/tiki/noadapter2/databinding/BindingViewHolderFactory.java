package vn.tiki.noadapter2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import vn.tiki.noadapter2.AbsViewHolder;
import vn.tiki.noadapter2.ViewHolderFactory;

/**
 * Created by Giang Nguyen on 1/13/17.
 */

class BindingViewHolderFactory implements ViewHolderFactory {

  private final ExtraBinding extraBinding;

  BindingViewHolderFactory(@Nullable ExtraBinding extraBinding) {
    this.extraBinding = extraBinding;
  }

  @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final ViewDataBinding binding = DataBindingUtil.inflate(inflater, type, parent, false);
    return new OnlyViewHolder(binding, extraBinding);
  }
}
