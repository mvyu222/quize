package com.chunmaru.quizland.presentation.screens.question.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunmaru.quizland.databinding.QuestionItemBinding


class QuestionAdapter(
    val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private var correctAnswer: String = ""
    private var onItemClicked = false


    fun customSubmitList(answers: List<String>, correctAnswer: String) {
        differ.submitList(answers)
        this.correctAnswer = correctAnswer
    }

    class ViewHolder(
        val binding: QuestionItemBinding
    ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = differ.currentList[position]
        with(holder.binding) {
            tvQuestionText.text = answer

            container.setOnClickListener {
                if (!onItemClicked) {
                    if (answer == correctAnswer) {
                        positive.visibility = View.VISIBLE
                    } else {
                        negative.visibility = View.VISIBLE
                    }
                    onItemClicked = true
                    onItemClick(answer)
                }

            }

        }

    }

    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differCallBack)


}
