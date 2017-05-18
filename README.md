# ProjectVLayout
淘宝开源库VLayout实践

最近淘宝出了vlayout,刚开始看淘宝的文档的时候还是有点懵，后来自己也总结规划了一下，写了一个比较好看的demo，顺便在这里总结一下。

VLayout是什么，说白了就是用一个原生RecycelerView加上VLayout来实现在一个页面上比较复杂的布局并且有一个比较好的复用，在RecyclerView里同时有GridLayout布局，瀑布流布局，浮动布局等VLayout提供的九大布局，这也是淘宝客户端首页加载不同布局的方法。

好了，简单介绍到这里，首先我们先导入VLayout:

```
compile ('com.alibaba.android:vlayout:版本@aar') {
	transitive = true
}
```
具体的版本请看github里VLayout给出的版本号。现在最新是1.0.6
接着我们就可以开始引用VLayout了,VLayout的通用代码如下:
```
VirtualLayoutManager manager = new VirtualLayoutManager(this);
recyclerview.setLayoutManager(manager);
DelegateAdapter adapter =new DelegateAdapter(manager, true);
```
- 其中VirtualLayoutManager它继承自LinearLayoutManager；引入了 LayoutHelper 的概念，它负责具体的布局逻辑；VirtualLayoutManager管理了一系列LayoutHelper，将具体的布局能力交给LayoutHelper来完成。
- DelegateAdapter是VLayout专门为LayoutHelper定制的Adapter，我们把装载有各种布局的LayoutHelper的Adapter放进DelegateAdapter里最后在RecyclerView.setAdapter(DelegateAdapter);就可以加载出复杂的布局。
- 或许你们会问什么是LayoutHelper，这个问题问得好，就是VLayout提供的九种默认通用布局，解耦所有的View和布局之间的关系: Linear, Grid, 吸顶, 浮动, 固定位置等具体,名称和功能如下:
1. LinearLayoutHelper: 线性布局
1. GridLayoutHelper: Grid布局， 支持横向的colspan
1. StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度
1. FixLayoutHelper: 固定布局，始终在屏幕固定位置显示
1. ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等
1. FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置
1. ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值
1. SingleLayoutHelper: 通栏布局，只会显示一个组件View
1. OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素
1. StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底

这就是九种布局对应的类，我们可以用着九个类实现各种复杂的布局，下面我会一一介绍每个布局和效果，以便更直观的看到效果:

- LinearLayoutHelper: 线性布局，就是实现ListView的效果很简单，直接看代码:

```
public class LinearLayoutHelperActivity extends Activity{
    public static RecyclerView recyclerview;
    public static DelegateRecyclerAdapter delegateRecyclerAdapter;
    public DelegateAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter =new DelegateAdapter(manager, true);

        adapter.addAdapter(init(this));
        recyclerview.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter init(Context context){
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(5);
        //设置布局底部与下个布局的间隔
        linearLayoutHelper.setMarginBottom(20);
        //设置间距
        linearLayoutHelper.setMargin(20,20,20,20);
        delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,linearLayoutHelper,"LinearLayoutHelper");
        return delegateRecyclerAdapter;
    }
}
```
而DelegateRecyclerAdapter的代码如下:

