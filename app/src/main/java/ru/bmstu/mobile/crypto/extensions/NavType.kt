package ru.bmstu.mobile.crypto.extensions

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.bmstu.mobile.crypto.model.DataX

class DataParamType : NavType<DataX?>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): DataX? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): DataX {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: DataX?) {
        bundle.putParcelable(key, value)
    }
}
