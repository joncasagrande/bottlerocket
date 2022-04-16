package com.joncasagrande.bottlerocket.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.util.Utils
import com.joncasagrande.bottlerocket.utils.SchedulerProvider
import io.reactivex.schedulers.Schedulers
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
    private lateinit var errorMessageObserver: Observer<Boolean>

    @Mock
    private lateinit var repo : StoreRepo

    @Mock
    private lateinit var schedulerProvider : SchedulerProvider

    private lateinit var mainActivityViewModel: MainActivityViewModel



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel(schedulerProvider,repo)
        mainActivityViewModel.listStore.observeForever(listStoreObserver)
        mainActivityViewModel.errorMessage.observeForever(errorMessageObserver)
    }

    @Test
    fun verifyChangeListStore() {
        //given
        val listStoreMocked = Utils.getListStore()
        Mockito.`when`(mainActivityViewModel.loadStore())

        //when
        //mainActivityViewModel.listStore.value = listStoreMocked

        //then
        BDDMockito.then(listStoreObserver).should().onChanged(listStoreMocked)
    }

    @Test
    fun verifyChangeErrorMessage() {
        //given
        val errorMessage= false

        //when
        mainActivityViewModel.errorMessage.value = errorMessage

        //then
        BDDMockito.then(errorMessageObserver).should().onChanged(errorMessage)
    }


}