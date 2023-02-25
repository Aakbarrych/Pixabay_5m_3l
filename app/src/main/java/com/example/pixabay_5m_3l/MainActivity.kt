package com.example.pixabay_5m_3l

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay_5m_3l.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter(arrayListOf())
    var page = 1
    var imageAmount = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding){
            changePageBtn.setOnClickListener{
                ++page
                page = 1
                ++imageAmount
                doRequest()
            }
            enterBtn.setOnClickListener{
                page = 1
                doRequest()
            }
            imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastItem = layoutManager.findLastVisibleItemPosition()
                    val allItems = layoutManager.itemCount
                    if (lastItem == allItems - 1) {
                        addNewData()
                    }
                }
            })
        }
    }

    private fun addNewData() {
        ++page
        page = 1
        ++imageAmount
    }

    private fun ActivityMainBinding.doRequest() {
        PixaService().api.getImages(
            pictureWord = searchEd.text.toString(), page = page, perPage = imageAmount
        ).enqueue(object : Callback<PixaModel> {
            override fun onResponse(
                call: Call<PixaModel>,
                response: Response<PixaModel>){

                if (response.isSuccessful) {
                    adapter = PixaAdapter(response.body()?.hits!!)
                    imageRecycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.e("ololo", "onFailure: ${t.message}")
            }

        })
    }
}