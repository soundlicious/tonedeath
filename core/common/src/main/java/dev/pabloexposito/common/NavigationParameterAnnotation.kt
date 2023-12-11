package dev.pabloexposito.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InitialParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FinalParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PinyinParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SelectedToneParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScoreParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoodToneParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VoiceGenderParameter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VoiceNumberParameter