package com.renkataoka.dubugger.module.main.contract;

import com.renkataoka.dubugger.BuildConfig;
import com.renkataoka.dubugger.entity.ToDebugItemsAndChatItems;

import java.util.List;

/**
 * メイン画面のContractクラス。
 * Contractは中継用プロトコルであり、各コンポーネントに実装すべきメソッドを提供する。
 */
public class MainContract {
    public static final int REQUEST_CODE = 100;//MainActivityからRubberDuckActivityを開始するためのリクエストコード。
    public static final String PARENT_TABLE_ID = BuildConfig.APPLICATION_ID + ".PARENT_TABLE_ID";//親テーブルの主キーを伝えるために使う。

    private MainContract() {
    }

    public interface View extends com.renkataoka.viper.View {
        void initRecyclerView();
        void setToDebugItems(List<ToDebugItemsAndChatItems> toDebugItems);
    }

    public interface Interactor extends com.renkataoka.viper.Interactor {
        void addToDebugItem(String content);
        void deleteToDebugItem(int id);
        void deleteAllToDebugItems();
        void readToDebugItems();
    }

    public interface InteractorCallback extends com.renkataoka.viper.InteractorCallback {
        void onReadToDebugItemsCompleted(List<ToDebugItemsAndChatItems> toDebugItems);
        void onAddToDebugItemCompleted();
        void onDeleteToDebugItemCompleted();
    }

    public interface Presenter extends com.renkataoka.viper.Presenter {
        void onCreate();
        void onClickAddButton(String inputContent);
        void onClickDeleteAllMenu();
        void onClickToDebugItem(int id);
    }

    public interface Router extends com.renkataoka.viper.Router {
        void startRubberDuckActivity(int id);
    }
}
