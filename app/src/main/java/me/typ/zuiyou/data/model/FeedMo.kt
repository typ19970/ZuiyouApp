package me.typ.zuiyou.data.model

/**
 * 对一条评论的态度
 */
data class Ugc(
    val commentCount: Int,
    val hasDissed: Boolean,
    val hasFavorite: Boolean,
    val hasLiked: Boolean,
    val hasdiss: Boolean,
    val likeCount: Int,
    val shareCount: Int
)

/**
 * 某评论的作者
 */
data class Author(
    val avatar: String,
    val commentCount: Int,
    val description: String,
    val expires_time: Long,
    val favoriteCount: Int,
    val feedCount: Int,
    val followCount: Int,
    val followerCount: Int,
    val hasFollow: Boolean,
    val historyCount: Int,
    val id: Int,
    val likeCount: Int,
    val name: String,
    val qqOpenId: String,
    val score: Int,
    val topCommentCount: Int,
    val userId: Long
)

/**
 * 一条评论
 */
data class Comment(
    val author: Author,
    val commentCount: Int,
    val commentId: Long,
    val commentText: String,
    val commentType: Int,
    val createTime: Long,
    val hasLiked: Boolean,
    val height: Int,
    val id: Int,
    val imageUrl: String?,
    val itemId: Long,
    val likeCount: Int,
    val ugc: Ugc,
    val userId: Long,
    val videoUrl: Any,
    val width: Int
)

/**
 * 一条帖子
 */
data class Feed(
    val activityIcon: String,
    val activityText: String,
    val author: Author,
    val authorId: Long,
    val cover: String,
    val createTime: Long,
    val duration: Double,
    val feeds_text: String,
    val height: Int,
    val id: Int,
    val itemId: Long,
    val itemType: Int,
    val topComment: Comment,
    val ugc: Ugc,
    val url: String,
    val width: Int
)
