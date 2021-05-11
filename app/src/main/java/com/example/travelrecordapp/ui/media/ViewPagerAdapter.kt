package com.example.travelrecordapp.ui.media

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.AudioInfo
import com.example.travelrecordapp.data.MediaData
import com.example.travelrecordapp.data.VideoInfo
import com.example.travelrecordapp.databinding.ActivityMediaBinding
import com.example.travelrecordapp.ui.media.onClickViewPager

class ViewPagerAdapter(private val list: List<Any>,val listener: onClickViewPager) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_vp_media, container, false)

        if(list[0] is AudioInfo ){
            val list = list as List<AudioInfo>
            view.findViewById<TextView>(R.id.tv_media_title).text = list[position].title
//        view.ivItem.setImageResource(list[position].getImageId(container.context))
        view.findViewById<TextView>(R.id.tv_media_detail).text = list[position].audioExplain
        }else{
            val list = list as List<VideoInfo>
            view.findViewById<TextView>(R.id.tv_media_title).text = list[position].title
//        view.ivItem.setImageResource(list[position].getImageId(container.context))
            view.findViewById<TextView>(R.id.tv_media_detail).text = list[position].videoExplain
        }


        view.findViewById<ImageView>(R.id.img_media).setOnClickListener {
            listener.onClickViewPager(position)

        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

}
