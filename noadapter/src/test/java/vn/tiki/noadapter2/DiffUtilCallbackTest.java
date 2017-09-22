package vn.tiki.noadapter2;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Giang Nguyen on 12/17/16.
 */
public class DiffUtilCallbackTest {

  @Mock private DiffCallback diffCallback;
  private DiffUtilCallback diffUtilCallback;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    List<String> oldItems = Arrays.asList(
        "1",
        "2",
        "3");
    List<String> newItems = Arrays.asList(
        "1",
        "2",
        "4",
        "5"
    );
    diffUtilCallback = new DiffUtilCallback(
        oldItems,
        newItems,
        diffCallback);
  }

  @Test
  public void shouldReturnOldSize() throws Exception {
    assertEquals(3, diffUtilCallback.getOldListSize());
  }

  @Test
  public void shouldReturnNewSize() throws Exception {
    assertEquals(4, diffUtilCallback.getNewListSize());
  }

  @Test
  public void shouldDelegateAreItemsTheSame() throws Exception {
    diffUtilCallback.areItemsTheSame(2, 2);
    Mockito.verify(diffCallback).areItemsTheSame(Mockito.eq("3"), Mockito.eq("4"));
  }

  @Test
  public void shouldDelegateAreContentsTheSame() throws Exception {
    diffUtilCallback.areContentsTheSame(2, 2);
    Mockito.verify(diffCallback).areContentsTheSame(Mockito.eq("3"), Mockito.eq("4"));
  }
}