package com.inti.student.androidassignment;

import android.view.View;
import android.widget.CompoundButton;

public interface ItemClickListener {

    void onClick(View view, int position, boolean isLongClick);

}
