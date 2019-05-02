# JellyAndroidMaster
[![](https://www.jitpack.io/v/MyFisherTiger/JellyAndroidMaster.svg)](https://www.jitpack.io/#MyFisherTiger/JellyAndroidMaster)
# 框架的引入
**Step 1. Add it in your root build.gradle at the end of repositories:**

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** **Add the dependency**

```groovy
dependencies {
	implementation 'com.github.MyFisherTiger:JellyAndroidMaster:1.0'
}
```
# 更新说明

v1.0
首次开源框架RecyclerView+Adapter+Retrofit+RxJava+MVP+DataManager+基本Base

# 整体模块

# MVP+RxJava+Retrofit的封装使用

### 1.在使用Retrofit请求网络之前需要进行配置，在框架中提供了了Config配置类
```
public class Config {
      /**必传参数**/
      //是否为BuildConfig.DEBUG,日志输出需要
      public static boolean DEBUG;
      //设置Context
      public static Context CONTEXT;
      /**Retrofit**/
      //网络请求的域名
      public static String URL_DOMAIN;
      //网络缓存地址
      public static String URL_CACHE;
      //设置OkHttp的缓存机制的最大缓存时间,默认为一天
      public static long MAX_CACHE_SECONDS= 60 * 60 * 24;
      //缓存最大的内存,默认为10M
      public static long MAX_MEMORY_SIZE=10 * 1024 * 1024;
      //设置网络请求json通用解析类
      public static Class MClASS;
      /**SharePreference**/
      public static String USER_CONFIG;
      /**Realm**/
      public static RealmMigration realmMigration;
      public static int realmVersion=0;
      public static String realmName="myRealm.realm";

}
```
### 在项目中需要根据项目需要进行配置，在Application中设置

```
private void config(){
	Config.DEBUG= BuildConfig.DEBUG;//这个如果是测试时，日志输出，网络请求相关信息输出
	Config.URL_CACHE=AppConfig.URL_CACHE;//OkHttp缓存地址
	Config.CONTEXT=this;//这个是必传
	Config.MClASS= Result.class;//如果项目的json数据格式统一可以设置一个统一的been类
	Config.URL_DOMAIN="http://api.tianapi.com/";//网络请求域名
}
```
### 根据项目需要定义一个通用的数据实体类，这是本例通用实体类，这个类需要设置到Applicatin中

```
public class Result<T> implements Serializable {

	private int code;
	private String msg;
	private T newslist;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getNewslist() {
		return newslist;
	}

	public void setNewslist(T newslist) {
		this.newslist = newslist;
	}
}
```
### 2.MVP+RxJava+Retrofit+OkHttp的缓存机制

上面的缓存配置完成之后通过以下代码即可：
```
public class WeChatWorldNewsPresenter extends WeChatWorldNewsContract.Presenter {
    @Override
     public void requestWorldNews(int page, int num) {

     RequestBuilder<Result<List<WeChatNews>>> resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<Result<List<WeChatNews>>>(mView) {
        @Override
        public void onNext(Result<List<WeChatNews>> result) {
            mView.refreshUI(result.getNewslist());
        }
    });

    resultRequestBuilder
            .setUrl(ApiUrl.URL_WETCHAT_WORLD_NEWS)
            .setTransformClass(WeChatNews.class)
            .setRequestParam(ApiClient.getRequiredBaseParam())
            .setHttpTypeAndReqType(RequestBuilder.HttpType.DEFAULT_GET, RequestBuilder.ReqType.DEFAULT_CACHE_LIST)
            .setParam("page",page)
            .setParam("num",num);

    rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));
  }
}
```
如果需要自定义磁盘缓存，可以查看一下例子
```
public class WeChatChinaNewsDefinitionPresenter extends WeChatChinaNewsContract.Presenter { 
	@Override 
	public void requestChinaNews(int page, int num) { 
	String filePath = AppConfig.STORAGE_DIR + "wechat/china"; String fileName = "limttime.t";
	RequestBuilder resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<Result<List<WeChatNews>>>(mView)		{
		@Override
		public void onNext(Result<List<WeChatNews>> result) {
			    mView.refreshUI(result.getNewslist());
		}
	    }).setFilePathAndFileName(filePath, fileName)
			.setTransformClass(WeChatNews.class)
			.setUrl(ApiUrl.URL_WETCHAT_CHINA_NEWS)
			.setRequestParam(ApiClient.getRequiredBaseParam())
	.setHttpTypeAndReqType(RequestBuilder.HttpType.DEFAULT_GET,RequestBuilder.ReqType.DISK_CACHE_LIST_LIMIT_TIME)
			.setParam("page", page)
			.setParam("num", num);

	    rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));
    }
}
```
### 注意
RxObservableListener有三个回调方法
```
void onNext(T result);
void onComplete();
void onError(NetWorkCodeException.ResponseThrowable e);
```
一般只需重写onNext方法，其它两个方法可以自行选择重写
RxObservableListener提供两个构造函数
```
protected RxObservableListener(BaseView view){
    this.mView = view;
}

protected RxObservableListener(BaseView view, String errorMsg){
     this.mView = view;
     this.mErrorMsg = errorMsg;
}
```
这两个构造函数主要主要是为了统一处理onError的，如果要自定义错误提醒，则可以选择第二个构造函数。

通过DataManager的网络请求方式会返回来一个DisposableObserver，需要把它通过rxManager.addObserver()添加进CompositeDisposable才能正常执行.

# Base的使用
### Base封装了MVP和项目的基类
MVP
BaseModel
BaseView
BasePresenter

### 在项目中的网络请求+MVP的完整实现
定义一个contract类，内部分别继承上面的MVP base类，在这里定义操作

```
public interface WeChatChinaNewsContract {

	interface Model extends BaseModel{
		Observable<Result<List<WeChatNews>>> loadChinaNews(int page, int num);
	}

	interface View extends BaseView{
		void refreshUI(List<WeChatNews> weChatNews);
	}

	abstract class Presenter extends BasePresenter<Model,View>{
		public abstract void requestChinaNews(int page,int num);
	}
}
```
Presenter的具体执行类
```
public class WeChatChinaNewsPresenter extends WeChatChinaNewsContract.Presenter {
	@Override
	public void requestChinaNews(int page, int num) {

		rxManager.addObserver(RequestManager.loadOnlyNetWork(mModel.loadChinaNews(page, num),
            new RxObservableListener<Result<List<WeChatNews>>>(mView) {
                @Override
                public void onNext(Result<List<WeChatNews>> result) {
                    mView.refreshUI(result.getNewslist());
                }
            }));

	}
}
```

Model的具体执行类
```
public class WeChatChinaNewsModel implements WeChatChinaNewsContract.Model {
	@Override
	public Observable<Result<List<WeChatNews>>> loadChinaNews(int page, int num) {

		Map<String,Object> map= ApiClient.getRequiredBaseParam();
		map.put("page",page);
		map.put("num",num);
		return RetrofitManager.getApiService(ApiService.class)
			.getWeChatChinaNews(ApiUrl.URL_WETCHAT_CHINA_NEWS,map);
	
	}
}
```
view层
```
public class FragmentChinaNews extends BaseFragment<WeChatChinaNewsModel,WeChatChinaNewsPresenter> implements WeChatChinaNewsContract.View{

	@Override
	public void init() {
	}

	@Override
	public void requestData() {
		((WeChatChinaNewsPresenter)mPresenter).requestChinaNews(pageSize,PAGE_SIZE);
	}

	@Override
	public void refreshUI(List<WeChatNews> newsList) {

	}

	@Override
	public void onError(String errorMsg) {
	}
}
```
### UI Base
(1)IBaseActivity
IBaseActivity:主要提供了一个页面的基本方法、处理了MVP之间的关联、使用者可以直接继承该类使用、也可以继承该类实现扩展。

IBaseActivity<T extends BaseModel, E extends BasePresenter>已经进行MVP之间的传递和关联。

处理好页面销毁之后Observables 和 Subscribers的解绑。

getLayoutId()设置布局、init()数据初始化、requestData()请求数据，执行顺序已经在IBaseActivity做好处理。

可以继承IBaseActivity进行扩展。
```
public abstract class BaseActivity<T extends BaseModel,E extends BasePresenter> extends IBaseActivity{ 
	private Unbinder unbinder;
	 @Override
 	protected void onCreate(@Nullable Bundle savedInstanceState) {
 	super.onCreate(savedInstanceState);
	   	unbinder= ButterKnife.bind(this);
 	}
}
```

(2)IBaseFragment
IBaseFragment:主要提供一个页面的基本方法，处理了MVP之间的关联，该类已经加入了懒人加载的控制方式、使用者可以直接继承该类使用、也可以继承该类实现扩展。

IBaseFragment<T extends BaseModel,E extends BasePresenter>已经进行MVP之间的传递和关联。

处理好页面销毁之后Observables 和 Subscribers的解绑。

加入了懒人加载方式，只有页面显示才会调用requestData()请求数据，并只会调用一次。

getLayoutId()设置布局、init()数据初始化、requestData()请求数据，执行顺序已经在IBaseFragment做好处理。

可以继承IBaseFragment进行扩展。
```
public abstract class BaseFragment<T extends BaseModel,E extends BasePresenter> extends IBaseFragment {

 	private Unbinder unbinder;

 	@Nullable
 	@Override
 	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
 		super.onCreateView(inflater, container, savedInstanceState);
 		unbinder= ButterKnife.bind(this,mainView);
 		return mainView;
 	}
 }
```
更多请查看例子的使用
