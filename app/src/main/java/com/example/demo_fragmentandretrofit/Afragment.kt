package com.example.demo_fragmentandretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.room.Room
import com.example.demo_fragmentandretrofit.roomDB.RoomDao
import com.example.demo_fragmentandretrofit.roomDB.RoomDbHelper
import com.example.demo_fragmentandretrofit.roomDB.RoomEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class Afragment : Fragment() {

    var fragmentActivity: FragmentActivity? = null
    var db: RoomDbHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentActivity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.a_fragment, container, false)
    }

    var i = 1

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(this)
        val btn = view.findViewById<Button>(R.id.button)
        val txt = view.findViewById<TextView>(R.id.textView)
        val btnn = view.findViewById<Button>(R.id.button2)
        btn.setOnClickListener {
//            navController.navigate(R.id.b_fragment)
//            txt.visibility = View.GONE
            GlobalScope.launch {
                db!!.roomDao()!!.insert(RoomEntity().apply {
                    id = i
                    name = "hina"
                })
            }
            i++
        }
        btnn.setOnClickListener {
//            navController.navigate(R.id.action_a_fragment_to_b_fragment)
//            txt.visibility = View.VISIBLE
            GlobalScope.launch {
                var list=db!!.roomDao()!!.getAll()
                list.forEach {item->
                    println(item.id)
                    println(item.name)
                }
            }
        }

        val btn3: Button = view.findViewById(R.id.button3)
        btn3.setOnClickListener {
            db= RoomDbHelper(fragmentActivity!!.applicationContext)
            val roomDao=db!!.roomDao()
            GlobalScope.launch {
                roomDao!!.insert(RoomEntity().apply {
                    id = i
                    name = "ina"
                })
            }
            i++
        }

//        val wah = ScheduledThreadPoolExecutor(3)
//        wah.scheduleWithFixedDelay(Runnable { // TODO Auto-generated method stub
//            i = i.toString() + "a"
//            System.out.println(i)
//        }, 0, 1, TimeUnit.SECONDS)


    }
}