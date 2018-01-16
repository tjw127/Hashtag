package co.uglytruth.hashtag.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.uglytruth.hashtag.R
import co.uglytruth.hashtag.hashtag.Hashtag
import co.uglytruth.hashtag.holder.HashtagHolder
/**
 * Created by tjw127 on 1/14/18.
 */
class HashtagAdapter(var list:ArrayList<Hashtag>?, var context: Context):RecyclerView.Adapter<HashtagHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HashtagHolder {

           val v = LayoutInflater.from(parent?.context).inflate(R.layout.hashtag_adapter_layout, parent, false)

            return HashtagHolder(v)
        }
        override fun onBindViewHolder(holder: HashtagHolder?, position: Int) {

            holder?.editText?.setText(list!![position].hastag_list)

            var clipButton = holder?.clipButton

            clipButton?.setOnClickListener(View.OnClickListener {

                clipButton.setBackgroundResource(R.mipmap.ic_bookmark_black_24dp)

                Log.v("ClipButton", "Click " + position)
                var clipBoardManager:ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                var clipData:ClipData = ClipData.newPlainText("hastag", list!![position].hastag_list)

                clipBoardManager.primaryClip = clipData




            })
        }

        override fun getItemCount(): Int {

            return list!!.size
        }

        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }

        override fun getItemViewType(position: Int): Int {
            return super.getItemViewType(position)
        }


}