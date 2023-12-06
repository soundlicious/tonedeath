package dev.pabloexposito.common

interface ResourceResolver<T> {
    operator fun invoke(resInfo: T): Result<Int>
}