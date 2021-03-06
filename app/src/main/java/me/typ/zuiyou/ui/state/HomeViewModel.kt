package me.typ.zuiyou.ui.state

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.reflect.TypeToken
import me.typ.zuiyou.data.model.Feed
import me.typ.zuiyou.data.repository.FeedRepository
import me.typ.zuiyou.domain.request.FeedRequest

class HomeViewModel : ViewModel() {

    val viewPagerAdapter = ObservableField<FragmentStateAdapter>()

    val feedList = MutableLiveData<List<Feed>>()

    val mRequest = FeedRequest()

    init {
        //mRequest.getFeedList()
        val json = """[
      {
        "id": 1578919900,
        "itemId": 6810585660219480000,
        "itemType": 2,
        "createTime": 1585713043,
        "duration": 46.321,
        "feeds_text": "别凑热闹，怕你笑坏",
        "authorId": 102540925606,
        "activityIcon": "https://sf1-nhcdn-tos.pstatp.com/obj/p1056/88c5ea2b90134313b99cf2f9a87e9ca1",
        "activityText": "追剧不能停",
        "width": 864,
        "height": 486,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6810585660219480333.mp4",
        "cover": "https://p9-ppx.byteimg.com/img/mosaic-legacy/2fe52000e38d208d50bf0~864x486_q80.jpeg",
        "author": {
          "id": 1856,
          "userId": 102540925606,
          "name": "张-益达",
          "avatar": "https://p9-ppx.byteimg.com/img/tos-cn-i-0000/d9b1dca954d3432c9b891682c1590839~200x200.jpeg",
          "description": "皮一下。很开心",
          "likeCount": 0,
          "topCommentCount": 0,
          "followCount": 0,
          "followerCount": 9,
          "qqOpenId": null,
          "expires_time": 0,
          "score": 0,
          "historyCount": 0,
          "commentCount": 0,
          "favoriteCount": 0,
          "feedCount": 0,
          "hasFollow": false
        },
        "topComment": {
          "id": 2022,
          "itemId": 6810585660219480000,
          "commentId": 6811005208387131000,
          "userId": 0,
          "commentType": 1,
          "createTime": 1585810726,
          "commentCount": 24,
          "likeCount": 29064,
          "commentText": "先帝创业未半而去当特种兵",
          "imageUrl": null,
          "videoUrl": null,
          "width": 0,
          "height": 0,
          "hasLiked": false,
          "author": {
            "id": 1755,
            "userId": 1578919786,
            "name": "、蓅哖╰伊人为谁笑",
            "avatar": "http://qzapp.qlogo.cn/qzapp/101794421/FE41683AD4ECF91B7736CA9DB8104A5C/100",
            "description": "小朋友,你是否有很多问号",
            "likeCount": 2,
            "topCommentCount": 10,
            "followCount": 109,
            "followerCount": 40,
            "qqOpenId": "FE41683AD4ECF91B7736CA9DB8104A5C",
            "expires_time": 1596726031266,
            "score": 1000,
            "historyCount": 2792,
            "commentCount": 417,
            "favoriteCount": 17,
            "feedCount": 10,
            "hasFollow": false
          },
          "ugc": {
            "likeCount": 22,
            "shareCount": 0,
            "commentCount": 0,
            "hasFavorite": false,
            "hasLiked": true,
            "hasdiss": false,
            "hasDissed": false
          }
        },
        "ugc": {
          "likeCount": 35475,
          "shareCount": 320,
          "commentCount": 599,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      },
      {
        "id": 1578919899,
        "itemId": 6832487096775612000,
        "itemType": 2,
        "createTime": 1590812369,
        "duration": 53.466,
        "feeds_text": "这个视频笑skr人了",
        "authorId": 106609488611,
        "activityIcon": null,
        "activityText": null,
        "width": 1280,
        "height": 720,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6832487096775612686.mp4",
        "cover": "https://p1-ppx.byteimg.com/img/mosaic-legacy/30b2d0011d73be892b3a4~1125x632_q80.jpeg",
        "author": {
          "id": 1855,
          "userId": 106609488611,
          "name": "小康总本总",
          "avatar": "https://p1-ppx.byteimg.com/img/tos-cn-i-0000/f9d767fe7c9d4375a80ba8eb82d00b2b~200x200.jpeg",
          "description": "抖音：小康总账号：Mister_Kang盗用作品必究！",
          "likeCount": 0,
          "topCommentCount": 0,
          "followCount": 0,
          "followerCount": 3,
          "qqOpenId": null,
          "expires_time": 0,
          "score": 0,
          "historyCount": 0,
          "commentCount": 0,
          "favoriteCount": 0,
          "feedCount": 0,
          "hasFollow": false
        },
        "topComment": null,
        "ugc": {
          "likeCount": 27588,
          "shareCount": 32,
          "commentCount": 1358,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      },
      {
        "id": 1578919896,
        "itemId": 6812996722365569000,
        "itemType": 2,
        "createTime": 1586274412,
        "duration": 8.442,
        "feeds_text": "你爸妈不想让你知道的......",
        "authorId": 109515659615,
        "activityIcon": null,
        "activityText": null,
        "width": 960,
        "height": 544,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6812996722365569283.mp4",
        "cover": "https://p9-ppx.byteimg.com/img/mosaic-legacy/2fabd000eb32275729a31~960x544_q80.jpeg",
        "author": {
          "id": 1852,
          "userId": 109515659615,
          "name": "唯9",
          "avatar": "https://p1-ppx.byteimg.com/img/tos-cn-i-0000/532e412bad6d46abb0c0c68434953d17~200x200.jpeg",
          "description": "",
          "likeCount": 0,
          "topCommentCount": 0,
          "followCount": 0,
          "followerCount": 3,
          "qqOpenId": null,
          "expires_time": 0,
          "score": 0,
          "historyCount": 0,
          "commentCount": 0,
          "favoriteCount": 0,
          "feedCount": 0,
          "hasFollow": false
        },
        "topComment": null,
        "ugc": {
          "likeCount": 43757,
          "shareCount": 3194,
          "commentCount": 3883,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      },
      {
        "id": 1578919894,
        "itemId": 6828336780488481000,
        "itemType": 2,
        "createTime": 1589846048,
        "duration": 31.231,
        "feeds_text": "终于在皮皮虾变成了沙雕模样，却也变成了没人要被屏蔽的样子，有时候会问自己当初为什么选择了玩皮皮虾，却找不到答案，其实改变了我的不是这个软件，而是这个软件上的皮友们。",
        "authorId": 57710322274,
        "activityIcon": "https://sf1-nhcdn-tos.pstatp.com/obj/p1056/88c5ea2b90134313b99cf2f9a87e9ca1",
        "activityText": "祭出令人笑喷的视频",
        "width": 1176,
        "height": 720,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6828336780488481035.mp4",
        "cover": "https://p9-ppx.byteimg.com/img/mosaic-legacy/30a00000383f70206afe6~1125x688_q80.jpeg",
        "author": {
          "id": 1850,
          "userId": 57710322274,
          "name": "歪果虾米",
          "avatar": "https://p9-ppx.byteimg.com/img/tos-cn-i-0000/88b954039b604ead965cd78ff525fd83~200x200.jpeg",
          "description": "快乐的小虾米",
          "likeCount": 0,
          "topCommentCount": 0,
          "followCount": 0,
          "followerCount": 2,
          "qqOpenId": null,
          "expires_time": 0,
          "score": 0,
          "historyCount": 0,
          "commentCount": 0,
          "favoriteCount": 0,
          "feedCount": 0,
          "hasFollow": false
        },
        "topComment": null,
        "ugc": {
          "likeCount": 2222,
          "shareCount": 5,
          "commentCount": 517,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      },
      {
        "id": 1578919804,
        "itemId": 1588996983057,
        "itemType": 2,
        "createTime": 1588996983057,
        "duration": 0,
        "feeds_text": "小朋友你是都有很多问号❓",
        "authorId": 1588993948,
        "activityIcon": null,
        "activityText": "2019高光时刻",
        "width": 720,
        "height": 1280,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/1588996970841.mp4",
        "cover": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/1588996984383.jpeg",
        "author": {
          "id": 1755,
          "userId": 1578919786,
          "name": "、蓅哖╰伊人为谁笑",
          "avatar": "http://qzapp.qlogo.cn/qzapp/101794421/FE41683AD4ECF91B7736CA9DB8104A5C/100",
          "description": "小朋友,你是否有很多问号",
          "likeCount": 2,
          "topCommentCount": 10,
          "followCount": 109,
          "followerCount": 40,
          "qqOpenId": "FE41683AD4ECF91B7736CA9DB8104A5C",
          "expires_time": 1596726031266,
          "score": 1000,
          "historyCount": 2792,
          "commentCount": 417,
          "favoriteCount": 17,
          "feedCount": 10,
          "hasFollow": false
        },
        "topComment": null,
        "ugc": {
          "likeCount": -192,
          "shareCount": 45,
          "commentCount": 27,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      },
      {
        "id": 1578919803,
        "itemId": 1588989577182,
        "itemType": 2,
        "createTime": 1588989577182,
        "duration": 0,
        "feeds_text": "第一次坐地铁，好多美眉啊，哈哈哈😁",
        "authorId": 1582079663,
        "activityIcon": null,
        "activityText": "多彩生活",
        "width": 720,
        "height": 1280,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/1588989579839.mp4",
        "cover": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/1588989611709.jpeg",
        "author": {
          "id": 1393,
          "userId": 1582079663,
          "name": "A丶飞天玉虎",
          "avatar": "http://qzapp.qlogo.cn/qzapp/101794421/75C349CD87A790A7B087A823B3C92324/100",
          "description": null,
          "likeCount": 7,
          "topCommentCount": 0,
          "followCount": 0,
          "followerCount": 6,
          "qqOpenId": "75C349CD87A790A7B087A823B3C92324",
          "expires_time": 1589855689397,
          "score": 0,
          "historyCount": 10,
          "commentCount": 1,
          "favoriteCount": 0,
          "feedCount": 0,
          "hasFollow": false
        },
        "topComment": null,
        "ugc": {
          "likeCount": -19,
          "shareCount": 7,
          "commentCount": 13,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      },
      {
        "id": 773,
        "itemId": 1588587440667,
        "itemType": 2,
        "createTime": 1588587440667,
        "duration": 0,
        "feeds_text": "一次性爆破兵",
        "authorId": 1578919786,
        "activityIcon": null,
        "activityText": "多彩生活",
        "width": 1280,
        "height": 1280,
        "url": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/dabing.mp4",
        "cover": "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/dabing_cover.jpg",
        "author": {
          "id": 1755,
          "userId": 1578919786,
          "name": "、蓅哖╰伊人为谁笑",
          "avatar": "http://qzapp.qlogo.cn/qzapp/101794421/FE41683AD4ECF91B7736CA9DB8104A5C/100",
          "description": "小朋友,你是否有很多问号",
          "likeCount": 2,
          "topCommentCount": 10,
          "followCount": 109,
          "followerCount": 40,
          "qqOpenId": "FE41683AD4ECF91B7736CA9DB8104A5C",
          "expires_time": 1596726031266,
          "score": 1000,
          "historyCount": 2792,
          "commentCount": 417,
          "favoriteCount": 17,
          "feedCount": 10,
          "hasFollow": false
        },
        "topComment": null,
        "ugc": {
          "likeCount": 1933,
          "shareCount": 523,
          "commentCount": 1019,
          "hasFavorite": false,
          "hasLiked": false,
          "hasdiss": false,
          "hasDissed": false
        }
      }
    ]"""
        val list = FeedRepository.mGson.fromJson<List<Feed>>(json, object : TypeToken<List<Feed>>() {}.type)
        feedList.value = list
    }

}