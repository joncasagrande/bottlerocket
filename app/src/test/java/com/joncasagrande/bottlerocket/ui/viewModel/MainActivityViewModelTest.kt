package com.joncasagrande.bottlerocket.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.ui.model.UiState
import com.joncasagrande.bottlerocket.util.Utils
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListStoreViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var listStoreObserver: Observer<UiState<List<Store>>>

    @Mock
    private lateinit var repo: StoreRepo

    private lateinit var viewModel: ListStoreViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ListStoreViewModel(repo)
        viewModel.listStore.observeForever(listStoreObserver)
    }

    /*@Test
    fun verifyChangeListStore() {
        //given
        val listStoreMocked = Utils.getListStore()
        val uiSate = UiState.Display(listStoreMocked)
        Mockito.`when`(repo.loadStoreFromAPI()).then {
            Single.just(
                uiSate
            )
        }

        //when
        viewModel.loadStore()

        //then
        BDDMockito.then(listStoreObserver).should().onChanged(uiSate)
    }*/
}