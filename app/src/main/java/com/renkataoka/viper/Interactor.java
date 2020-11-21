package com.renkataoka.viper;

/**
 * Presenterからの指示を受け、実際の処理を行うコンポーネント。
 * 処理結果は、InteractorCallbackに返す。
 */
public interface Interactor extends Node {
    /**
     * 実行結果を受け取るCallbackを指定する。
     *
     * @param callback
     */
    void setInteractorCallback(InteractorCallback callback);
}
