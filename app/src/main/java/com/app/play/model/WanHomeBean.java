package com.app.play.model;

import java.util.List;

/**
 * Created by thkcheng on 2018/7/4.
 */

public class WanHomeBean extends BaseModel{


    /**
     * data : {"curPage":2,"datas":[{"apkLink":"","author":"腾讯音乐技术团队","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3078,"link":"https://mp.weixin.qq.com/s/mwTtxk4YfYWCG4m6n_ropw","niceDate":"2018-06-30","origin":"","projectLink":"","publishTime":1530335020000,"superChapterId":174,"superChapterName":"热门专题","tags":[],"title":"记一次全民K歌的crash定位过程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"javalong","chapterId":98,"chapterName":"WebView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3077,"link":"https://www.jianshu.com/p/ba62f39beadd","niceDate":"2018-06-30","origin":"","projectLink":"","publishTime":1530324688000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"编写一个JsBridge","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Othershe","chapterId":380,"chapterName":"ImageView","collect":false,"courseId":13,"desc":"Android 仿钉钉、微信 群聊组合头像 CombineBitmap","envelopePic":"http://www.wanandroid.com/blogimgs/f36e29c7-5475-461c-b27a-d1e9c0dc7b9d.png","fresh":false,"id":3076,"link":"http://www.wanandroid.com/blog/show/2180","niceDate":"2018-06-29","origin":"","projectLink":"https://github.com/Othershe/CombineBitmap","publishTime":1530261697000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=380"}],"title":"Android 仿钉钉、微信 群聊组合头像 CombineBitmap","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"JerryloveEmily","chapterId":202,"chapterName":"机器学习&人工智能","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3075,"link":"https://juejin.im/post/5b33ac576fb9a00e385846cb","niceDate":"2018-06-28","origin":"","projectLink":"","publishTime":1530175411000,"superChapterId":181,"superChapterName":"延伸技术","tags":[],"title":"人工智能数学基础----线性二阶近似","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"爱吃鱼的KK","chapterId":245,"chapterName":"集合相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3074,"link":"https://www.jianshu.com/p/edc2fd149255","niceDate":"2018-06-28","origin":"","projectLink":"","publishTime":1530171848000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"ConcurrentSkipListMap 源码分析 (基于Java 8)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"tony~博客小屋","chapterId":245,"chapterName":"集合相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3073,"link":"https://www.cnblogs.com/ITtangtang/p/3948786.html","niceDate":"2018-06-28","origin":"","projectLink":"","publishTime":1530156602000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Java集合---ConcurrentHashMap原理分析 1.7","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"dsbGenius","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"SketchBoard是一个可涂鸦、绘图、添加文字、图像（可旋转缩放）、背景的Fragment，其中主要由SketchView利用matrix完成所有图形绘制操作。","envelopePic":"http://www.wanandroid.com/blogimgs/cec73ace-7d33-4a93-88e0-26319b4ec831.png","fresh":false,"id":3072,"link":"http://www.wanandroid.com/blog/show/2179","niceDate":"2018-06-27","origin":"","projectLink":"https://github.com/dsbGenius/WhiteBoard","publishTime":1530105225000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"一个可涂鸦、绘图、添加文字、图像画板 WhiteBoard","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xujiaji","chapterId":140,"chapterName":"dagger2","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3071,"link":"https://blog.xujiaji.com/post/learn-dagger","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530080710000,"superChapterId":192,"superChapterName":"5.+高新技术","tags":[],"title":"Dagger2 的深入分析与使用","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"爱运动的青蛙","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3070,"link":"https://www.jianshu.com/p/73ad5440b3ef","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530071651000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"浅析HTTPS中间人攻击与证书校验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"腾讯Bugly","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3069,"link":"https://mp.weixin.qq.com/s/qOMO0LIdA47j3RjhbCWUEQ","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530070889000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"彻底弄懂 Http 缓存机制 - 基于缓存策略三要素分解法","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 承香墨影","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3068,"link":"https://juejin.im/post/5b30d05ee51d45587c51d276","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530070873000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"图解 HTTP 的缓存机制 | 实用 HTTP","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"yjfnypeu","chapterId":358,"chapterName":"项目基础功能","collect":false,"courseId":13,"desc":"一系列简单、轻量、方便的Android开发工具集合(持续更新中)\r\n","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":3067,"link":"http://www.wanandroid.com/blog/show/2178","niceDate":"2018-06-27","origin":"","projectLink":"https://github.com/yjfnypeu/EasyAndroid","publishTime":1530067647000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=358"}],"title":"一系列简单、轻量、方便的Android开发工具集合 EasyAndroid","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"天天P图攻城狮","chapterId":97,"chapterName":"音视频","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3066,"link":"https://mp.weixin.qq.com/s/fLlomfOMXLgylIXJ_fKK4Q","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530066913000,"superChapterId":97,"superChapterName":"多媒体技术","tags":[],"title":"Android 音视频系列：H264视频编码介绍","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"黄俊彬","chapterId":379,"chapterName":"Android P 适配","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3065,"link":"https://www.jianshu.com/p/9e9e902ea039","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530066324000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android P 兼容与适配","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"roland_reed","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3063,"link":"https://segmentfault.com/a/1190000011827088","niceDate":"2018-06-26","origin":"","projectLink":"","publishTime":1529986435000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"浅谈 HTTPS","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"roland_reed","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3062,"link":"https://segmentfault.com/a/1190000013075736","niceDate":"2018-06-26","origin":"","projectLink":"","publishTime":1529986415000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"HTTPS 中间人攻击及其防范","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Gordon0918","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3061,"link":"https://www.cnblogs.com/gordon0918/p/5237717.html","niceDate":"2018-06-26","origin":"","projectLink":"","publishTime":1529985440000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"Https协议简析及中间人攻击原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"brucevanfdm","chapterId":198,"chapterName":"基础概念","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3060,"link":"https://www.jianshu.com/p/75e0f4f16471","niceDate":"2018-06-25","origin":"","projectLink":"","publishTime":1529934442000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"浅谈Android中的meta-data及其应用","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"HuangTao_Zoey","chapterId":302,"chapterName":"ANR","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3059,"link":"https://www.jianshu.com/p/f406d535a8bc","niceDate":"2018-06-25","origin":"","projectLink":"","publishTime":1529934393000,"superChapterId":174,"superChapterName":"热门专题","tags":[],"title":"Android trace文件抓取原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"jenly1314","chapterId":378,"chapterName":"CounterView","collect":false,"courseId":13,"desc":"SpinCounterView一个类似码表变化的旋转计数器动画控件。（类似场景：金融类App金额动画效果、可用额度、可用流量、芝麻信用等）","envelopePic":"http://www.wanandroid.com/blogimgs/010f3a02-a544-42fc-b2dd-de89f4bf7430.png","fresh":false,"id":3058,"link":"http://www.wanandroid.com/blog/show/2177","niceDate":"2018-06-25","origin":"","projectLink":"https://github.com/jenly1314/SpinCounterView","publishTime":1529934292000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=378"}],"title":"SpinCounterView一个类似码表变化的旋转计数器动画控件","type":0,"userId":-1,"visible":1,"zan":0}],"offset":20,"over":false,"pageCount":72,"size":20,"total":1426}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curPage : 2
         * datas : [{"apkLink":"","author":"腾讯音乐技术团队","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3078,"link":"https://mp.weixin.qq.com/s/mwTtxk4YfYWCG4m6n_ropw","niceDate":"2018-06-30","origin":"","projectLink":"","publishTime":1530335020000,"superChapterId":174,"superChapterName":"热门专题","tags":[],"title":"记一次全民K歌的crash定位过程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"javalong","chapterId":98,"chapterName":"WebView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3077,"link":"https://www.jianshu.com/p/ba62f39beadd","niceDate":"2018-06-30","origin":"","projectLink":"","publishTime":1530324688000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"编写一个JsBridge","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Othershe","chapterId":380,"chapterName":"ImageView","collect":false,"courseId":13,"desc":"Android 仿钉钉、微信 群聊组合头像 CombineBitmap","envelopePic":"http://www.wanandroid.com/blogimgs/f36e29c7-5475-461c-b27a-d1e9c0dc7b9d.png","fresh":false,"id":3076,"link":"http://www.wanandroid.com/blog/show/2180","niceDate":"2018-06-29","origin":"","projectLink":"https://github.com/Othershe/CombineBitmap","publishTime":1530261697000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=380"}],"title":"Android 仿钉钉、微信 群聊组合头像 CombineBitmap","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"JerryloveEmily","chapterId":202,"chapterName":"机器学习&人工智能","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3075,"link":"https://juejin.im/post/5b33ac576fb9a00e385846cb","niceDate":"2018-06-28","origin":"","projectLink":"","publishTime":1530175411000,"superChapterId":181,"superChapterName":"延伸技术","tags":[],"title":"人工智能数学基础----线性二阶近似","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"爱吃鱼的KK","chapterId":245,"chapterName":"集合相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3074,"link":"https://www.jianshu.com/p/edc2fd149255","niceDate":"2018-06-28","origin":"","projectLink":"","publishTime":1530171848000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"ConcurrentSkipListMap 源码分析 (基于Java 8)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"tony~博客小屋","chapterId":245,"chapterName":"集合相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3073,"link":"https://www.cnblogs.com/ITtangtang/p/3948786.html","niceDate":"2018-06-28","origin":"","projectLink":"","publishTime":1530156602000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Java集合---ConcurrentHashMap原理分析 1.7","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"dsbGenius","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"SketchBoard是一个可涂鸦、绘图、添加文字、图像（可旋转缩放）、背景的Fragment，其中主要由SketchView利用matrix完成所有图形绘制操作。","envelopePic":"http://www.wanandroid.com/blogimgs/cec73ace-7d33-4a93-88e0-26319b4ec831.png","fresh":false,"id":3072,"link":"http://www.wanandroid.com/blog/show/2179","niceDate":"2018-06-27","origin":"","projectLink":"https://github.com/dsbGenius/WhiteBoard","publishTime":1530105225000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"一个可涂鸦、绘图、添加文字、图像画板 WhiteBoard","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xujiaji","chapterId":140,"chapterName":"dagger2","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3071,"link":"https://blog.xujiaji.com/post/learn-dagger","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530080710000,"superChapterId":192,"superChapterName":"5.+高新技术","tags":[],"title":"Dagger2 的深入分析与使用","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"爱运动的青蛙","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3070,"link":"https://www.jianshu.com/p/73ad5440b3ef","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530071651000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"浅析HTTPS中间人攻击与证书校验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"腾讯Bugly","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3069,"link":"https://mp.weixin.qq.com/s/qOMO0LIdA47j3RjhbCWUEQ","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530070889000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"彻底弄懂 Http 缓存机制 - 基于缓存策略三要素分解法","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 承香墨影","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3068,"link":"https://juejin.im/post/5b30d05ee51d45587c51d276","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530070873000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"图解 HTTP 的缓存机制 | 实用 HTTP","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"yjfnypeu","chapterId":358,"chapterName":"项目基础功能","collect":false,"courseId":13,"desc":"一系列简单、轻量、方便的Android开发工具集合(持续更新中)\r\n","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":3067,"link":"http://www.wanandroid.com/blog/show/2178","niceDate":"2018-06-27","origin":"","projectLink":"https://github.com/yjfnypeu/EasyAndroid","publishTime":1530067647000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=358"}],"title":"一系列简单、轻量、方便的Android开发工具集合 EasyAndroid","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"天天P图攻城狮","chapterId":97,"chapterName":"音视频","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3066,"link":"https://mp.weixin.qq.com/s/fLlomfOMXLgylIXJ_fKK4Q","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530066913000,"superChapterId":97,"superChapterName":"多媒体技术","tags":[],"title":"Android 音视频系列：H264视频编码介绍","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"黄俊彬","chapterId":379,"chapterName":"Android P 适配","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3065,"link":"https://www.jianshu.com/p/9e9e902ea039","niceDate":"2018-06-27","origin":"","projectLink":"","publishTime":1530066324000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android P 兼容与适配","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"roland_reed","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3063,"link":"https://segmentfault.com/a/1190000011827088","niceDate":"2018-06-26","origin":"","projectLink":"","publishTime":1529986435000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"浅谈 HTTPS","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"roland_reed","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3062,"link":"https://segmentfault.com/a/1190000013075736","niceDate":"2018-06-26","origin":"","projectLink":"","publishTime":1529986415000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"HTTPS 中间人攻击及其防范","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Gordon0918","chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3061,"link":"https://www.cnblogs.com/gordon0918/p/5237717.html","niceDate":"2018-06-26","origin":"","projectLink":"","publishTime":1529985440000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"Https协议简析及中间人攻击原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"brucevanfdm","chapterId":198,"chapterName":"基础概念","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3060,"link":"https://www.jianshu.com/p/75e0f4f16471","niceDate":"2018-06-25","origin":"","projectLink":"","publishTime":1529934442000,"superChapterId":168,"superChapterName":"基础知识","tags":[],"title":"浅谈Android中的meta-data及其应用","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"HuangTao_Zoey","chapterId":302,"chapterName":"ANR","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3059,"link":"https://www.jianshu.com/p/f406d535a8bc","niceDate":"2018-06-25","origin":"","projectLink":"","publishTime":1529934393000,"superChapterId":174,"superChapterName":"热门专题","tags":[],"title":"Android trace文件抓取原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"jenly1314","chapterId":378,"chapterName":"CounterView","collect":false,"courseId":13,"desc":"SpinCounterView一个类似码表变化的旋转计数器动画控件。（类似场景：金融类App金额动画效果、可用额度、可用流量、芝麻信用等）","envelopePic":"http://www.wanandroid.com/blogimgs/010f3a02-a544-42fc-b2dd-de89f4bf7430.png","fresh":false,"id":3058,"link":"http://www.wanandroid.com/blog/show/2177","niceDate":"2018-06-25","origin":"","projectLink":"https://github.com/jenly1314/SpinCounterView","publishTime":1529934292000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=378"}],"title":"SpinCounterView一个类似码表变化的旋转计数器动画控件","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 20
         * over : false
         * pageCount : 72
         * size : 20
         * total : 1426
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * author : 腾讯音乐技术团队
             * chapterId : 78
             * chapterName : 性能优化
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : false
             * id : 3078
             * link : https://mp.weixin.qq.com/s/mwTtxk4YfYWCG4m6n_ropw
             * niceDate : 2018-06-30
             * origin :
             * projectLink :
             * publishTime : 1530335020000
             * superChapterId : 174
             * superChapterName : 热门专题
             * tags : []
             * title : 记一次全民K歌的crash定位过程
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private long publishTime;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
