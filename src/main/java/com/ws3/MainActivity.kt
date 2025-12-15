package com.ws3

import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnDragListener, View.OnTouchListener { // klass
    private lateinit var fView: View
    private lateinit var fView2: View
    private lateinit var sView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fView = findViewById<View>(R.id.imageFrame)
        sView = findViewById<View>(R.id.imageSquare)
        fView2 = findViewById<View>(R.id.imageView3)

        fView.setOnDragListener(this)
        fView2.setOnDragListener(this)

        sView.setOnTouchListener(this)
        sView.setOnDragListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val shadow = View.DragShadowBuilder(v)
            v.startDragAndDrop(null, shadow, v, 0)
            //v.visibility = View.INVISIBLE
            return true
        }
        return false
    }

    override fun onDrag(v: View, event: DragEvent) : Boolean {
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> return true
            DragEvent.ACTION_DRAG_LOCATION -> return true
            DragEvent.ACTION_DRAG_ENTERED -> return true
            DragEvent.ACTION_DRAG_EXITED -> return true

            DragEvent.ACTION_DROP -> {
                val draggedView = event.localState as View
                draggedView.x = v.x
                draggedView.y = v.y
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                val draggedView = event.localState as View
                draggedView.visibility = View.VISIBLE
                return true
            }
        }
        return false
    }
}