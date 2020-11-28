package com.renkataoka.viper;

/**
 * コンポーネント間の処理を繋ぐコンポーネント。
 */
public interface Presenter {
    /**
     * 依存オブジェクトの結合を解除する。
     */
    void disassembleModules();
}
