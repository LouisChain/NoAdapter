package vn.tiki.noadapter2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Giang Nguyen on 12/17/16.
 */
public class DiffUtilCallbackTest {

  @Mock DiffCallback diffCallback;
  private DiffUtilCallback diffUtilCallback;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    diffUtilCallback = new DiffUtilCallback(diffCallback);
  }

  @Test
  public void shouldReturnOldSize() throws Exception {
    diffUtilCallback.setItems(Arrays.asList("1", "2", "3"));
    assertEquals(3, diffUtilCallback.getOldListSize());
  }

  @Test
  public void shouldReturnNewSize() throws Exception {
    diffUtilCallback.setNewItems(Arrays.asList("1", "2"));
    assertEquals(2, diffUtilCallback.getNewListSize());
  }

  @Test
  public void shouldDelegateAreItemsTheSame() throws Exception {
    diffUtilCallback.setItems(Arrays.asList(
        "1",
        "2",
        "3"
    ));
    diffUtilCallback.setNewItems(Arrays.asList(
        "1",
        "2",
        "4"
    ));
    diffUtilCallback.areItemsTheSame(2, 2);
    Mockito.verify(diffCallback).areItemsTheSame(Mockito.eq("3"), Mockito.eq("4"));
  }

  @Test
  public void shouldDelegateAreContentsTheSame() throws Exception {
    diffUtilCallback.setItems(Arrays.asList(
        "1",
        "2",
        "3"
    ));
    diffUtilCallback.setNewItems(Arrays.asList(
        "1",
        "2",
        "4"
    ));
    diffUtilCallback.areContentsTheSame(2, 2);
    Mockito.verify(diffCallback).areContentsTheSame(Mockito.eq("3"), Mockito.eq("4"));
  }
}