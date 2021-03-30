package com.example.hilt_mvvm.core.network

import okhttp3.ResponseBody
import org.jetbrains.annotations.Nullable
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


/**
 * Created By @HashimHabtur on 12/07/2020 AD
 */


/**
 * https://github.com/square/retrofit/issues/1554#issuecomment-178633697
 * https://github.com/square/retrofit/issues/1554#issuecomment-212941985
 */
class NullOnEmptyConverterFactory private constructor() : Converter.Factory() {
    @Nullable
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter<ResponseBody, Any?> { body ->
            if (body.contentLength() == 0L) {
                null
            } else delegate.convert(body)
        }
    }

    companion object {
        fun create(): Converter.Factory {
            return NullOnEmptyConverterFactory()
        }
    }
}
