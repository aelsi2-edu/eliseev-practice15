package com.aelsi2.practice15

import androidx.fragment.app.FragmentActivity
import android.os.Bundle
import android.view.View
import com.aelsi2.practice15.utils.debouncers.OnClickDebouncer

class MainActivity : FragmentActivity(), View.OnClickListener {
    private lateinit var routeLine : View
    private lateinit var firstRouteItemSelected : View
    private lateinit var firstRouteItem : View
    private lateinit var logOutButton : View
    private var routeShown = false
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    private val routeItems = HashSet<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    private fun initViews() {
        firstRouteItem = findViewById(R.id.first_route_item)
        firstRouteItemSelected = findViewById(R.id.first_route_item_selected)
        logOutButton = findViewById(R.id.sign_out_button)
        routeLine = findViewById(R.id.route_line)
        routeItems.add(findViewById(R.id.first_route_item))
        routeItems.add(findViewById(R.id.first_route_item_selected))
        routeItems.add(findViewById(R.id.route_item_2))
        routeItems.add(findViewById(R.id.route_item_3))
        routeItems.add(findViewById(R.id.route_item_4))
        routeItems.add(findViewById(R.id.route_item_5))
        routeItems.add(findViewById(R.id.route_item_6))
        for (item in routeItems) {
            item.setOnClickListener(onClickDebouncer)
        }
        logOutButton.setOnClickListener(onClickDebouncer)
    }

    private fun toggleRoute() {
        routeShown = !routeShown
        if (routeShown) {
            routeLine.visibility = View.VISIBLE
            firstRouteItemSelected.visibility = View.VISIBLE
            if (firstRouteItem.isFocused) firstRouteItemSelected.requestFocus()
            firstRouteItem.visibility = View.GONE

        }
        else {
            routeLine.visibility = View.GONE
            firstRouteItem.visibility = View.VISIBLE
            if (firstRouteItemSelected.isFocused) firstRouteItem.requestFocus()
            firstRouteItemSelected.visibility = View.GONE
        }
    }

    override fun onClick(view: View?) {
        if (routeItems.contains(view)) {
            toggleRoute()
            return
        }
        when (view) {
            logOutButton -> {
                finish()
                return
            }
        }
    }
}