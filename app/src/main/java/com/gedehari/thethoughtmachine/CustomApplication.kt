package com.gedehari.thethoughtmachine

import android.app.Application
import com.gedehari.thethoughtmachine.data.ThoughtDatabase

class CustomApplication: Application() {
    val database: ThoughtDatabase by lazy { ThoughtDatabase.getDatabase(this) }
}
