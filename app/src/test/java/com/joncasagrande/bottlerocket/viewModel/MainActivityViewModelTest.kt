package com.joncasagrande.bottlerocket.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.util.Utils
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var listStoreObserver: Observer<List<Store>>
    @Mock
    private lateinit var errorMessageObserver: Observer<String>


    private lateinit var mainActivityViewModel: MainActivityViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel()
        mainActivityViewModel.listStore.observeForever(listStoreObserver)
        mainActivityViewModel.errorMessage.observeForever(errorMessageObserver)
    }

    @Test
    fun verifyChangeListStore() {
        //given
            val listStoreMocked = Utils.getListStore()
        //when
        mainActivityViewModel.listStore.value = listStoreMocked

        //then
        BDDMockito.then(listStoreObserver).should().onChanged(listStoreMocked)
    }

    @Test
    fun verifyChangeErrorMessage() {
        //given
        val errorMessage= "ERROR"
        //when
        mainActivityViewModel.errorMessage.value = errorMessage
        //then
        BDDMockito.then(errorMessageObserver).should().onChanged(errorMessage)
    }


}