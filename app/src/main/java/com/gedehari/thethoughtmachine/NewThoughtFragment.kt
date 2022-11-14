package com.gedehari.thethoughtmachine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gedehari.thethoughtmachine.databinding.FragmentNewThoughtBinding

class NewThoughtFragment : Fragment() {
    private var _binding: FragmentNewThoughtBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewThoughtBinding.inflate(inflater, container, false)
        return binding.root
    }
}