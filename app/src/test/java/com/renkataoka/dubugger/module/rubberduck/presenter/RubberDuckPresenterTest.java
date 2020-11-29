package com.renkataoka.dubugger.module.rubberduck.presenter;

import com.renkataoka.dubugger.module.rubberduck.interactor.MockRubberDuckInteractor;
import com.renkataoka.dubugger.module.rubberduck.router.MockRubberDuckRouter;
import com.renkataoka.dubugger.module.rubberduck.view.MockRubberDuckView;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * RubberDuckPresenterの単体テスト。
 */
public class RubberDuckPresenterTest {
    private RubberDuckPresenter presenter;
    private MockRubberDuckView mockView;
    private MockRubberDuckInteractor mockInteractor;
    private MockRubberDuckRouter mockRouter;

    @Before
    public void setUp() {
        mockView = new MockRubberDuckView();
        mockInteractor = new MockRubberDuckInteractor();
        mockRouter = new MockRubberDuckRouter();
        presenter = new RubberDuckPresenter(mockView, mockInteractor, mockRouter);

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
    public void onAddChatItemCompleted() {
        presenter.onAddChatItemCompleted();

        assertEquals(1, mockInteractor.getCountReadChatItems());
    }

    @Test
    public void onDeleteChatItemCompleted() {
        presenter.onDeleteChatItemCompleted();

        assertEquals(1, mockInteractor.getCountReadChatItems());
    }

    @Test
    public void onReadChatItemsCompleted() {
        presenter.onReadChatItemsCompleted(new ArrayList<>());

        assertEquals(1, mockView.getCountSetChatItems());
    }

    @Test
    public void onClickAddChatButtonSuccess() {
        String content = "content";
        String attribute = "attribute";
        presenter.onClickAddChatButton(content, attribute);

        assertEquals(1, mockInteractor.getCountAddChatItem());
    }

    @Test
    public void onClickAddChatButtonFailure() {
        String content = "";
        String attribute = "attribute";
        presenter.onClickAddChatButton(content, attribute);

        assertEquals(0, mockInteractor.getCountAddChatItem());
    }

    @Test
    public void onClickDeleteAllMenu() {
        presenter.onClickDeleteAllMenu();

        assertEquals(1, mockInteractor.getCountDeleteAllChatItems());
    }

    @Test
    public void disassembleModules() {
        presenter.disassembleModules();

        assertEquals(1, mockView.getCountOnDisassemble());
        assertEquals(1, mockInteractor.getCountOnDisassemble());
        assertEquals(1, mockRouter.getCountOnDisassemble());
    }
}