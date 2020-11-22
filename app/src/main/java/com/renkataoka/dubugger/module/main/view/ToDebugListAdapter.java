package com.renkataoka.dubugger.module.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renkataoka.dubugger.R;

import java.util.LinkedList;


/**
 * ToDebugListのデータとRecyclerViewを繋ぐAdapterクラス。
 * Viewアイテムを提供するViewHolderを作成する。
 */
public class ToDebugListAdapter extends RecyclerView.Adapter<ToDebugItemViewHolder> {
    //View開発時用のテストdataset。
    private LinkedList<String> dataset;

    ToDebugListAdapter(LinkedList<String> dataset) {
        this.dataset = dataset;
    }

    /**
     * @param parent   新しいViewが加えられるViewGroup
     * @param viewType 新しいViewのType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ToDebugItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_debug_list, parent, false);
        return new ToDebugItemViewHolder(view);
    }

    /**
     * ToDebugItemのデータをViewHolderに結び付ける。
     *
     * @param holder   データ更新対象のViewHolder。
     * @param position Viewのポジション。
     */
    @Override
    public void onBindViewHolder(@NonNull ToDebugItemViewHolder holder, int position) {
        String itemContent = dataset.get(position);
        //本来はアイテム追加時刻を入れるが、暫定的にIDを加えて表示を確かめる。
        String itemCreatedAt = "ID : " + position;
        holder.itemContentView.setText(itemContent);
        holder.itemCreatedAtView.setText(itemCreatedAt);
    }

    /**
     * @return Adapterが保持するアイテム数。
     */
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
