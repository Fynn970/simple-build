# simple-build

用于快速搭建Android开发框架的工具包

使用方式

```gradle
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

依赖

 ```android
 dependencies {
	        implementation 'com.github.Fynn970.simple-build:simple-build:v0.0.1-beta'
	}
```

XLog使用方式
在application中初始化，可以设置是否显示线程，显示堆栈深度等配置，方法均在XLogConfig，可以通过实现XLogPrinter自定义打印方式,自带Console打印和activity内底部弹窗打印显示
```
    XLogManager.init(object : XLogConfig() {
            override fun injectJsonParse(): JsonParser {
                return object : JsonParser {
                    override fun toJson(src: Any): String {
                        return Gson().toJson(src)
                    }
                }
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
            override fun getGlobalTag(): String {
                return "build"
            }

            override fun enable(): Boolean {
                if (BuildConfig.DEBUG){
                    return true
                }
                return false
            }
        }, XConsolePrinter())
```
