package com.grap.dao

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import java.net.URL


@Component
public class NetworkDAO {
    /**
     * Return the data found at the given endpoint
     * @param endpoint a URL or other location where we can find data.
     * @return All of the data returned as one string.
     * @throws Exception
     */

    @Throws(Exception::class)
    open fun request(endpoint: String?): String? {
        var responseBody = ""
        val client = OkHttpClient()
        val mediaType: MediaType = MediaType.parse("application/octet-stream")
        val body: RequestBody = RequestBody.create(mediaType, "{}")
        val request: Request = Builder()
                .url(endpoint)
                .get()
                .build()
        client.newCall(request).execute().use({ response -> responseBody = response.body().string() })
        return responseBody
    }

    @Throws(java.lang.Exception::class)
    fun requestImage(endpoint: String): URL? {
        // Replace SOME URL with actual URL we will be pointing to
        return URL("SOME URL$endpoint")
    }
}