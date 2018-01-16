package co.uglytruth.hashtag

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import co.uglytruth.hashtag.adapter.HashtagAdapter
import co.uglytruth.hashtag.endpoint.Endpoint
import co.uglytruth.hashtag.hashtag.Hashtag
import co.uglytruth.hashtag.hashtag.HashtagRetrofitRequest

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var hashtagList:ArrayList<Hashtag>? = ArrayList<Hashtag>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //var url = Endpoint.Builder().baseUrl().hashtag().key().build()



//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        loadHastag()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }



    override fun onDestroy() {
        super.onDestroy()
    }

    private fun loadHastag()
    {
        val request:HashtagRetrofitRequest = HashtagRetrofitRequest()

        //hashtagList = request.callHastagData(result = hashtagList)

        Log.v("HashtagList1", hashtagList.toString())

        //val adapter:HashtagAdapter = HashtagAdapter(list = hashtagList, context = this)

        val recyclerView:RecyclerView = this.findViewById(R.id.hashtagRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)

        request.callHastagData(recyclerView = recyclerView, context = this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_reload -> consume { loadHastag() }

            else -> super.onOptionsItemSelected(item)
        }
    }

    inline fun consume (f: () -> Unit): Boolean {

        f()
        return true

    }


}
