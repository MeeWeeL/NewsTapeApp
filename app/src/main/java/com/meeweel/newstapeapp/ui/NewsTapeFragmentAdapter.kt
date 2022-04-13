package com.meeweel.newstapeapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.newstapeapp.R
import com.meeweel.newstapeapp.data.models.NewsPostDTO
import com.meeweel.newstapeapp.databinding.NewsRecyclerItemBinding

class NewsTapeFragmentAdapter :
    RecyclerView.Adapter<NewsTapeFragmentAdapter.MainViewHolder>() {

    private var newsData: List<NewsPostDTO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = NewsRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(newsData[position])
    }

    override fun getItemCount(): Int {
        return newsData.size
    }

    inner class MainViewHolder(private val binding: NewsRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsPost: NewsPostDTO) {
            binding.apply {
                newsTapeItemImage.setImageResource(R.drawable.default_news_image)
//                loadPicture(newsPost.image, binding)
                newsTapeItemTitle.text = newsPost.title
                newsTapeItemDescription.text = newsPost.description
            }
        }
    }

    private fun loadPicture(image: String, binding: NewsRecyclerItemBinding) {
        TODO()
    }

    fun setNewsData(data: List<NewsPostDTO>) {
        newsData = data
        notifyDataSetChanged()
    }
}