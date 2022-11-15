package com.gedehari.thethoughtmachine

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gedehari.thethoughtmachine.databinding.FragmentThoughtListBinding
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class ThoughtListFragment : Fragment() {
    private val viewModel: ThoughtViewModel by activityViewModels {
        ThoughtViewModelFactory(
            (activity?.application as CustomApplication).database.thoughtDao()
        )
    }

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

        val adapter = ThoughtListAdapter()

        binding.thoughtList.layoutManager = LinearLayoutManager(this.context)
        binding.thoughtList.adapter = adapter
        binding.thoughtList.itemAnimator = FadeInAnimator()
        viewModel.allThoughts.observe(this.viewLifecycleOwner) {
            it.let {
                adapter.submitList(it)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ThoughtListFragmentDirections.actionThoughtListFragmentToNewThoughtFragment()
            this.findNavController().navigate(action)
        }
    }
}