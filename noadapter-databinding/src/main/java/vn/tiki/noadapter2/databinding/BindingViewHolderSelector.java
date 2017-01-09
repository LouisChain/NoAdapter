package vn.tiki.noadapter2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import vn.tiki.noadapter2.AbsViewHolder;
import vn.tiki.noadapter2.ViewHolderSelector;

/**
 * Created by Giang Nguyen on 12/17/16.
 */

public class BindingViewHolderSelector implements ViewHolderSelector {

  private LayoutSelector layoutSelector;
  private ExtraBinding extraBinding;

  private BindingViewHolderSelector(LayoutSelector layoutSelector, ExtraBinding extraBinding) {
    this.layoutSelector = layoutSelector;
    this.extraBinding = extraBinding;
  }

  @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final int layoutId = layoutSelector.layoutForType(type);
    final ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
    return new OnlyViewHolder(binding, extraBinding);
  }

  public static class Builder {

    private LayoutSelector layoutSelector;
    private ExtraBinding extraBinding;

    public Builder layoutSelector(LayoutSelector layoutSelector) {
      this.layoutSelector = layoutSelector;
      return this;
    }

    public Builder extraBinding(ExtraBinding extraBinding) {
      this.extraBinding = extraBinding;
      return this;
    }

    public BindingViewHolderSelector build() {
      return new BindingViewHolderSelector(layoutSelector, extraBinding);
    }
  }
}
