# KotlinKnifer

[![GitHub release](https://img.shields.io/github/release/pokk/KotlinKnifer.svg?style=flat-square)](https://github.com/pokk/KotlinKnifer)
[![license](https://img.shields.io/github/license/pokk/KotlinKnifer.svg?style=flat-square)](https://github.com/pokk/KotlinKnifer)
![Gson](https://img.shields.io/badge/Gson-2.8.2-green.svg?style=flat-square)
![RxJava](https://img.shields.io/badge/RxJava-2.1.6-green.svg?style=flat-square)
![RxKotlin](https://img.shields.io/badge/RxKotlin-2.1.0-green.svg?style=flat-square)

The library has util tools as below:

1. ArrayList
2. Bitmap
3. Color
4. Delegate
5. Drawable
6. Fragment
7. Glide
8. Keyboard
9. Listener
10. Log
11. Observable of RxJava
12. Observer of RxJava
13. RecyclerView.ItemDecorator
14. RxMvvm
15. String
16. Thread
17. Time
18. View

# Download

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
implementation 'com.devrapid.jieyi:kotlinknifer:1.1.2'
```

Then you can use it!!!

## Maven

```maven
<dependency>
  <groupId>com.devrapid.jieyi</groupId>
  <artifactId>kotlinknifer</artifactId>
  <version>1.1.2</version>
  <type>pom</type>
</dependency>
```

# Also Included Library

1. Gson 2.8.2
2. RxJava 2.1.6
3. RxKotlin 2.1.0
4. Glide 4.0.0

If you'd not like to use them to your project, you can add the exclude as like below

```
implementation('com.devrapid.jieyi:kotlinknifer:1.1.2', {
  exclude group: 'io.reactivex.rxjava2', module: 'rxjava'
  exclude group: 'io.reactivex.rxjava2', module: 'rxkotlin'
  exclude group: 'com.google.code.gson', module: 'gson'
  exclude group: 'com.github.bumptech.glide', module: 'glide'
})
```

Then you're able to add the version of a library what you need.

# Feature

I will add some useful tool modules for everyone and myself. If you have any
ideas or problems. Please let me know, thank you!

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
