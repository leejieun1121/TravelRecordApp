package com.example.travelrecordapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//리싸이클러뷰 아이템 간격 설정
class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
    }
}
