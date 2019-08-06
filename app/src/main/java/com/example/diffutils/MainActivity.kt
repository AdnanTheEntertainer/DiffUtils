package com.example.diffutils

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.journaldev.androidrecyclerviewdiffutil.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var modelArrayList = ArrayList<Model>()
    lateinit var adapter: RecyclerViewAdapter
    var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        dummyData()
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(modelArrayList, this)
        recyclerView.adapter = adapter
        fab.setOnClickListener { view ->
            addMoreCoinsToTheList()
        }
        fab_diff.setOnClickListener { view ->
            changePricesInTheList()
        }
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun dummyData() {

        modelArrayList.add(Model(counter++, "Bitcoin", 8000))
        modelArrayList.add(Model(counter++, "Ethereum", 600))
        modelArrayList.add(Model(counter++, "Litecoin", 250))
        modelArrayList.add(Model(counter++, "Bitcoin Cash", 1000))
    }

    private fun addMoreCoinsToTheList() {
        val models = ArrayList<Model>()

        for (model in modelArrayList) {
            models.add(model.clone() as Model)
        }
        models.add(Model(counter++, "Tron", 1))
        models.add(Model(counter++, "Ripple", 5))
        models.add(Model(counter++, "NEO", 100))
        models.add(Model(counter++, "OMG", 20))

        adapter.setData(models)
    }

    private fun changePricesInTheList() {

        var models = ArrayList<Model>()

        for (model in modelArrayList) {
            models.add(model.clone() as Model)
        }

        for (model: Model in models) {
            if (model.price < 900)
                model.price = 900
        }
        adapter.setData(models)
    }
}
