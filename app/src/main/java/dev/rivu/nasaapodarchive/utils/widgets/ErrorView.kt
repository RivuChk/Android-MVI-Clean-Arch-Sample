package dev.rivu.nasaapodarchive.utils.widgets

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import dev.rivu.nasaapodarchive.R
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView : LinearLayout {

    private var errorListener: ErrorListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    fun setErrorMessage(errorMessage: String) {
        txtErrorMessage.text = errorMessage
    }

    private fun init() {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        LayoutInflater.from(context).inflate(R.layout.view_error, this)
        buttonReload.setOnClickListener(::onReloadButtonClick)
    }

    fun onReloadButtonClick(ignore: View) {
        errorListener?.let { errorListener?.onReloadData() }
    }

    fun setErrorListener(errorListener: ErrorListener) {
        this.errorListener = errorListener
    }

    interface ErrorListener {
        fun onReloadData()
    }
}