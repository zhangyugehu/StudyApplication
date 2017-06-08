# UI绘制流程

### 绘制入口`ViweRootImpl#performTraversals()`

#### 依次调用

测量`performMeasure()`

摆放布局`performLayout()`

### 绘制流程

#### 1. Measure

##### `MeasureSpac`

> 在Measure流程中，系统将View的LayoutParams根据父容器所施加的规则转换成对应的MeasureSpec，
在onMeasure中根据这个MeasureSpec来确定view的测量高度

##### 测量模式

> `EXACTLY` 父容器已经测量出精确大小，这也是childview的最终大小
    -----match_parent， 精确值

> `AT_MOST` childview最终的大小不能超过父容器给的值
    ------ wrap_content

> `UNSPWCIFIED` 不确定，源码内部使用
     ------ 一般在ScrollView，ListView中
     
##### 测量大小：

> 根据测量模式来确定测量大小的值


#### 为什么要在onMeasure之后才能拿到measureWidth和measureHeight
````
ViweRootImpl#performMeasure() -> 
View#measure(childwidSpec, childHeiSpec) ->
View#onMeasure(childwidSpec, childHeiSpec) ->
childwidSpec => View#getDefaultSize(View#getSuggestedMinimumWidth(), childwidSpec) => 
View#setMeasuredDimension(measuredWidth, measuredHeight) ->
View#setMeasuredDimensionRaw(measuredWidth, measuredHeight) -> 
{mMeasuredWidth = measuredWidth; mMeasuredHeight = measuredHeight;}

````
#### `FrameLayout#onMeasure`