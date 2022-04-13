package com.meeweel.newstapeapp.data.models

class Converter {

    fun convertResponseToNewsPostList(response: Response) : List<NewsPostDTO> {
        return response.articles.map {
            NewsPostDTO(it.title, it.description, it.url, it.source.name, it.urlToImage, it.publishedAt)
        }
    }
}