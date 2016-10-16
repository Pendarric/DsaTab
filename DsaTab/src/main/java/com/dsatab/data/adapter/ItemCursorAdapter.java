package com.dsatab.data.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsatab.R;
import com.dsatab.db.DataManager;
import com.dsatab.util.Util;
import com.dsatab.view.ItemListItem;
import com.franlopez.flipcheckbox.FlipCheckBox;
import com.h6ah4i.android.widget.advrecyclerview.selectable.ElevatingSelectableViewHolder;

public class ItemCursorAdapter extends CursorAdapter {

    public ItemCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_listitem_view, parent, false);
        v.setTag(new ItemViewHolder(v));
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ItemViewHolder viewHolder =(ItemViewHolder) view.getTag();
        // this seems to be called even after stop in some rare occasions...
        if (cursor != null && !cursor.isClosed()) {
            ((ItemListItem)viewHolder.itemView).setItem(DataManager.getItemByCursor(cursor));
        }

        Util.applyRowStyle(viewHolder.itemView, cursor.getPosition());
    }

    protected static class ItemViewHolder extends ElevatingSelectableViewHolder {

		TextView text1, text2, text3;
        FlipCheckBox icon1;
		ImageView  icon2, icon_chain_top, icon_chain_bottom;

		public ItemViewHolder(View v) {
			super(v);

			text1 = (TextView) v.findViewById(android.R.id.text1);
			text2 = (TextView) v.findViewById(android.R.id.text2);
			text3 = (TextView) v.findViewById(R.id.text3);
			icon1 = (FlipCheckBox) v.findViewById(android.R.id.checkbox);

			icon2 = (ImageView) v.findViewById(android.R.id.icon2);
			icon_chain_bottom = (ImageView) v.findViewById(R.id.icon_chain_bottom);
			icon_chain_top = (ImageView) v.findViewById(R.id.icon_chain_top);
		}
	}

}
