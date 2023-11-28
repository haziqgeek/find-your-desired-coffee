package com.example.android.coffeerecommender.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.coffeerecommender.R;
import com.example.android.coffeerecommender.model.Coffee;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {

    private List<Coffee> mListData;   // list of appointment objects
    private Context mContext;       // activity context
    private int currentPos;         //current selected position.
    public RecommendationAdapter(Context context, List<Coffee> listData) {
        mListData = listData;
        mContext = context;
    }

    private Context getmContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the single item layout
        View view = inflater.inflate(R.layout.view_list_item_coffee, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // bind data to the view holder
        Coffee m = mListData.get(position);
        holder.tvName.setText(m.getName());
        holder.tvShop.setText(m.getShop());
        holder.tvType.setText(m.getType());
        Glide.with(mContext).load(mListData.get(position).getImage()).placeholder(R.drawable.no_image).error(R.drawable.ic_launcher_background).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public Coffee getSelectedItem() {
        if (currentPos >= 0 && mListData != null && currentPos < mListData.size()) {
            return mListData.get(currentPos);
        }
        return null;
    }

    /**
     * Create ViewHolder class to bind list item view
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        public TextView tvName;
        public TextView tvShop;
        public TextView tvType;
        public ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvShop = (TextView) itemView.findViewById(R.id.tvShop);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            ivImage = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            currentPos = getAdapterPosition(); //key point, record the position here
            return false;
        }
    }
}
