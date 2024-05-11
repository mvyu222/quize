package com.chunmaru.quizland.presentation.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chunmaru.quizland.R
import com.chunmaru.quizland.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        binding.message.goBack.visibility = View.GONE
        binding.message.message.text = getString(R.string.this_section_is_not_currently_implemented)

        return binding.root
    }

}