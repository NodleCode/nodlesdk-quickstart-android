package com.nodlesdk.android.nodlesdk.quickstart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.nodle.sdk.NodleBluetoothScanRecord
import io.nodle.sdk.NodleEvent
import io.nodle.sdk.NodleEventType
import io.nodle.sdk.android.Nodle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start Nodle -> add the key in the following format -> ss58:your-public-key
        Nodle.Nodle().start( "ss58:your-public-key-here", "tag1", "tag2")

        // show the events
        CoroutineScope(Dispatchers.Main).launch {
            Nodle.Nodle().getEvents().collect {
                when (it.type) {
                    NodleEventType.BlePayloadEvent -> payloadCheck(it)
                    NodleEventType.BleStartSearching -> println("Bluetooth started searching ")
                    NodleEventType.BleStopSearching -> println("Bluetooth stopped searching")
                }
            }
        }
    }

    fun payloadCheck(payload: NodleEvent) {
        val data = payload as NodleBluetoothScanRecord
        println("Bluetooth payload available ${data.device} ")
    }
}