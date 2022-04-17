package com.joncasagrande.bottlerocket.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.joncasagrande.bottlerocket.model.StoreRest
import com.joncasagrande.bottlerocket.network.api.StoreAPI
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class NetworkApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: StoreAPI
    private lateinit var mockWebServer: MockWebServer
    private lateinit var subscriber: TestObserver<StoreRest>

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        subscriber = TestObserver()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StoreAPI::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldRequestTripList() {
        // given
        enqueueResponse()

        // when
        service.getStore().subscribe(subscriber)

        //then
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, CoreMatchers.`is`("/stores.json"))
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }

    private fun enqueueResponse(headers: Map<String, String> = emptyMap()) {
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        getJson()?.let { mockResponse.setBody(it) }?.let { mockWebServer.enqueue(it) }
    }

    private fun getJson(): String? {
        return this::class.java.classLoader?.getResource("api-response/api_response.json")?.readText(Charsets.UTF_8)
    }
}