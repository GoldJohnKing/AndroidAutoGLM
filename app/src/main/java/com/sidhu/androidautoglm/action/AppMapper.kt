package com.sidhu.androidautoglm.action

object AppMapper {
    private val appMap = mapOf(
        // === System Apps ===
        "AndroidSystemSettings" to "com.android.settings",
        "Android System Settings" to "com.android.settings",
        "Android  System Settings" to "com.android.settings",
        "Android-System-Settings" to "com.android.settings",
        "设置" to "com.android.settings",
        "Settings" to "com.android.settings",
        
        "相机" to "com.android.camera2",
        "Camera" to "com.android.camera2",
        
        "电话" to "com.google.android.dialer",
        "Phone" to "com.google.android.dialer",
        
        "短信" to "com.google.android.apps.messaging",
        "Messages" to "com.google.android.apps.messaging",
        "SimpleSMSMessenger" to "com.simplemobiletools.smsmessenger",
        
        "相册" to "com.google.android.apps.photos",
        "Photos" to "com.google.android.apps.photos",
        
        "联系人" to "com.google.android.contacts",
        "Contacts" to "com.android.contacts", // Generic
        "contacts" to "com.android.contacts",
        "GoogleContacts" to "com.google.android.contacts",
        "Google-Contacts" to "com.google.android.contacts",
        "Google Contacts" to "com.google.android.contacts",
        "google-contacts" to "com.google.android.contacts",
        "google contacts" to "com.google.android.contacts",

        "日历" to "com.google.android.calendar",
        "Calendar" to "com.google.android.calendar",
        "GoogleCalendar" to "com.google.android.calendar",
        "Google-Calendar" to "com.google.android.calendar",
        "Google Calendar" to "com.google.android.calendar",
        "google-calendar" to "com.google.android.calendar",
        "google calendar" to "com.google.android.calendar",
        "SimpleCalendarPro" to "com.scientificcalculatorplus.simplecalculator.basiccalculator.mathcalc",

        "时钟" to "com.google.android.deskclock",
        "Clock" to "com.android.deskclock",
        "clock" to "com.android.deskclock",
        "GoogleClock" to "com.google.android.deskclock",
        "Google Clock" to "com.google.android.deskclock",
        "Google-Clock" to "com.google.android.deskclock",
        
        "计算器" to "com.google.android.calculator",
        "Calculator" to "com.google.android.calculator",
        
        "文件" to "com.google.android.documentsui",
        "Files" to "com.android.fileexplorer",
        "files" to "com.android.fileexplorer",
        "File Manager" to "com.android.fileexplorer",
        "file manager" to "com.android.fileexplorer",
        "GoogleFiles" to "com.google.android.apps.nbu.files",
        "googlefiles" to "com.google.android.apps.nbu.files",
        "FilesbyGoogle" to "com.google.android.apps.nbu.files",

        "浏览器" to "com.android.chrome",
        "Chrome" to "com.android.chrome",
        "chrome" to "com.android.chrome",
        "Google Chrome" to "com.android.chrome",
        
        "应用商店" to "com.android.vending",
        "Play Store" to "com.android.vending",
        "GooglePlayStore" to "com.android.vending",
        "Google Play Store" to "com.android.vending",
        "Google-Play-Store" to "com.android.vending",
        
        "AudioRecorder" to "com.android.soundrecorder",
        "audiorecorder" to "com.android.soundrecorder",

        // === Social & Communication (CN) ===
        "微信" to "com.tencent.mm",
        "WeChat" to "com.tencent.mm",
        "wechat" to "com.tencent.mm",
        
        "QQ" to "com.tencent.mobileqq",
        "QQ邮箱" to "com.tencent.androidqqmail",
        
        "微博" to "com.sina.weibo",
        "Weibo" to "com.sina.weibo",
        
        "小红书" to "com.xingin.xhs",
        "Xiaohongshu" to "com.xingin.xhs",
        "RED" to "com.xingin.xhs",
        
        "知乎" to "com.zhihu.android",
        "Zhihu" to "com.zhihu.android",
        
        "豆瓣" to "com.douban.frodo",

        // === Shopping & Life (CN) ===
        "支付宝" to "com.eg.android.AlipayGphone",
        "Alipay" to "com.eg.android.AlipayGphone",
        
        "淘宝" to "com.taobao.taobao",
        "Taobao" to "com.taobao.taobao",
        "淘宝闪购" to "com.taobao.taobao",
        
        "京东" to "com.jingdong.app.mall",
        "JD" to "com.jingdong.app.mall",
        "京东秒送" to "com.jingdong.app.mall",
        
        "拼多多" to "com.xunmeng.pinduoduo",
        "Pinduoduo" to "com.xunmeng.pinduoduo",
        
        "美团" to "com.sankuai.meituan",
        "Meituan" to "com.sankuai.meituan",
        
        "饿了么" to "me.ele",
        "Eleme" to "me.ele",
        
        "大众点评" to "com.dianping.v1",
        "Dianping" to "com.dianping.v1",
        
        "闲鱼" to "com.taobao.idlefish",
        "Xianyu" to "com.taobao.idlefish",
        
        "肯德基" to "com.yek.android.kfc.activitys",
        "美柚" to "com.lingan.seeyou",
        "贝壳找房" to "com.lianjia.beike",
        "安居客" to "com.anjuke.android.app",
        "同花顺" to "com.hexin.plat.android",

        // === Travel (CN) ===
        "携程" to "ctrip.android.view",
        "铁路12306" to "com.MobileTicket",
        "12306" to "com.MobileTicket",
        "去哪儿" to "com.Qunar",
        "去哪儿旅行" to "com.Qunar",
        "滴滴出行" to "com.sdu.did.psnger",

        // === Entertainment (CN) ===
        "抖音" to "com.ss.android.ugc.aweme",
        "Douyin" to "com.ss.android.ugc.aweme",
        
        "快手" to "com.smile.gifmaker",
        "Kuaishou" to "com.smile.gifmaker",
        
        "哔哩哔哩" to "tv.danmaku.bili",
        "Bilibili" to "tv.danmaku.bili",
        "B站" to "tv.danmaku.bili",
        "bilibili" to "tv.danmaku.bili",
        
        "网易云音乐" to "com.netease.cloudmusic",
        "NetEase Music" to "com.netease.cloudmusic",
        
        "QQ音乐" to "com.tencent.qqmusic",
        "QQ Music" to "com.tencent.qqmusic",
        "汽水音乐" to "com.luna.music",
        "喜马拉雅" to "com.ximalaya.ting.android",
        
        "爱奇艺" to "com.qiyi.video",
        "iQIYI" to "com.qiyi.video",
        
        "腾讯视频" to "com.tencent.qqlive",
        "Tencent Video" to "com.tencent.qqlive",
        
        "优酷" to "com.youku.phone",
        "Youku" to "com.youku.phone",
        "优酷视频" to "com.youku.phone",
        "芒果TV" to "com.hunantv.imgo.activity",
        "红果短剧" to "com.phoenix.read",

        // === Reading (CN) ===
        "番茄小说" to "com.dragon.read",
        "番茄免费小说" to "com.dragon.read",
        "七猫免费小说" to "com.kmxs.reader",
        "腾讯新闻" to "com.tencent.news",
        "今日头条" to "com.ss.android.article.news",

        // === Tools & Navigation (CN) ===
        "高德地图" to "com.autonavi.minimap",
        "Amap" to "com.autonavi.minimap",
        "百度地图" to "com.baidu.BaiduMap",
        "Baidu Map" to "com.baidu.BaiduMap",
        "钉钉" to "com.alibaba.android.rimet",
        "DingTalk" to "com.alibaba.android.rimet",
        "飞书" to "com.ss.android.lark",
        "Feishu" to "com.ss.android.lark",
        "Lark" to "com.ss.android.lark",
        "豆包" to "com.larus.nova",

        // === Games ===
        "星穹铁道" to "com.miHoYo.hkrpg",
        "崩坏：星穹铁道" to "com.miHoYo.hkrpg",
        "恋与深空" to "com.papegames.lysk.cn",

        // === International Apps ===
        "YouTube" to "com.google.android.youtube",
        "Gmail" to "com.google.android.gm",
        "gmail" to "com.google.android.gm",
        "GoogleMail" to "com.google.android.gm",
        "Google Mail" to "com.google.android.gm",
        
        "Google Maps" to "com.google.android.apps.maps",
        "Maps" to "com.google.android.apps.maps",
        "地图" to "com.google.android.apps.maps",
        "GoogleMaps" to "com.google.android.apps.maps",
        "googlemaps" to "com.google.android.apps.maps",
        "google maps" to "com.google.android.apps.maps",
        
        "GoogleChat" to "com.google.android.apps.dynamite",
        "Google Chat" to "com.google.android.apps.dynamite",
        "Google-Chat" to "com.google.android.apps.dynamite",
        
        "GoogleDocs" to "com.google.android.apps.docs.editors.docs",
        "Google Docs" to "com.google.android.apps.docs.editors.docs",
        "googledocs" to "com.google.android.apps.docs.editors.docs",
        "google docs" to "com.google.android.apps.docs.editors.docs",
        
        "Google Drive" to "com.google.android.apps.docs",
        "Google-Drive" to "com.google.android.apps.docs",
        "google drive" to "com.google.android.apps.docs",
        "google-drive" to "com.google.android.apps.docs",
        "GoogleDrive" to "com.google.android.apps.docs",
        "Googledrive" to "com.google.android.apps.docs",
        "googledrive" to "com.google.android.apps.docs",
        
        "GoogleFit" to "com.google.android.apps.fitness",
        "googlefit" to "com.google.android.apps.fitness",
        "GoogleKeep" to "com.google.android.keep",
        "googlekeep" to "com.google.android.keep",
        
        "Google Play Books" to "com.google.android.apps.books",
        "Google-Play-Books" to "com.google.android.apps.books",
        "google play books" to "com.google.android.apps.books",
        "google-play-books" to "com.google.android.apps.books",
        "GooglePlayBooks" to "com.google.android.apps.books",
        "googleplaybooks" to "com.google.android.apps.books",
        
        "GoogleSlides" to "com.google.android.apps.docs.editors.slides",
        "Google Slides" to "com.google.android.apps.docs.editors.slides",
        "Google-Slides" to "com.google.android.apps.docs.editors.slides",
        "GoogleTasks" to "com.google.android.apps.tasks",
        "Google Tasks" to "com.google.android.apps.tasks",
        "Google-Tasks" to "com.google.android.apps.tasks",

        "Twitter" to "com.twitter.android",
        "twitter" to "com.twitter.android",
        "X" to "com.twitter.android",
        
        "Facebook" to "com.facebook.katana",
        "Instagram" to "com.instagram.android",
        "WhatsApp" to "com.whatsapp",
        "Whatsapp" to "com.whatsapp",
        "Telegram" to "org.telegram.messenger",
        "Spotify" to "com.spotify.music",
        "Netflix" to "com.netflix.mediaclient",
        "Amazon" to "com.amazon.mShop.android.shopping",
        
        "Bluecoins" to "com.rammigsoftware.bluecoins",
        "bluecoins" to "com.rammigsoftware.bluecoins",
        "Broccoli" to "com.flauschcode.broccoli",
        "broccoli" to "com.flauschcode.broccoli",
        "Booking.com" to "com.booking",
        "Booking" to "com.booking",
        "booking.com" to "com.booking",
        "booking" to "com.booking",
        "BOOKING.COM" to "com.booking",
        
        "Duolingo" to "com.duolingo",
        "duolingo" to "com.duolingo",
        "Expedia" to "com.expedia.bookings",
        "expedia" to "com.expedia.bookings",
        "Joplin" to "net.cozic.joplin",
        "joplin" to "net.cozic.joplin",
        "McDonald" to "com.mcdonalds.app",
        "mcdonald" to "com.mcdonalds.app",
        "Osmand" to "net.osmand",
        "osmand" to "net.osmand",
        "PiMusicPlayer" to "com.Project100Pi.themusicplayer",
        "pimusicplayer" to "com.Project100Pi.themusicplayer",
        "Quora" to "com.quora.android",
        "quora" to "com.quora.android",
        "Reddit" to "com.reddit.frontpage",
        "reddit" to "com.reddit.frontpage",
        "RetroMusic" to "code.name.monkey.retromusic",
        "retromusic" to "code.name.monkey.retromusic",
        "temu" to "com.einnovation.temu",
        "Temu" to "com.einnovation.temu",
        "Tiktok" to "com.zhiliaoapp.musically",
        "tiktok" to "com.zhiliaoapp.musically",
        "VLC" to "org.videolan.vlc",
        "keep" to "com.gotokeep.keep"
    )

    fun getPackageName(appName: String): String? {
        // 1. Exact match
        appMap[appName]?.let { return it }
        
        // 2. Case insensitive match
        appMap.entries.find { it.key.equals(appName, ignoreCase = true) }?.let { return it.value }
        
        // 3. Partial match (optional, but risky if names are short)
        // appMap.entries.find { it.key.contains(appName, ignoreCase = true) }?.let { return it.value }
        
        return null 
    }
}
