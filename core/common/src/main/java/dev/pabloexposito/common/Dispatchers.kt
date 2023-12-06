package dev.pabloexposito.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: TDDispatchers)

enum class TDDispatchers {
    Default,
    IO,
}
