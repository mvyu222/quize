package com.chunmaru.quizland.presentation.screens.profile

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
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chunmaru.quizland.R
import com.chunmaru.quizland.databinding.BottomsheetIcondialogBinding
import com.chunmaru.quizland.databinding.FragmentProfileBinding
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        viewModel.userData.observe(viewLifecycleOwner, Observer { userModel ->

            binding.userName.text = userModel.name
            binding.coins.text = userModel.score.toString()


            val draw = resources.getIdentifier(
                userModel.pic,
                "drawable",
                binding.root.context.packageName
            )
            Glide.with(binding.root.context).load(draw).into(binding.userImg)

            binding.userName.setOnClickListener {
                dialogChangeNameHandle()
            }

            binding.userImg.setOnClickListener {
                dialogChangeIconHandle()
            }

        })

        binding.getStart.setOnClickListener {
            findNavController().navigate(R.id.home)
        }

        binding.linearLayout4.setOnClickListener {
            showAlertDialogReset()
        }

        return binding.root
    }


    private fun showAlertDialogReset() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Reset Confirm")
            .setMessage("Do you really want to reset your results?")
            .setPositiveButton("Ok") { dialog, _ ->
                viewModel.resetUserScore()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }


    private fun dialogChangeIconHandle() {

        val binding = BottomsheetIcondialogBinding.inflate(layoutInflater)

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)

        val iconMap = mapOf(
            R.id.person1 to "person1",
            R.id.person2 to "person2",
            R.id.person3 to "person3",
            R.id.person4 to "person4",
            R.id.person5 to "person5",
            R.id.person6 to "person6",
            R.id.person7 to "person7",
            R.id.person8 to "person8",
            R.id.person9 to "person9"
        )

        val clickedItemHandle = View.OnClickListener { view ->
            val iconName = iconMap[view.id]
            if (iconName != null) {
                viewModel.setNewIcon(iconName)
            }
            dialog.dismiss()

        }

        binding.root.setClickListenersToShareableImageViews(clickedItemHandle)

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

    }


    private fun dialogChangeNameHandle() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet_namedialog)

        val buttonSuccess = dialog.findViewById<TextView>(R.id.button_success)
        val editText = dialog.findViewById<EditText>(R.id.edit_tv)

        buttonSuccess.setOnClickListener {
            viewModel.setNewName(editText.text.toString())

            dialog.dismiss()
        }

        val parentHeight = (binding.root.height * 0.8).toInt()

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            parentHeight
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun ViewGroup.setClickListenersToShareableImageViews(listener: View.OnClickListener) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child is ViewGroup) {
                child.setClickListenersToShareableImageViews(listener)
            } else if (child is ShapeableImageView) {
                child.setOnClickListener(listener)
            }
        }
    }


}