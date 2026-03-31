package com.streamchatreactnative

import android.graphics.Color
import android.view.View
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class StreamShimmerViewManager : SimpleViewManager<View>() {

    companion object {
        const val REACT_CLASS = "StreamShimmerView"
    }

    override fun getName(): String = REACT_CLASS

    override fun createViewInstance(reactContext: ThemedReactContext): View {
        return View(reactContext).apply {
            setBackgroundColor(Color.TRANSPARENT)
        }
    }

    @ReactProp(name = "shimmerColors")
    fun setShimmerColors(view: View, colors: String?) {
        // placeholder
    }

    @ReactProp(name = "duration")
    fun setDuration(view: View, duration: Int) {
        // placeholder
    }
}