```
public class DelegateRecyclerAdapter extends DelegateAdapter.Adapter{
    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;

    public DelegateRecyclerAdapter(Context context,LayoutHelper helper,String name){
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.context=context;
        this.name=name;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.layout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(position%2==0){
                holder.itemView.setBackgroundColor(0xaa3F51B5);
            }else{
                holder.itemView.setBackgroundColor(0xccFF4081);
            }
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.name.setText(name+position+"");
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.item_name);
        }
    }

}
```
这里需要说的就是在Adapter类里我们需要继承Vlayout的DelegateAdapter.Adapter类，然后多回调onCreateLayoutHelper()方法，返回我们传进去的LayoutHelper类，其他的和普通的RecyclerView是一个样的。DelegateRecyclerAdapter在我们后面也有多次引用。其他要说的就是我们初始完LinearLayoutHelper后生产DelegateAdapter.Adapter类再赋给DelegateAdapter，然后然后RecyclerView在setAdapter()就这样。
![image.png](http://upload-images.jianshu.io/upload_images/925576-f49efd0bc8ef6598.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- GridLayoutHelper: Grid布局， 支持横向的colspan，也很简单，代码如下:

```
public static DelegateRecyclerAdapter init(Context context){
        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(4);
        //自定义设置某些位置的Item的占格数
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position >5) {
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        //是否填满可用区域
        gridLayoutHelper.setAutoExpand(false);
        delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,gridLayoutHelper,"GridLayoutHelper");
        return delegateRecyclerAdapter;
    }
```
其他代码很上面的一样。
![img2.PNG](http://upload-images.jianshu.io/upload_images/925576-b249b0f828e85a4a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度,代码如下:

```
 public static StaggeredAdapter init(Context context){
    StaggeredGridLayoutHelper staggeredGridLayoutHelper=new StaggeredGridLayoutHelper(3,20);
    staggeredAdapter=new StaggeredAdapter(context,staggeredGridLayoutHelper,"StaggeredGridLayoutHelper");
    return staggeredAdapter;
 }
```
在StaggeredAdapter里我们在onBindViewHolder里用
```
ViewGroup.LayoutParams layoutParams = ((MyViewholder) holder).text.getLayoutParams();
layoutParams.height = 260 + position % 7 * 20;
((MyViewholder) holder).text.setLayoutParams(layoutParams);
```
来实现高度不一致，效果如下:
![img3.PNG](http://upload-images.jianshu.io/upload_images/925576-4f28984953b9a0a5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- FixLayoutHelper: 固定布局，始终在屏幕固定位置显示,代码如下：

```
public static FixLayoutAdapter initFixLayoutHelper(Context context){
    FixLayoutHelper fixLayoutHelper=new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT, 200, 200);
    FixLayoutAdapter fixLayoutAdapter=new FixLayoutAdapter(context,fixLayoutHelper,"fixlayouthelp");
    return fixLayoutAdapter;
}
```
除了有FixLayoutHelper.BOTTOM_LEFT之外，还有FixLayoutHelper.TOP_LEFT，FixLayoutHelper.BOTTOM_RIGHT，FixLayoutHelper.TOP_RIGHT, 200,200分别对应偏移量x,y，效果如下:
![img4.PNG](http://upload-images.jianshu.io/upload_images/925576-cb8265841bbfe5e5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
fixlayouthelp区域块就是FixLayoutHelper了。

- ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等，代码如下:

```
public static FixLayoutAdapter initScrollFixLayout(Context context){
    ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(15,15);
    //show_always:总是显示
    //show_on_enter:当页面滚动到这个视图的位置的时候，才显示
    //show_on_leave:当页面滚出这个视图的位置的时候显示
    scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
    return new FixLayoutAdapter(context, scrollFixLayoutHelper,"scrollfixlayouthelper");
}
```
代码很简单，看效果：
![img5.PNG](http://upload-images.jianshu.io/upload_images/925576-21ed8eff046b70e6.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
ScrollFixLayoutHelper继承自FixLayoutHelper，不同的是showType来决定这个布局的Item是否显示，可以用来做一些返回顶部之类的按钮，
1.  SHOW_ALWAYS：与FixLayoutHelper的行为一致，固定在某个位置； 
1.  SHOW_ON_ENTER：默认不显示视图，当页面滚动到这个视图的位置的时候，才显示； 
1. SHOW_ON_LEAVE：默认不显示视图，当页面滚出这个视图的位置的时候显示；

这里效果不明显，等集合所有布局之后大家就可以看很直观的效果

- FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置，代码如下：

```
public static FixLayoutAdapter initFloatLayoutHelper(Context context){
    FloatLayoutHelper floatLayoutHelper=new FloatLayoutHelper();
    floatLayoutHelper.setDefaultLocation(20,250);
    FixLayoutAdapter fixLayoutAdapter=new FixLayoutAdapter(context,floatLayoutHelper,"floatlayouthelper");
    return  fixLayoutAdapter;
}
```
效果如下：

![img6.gif](http://upload-images.jianshu.io/upload_images/925576-d3c37f8602d1b4eb.gif?imageMogr2/auto-orient/strip)

其中setDefaultLocation()使用来设置他的初始位置的，setAlignType（表示吸边时的基准位置，默认左上角，有四个取值，分别是TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT）

-  ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值，代码如下:

```
 public static ColumnLayoutAdapter initColumnLayout(Context context){
    ColumnLayoutHelper columnLayoutHelper=new ColumnLayoutHelper();
    columnLayoutHelper.setWeights(new float[]{20,20,20,20,20});
    columnLayoutHelper.setMarginBottom(20);
    ColumnLayoutAdapter columnLayoutAdapter=new ColumnLayoutAdapter(context,columnLayoutHelper,"ColumnLayoutHelper");
    return columnLayoutAdapter;
}
```
ColumnLayoutHelper需要设置Weights，是一个float数组，总和为100，否则超出布局。效果图如下：
![img7.PNG](http://upload-images.jianshu.io/upload_images/925576-7a749e29d9653da2.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- SingleLayoutHelper: 通栏布局，只会显示一个组件View，这里建议设置Adapter个数为1，因为他就只会显示一栏，假如有多个可能会出现一些问题，本人实测个数多时会出点问题。代码如下：

```
public static SingleLayoutAdapter initSingleLayout(Context context){
    SingleLayoutHelper singleLayoutHelper=new SingleLayoutHelper();
    //设置间距
    singleLayoutHelper.setMargin(20,20,20,20);
    SingleLayoutAdapter singleLayoutAdapter=new SingleLayoutAdapter(context,singleLayoutHelper,"SingleLayoutHelper");
    return singleLayoutAdapter;
}
```
效果图如下:

![img8.PNG](http://upload-images.jianshu.io/upload_images/925576-c0c41a40cc9c215d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素,根据个数的不同所呈现的界面也是不一样的,不同个数效果如下：
 
//个数为1

![个数为1.PNG](http://upload-images.jianshu.io/upload_images/925576-a4c577ec374e57cd.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

//个数为2

![个数为2.PNG](http://upload-images.jianshu.io/upload_images/925576-d874d7b8fa5d9971.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

//个数为3

![个数为3.PNG](http://upload-images.jianshu.io/upload_images/925576-9f228d43a65eeae7.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

//个数为4

![个数为4.PNG](http://upload-images.jianshu.io/upload_images/925576-74dc8792c026cb34.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

//个数为5

![个数为5.PNG](http://upload-images.jianshu.io/upload_images/925576-29d47f791ba380b6.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

代码如下：

```
 public static OnePlusNLayoutAdapter initOnePlusNLayout(Context context){
    OnePlusNLayoutHelper onePlusNLayoutHelper=new OnePlusNLayoutHelper();
    //设置布局底部与下个布局的间隔
    onePlusNLayoutHelper.setMarginBottom(20);
    OnePlusNLayoutAdapter onePlusNLayoutAdapter=new OnePlusNLayoutAdapter(context,onePlusNLayoutHelper,"OnePlusNLayoutHelper");
    return onePlusNLayoutAdapter;
}

```

- StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底，代码如下:

```
public static StickyLayoutAdapter initStickyLayoutHelper(Context context){
    StickyLayoutHelper stickyLayoutHelper=new StickyLayoutHelper();
    return new StickyLayoutAdapter(context,stickyLayoutHelper);
}

```
效果图如下：

![img10.gif](http://upload-images.jianshu.io/upload_images/925576-7511442ba426d5a1.gif?imageMogr2/auto-orient/strip)

- 最后假如只是单单加载其中的一个布局其实意义不大，VLayout只最大的意义在于加载多个布局并且保持一个很好的复用，所以我们把上面的所有布局一起加载起来，代码如下：

```
public class AllActivity extends Activity{

    private RecyclerView recyclerview;
    private DelegateAdapter delegateAdapter ;
    final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);

        initView();
    }

    public void initView(){
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerview.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);

        adapters.add(LinearLayoutHelperActivity.init(this));
        adapters.add(ColumnLayoutHelperActivity.initColumnLayout(this));
        adapters.add(GridLayoutHelperActivity.init(this));
        adapters.add(FixLayoutHelperActivity.initFixLayoutHelper(this));
        adapters.add(ScrollFixLayoutHelperActivity.initScrollFixLayout(this));
        adapters.add(SingleLayoutHelperActivity.initSingleLayout(this));
        adapters.add(OnePlusNLayoutHelperActivity.initOnePlusNLayout(this));
        adapters.add(FloatLayoutHelperActivity.initFloatLayoutHelper(this));
        adapters.add(StickyLayoutHelperActivity.initStickyLayoutHelper(this));
        adapters.add(StaggeredGridLayoutHelperActivity.init(this));

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        delegateAdapter = new DelegateAdapter(manager);

        delegateAdapter.setAdapters(adapters);
        recyclerview.setAdapter(delegateAdapter);
    }

}
```
要注意的是DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, hasConsistItemType);里当hasConsistItemType=true的时候，不论是不是属于同一个子adapter，相同类型的item都能复用。表示它们共享一个类型。 当hasConsistItemType=false的时候，不同子adapter之间的类型不共享。

效果如下图：

![img11.gif](http://upload-images.jianshu.io/upload_images/925576-56a30a38b9907e92.gif?imageMogr2/auto-orient/strip)

最后源码demo,代码都在这[https://github.com/jack921/ProjectVLayout](https://github.com/jack921/ProjectVLayout)
