package mobi.riemer.azure.tweetsearch

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.social.twitter.api.SearchParameters
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class TweetsearchApplicationTests {

    @Autowired
    lateinit var twitterTemplate: TwitterTemplate

    @Test
    fun contextLoads() {
    }

    @Test
    fun searchTweets() {
        val results = twitterTemplate.searchOperations().search(
                SearchParameters("kotlin")
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(10))

        val tweets = results.tweets

        assert(tweets.count() == 10)
    }
}
