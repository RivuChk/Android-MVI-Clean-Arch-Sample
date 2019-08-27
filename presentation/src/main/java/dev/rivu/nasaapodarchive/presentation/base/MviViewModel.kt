package dev.rivu.nasaapodarchive.presentation.base

import androidx.lifecycle.LiveData
import io.reactivex.Observable

interface MviViewModel<I : MviIntent, S : MviState> {
    fun processIntents(intents: Observable<I>)

    fun states(): LiveData<S>
}