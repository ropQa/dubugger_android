# ラバーダックデバッグ画面

## 概要

* 自分と相手役の間で会話を構築する。

## クラス

### [RubberDuckContract]()

* Contractクラス。
* VIPERモジュール間の連携APIを定義。

### [RubberDuckActivity]()

* VIPERモジュールのViewクラス。
* ユーザーとラバーダックの会話画面を表示する。
* 目的リマインドダイアログを表示する。

#### [RemindAimDialogFragment]()

* 目的リマインドダイアログ。
* 深追いし過ぎているときに当初の目的を思い出させる。

### [RubberDuckInteractor]()

* VIPERモジュールのInteractorクラス。
* 入力されたテキストを会話ログとして保存する。
* 会話ログを操作する。
* 会話ログの件数を確認し、リマインドダイアログの表示を指示する。
* タスク完了状態を確認する。

#### [ConversationDataManager]()

* DBを操作するためのクラス。
* 会話ログを保存・修正・取得する。

#### [ToDebugListDataManager]()

* DBを操作するためのクラス。
* タスク完了状態を保存・修正・取得する。

### [RubberDuckRouter]()

* VIPERモジュールのRouterクラス。
* 戻るボタンを押下後、画面を閉じる。

### [RubberDuckPresenter]()

* VIPERモジュールのPresenterクラス。
* View, Interactor, Routerと相互やり取りを行う。

### [RubberDuckAssembler]()

* VIPERモジュールのAssemblerクラス。
* 具象クラスの依存性注入を行う。