package me.typ.zuiyou.util

import android.content.ComponentName
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.fragment.FragmentNavigator
import me.typ.scaffold.navigator.FixFragmentNavigator
import me.typ.zuiyou.data.config.AppConfig

object NavGraphBuilder {

    fun build(
        activity: FragmentActivity,
        childFragmentManager: FragmentManager,
        controller: NavController,
        containerId: Int
    ) {

        val provider = controller.navigatorProvider
        //NavGraphNavigator也是页面路由导航器的一种，只不过他比较特殊。
        //它只为默认的展示页提供导航服务,但真正的跳转还是交给对应的navigator来完成的
        val navGraph = NavGraph(NavGraphNavigator(provider))

        //使用hide/show的FragmentNavigator
        val fragmentNavigator = FixFragmentNavigator(activity, childFragmentManager, containerId)
        provider.addNavigator(fragmentNavigator)
        val activityNavigator = provider.getNavigator(ActivityNavigator::class.java)
        val destConfig = AppConfig.getDestConfig()
        destConfig.values.forEach { node ->
            if (node.isFragment) {
                val destination: FragmentNavigator.Destination =
                    fragmentNavigator.createDestination()
                destination.id = node.id
                destination.className = node.className
                destination.addDeepLink(node.pageUrl)
                navGraph.addDestination(destination)
            } else {
                val destination = activityNavigator.createDestination()
                destination.id = node.id
                destination.setComponentName(
                    ComponentName(
                        AppGlobals.getApplication().packageName,
                        node.className
                    )
                )
                destination.addDeepLink(node.pageUrl)
                navGraph.addDestination(destination)
            }

            //给APP页面导航结果图 设置一个默认的展示页的id
            if (node.asStarter) {
                navGraph.startDestination = node.id
            }
        }
        controller.graph = navGraph
    }

}