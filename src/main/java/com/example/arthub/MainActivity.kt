package com.example.arthub

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.Surface
import android.widget.TextView
import androidx.room.Room
import com.example.arthub.ArtHub_Tables.ARTHUB_PROJECT_DAO
import com.example.arthub.ArtHub_Tables.Arthub_DB

import com.example.arthub.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    val applicationScope = CoroutineScope(SupervisorJob())


    private val viewModel: YourViewModel by viewModels { YourViewModelFactory(repository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val the_db by lazy {Arthub_DB.Get_Arthub_DB(this, applicationScope)}

        val test_text = findViewById<TextView>(R.id.TITLE_TEST)

        viewModel.allData.observe(this, Observer { dataList ->
            dataList.forEach { data ->
                println("ID: ${data.id}, Data: ${data.data}")
            }
        })

    }


}