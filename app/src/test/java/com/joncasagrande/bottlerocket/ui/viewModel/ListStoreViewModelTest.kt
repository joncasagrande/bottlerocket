package com.joncasagrande.bottlerocket.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.model.StoreRest
import com.joncasagrande.bottlerocket.repo.StoreRepo
import com.joncasagrande.bottlerocket.ui.model.UiState
import com.joncasagrande.bottlerocket.util.RxImmediateSchedulerRule
import com.joncasagrande.bottlerocket.util.Utils
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import junit.framework.TestCase
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ListStoreViewModelTest : TestCase(){
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    private lateinit var listStoreObserver: Observer<UiState<List<Store>>>

    @Mock
    private lateinit var repo: StoreRepo

    private lateinit var viewModel: ListStoreViewModel


    @Before
    public override fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ListStoreViewModel( repo)
        viewModel.listStore.observeForever(listStoreObserver)
    }

    @Test
    fun verifyChangeListStore() {
        //given
        val listStoreMocked = Utils.getListStore()
        val uiState = UiState.Display(listStoreMocked)
        val delayer = PublishSubject.create<UiState.Loading>()
        `when`(repo.loadStoreFromAPI()).then {
            Single.just(
                StoreRest(listStoreMocked)
            ).delaySubscription(delayer)
        }

        //when
        viewModel.loadStore()

        //then
        assertEquals((viewModel.listStore.value as UiState.Loading), UiState.Loading)
        delayer.onComplete()
        assertEquals((viewModel.listStore.value as UiState.Display), uiState)
    }

    @Test
    fun verifyChangeListStoreWhenHasNoConneciton() {
        //given
        val listStoreMocked = Utils.getListStore()
        val delayer = PublishSubject.create<UiState.Loading>()
        `when`(repo.loadStoreFromAPI()).then {
            Single.error<Throwable>(
                Throwable()
            ).delaySubscription(delayer)
        }
        `when`(repo.loadStoreFromDB()).then { listStoreMocked }

        //when
        viewModel.loadStore()

        //then
        assertEquals((viewModel.listStore.value as UiState.Loading), UiState.Loading)
        delayer.onComplete()
        assertEquals((viewModel.listStore.value as UiState.Error), UiState.Error())
    }

}