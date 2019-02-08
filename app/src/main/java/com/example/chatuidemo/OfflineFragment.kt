package com.example.chatuidemo


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_offline.*


class OfflineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline, container, false)
    }

    override fun onStart() {
        retryButton.setOnClickListener {
            (activity as MainActivity).handleScreenClick()
        }
        super.onStart()
    }


}
