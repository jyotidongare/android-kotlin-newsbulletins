package com.app.newsfeed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.app.newsfeed.R
import com.app.newsfeed.model.NewsDetail
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_news_details.view.*

/**
 *  In NewsAdapter pass the ArrayList
 *  and load the data for each row and images using Glide library
 */


class NewsAdapter(val context: Context, var newsList: ArrayList<NewsDetail>) :
    Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_news_details,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return newsList.count();
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var newsDetails: NewsDetail = newsList.get(position);


        holder.newsTitle.text =
            if (newsDetails.title.isNullOrEmpty()) "" else newsDetails.title
        holder.newsHeader.text =
            if (newsDetails.description.isNullOrEmpty()) "" else newsDetails.description

        loadImage(holder.imgNews, newsDetails.imgSource)

    }

    private fun loadImage(imageHolder: ImageView, imgSource: String?) {
        Glide.with(context)
            .load(getSecureImagePath(imgSource))
            .centerCrop()
            .override(200, 150)
            .placeholder(R.drawable.ic_launcher_background) // Can update image as per requirement
            .error(R.drawable.ic_launcher_background) // Can update image as per requirement
            .fallback(R.drawable.ic_launcher_background) // Can update image as per requirement
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageHolder)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTitle = itemView.txt_news_title as TextView
        val newsHeader = itemView.txt_news_headline as TextView
        val imgNews = itemView.img_news as ImageView
    }

    /**
     * Replace the http with https in URL, to resolve image loading issue
     */
    private fun getSecureImagePath(imageSource: String?): String? {
        if (!imageSource.isNullOrEmpty() && !imageSource.contains("https")) {
            return imageSource.replace("http", "https")
        } else {
            return imageSource
        }

    }

}