package me.typ.zuiyou.data.model


/**
 * 由注解处理器生产的json文件  见assets/destination.json
 */
data class Destination(
    val asStarter: Boolean,
    val className: String,
    val id: Int,
    val isFragment: Boolean,
    val needLogin: Boolean,
    val pageUrl: String
)

/**
 * 主页底部导航栏配置文件 见assets/main_tabs_config.json
 * 可以由后端动态下发  实现更灵活的配置
 */
data class BottomBar(
    val activeColor: String,
    val inActiveColor: String,
    val selectTab: Int,
    val tabs: List<BottomTab>
)

data class BottomTab(
    val enable: Boolean,
    val index: Int,
    val pageUrl: String,
    val size: Int,
    val tintColor: String,
    val title: String
)

data class TabMo(
    val enable: Boolean,
    val index: Int,
    val activeSize: Int,
    val normalSize: Int,
    val activeColor: String,
    val normalColor: String,
    val tag: String,
    val title: String
)