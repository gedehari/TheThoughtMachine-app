package com.gedehari.thethoughtmachine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gedehari.thethoughtmachine.databinding.FragmentThoughtListBinding

class ThoughtListFragment : Fragment() {
    private var _binding: FragmentThoughtListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThoughtListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = ThoughtListFragmentDirections.actionThoughtListFragmentToNewThoughtFragment()
            this.findNavController().navigate(action)
        }
    }
}