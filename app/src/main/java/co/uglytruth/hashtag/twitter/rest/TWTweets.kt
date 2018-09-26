package co.uglytruth.hashtag.twitter.rest

import android.content.Context
import android.util.Log
import co.uglytruth.hashtag.Webservice
import co.uglytruth.hashtag.twitter.url.TwitterURL
import co.uglytruth.hashtag.twitter.url_parameters.TwtterURLParams

/**
 * Created by tjw127 on 1/26/18.
 */
class TWTweets{

    fun search(q:HashMap<String, String>, context:Context, geocode:HashMap<String, String>?, lang:HashMap<String, String>?, locale:HashMap<String, String>?,
               result_type:HashMap<String, String>?, count:HashMap<String, String>?, until:HashMap<String, String>?,
               since_id:HashMap<String, String>?, max_id:HashMap<String, String>?, include_entities:HashMap<String, String>?){

       val twitterParametersBuilder:TwtterURLParams.Builder = TwtterURLParams.Builder()


        twitterParametersBuilder.q(q.get("q"))
//        twitterParametersBuilder.access_token("AAAAAAAAAAAAAAAAAAAAAFBW0QAAAAAA1okAntAYq8UdQ3kd7TDC9EiJ7g8%3DI8cGsqxanOjQadw3Szq4eaeXoDpdnE35pgviZBUH0Gwli3nAHv")
        if (geocode != null)
        {
            twitterParametersBuilder.geocode(geocode.get("geocode"))
        }

        if (lang != null)
        {
            twitterParametersBuilder.lang(lang.get("lang"))
        }

        if (locale  != null)
        {
            twitterParametersBuilder.locale(locale.get("locale"))
        }

        if (result_type != null)
        {
            twitterParametersBuilder.result_type(result_type.get("result_type"))
        }

        if (count != null)
        {
            twitterParametersBuilder.count(count.get("count"))
        }

        if (until != null)
        {
            twitterParametersBuilder.until(until.get("until"))
        }

        if (since_id != null)
        {
            twitterParametersBuilder.since_id(since_id.get("since_id"))
        }

        if (max_id != null)
        {
            twitterParametersBuilder.max_id(max_id.get("max_id"))
        }

        if (include_entities != null)
        {
            twitterParametersBuilder.include_entities(include_entities.get("include_entities"))
        }

        val parameters:String? = twitterParametersBuilder.build().parameters

       val twitterURLBuilder:TwitterURL.Builder = TwitterURL.Builder()

        twitterURLBuilder.baseUrl()
        twitterURLBuilder.version()
        twitterURLBuilder.search()

        Log.v("TwitterP", parameters)
        if (parameters != null)
        {
            twitterURLBuilder.parameters(parameters)
        }

        twitterURLBuilder.tweets()
        twitterURLBuilder.responseFormat("json")
        val tw = twitterURLBuilder.build()

        val w = Webservice()
        w.getResponse(urlString = tw.url, context = context, enums = null, parameter = parameters!!)

        Log.v("Twitter", tw.url)

    }
}