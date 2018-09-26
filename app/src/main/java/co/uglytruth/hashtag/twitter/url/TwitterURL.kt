package co.uglytruth.hashtag.twitter.url

import android.util.Log
import co.uglytruth.hashtag.enum.CredentialsEnum

/**
 * Created by tjw127 on 1/26/18.
 */

class TwitterURL(val url: String){

    private constructor(builder:Builder) : this(builder.url)

    class Builder{

        private var endpointList:ArrayList<String> = ArrayList<String>()

        private var responseFormatList:ArrayList<String> = ArrayList<String>()

        private var parametersList:ArrayList<String> = ArrayList<String>()

        var url : String = ""

        fun baseUrl() = apply {

            this.endpointList.add(CredentialsEnum.TwitterBaseURL.value)
        }

        fun parameters(value:String) = apply {

            this.parametersList.add(value)
        }

        fun responseFormat(value:String) = apply {

            responseFormatList.add(value)
        }

        fun version() = apply {

            this.endpointList.add(CredentialsEnum.TwitterAPIVersion.value)
        }

        fun trends() = apply {

            endpointList.add("trends")
        }

        fun place() = apply {

            endpointList.add("place")
        }

        fun available() = apply {

            endpointList.add("available")
        }

        fun closest() = apply {

            endpointList.add("closest")
        }

        fun search() = apply {

            endpointList.add("search")
        }

        fun tweets() = apply {

            endpointList.add("tweets")
        }

        fun build() : TwitterURL{

            var final_url : String = ""
            var url_counter : Int = 0

            for (value in endpointList) {

                Log.v("TwitterV", value)
                url_counter += 1

                if (url_counter < endpointList.size) {

                    final_url += value

                    if (url_counter > 1) {

                        final_url += "/"

                    }
                } else {

                    final_url += value
                }

            }

            if (responseFormatList.size > 0){

                final_url += "."
                final_url += responseFormatList.get(0)
            }

            if (parametersList.size > 0){

                final_url += "?"

                for (params in parametersList)
                {
                    final_url += params
                }
            }

            this.url = final_url

          return TwitterURL(this)
        }


    }
}