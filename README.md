# BlurBackground

打开新页面，给背景模糊效果。
模糊算法来自：http://stackoverflow.com/questions/2067955/fast-bitmap-blur-for-android-sdk

##效果图：
 ![](https://github.com/yanxinit/BlurBackground/blob/master/Demo.gif)

##使用方法：
打开新页面前，在前一个页面：
```java
BlurHandler.getInstance().beginBlur(getWindow().getDecorView())
```
在新页面：
```java
@Override
public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBackground();
        BlurHandler.getInstance().addBlurCompleteListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BlurHandler.getInstance().removeBlurCompleteListener(this);
    }

    @Override
    public void onComplete() {
        updateBackground();
    }

    private void updateBackground() {
        getWindow().getDecorView().getRootView()
        .setBackgroundDrawable(new BitmapDrawable(BlurHandler.getInstance().getImage()));
    }
```
