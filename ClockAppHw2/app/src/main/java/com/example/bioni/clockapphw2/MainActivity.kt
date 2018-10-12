package com.example.bioni.clockapphw2

import android.icu.util.GregorianCalendar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.CustomDigitalClock
import android.widget.DigitalClock
import com.example.bioni.clockapphw2.R.id.addHour

class CustomTime {
    var cal = GregorianCalendar()                                           //Time can be manipulated

    var sysCal = GregorianCalendar.getInstance()                   //Gets a calendar with the default timezone (System time)

    /**********
     * Function will be used to initialize the default time
     ************/

    fun addHour() {
        cal.add(GregorianCalendar.HOUR_OF_DAY, 1)
    }
    fun minHour() {
        cal.add(GregorianCalendar.HOUR_OF_DAY, -1)
    }
    fun addMin () {
        cal.add(GregorianCalendar.MINUTE, 1)
    }
    fun minMin() {
        cal.add(GregorianCalendar.MINUTE, -1)
    }
    fun addSec() {
        cal.add(GregorianCalendar.SECOND, 1)
    }

    fun minSec() {
        cal.add(GregorianCalendar.SECOND, -1)
    }
    fun addMonth() {
        cal.add(GregorianCalendar.MONTH, 1)
    }
    fun minMonth() {
        cal.add(GregorianCalendar.MONTH, -1)
    }
    fun addDay() {
        cal.add(GregorianCalendar.DAY_OF_MONTH, 1)
    }
    fun minDay() {
        cal.add(GregorianCalendar.DAY_OF_MONTH, -1)
    }
    fun addYear() {
        cal.add(GregorianCalendar.YEAR, 1)
    }
    fun minYear() {
        cal.add(GregorianCalendar.YEAR, -1)
    }
    //Will Handle the runnable threads
    var hand = Handler()

    // threads
    var r: Runnable = object : Runnable {
        override fun run() {
            cal.add(GregorianCalendar.SECOND, 1)
            hand.postDelayed(this, 1000)
            Log.d("Time Test", cal.time.toString())//Will continuously increment the seconds field of time
        }
    }

    init {
        //Log.d("Test", "Creating CustomClock")
        cal.set(GregorianCalendar.HOUR_OF_DAY, sysCal.get(GregorianCalendar.HOUR_OF_DAY))                       //Will set the initial hour
        cal.set(GregorianCalendar.MINUTE, sysCal.get(GregorianCalendar.MINUTE))                          //Will set the initial minute
        cal.set(GregorianCalendar.SECOND, sysCal.get(GregorianCalendar.SECOND))                         //Will set the initial second
        cal.set(GregorianCalendar.MONTH, sysCal.get(GregorianCalendar.MONTH))
        cal.set(GregorianCalendar.DAY_OF_MONTH, sysCal.get(GregorianCalendar.DAY_OF_MONTH))
        cal.set(GregorianCalendar.YEAR, sysCal.get(GregorianCalendar.YEAR))
        cal.set(GregorianCalendar.AM_PM, sysCal.get(GregorianCalendar.AM_PM))                            //Will set am/pm
        hand.post(r)
    }
    fun killThread() {
        hand.removeCallbacks(r)
    }
}

interface Command {
    fun execute()
    fun dexecute()
}

class TimeCommand (clk: CustomTime, key: Int) : Command {
    override fun dexecute() {
        if(key == 0) {
            modelClock.minHour()
        }
        //decrement hour field
        else if(key == 1) {
            modelClock.addHour()
        }
        else if (key == 2 ){
            modelClock.minMin()
        }
        else if (key == 3) {
            modelClock.addMin()
        }

        else if (key == 4) {
            modelClock.minSec()
        }
        else if (key == 5) {
            modelClock.addSec()
        }
        else if(key == 6) {
            modelClock.minMonth()
        }
        else if(key == 7) {
            modelClock.addMonth()
        }
        else if (key==8) {
            modelClock.minDay()
        }
        else if (key == 9) {
            modelClock.addDay()
        }
        else if(key == 10) {
            modelClock.minYear()
        }
        else if(key == 11) {
            modelClock.addYear()
        }
    }

    var modelClock = clk
    var key = key
    override fun execute() {
        //increment hour field
        if(key == 0) {
            modelClock.addHour()
        }
        //decrement hour field
        else if(key == 1) {
            modelClock.minHour()
        }
        else if (key == 2 ){
            modelClock.addMin()
    }
        else if (key == 3) {
            modelClock.minMin()
        }

        else if (key == 4) {
            modelClock.addSec()
    }
        else if (key == 5) {
            modelClock.minSec()
        }
        else if(key == 6) {
            modelClock.addMonth()
        }
        else if(key == 7) {
            modelClock.minMonth()
        }
        else if (key==8) {
            modelClock.addDay()
        }
        else if (key == 9) {
            modelClock.minDay()
        }
        else if(key == 10) {
            modelClock.addYear()
        }
        else if(key == 11) {
            modelClock.minYear()
        }
}
}


class MainActivity : AppCompatActivity() {

    var cTime = CustomTime()
    /**********************************
     * The CustomTime class will be used as the model representation for this application
     * as the data for the clocks will be passed into the class based on the button pressed
     * by the user
     *
     ************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addHr = findViewById<Button>(R.id.addHour)
        val minHr = findViewById<Button>(R.id.minusHour)
        val addMin = findViewById<Button>(R.id.addMin)
        val minMin = findViewById<Button>(R.id.minMin)
        val addSec = findViewById<Button>(R.id.addSec)
        val minSec = findViewById<Button>(R.id.minSec)
        val addMonth = findViewById<Button>(R.id.addMonth)
        val minMonth = findViewById<Button>(R.id.minMonth)
        val addDay = findViewById<Button>(R.id.addDay)
        val minDay = findViewById<Button>(R.id.minDay)
        val addYear = findViewById<Button>(R.id.addYear)
        val minYear = findViewById<Button>(R.id.minYear)
        val dClock = findViewById<CustomDigitalClock>(R.id.digitalClock)

        dClock.clockSet(cTime)



        addHr.setOnClickListener{
            var command = TimeCommand (cTime,0)
            command.execute()

        }
        minHr.setOnClickListener {
            var command = TimeCommand (cTime, 1)
            command.execute()
        }
        addMin.setOnClickListener {
            var command = TimeCommand (cTime, 2)
            command.execute()

        }
        minMin.setOnClickListener{
            var command = TimeCommand (cTime, 3)
            command.execute()
        }
        addSec.setOnClickListener{
            var command = TimeCommand(cTime, 4)
            command.execute()
        }
        minSec.setOnClickListener{
            var command = TimeCommand(cTime, 5)
            command.execute()
        }
        addMonth.setOnClickListener{
            var command = TimeCommand(cTime, 6)
            command.execute()
        }
        minMonth.setOnClickListener{
            var command = TimeCommand(cTime, 7)
            command.execute()
        }
        addDay.setOnClickListener{
            var command = TimeCommand(cTime, 8)
            command.execute()
        }
        minDay.setOnClickListener {
            var command = TimeCommand(cTime, 9)
            command.execute()
        }
        addYear.setOnClickListener{
            var command = TimeCommand(cTime, 10)
            command.execute()
        }
        minYear.setOnClickListener{
            var command = TimeCommand(cTime, 11)
            command.execute()
        }




    }










}
