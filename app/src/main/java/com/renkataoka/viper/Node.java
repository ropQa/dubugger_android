package com.renkataoka.viper;

/**
 * コンポーネントの依存性を取り除く性質。
 */
public interface Node {
    /**
     * 依存オブジェクトを取り除く。
     */
    void onDisassemble();
}
