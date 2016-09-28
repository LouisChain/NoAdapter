package vn.tiki.noadapter.sample.singlechoice;

import java.util.List;

import ix.Ix;
import ix.IxFunction;
import vn.tiki.noadapter.sample.entity.Option;

/**
 * Created by Giang Nguyen on 9/28/16.
 */

class SingleChoiceViewModel {

  private final Ix<Option> optionIx;

  SingleChoiceViewModel(List<Option> options) {
    optionIx = Ix.from(options)
        .distinct();
  }

  List<Option> selectPosition(int position) {
    final Option selectedOption = optionIx.toList().get(position);
    return selectItem(selectedOption);

  }

  List<Option> selectItem(final Option item) {
    return optionIx.map(new IxFunction<Option, Option>() {
      @Override public Option apply(Option option) {
        final String text = option.getText();
        return new Option(text, option.equals(item));
      }
    }).toList();
  }
}
