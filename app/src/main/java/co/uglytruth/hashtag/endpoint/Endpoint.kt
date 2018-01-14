package co.uglytruth.hashtag.endpoint
import co.uglytruth.hashtag.enum.CredentialsEnum
/**
 * Created by tjw127 on 1/13/18.
 */
class Endpoint(val url:String){

    private constructor(builder: Builder) : this(builder.url)

     class Builder {

        private var endpointList : ArrayList<String> = ArrayList<String>()

         var url : String = ""

        var hashtag: String? = null
            private set

        fun baseUrl() = apply {
            endpointList.add(CredentialsEnum.GoogleComputeEngineURL.value)
        }

        fun hashtag() = apply{

            endpointList.add("hashtag.php")
        }

         fun key() = apply {

             endpointList.add(CredentialsEnum.GoogleComputeEngineKey.value)
         }

        fun build() : Endpoint {

            var final_url : String = ""
            var valueCounter : Int = 0

            for (value in endpointList){
                valueCounter += 1
                if (valueCounter < endpointList.size){

                    final_url += value
                }else{

                    final_url += "?"
                    final_url += "key="
                    final_url += value
                }
            }

            this.url = final_url

            return Endpoint(this)
        }



    }
}