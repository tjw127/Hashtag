package co.uglytruth.hashtag.holder

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import co.uglytruth.hashtag.R
import android.widget.EditText
import android.widget.ImageView
/**
 * Created by tjw127 on 1/14/18.
 */
class HashtagHolder(itemView: View):RecyclerView.ViewHolder(itemView){

            val editText:EditText = itemView.findViewById(R.id.hashtagText)
            val clipButton: Button = itemView.findViewById(R.id.bookmark)
}