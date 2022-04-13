package com.meeweel.newstapeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
                loadPicture(newsPost.image, binding)
                newsTapeItemTitle.text = newsPost.title
                newsTapeItemDescription.text = newsPost.description
                newsTapeItemButton.setOnClickListener {
                    when (newsTapeItemDescription.visibility) {
                        GONE -> {
                            newsTapeItemDescription.visibility = VISIBLE
                            newsTapeItemButton.text = HIDE
                        }
                        VISIBLE -> {
                            newsTapeItemDescription.visibility = GONE
                            newsTapeItemButton.text = MORE
                        }
                        View.INVISIBLE -> newsTapeItemDescription.visibility = GONE
                    }
                }
            }
        }
    }

    private fun loadPicture(image: String?, binding: NewsRecyclerItemBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.ic_no_picture)
                .placeholder(R.drawable.default_news_image)
                .into(binding.newsTapeItemImage)
        }
    }

    fun setNewsData(data: List<NewsPostDTO>) {
        newsData = data
        notifyDataSetChanged()
    }

    companion object {
        const val HIDE = "Hide description"
        const val MORE = "Read more"
    }
}