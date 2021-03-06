package com.xabber.android.ui.adapter.contactlist;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xabber.android.R;
import com.xabber.android.data.log.LogManager;

class ContactListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    private static final String LOG_TAG = ContactListItemViewHolder.class.getSimpleName();
    final ImageView color;
    final ImageView avatar;
    final TextView name;
    final TextView outgoingMessageIndicator;
    final TextView secondLineMessage;
    final TextView smallRightText;
    final ImageView smallRightIcon;
    final ImageView largeClientIcon;
    final ImageView statusIcon;
    final ImageView offlineShadow;
    final ImageView mucIndicator;
    final View separator;
    private final ContactClickListener listener;


    interface ContactClickListener {
        void onContactClick(int adapterPosition);
        void onContactAvatarClick(int adapterPosition);
        void onContactCreateContextMenu(int adapterPosition, ContextMenu menu);
    }

    ContactListItemViewHolder(View view, ContactClickListener listener) {
        super(view);
        this.listener = listener;

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        color = (ImageView) view.findViewById(R.id.account_color_indicator);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        avatar.setOnClickListener(this);
        name = (TextView) view.findViewById(R.id.contact_list_item_name);
        outgoingMessageIndicator = (TextView) view.findViewById(R.id.outgoing_message_indicator);
        secondLineMessage = (TextView) view.findViewById(R.id.second_line_message);
        smallRightIcon = (ImageView) view.findViewById(R.id.small_right_icon);
        smallRightText = (TextView) view.findViewById(R.id.small_right_text);
        largeClientIcon = (ImageView) view.findViewById(R.id.client_icon_large);
        statusIcon = (ImageView) view.findViewById(R.id.contact_list_item_status_icon);
        offlineShadow = (ImageView) view.findViewById(R.id.offline_shadow);
        mucIndicator = (ImageView) view.findViewById(R.id.contact_list_item_muc_indicator);
        separator = view.findViewById(R.id.contact_list_item_separator);
    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition();
        if (adapterPosition == RecyclerView.NO_POSITION) {
            LogManager.w(LOG_TAG, "onClick: no position");
            return;
        }

        if (v.getId() == R.id.avatar) {
            listener.onContactAvatarClick(adapterPosition);
        } else {
            listener.onContactClick(adapterPosition);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int adapterPosition = getAdapterPosition();
        if (adapterPosition == RecyclerView.NO_POSITION) {
            LogManager.w(LOG_TAG, "onCreateContextMenu: no position");
            return;
        }

        listener.onContactCreateContextMenu(adapterPosition, menu);
    }

}
