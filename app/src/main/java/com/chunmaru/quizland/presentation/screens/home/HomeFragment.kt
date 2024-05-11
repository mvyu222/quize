package com.chunmaru.quizland.presentation.screens.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chunmaru.quizland.R
import com.chunmaru.quizland.data.models.CategoryConst
import com.chunmaru.quizland.data.models.UserModel
import com.chunmaru.quizland.databinding.ElementMessageBinding
import com.chunmaru.quizland.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // viewModel.insertData()

        viewModel.userData.observe(viewLifecycleOwner, Observer { userModel ->
            userDataHandle(userModel)
        })

        binding.cardCreateQuiz.setOnClickListener { dialogChangeNameHandle() }
        binding.cardMultiPlayer.setOnClickListener { dialogChangeNameHandle() }

        onCategoryHandle()
        return binding.root
    }

    private fun userDataHandle(userModel: UserModel) {
        val welcomeMessage = getString(R.string.welcome_message, userModel.name)
        binding.tvName.text = welcomeMessage

        binding.coints.text = userModel.score.toString()

        val draw1 = resources.getIdentifier(
            userModel.pic,
            "drawable",
            binding.root.context.packageName
        )

        Glide.with(binding.root.context).load(draw1).into(binding.userImage)

    }

    private fun onCategoryHandle() {

        val onStartClick = View.OnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment()
            findNavController().navigate(action)
        }

        binding.cardSinglePlayer.setOnClickListener(onStartClick)
        binding.btStartNow.setOnClickListener(onStartClick)

        binding.art.setOnClickListener {
            navigateByCategory(CategoryConst.ART)
        }

        binding.history.setOnClickListener {
            navigateByCategory(CategoryConst.HISTORY)
        }

        binding.sport.setOnClickListener {
            navigateByCategory(CategoryConst.SPORT)
        }

        binding.science.setOnClickListener {
            navigateByCategory(CategoryConst.SCIENCE)
        }

    }


    private fun dialogChangeNameHandle() {

        val bind = ElementMessageBinding.inflate(layoutInflater)

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bind.root)


        bind.goBack.visibility = View.GONE
        bind.message.text = getString(R.string.this_section_is_not_currently_implemented)

        val high = binding.root.height * 0.5
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            high.toInt()
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.CENTER)
    }


    private fun navigateByCategory(category: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToQuestionFragment(category)
        findNavController().navigate(action)
    }


}