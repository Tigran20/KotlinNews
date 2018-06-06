package com.alextroy.news22.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alextroy.news22.R
import com.alextroy.news22.model.Result
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private var news: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bindItems(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bindItems(news: Result) {
            itemView.news_title.text = news.webTitle
            itemView.news_description.text = news.fields.trailText
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }
    }

    fun data(items: List<Result>) {
        news = items
    }

}