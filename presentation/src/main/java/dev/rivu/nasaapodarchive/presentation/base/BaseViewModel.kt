package dev.rivu.nasaapodarchive.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Publisher

abstract class BaseViewModel<I : MviIntent, S : MviState, A : MviAction, R : MviResult> : ViewModel(),
    MviViewModel<I, S> {

    private val intentsSubject: PublishSubject<I> = PublishSubject.create()
    private val statesLiveData: LiveData<S> by lazy {
        LiveDataReactiveStreams.fromPublisher<S>(statesObservable)
    }
    val statesObservable: Flowable<S> by lazy {
        compose()
    }

    abstract fun intentFilter(): FlowableTransformer<I, I>

    override fun processIntents(intents: Observable<I>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): LiveData<S> {
        return statesLiveData
    }

    abstract fun actionFromIntent(intent: I): A


    private fun compose(): Flowable<S> {
        return intentsSubject
            .toFlowable(BackpressureStrategy.LATEST)
            .compose(intentFilter())
            .map(this::actionFromIntent)
            .compose(actionProcessor.transformFromAction())
            .scan(initialState(), reducer())
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    protected abstract val actionProcessor: MviActionProcessor<A, R>

    abstract fun reducer(): BiFunction<S, R, S>

    abstract fun initialState(): S
}