//package com.example.meets.Adapter;
//
//import android.content.ClipData;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.meets.R;
//
//import java.util.List;
//
//public class TimeLineAdapter extends RecyclerView.ViewHolder {
//
//    private static final int VIEW_TYPE_TOP = 0;
//    private static final int VIEW_TYPE_MIDDLE = 1;
//    private static final int VIEW_TYPE_BOTTOM = 2;
//
//    private List<ClipData.Item> mItems;
//
//    TextView mItemTitle;
//    TextView mItemSubtitle;
//    FrameLayout mItemLine;
//
//    public TimeLineAdapter(@NonNull View itemView) {
//        super(itemView);
//
//        mItemTitle = (TextView)itemView.findViewById(R.id.item_title);
//        mItemSubtitle = (TextView)itemView.findViewById(R.id.item_subtitle);
//        mItemLine = (FrameLayout)itemView.findViewById(R.id.item_line);
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        ClipData.Item item = mItems.get(position);
//        // Populate views...
//        switch(holder.getItemViewType()) {
//            case VIEW_TYPE_TOP:
//                // The top of the line has to be rounded
//                holder.mItemLine.setBackground(R.drawable.line_bg_top);
//                break;
//            case VIEW_TYPE_MIDDLE:
//                // Only the color could be enough
//                // but a drawable can be used to make the cap rounded also here
//                holder.mItemLine.setBackground(R.drawable.line_bg_middle);
//                break;
//            case VIEW_TYPE_BOTTOM:
//                holder.mItemLine.setBackground(R.drawable.line_bg_bottom);
//                break;
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return VIEW_TYPE_TOP;
//        }else if (position == mItems.size() - 1) {
//            return VIEW_TYPE_BOTTOM;
//        }
//        return VIEW_TYPE_MIDDLE;
//    }
//}
//}
