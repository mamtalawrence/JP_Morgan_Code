package com.jp.mamtalawrence.albums.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jp.mamtalawrence.albums.R;
import com.jp.mamtalawrence.albums.models.AlbumData;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.CustomViewHolder> {

    private List<AlbumData> list;
    private Context mContext;

    AlbumListAdapter(Context context, List<AlbumData> list) {
        this.mContext = context;
        this.list = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.item_textView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_item_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        //Set information in text view
        String data = mContext.getString(R.string.album_name_txt) + list.get(position).getTitle()
                + mContext.getString(R.string.id_txt) + list.get(position).getId()
                + mContext.getString(R.string.user_id) + list.get(position).getUserId();
        holder.txtTitle.setText(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
