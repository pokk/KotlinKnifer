# KotlinKnifer

[![GitHub release](https://img.shields.io/github/release/pokk/KotlinKnifer.svg?style=flat-square)](https://github.com/pokk/KotlinKnifer)
[![license](https://img.shields.io/github/license/pokk/KotlinKnifer.svg?style=flat-square)](https://github.com/pokk/KotlinKnifer)
![Gson](https://img.shields.io/badge/Gson-2.8.2-green.svg?style=flat-square)
![RxJava](https://img.shields.io/badge/RxJava-2.1.8-green.svg?style=flat-square)
![RxKotlin](https://img.shields.io/badge/RxKotlin-2.2.0-green.svg?style=flat-square)

The library has util tools as below:

1. [ArrayList](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/ArrayList.kt)
2. [Bitmap](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Bitmap.kt)
3. [Color](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Color.kt)
4. [Delegate](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Delegate.kt)
5. [Drawable](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Drawable.kt)
6. [Fragment](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Fragment.kt)
7. [Glide](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Glide.kt)
8. [Keyboard](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Keyboard.kt)
9. [Listener](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Listener.kt)
10. [Log](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Logs.kt)
11. [Observable of RxJava](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Observable.kt)
12. [Observer of RxJava](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Observer.kt)
13. [RecyclerView.ItemDecorator](https://github.com/pokk/KotlinKnifer/tree/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/recyclerview/itemdecorator)
14. [RxMvvm](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/mvvm/RxOperation.kt)
15. [String](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/String.kt)
16. [Thread](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Thread.kt)
17. [Time](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/Time.kt)
18. [View](https://github.com/pokk/KotlinKnifer/blob/010e7457844c341fa62260e835724b4bf0090332/kotlinknifer/src/main/java/com/devrapid/kotlinknifer/View.kt)


# NOTE

### THE NEW VERSION.

I've separated a library to two difference libraries. One is the same **`kotlinkinfer`**, another is
**`kotlinshaver`**. the reason is when you have a pure kotlin/java module, this library couldn't be
imported into. That's why I should split this into two.

- `kotlinknifer` is for android library.
- `kotlinshaver` is for pure kotlin/java library.

After `kotlinknifer` v1.4.1 won't include the pure kotlin/java library. If you'd like to use both,
please import `kotlinshaver` together.

# Import To Your Project

## Gradle

First you have to make sure your project `bundle.gradle` as below:

```gradle
allprojects {
    repositories {
        jcenter()
    }
}
```

And add our dependency to your app `bundle.gradle`.

```gradle
implementation 'com.devrapid.jieyi:kotlinknifer:1.4.1'

implementation 'com.devrapid.jieyi:kotlinshaver:0.1.5'
```

Then you can use it!!!

## Maven

```maven
<dependency>
  <groupId>com.devrapid.jieyi</groupId>
  <artifactId>kotlinknifer</artifactId>
  <version>1.4.1</version>
  <type>pom</type>
</dependency>

<dependency>
  <groupId>com.devrapid.jieyi</groupId>
  <artifactId>kotlinshaver</artifactId>
  <version>0.1.5</version>
  <type>pom</type>
</dependency>
```

# Also Included Library

## kotlinknifer

1. Gson 2.8.2
2. Glide 4.0.0

## kotlinshaver

1. RxJava 2.1.8
2. RxKotlin 2.2.0

If you'd not like to use them to your project, you can add the exclude as like below

```gradle
implementation('com.devrapid.jieyi:kotlinknifer:1.4.1', {
  exclude group: 'com.google.code.gson', module: 'gson'
  exclude group: 'com.github.bumptech.glide', module: 'glide'
})
```

```gradle
implementation('com.devrapid.jieyi:kotlinshaverr:0.1.5', {
  exclude group: 'io.reactivex.rxjava2', module: 'rxjava'
  exclude group: 'io.reactivex.rxjava2', module: 'rxkotlin'
})
```

Then you're able to add the version of a library what you need.

# Feature

I will add some useful tool modules for everyone and myself. If you have any ideas or problems.
Please let me know, thank you!

# License

```
Copyright (C) 2017 Jieyi Wu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

