# メイン画面

## 概要

* 理解したいこと・理解を確かめたいことをリストアップする。

※「理解したいこと・理解を確かめたいこと」を**ToDebugItem**、そのリストを**ToDebugList**と呼ぶ。

## クラス

### [MainContract](../../app/src/main/java/com/renkataoka/dubugger/module/main/contract/MainContract.java)

* Contractクラス。
* VIPERモジュール間の連携APIを定義。

### [MainActivity](../../app/src/main/java/com/renkataoka/dubugger/module/main/view/MainActivity.java)

* VIPERモジュールのViewクラス。
* ToDebugListを表示する。
* ToDebugListの要素が選択されたことをキャッチする。

#### [ToDebugItemViewHolder](../../app/src/main/java/com/renkataoka/dubugger/module/main/view/ToDebugItemViewHolder.java)

* ToDebugItemの個別アイテムに対応するViewを保持するクラス。

#### [ToDebugListAdapter](../../app/src/main/java/com/renkataoka/dubugger/module/main/view/ToDebugListAdapter.java)

* ToDebugListのデータ(ToDebugItems)とRecyclerViewを繋ぐクラス。
* 個々のViewを提供するViewHolderを作成する。
* 個々のViewに対するクリックイベントを用意する。

### [MainInteractor](../../app/src/main/java/com/renkataoka/dubugger/module/main/interactor/MainInteractor.java)

* VIPERモジュールのInteractorクラス。
* 入力されたテキストをToDebugListに追加する。

#### [ToDebugItemsDataManager](../../app/src/main/java/com/renkataoka/dubugger/datamanager/ChatItemsDataManager.java)

* DBを操作するためのクラス。
* タスクを保存・修正・取得する。
* タスク完了状態を保存・修正・取得する。

### [MainRouter](../../app/src/main/java/com/renkataoka/dubugger/module/main/router/MainRouter.java)

* VIPERモジュールのRouterクラス。
* ToDebugListの各要素に対応するアクティビティへ遷移する。

### [MainPresenter](../../app/src/main/java/com/renkataoka/dubugger/module/main/presenter/MainPresenter.java)

* VIPERモジュールのPresenterクラス。
* View, Interactor, Routerと相互やり取りを行う。

### [MainAssembler](../../app/src/main/java/com/renkataoka/dubugger/module/main/assembler/MainAssembler.java)

* VIPERモジュールのAssemblerクラス。
* 具象クラスの依存性注入を行う。

```plantuml
@startuml
package com.renkataoka.viper {
  interface "View" as viperView
  interface "Interactor" as viperInteractor
  interface "IntaractorCallback" as viperInteractorCallback
  interface "Presenter" as viperPresenter
  interface "Router" as viperRouter
  interface "Assembler" as viperAssembler
}

package com.renkataoka.dubugger.module.main {
  package contract {
    class MainContract

    interface View {
      void initRecyclerView()
      void setToDebugItems(List<ToDebugItemsAndChatItems> toDebugItems)
    }

    interface Interactor {
      void addToDebugItem(String content)
      void deleteToDebugItem(int id)
      void deleteAllToDebugItems()
      void readToDebugItems()
    }

    interface InteractorCallback {
      void onReadToDebugItemsCompleted(List<ToDebugItemsAndChatItems> toDebugItems)
      void onAddToDebugItemCompleted()
      void onDeleteToDebugItemCompleted();
    }

    interface Presenter {
      void onCreate()
      void onClickAddButton(String inputContent)
      void onClickDeleteAllMenu()
      void onClickToDebugItem(int id, String title)
    }

    interface Router {
      void startRubberDuckActivity(int id, String title)
    }
  }
  package view {
    class MainActivity
    class ToDebugItemViewHolder
    class ToDebugListAdapter
  }
  package interactor {
    class MainInteractor
  }
  package presenter {
    class MainPresenter
  }
  package router {
    class MainRouter
  }
  package assembler {
    class MainAssembler
  }
' Contract内にInterfaceを定義する。
MainContract +- View
MainContract +- Interactor
MainContract +- InteractorCallback
MainContract +- Presenter
MainContract +- Router


' 各InterfaceはVIPERモジュールのInterfaceを継承する。
viperView <|-- View
viperInteractor <|-- Interactor
viperInteractorCallback <|-- InteractorCallback
viperPresenter <|-- Presenter
viperRouter <|--- Router


' VIPERモジュール。
View <|.. MainActivity
Interactor <|.. MainInteractor
InteractorCallback <|.. MainPresenter
Presenter <|.. MainPresenter
Router <|.. MainRouter
viperAssembler <|... MainAssembler

}
@enduml
```

## シーケンス図

```plantuml
@startuml

Actor User
participant MainActivity <<View : 画面>>
participant MainPresenter <<Presenter : 中継>>
participant MainInteractor <<Interactor : 機能>>
participant MainRouter <<Router : 遷移>>

== メイン画面表示 ==

MainActivity -> MainPresenter : onCreate()
MainPresenter ->> MainInteractor : readToDebugItems()

activate MainInteractor
MainInteractor -->> MainPresenter : onReadToDebugItemsCompleted()
deactivate MainInteractor

MainPresenter -> MainActivity : setToDebugItems()
MainActivity -> User : dbに保存されたToDebugListを表示する。

== アイテム追加・削除 ==
alt アイテム追加
User -> MainActivity : Debugしたいことを入力する。
MainActivity -> MainPresenter : onClickAddButton()
MainPresenter ->> MainInteractor : addToDebugItem()

activate MainInteractor
MainInteractor -->> MainPresenter : onAddToDebugItemCompleted()
deactivate MainInteractor

else アイテム削除
User -> MainActivity : Delete Allメニューを選択する。
MainActivity -> MainPresenter : onClickDeleteAllMenu()
MainPresenter ->> MainInteractor : deleteAllToDebugItems()

activate MainInteractor
MainInteractor -->> MainPresenter : onDeleteToDebugItemCompleted()
deactivate MainInteractor
end
MainPresenter ->> MainInteractor : readToDebugItems()

note right
アイテム追加・削除が完了したら、
再度dbを読み込む。
end note

activate MainInteractor
MainInteractor -->> MainPresenter : onReadToDebugItemsCompleted()
deactivate MainInteractor

MainPresenter -> MainActivity : setToDebugItems()
MainActivity -> User : dbへの追加・削除が反映されたToDebugListを表示する。

== 画面遷移 ==
User -> MainActivity : リスト内のアイテムを選択する。
MainActivity -> MainPresenter : onClickToDebugItem()
MainPresenter -> MainRouter : startRubberDuckActivity()
MainRouter -> User : RubberDuck画面を表示する。
@enduml
```