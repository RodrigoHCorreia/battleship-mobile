package battleship.mobile.utils

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class LoggedMutableState<T>(private val at: String, private val delegate: MutableState<T>)
    : MutableState<T> by delegate {

    override var value: T
        get() {
            Log.v(TAG, "$TAG: @$at: read value = ${delegate.value}")
            return delegate.value
        }
        set(value) {
            Log.v(TAG, "$TAG: @$at: write value = $value")
            delegate.value = value
        }
}

fun <T> loggableMutableStateOf(at: String, value: T): MutableState<T> =
    LoggedMutableState(at, mutableStateOf(value))

