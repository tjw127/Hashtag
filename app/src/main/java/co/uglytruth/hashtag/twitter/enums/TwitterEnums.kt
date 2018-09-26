package co.uglytruth.hashtag.twitter.enums

/**
 * Created by tjw127 on 1/25/18.
 */
enum class TwitterEnums(val value:String){

    //--------------Twitters Trends API-----------
    trends("trends"),
    available("avaliable"),
    closest("closest"),


    //-------------Response Format--------
    json("json"),

    //--------------Parameters---------
    lat("lat"),
    long("long"),
    q("q"),
    geocode("geocode"),
    lang("lang"),
    locale("locale"),
    result_type("result_type"),
    count("count"),
    until("until"),
    since_id("since_id"),
    max_id("max_id"),
    include_entities("include_entities"),

    //------------Standard Search API-------
    search("search"),
    tweets("tweets")


}