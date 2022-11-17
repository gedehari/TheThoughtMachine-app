package com.gedehari.thethoughtmachine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gedehari.thethoughtmachine.databinding.FragmentThoughtListBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        binding.swipeRefresh.setOnRefreshListener { getLatestThoughts() }

        val adapter = ThoughtListAdapter()
        binding.thoughtList.layoutManager = LinearLayoutManager(this.context)
        binding.thoughtList.adapter = adapter
        //binding.thoughtList.itemAnimator = FadeInAnimator()
        viewModel.allThoughts.observe(this.viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.errorText.text = "No Thoughts..."
                binding.errorText.visibility = View.VISIBLE
            }
            else
                binding.errorText.visibility = View.GONE

            it.let {
                adapter.submitList(it)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ThoughtListFragmentDirections.actionThoughtListFragmentToNewThoughtFragment()
            this.findNavController().navigate(action)
        }

        getLatestThoughts()
    }

    private fun getLatestThoughts() {
        binding.swipeRefresh.isRefreshing = true

        viewModel.getLatestThoughts {
            binding.swipeRefresh.isRefreshing = false
            viewLifecycleOwner.lifecycleScope.launch {
                delay(100L)
                binding.thoughtList.smoothScrollToPosition(0)
            }
        }
    }
}