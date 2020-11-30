# ラバーダックデバッグ画面

## 概要

* 自分と相手役の間で会話を構築する。

## クラス

### [RubberDuckContract](../../app/src/main/java/com/renkataoka/dubugger/module/rubberduck/contract/RubberDuckContract.java)

* Contractクラス。
* VIPERモジュール間の連携APIを定義。

### [RubberDuckActivity](../../app/src/main/java/com/renkataoka/dubugger/module/rubberduck/view/RubberDuckActivity.java)

* VIPERモジュールのViewクラス。
* ユーザーとラバーダックの会話画面を表示する。
* 目的リマインドダイアログを表示する。

#### [RemindAimDialogFragment]()

* 目的リマインドダイアログ。
* 深追いし過ぎているときに当初の目的を思い出させる。

### [RubberDuckInteractor](../../app/src/main/java/com/renkataoka/dubugger/module/rubberduck/interactor/RubberDuckInteractor.java)

* VIPERモジュールのInteractorクラス。
* 入力されたテキストを会話ログとして保存する。
* 会話ログを操作する。
* 会話ログの件数を確認し、リマインドダイアログの表示を指示する。
* タスク完了状態を確認する。

#### [ChatItemsDataManager](../../app/src/main/java/com/renkataoka/dubugger/datamanager/ChatItemsDataManager.java)

* DBを操作するためのクラス。
* 会話ログを保存・修正・取得する。

### [RubberDuckRouter](#)

* VIPERモジュールのRouterクラス。
* 戻るボタンを押下後、画面を閉じる。

### [RubberDuckPresenter](../../app/src/main/java/com/renkataoka/dubugger/module/rubberduck/presenter/RubberDuckPresenter.java)

* VIPERモジュールのPresenterクラス。
* View, Interactor, Routerと相互やり取りを行う。

### [RubberDuckAssembler](../../app/src/main/java/com/renkataoka/dubugger/module/rubberduck/assembler/RubberDuckAssembler.java)

* VIPERモジュールのAssemblerクラス。
* 具象クラスの依存性注入を行う。