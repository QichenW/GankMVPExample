package com.imallan.gankmvp

import android.app.Application
import com.facebook.stetho.Stetho
import com.imallan.gankmvp.di.component.ApplicationComponent
import com.imallan.gankmvp.di.component.DaggerApplicationComponent
import com.imallan.gankmvp.di.module.ApiModule
import com.imallan.gankmvp.di.module.ApplicationModule
import com.imallan.gankmvp.extensions.logd
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class GankMVPApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
        private val componentMap: MutableMap<String, Any> = mutableMapOf()

        fun <T> getComponent(key: String, generate: ApplicationComponent.() -> T): T {
            if (componentMap.containsKey(key)) {
                logd("Get Component, key: $key hit")
                @Suppress("UNCHECKED_CAST")
                return componentMap[key] as T
            }
            logd("Get Component, key: $key miss, creating")
            val t = applicationComponent.generate()
            componentMap.put(key, t!!)
            return t
        }

        fun removeComponent(key: String) {
            logd("Destroy Component: $key")
            componentMap.remove(key)
        }
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule(BuildConfig.DEBUG))
                .build()

        Realm.setDefaultConfiguration(RealmConfiguration.Builder(this).build())

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build())
    }

}
