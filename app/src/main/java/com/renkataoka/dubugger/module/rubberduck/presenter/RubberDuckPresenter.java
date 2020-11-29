package com.renkataoka.dubugger.module.rubberduck.presenter;

import com.renkataoka.dubugger.entity.ChatItems;
import com.renkataoka.dubugger.module.rubberduck.contract.RubberDuckContract;

import java.util.List;

/**
 * RubberDuck画面のPresenterクラス。
 */
public class RubberDuckPresenter implements RubberDuckContract.Presenter, RubberDuckContract.InteractorCallback {

    //各コンポーネントをインターフェースとして持つ。
    private RubberDuckContract.View view;
    private RubberDuckContract.Interactor interactor;
    private RubberDuckContract.Router router;

    /**
     * コンストラクタ
     *
     * @param view
     * @param interactor
     * @param router
     */
    public RubberDuckPresenter(RubberDuckContract.View view,
                               RubberDuckContract.Interactor interactor,
                               RubberDuckContract.Router router) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
    }

    @Override
    public void onCreate(int position) {
        if (interactor != null) {
            interactor.setMasterTable(position);
            interactor.readChatItems();
        }
    }

    /**
     * アイテム追加が完了したら、再度dbを読み込む。
     */
    @Override
    public void onAddChatItemCompleted() {
        if (interactor != null) {
            interactor.readChatItems();
        }
    }

    /**
     * アイテム削除が完了したら、再度dbを読み込む。
     */
    @Override
    public void onDeleteChatItemCompleted() {
        if (interactor != null) {
            interactor.readChatItems();
        }
    }

    /**
     * dbからアイテムを読み込んだら、Viewに渡す。
     *
     * @param chatItems
     */
    @Override
    public void onReadChatItemsCompleted(List<ChatItems> chatItems) {
        if (chatItems != null) {
            if (view != null) {
                view.setChatItems(chatItems);
            }
        }
    }

    /**
     * チャットを追加する。
     *
     * @param inputContent
     * @param attribute
     */
    @Override
    public void onClickAddChatButton(String inputContent, String attribute) {
        if (inputContent.length() != 0 && attribute != null) {
            if (interactor != null) {
                interactor.addChatItem(inputContent, attribute);
            }
        }
    }

    /**
     * チャットを全て削除する。
     */
    @Override
    public void onClickDeleteAllMenu() {
        if (interactor != null) {
            interactor.deleteAllChatItems();
        }
    }

    /**
     * 各コンポーネントへの参照を破棄し、破棄させる。
     */
    @Override
    public void disassembleModules() {
        if (view != null) {
            view.onDisassemble();
            view = null;
        }

        if (interactor != null) {
            interactor.onDisassemble();
            interactor = null;
        }

        if (router != null) {
            router.onDisassemble();
            router = null;
        }
    }
}
