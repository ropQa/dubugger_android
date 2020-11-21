# メイン画面

## 概要

* 理解したいこと・理解を確かめたいことをリストアップする。

※「理解したいこと・理解を確かめたいことのリスト」を**ToDebugList**と呼ぶ。

## クラス

### [MainContract]()

* Contractクラス。
* VIPERモジュール間の連携APIを定義。

### [MainActivity]()

* VIPERモジュールのViewクラス。
* ToDebugListを表示する。
* ToDebugListの要素が選択されたことをキャッチする。

### [MainInteractor]()

* VIPERモジュールのInteractorクラス。
* 入力されたテキストをToDebugListに追加する。

### [MainRouter]()

* VIPERモジュールのRouterクラス。
* ToDebugListの各要素に対応するアクティビティへ遷移する。

### [MainPresenter]()

* VIPERモジュールのPresenterクラス。
* View, Interactor, Routerと相互やり取りを行う。

### [MainAssembler]()

* VIPERモジュールのAssemblerクラス。
* 具象クラスの依存性注入を行う。