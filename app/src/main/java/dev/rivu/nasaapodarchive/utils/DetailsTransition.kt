package dev.rivu.nasaapodarchive.utils

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet

class DetailsTransition : TransitionSet() {
    fun DetailsTransition() {
        ordering = ORDERING_TOGETHER
        addTransition( ChangeBounds()).
            addTransition( ChangeTransform()).
            addTransition( ChangeImageTransform())
    }
}