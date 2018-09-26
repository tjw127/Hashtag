package co.uglytruth.hashtag.twitter.url_parameters

/**
 * Created by tjw127 on 1/26/18.
 */
class TwtterURLParams(val parameters:String){

    private constructor(builder: Builder) : this(builder.parameters)

    class Builder{

        private var paramsList: ArrayList<Map<String, String?>> = ArrayList<Map<String, String?>>()

        var parameters:String = ""


        fun access_token(value:String?) = apply{

            val access_token = mapOf<String, String?>("access_token" to value)

            paramsList.add(access_token)

        }

        fun lang(value:String?) = apply{

            val lang = mapOf<String, String?>("lang" to value)

            paramsList.add(lang)

        }
        fun lat(value:String?) = apply{

            val lat = mapOf<String, String?>("lat" to value)

            paramsList.add(lat)

        }

        fun long(value:String?) = apply {

            val long = mapOf<String, String?>("long" to value)

            paramsList.add(long)
        }

        fun q(value:String?) = apply {

            val q = mapOf<String, String?>("q" to value)

            paramsList.add(q)
        }

        fun geocode(value:String?) = apply {

            val geocode = mapOf<String, String?>("geocode" to value)

            paramsList.add(geocode)
        }

        fun locale(value:String?) = apply {

            val locale = mapOf<String, String?>("locale" to value)

            paramsList.add(locale)
        }

        fun result_type(value:String?) = apply {

            val result_type = mapOf<String, String?>("result_type" to value)

            paramsList.add(result_type)
        }

        fun count(value:String?) = apply {

            val count = mapOf<String, String?>("count" to value)

            paramsList.add(count)
        }

        fun until(value:String?) = apply {

            val until = mapOf<String, String?>("until" to value)

            paramsList.add(until)
        }

        fun since_id(value:String?) = apply {

            val since_id = mapOf<String, String?>("since_id" to value)

            paramsList.add(since_id)
        }

        fun max_id(value:String?) = apply {

            val max_id = mapOf<String, String?>("max_id" to value)

            paramsList.add(max_id)
        }

        fun include_entities(value:String?) = apply {

            val include_entities = mapOf<String, String?>("include_entities" to value)

            paramsList.add(include_entities)
        }

        fun id(value: String?) = apply {

            val id = mapOf<String, String?>("id" to value)

            paramsList.add(id)
        }

        fun exclude(value:String?) = apply {

            val exclude = mapOf<String, String?>("exclude" to value)

            paramsList.add(exclude)
        }

        fun build() : TwtterURLParams{

            var final_parameters : String = ""

            var counter : Int = 0

            for (value in paramsList){

                counter += 1

                if (counter < paramsList.size){

                    val key = value.keys.toTypedArray()[0]

                    val data = value.get(key)

                    val pair:String = key + "=" + data

                    final_parameters += pair

                    final_parameters += "&"

                }else{

                    val key = value.keys.toTypedArray()[0]

                    val data = value.get(key)

                    val pair:String = key + "=" + data

                    final_parameters += pair
                }

            }

            this.parameters = final_parameters

            return TwtterURLParams(this)
        }

    }
}