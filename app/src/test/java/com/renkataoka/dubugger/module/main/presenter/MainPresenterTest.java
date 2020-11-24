package com.renkataoka.dubugger.module.main.presenter;

import com.renkataoka.dubugger.module.main.interactor.MockMainInteractor;
import com.renkataoka.dubugger.module.main.router.MockMainRouter;
import com.renkataoka.dubugger.module.main.view.MockMainView;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class MainPresenterTest {
    private MainPresenter presenter;
    private MockMainView mockView;
    private MockMainInteractor mockInteractor;
    private MockMainRouter mockRouter;

    @Before
    public void setUp() {
        mockView = new MockMainView();
        mockInteractor = new MockMainInteractor();
        mockRouter = new MockMainRouter();
        presenter = new MainPresenter(mockView, mockInteractor, mockRouter);

        initMock();
    }

    @After
    public void tearDown() {
        initMock();
    }

    private void initMock() {
        mockView.clear();
        mockInteractor.clear();
        mockRouter.clear();
    }

    @Test
    public void onAddButtonClicked() {
        String text = "testText";
        presenter.onAddButtonClicked(text);

        assertEquals(1, mockInteractor.getCountAddToDebugItem());
    }

    @Test
    public void disassembleModules() {
        presenter.disassembleModules();

        assertEquals(1, mockView.getCountOnDisassemble());
        assertEquals(1, mockInteractor.getCountOnDisassemble());
        assertEquals(1, mockRouter.getCountOnDisassemble());
    }
}