package com.alextroy.news22.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.alextroy.news22.R
import com.alextroy.news22.adapter.NewsAdapter
import com.alextroy.news22.api.NewsApp
import com.alextroy.news22.model.News
import com.alextroy.news22.utils.KEY
import com.alextroy.news22.utils.TRAIL_TEXT
import com.alextroy.news22.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_list.*


class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private var adapter: NewsAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    private val newsApp by lazy {
        NewsApp.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list)

        initRecyclerView()
        refreshData()
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
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

    private fun data(result: News) {
        adapter!!.data(result.response.results)
        adapter!!.notifyDataSetChanged()
    }

    private fun query() {
        disposable = newsApp.getNews(TRAIL_TEXT, KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            data(result)
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
