package com.joncasagrande.bottlerocket.web

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class BaseHttpTest {

    lateinit var baseHttp: BaseHttp
    @Before
    fun setUp() {
        baseHttp = BaseHttp()
    }

    @Test
    fun `getHttpClient$app_debug`() {
        assertNotNull(baseHttp)
        assertNotNull(baseHttp.httpClient)
    }
}