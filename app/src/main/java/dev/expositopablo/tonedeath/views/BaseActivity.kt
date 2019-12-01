package dev.expositopablo.tonedeath.views

import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity : AppCompatActivity() {
    private val modules: MutableList<Module> = mutableListOf()

    protected fun loadKoinModules(module: Module) {
        modules.add(module)
        org.koin.core.context.loadKoinModules(module)
    }

    protected fun loadKoinModules(moduleList: List<Module>) {
        modules.addAll(moduleList)
        org.koin.core.context.loadKoinModules(moduleList)
    }

    override fun onDestroy() {
        unloadKoinModules(modules)
        super.onDestroy()
    }
}