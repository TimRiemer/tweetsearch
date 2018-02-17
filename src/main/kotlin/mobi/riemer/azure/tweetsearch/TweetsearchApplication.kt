package mobi.riemer.azure.tweetsearch

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.social.twitter.api.SearchParameters
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class TweetsearchApplication {
    @Bean
    fun twitterTemplate(@Value("\${spring.social.twitter.appId}") consumerKey: String,
                        @Value("\${spring.social.twitter.appSecret}") consumerSecret: String,
                        @Value("\${twitter.access.token}") accessToken: String,
                        @Value("\${twitter.access.token.secret}") accessTokenKey: String): TwitterTemplate {
        return TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenKey)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(TweetsearchApplication::class.java, *args)
}


@RestController
class CustomerController(val twitterTemplate: TwitterTemplate) {

    @GetMapping("/tweets/{search}")
    fun searchTwitter(@PathVariable("search") search: String): List<String> {

        val results = twitterTemplate.searchOperations().search(
                SearchParameters(search)
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(10))

        return results.tweets.map(Tweet::getText)
    }

    @GetMapping("/tweets")
    fun getLastTenTweets(): List<String> {
        return twitterTemplate.timelineOperations()
                .getHomeTimeline(10)
                .map(Tweet::getText)
    }
}