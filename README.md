# KotlinKnifer

# Download

## Gradle

First you have to add them to project `bundle.gradle` as below:

```
allprojects {
    repositories {
        jcenter()

        maven { url 'https://dl.bintray.com/pokk/maven' }
    }
}
```

And add the dependency.


```gradle
compile 'com.devrapid.jieyi:kotlinknifer:0.0.4'
```

Then you can use it!!!

## Maven

```maven
<dependency>
  <groupId>com.devrapid.jieyi</groupId>
  <artifactId>kotlinknifer</artifactId>
  <version>0.0.2</version>
  <type>pom</type>
</dependency>
```

# Feature

I will add some useful tool modules for everyone and myself. If you have any
ideas or problems. Please let me know, thank you!

# License

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
