package dev.rivu.nasaapodarchive.presentation.base

import io.reactivex.Observable

interface MviView<I: MviIntent, S: MviState> {
    fun intents(): Observable<I>

    fun render(state: S)
}