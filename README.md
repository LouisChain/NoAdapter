# NoAdapter

[![Build Status](https://travis-ci.org/tikivn/NoAdapter.svg?branch=master)](https://travis-ci.org/tikivn/NoAdapter)
[![codecov](https://codecov.io/gh/tikivn/NoAdapter/branch/master/graph/badge.svg)](https://codecov.io/gh/tikivn/NoAdapter)

Too much boilerplate and effort to implement a list using RecyclerView. But, most of them can be omitted.

![](./favicon.png)

## Table of contents
- [Demo](#demo)
- [Single View Type](#single-view-type)
- [Multiple View Type](#multiple-view-type)
- [Use with Data Binding](#use-with-data-binding)
- [Download](#download)

## Demo


<p align="center">
<img alt="Sample app demo gif" src="https://github.com/tikivn/NoAdapter/blob/master/demo.gif" width="200" height="354" />
</p>


## Why NoAdapter
- You'll never have to implement Adapter for RecyclerView anymore.
- Never worry about `notifyChanged` anymore. We used DiffUtil to calculate and notify changes for you.

## Single View Type

#### 1. Implement ViewHolder

`item_text.xml`

~~~xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:layout_width="match_parent"
  android:layout_height="60dp"
  android:layout_margin="8dp"
  app:cardElevation="4dp">

  <TextView
    android:id="@+id/text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:textAppearance="@style/Base.TextAppearance.AppCompat.Body2"
  />
</android.support.v7.widget.CardView>
~~~

`TextViewHolder.java`

~~~java
public class TextViewHolder extends AbsViewHolder {
  private final TextView text;

  private TextViewHolder(View view) {
    super(view);
    text = ((TextView) view.findViewById(R.id.text));
    // Set "this" to clickListener then it'll be delegated to Adapter
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
~~~

#### 2. Setup Adapter

~~~java
adapter = new OnlyAdapter.Builder()
    .viewHolderFactory(new ViewHolderFactory() {
      @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        return TextViewHolder.create(parent);
      }
    })
    .onItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(View view, Object item, int position) {
        Toast
            .makeText(MainActivity.this, "Clicked on item: " + item, Toast.LENGTH_SHORT)
            .show();
      }
    })
    .build();
rvList.setAdapter(adapter);
~~~

#### 3. Bind data

~~~java
final List<String> items = Arrays.asList(
    "Text #1",
    "Text #2",
    "Text #3",
    "Text #4",
    "Text #5"
);
adapter.setItems(items);
~~~

## Multiple View Type
#### 1. ViewHolder

**1.1 Color item**

`item_color.xml`

~~~xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:layout_width="match_parent" android:layout_height="60dp"
  android:layout_margin="8dp"
  app:cardElevation="4dp"/>
~~~

`ColorViewHolder.java`

~~~java
public class ColorViewHolder extends AbsViewHolder {

  private ColorViewHolder(View view) {
    super(view);
    // Set "this" to clickListener then it'll delegate to Adapter
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
~~~

**1.2. Text item**

`item_text.xml`

~~~xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:layout_width="match_parent"
  android:layout_height="60dp"
  android:layout_margin="8dp"
  app:cardElevation="4dp">

  <TextView
    android:id="@+id/text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:textAppearance="@style/Base.TextAppearance.AppCompat.Body2"
  />
</android.support.v7.widget.CardView>
~~~

`TextViewHolder.java`

~~~java
public class TextViewHolder extends AbsViewHolder {
  private final TextView text;

  private TextViewHolder(View view) {
    super(view);
    text = ((TextView) view.findViewById(R.id.text));
    // Set "this" to clickListener then it'll be delegated to Adapter
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
~~~

#### 2. Setup Adapter

~~~java
adapter = new OnlyAdapter.Builder()
    .typeFactory(new TypeFactory() {
      @Override public int typeOf(Object item) {
        return item instanceof Color ? 1 : 0;
      }
    })
    .viewHolderFactory(new ViewHolderFactory() {
      @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
        switch (type) {
          case 1:
            return ColorViewHolder.create(parent);
          default:
            return TextViewHolder.create(parent);
        }
      }
    })
    .onItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(View view, Object item, int position) {
        Toast
            .makeText(MainActivity.this, "Clicked on item: " + item, Toast.LENGTH_SHORT)
            .show();
      }
    })
    .diffCallback(new DiffCallback() {
      @Override public boolean areItemsTheSame(Object oldItem, Object newItem) {
        if (oldItem instanceof Color) {
          return newItem instanceof Color
              && ((Color) oldItem).getId() == ((Color) newItem).getId();
        } else {
          return oldItem.equals(newItem);
        }
      }

      @Override public boolean areContentsTheSame(Object oldItem, Object newItem) {
        return oldItem.equals(newItem);
      }
    })
    .build();
rvList.setAdapter(adapter);
~~~

#### 3. Bind data

~~~java
final List<Object> items = Arrays.asList(
    new Color(1, randomColor()),
    new Color(2, randomColor()),
    "Text #3",
    new Color(4, randomColor()),
    "Text #5"
);
adapter.setItems(items);
~~~

## Use with Data Binding

#### 1. Implement Layout + Binding

`item_text.xml`

~~~xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
<data>
  <variable
    name="item"
    type="java.lang.String"/>
  <variable
    name="onClick"
    type="android.view.View.OnClickListener"/>
</data>
<android.support.v7.widget.CardView
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:layout_width="match_parent"
  android:layout_height="60dp"
  android:layout_margin="8dp"
  android:onClick="@{onClick}"
  app:cardElevation="4dp">

  <TextView
    android:id="@+id/text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:text="@{item}"
    android:textAppearance="@style/Base.TextAppearance.AppCompat.Body2"
  />
</android.support.v7.widget.CardView>
</layout>
~~~

**NOTE**:
- `item`, `onClick` are predefined
- `item` represented for data
- `onClick` represented for `onClickListener`

#### 2. Setup Adapter

~~~java
adapter = new BindingBuilder()
        .layoutFactory(new LayoutFactory() {
          @Override public int layoutOf(Object item) {
            return R.layout.item_text;
          }
        })
        .onItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, Object item, int position) {
            Toast
                .makeText(MainActivity.this, "Clicked on item: " + item, Toast.LENGTH_SHORT)
                .show();
          }
        })
        .build();
rvList.setAdapter(adapter);
~~~

#### 3. Bind data

~~~java
final List<String> items = Arrays.asList(
    "Text #1",
    "Text #2",
    "Text #3",
    "Text #4",
    "Text #5"
);
adapter.setItems(items);
~~~

**See more in the sample**

## Download

Download [the latest JAR][1] or grab via Gradle:

```groovy
compile 'vn.tiki.noadapter2:noadapter:2.0.1'
// Use with data binding
compile 'vn.tiki.noadapter2:noadapter-databinding:2.0.1'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].


## ProGuard

No specific

## License

    Copyright 2016 Tiki Corp

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://search.maven.org/remote_content?g=vn.tiki.noadapter&a=noadapter&v=LATEST
[snap]: https://oss.sonatype.org/content/repositories/snapshots/
