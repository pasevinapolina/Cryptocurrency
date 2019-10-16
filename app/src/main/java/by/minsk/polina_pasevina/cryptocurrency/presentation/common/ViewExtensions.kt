package by.minsk.polina_pasevina.cryptocurrency.presentation.common

import android.view.View

fun View.updateVisibility(show: Boolean) {
    val visibility = if (show) View.VISIBLE else View.GONE
    if (this.visibility != visibility) {
        this.visibility = visibility
    }
}