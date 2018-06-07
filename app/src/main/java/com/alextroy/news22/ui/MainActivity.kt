package com.alextroy.news22.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alextroy.news22.R
import com.alextroy.news22.adapter.NewsAdapter
import com.alextroy.news22.api.NewsApp
import com.alextroy.news22.model.News
import com.alextroy.news22.utils.KEY
import com.alextroy.news22.utils.TRAIL_TEXT
import com.alextroy.news22.utils.toast
import kotlinx.android.synthetic.main.list_news.*
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {

    private var adapter: NewsAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_news)

        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager

        refreshData()
    }

    private fun swipeScreen() {
        recycler.visibility = View.VISIBLE
        screen_update.visibility = View.GONE
    }


    private fun refreshData() {
        swiperefresh.setOnRefreshListener {
            swipeScreen()
            toast("News uploaded")
            query()
            swiperefresh.isRefreshing = false
        }
    }

    private fun query() {
        val request = NewsApp.create().getNews(TRAIL_TEXT, KEY)

        request.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>?, response: retrofit2.Response<News>?) {
                response?.body().let { news ->
                    if (news != null) {
                        adapter!!.data(news.response.results)
                    }
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
                toast("Unable to fetch the news")
            }
        })


    }
}
