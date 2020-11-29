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
    }

    public interface InteractorCallback extends com.renkataoka.viper.InteractorCallback {
    }

    public interface Presenter extends com.renkataoka.viper.Presenter {
        void onClickAddChatButton(String inputContent, String attribute);
    }

    public interface Router extends com.renkataoka.viper.Router {
    }
}
