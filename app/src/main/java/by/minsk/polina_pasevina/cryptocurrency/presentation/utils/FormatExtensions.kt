package by.minsk.polina_pasevina.cryptocurrency.presentation.utils

import java.text.DecimalFormat

internal val moneyFormat = DecimalFormat("#,###.00")

fun Any.toMoneyFormat() = moneyFormat.format(this)