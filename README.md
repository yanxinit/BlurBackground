# BlurBackground

打开新页面，给背景模糊效果。

##效果图：
 ![](https://github.com/yanxinit/BlurBackground/blob/master/Demo.gif)

##下载
[![](https://jitpack.io/#yanxinit/BlurBackground.svg)](https://jitpack.io/#yanxinit/BlurBackground/release-1.0.0)

##使用方法：
打开新页面前：
```java
BlurHandler.getInstance().beginBlur(this);
```
在新页面：
```java
BlurHandler.getInstance().blurBackground(this);
```
