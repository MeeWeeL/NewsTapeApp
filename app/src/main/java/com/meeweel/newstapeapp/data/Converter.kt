package com.meeweel.newstapeapp.data

class Converter {

    fun convertResponseToNewsPostList(response: Response) : List<NewsPostDTO> {
        return response.news.map {
            NewsPostDTO(it.title, it.description, it.url, it.source.name, it.image, it.publishedAt)
        }
    }
}