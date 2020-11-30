package com.renkataoka.dubugger.module.rubberduck.contract;

import com.renkataoka.dubugger.entity.ChatItems;

import java.util.List;

/**
 * ラバーダックデバッグ画面のContractクラス。
 */
public class RubberDuckContract {
    public static final String RUBBER_DUCK = "rubberduck";
    public static final String USER = "user";

    private RubberDuckContract() {
    }

    public interface View extends com.renkataoka.viper.View {
        void initRecyclerView();
        void setChatItems(List<ChatItems> chatItems);
    }

    public interface Interactor extends com.renkataoka.viper.Interactor {
        void setParentTableKey(int key);
        void addChatItem(String content, String attribute);
        void deleteChatItem(int id);
        void deleteAllChatItems();
        void readChatItems();
    }

    public interface InteractorCallback extends com.renkataoka.viper.InteractorCallback {
        void onAddChatItemCompleted();
        void onDeleteChatItemCompleted();
        void onReadChatItemsCompleted(List<ChatItems> chatItems);
    }

    public interface Presenter extends com.renkataoka.viper.Presenter {
        void onCreate(int id);
        void onClickAddChatButton(String inputContent, String attribute);
        void onClickDeleteAllMenu();
    }

    public interface Router extends com.renkataoka.viper.Router {
    }
}
