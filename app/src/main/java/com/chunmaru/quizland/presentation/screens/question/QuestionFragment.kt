package com.chunmaru.quizland.presentation.screens.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chunmaru.quizland.R
import com.chunmaru.quizland.databinding.FragmentQuestionBinding
import com.chunmaru.quizland.presentation.screens.question.adapter.QuestionAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var adapterList: QuestionAdapter

    private val args: QuestionFragmentArgs by navArgs()

    private val viewModel: QuestionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(layoutInflater, container, false)

        viewModel.setCategory(args.category)


        viewModel.state.observe(viewLifecycleOwner, Observer { state ->

            when (state) {
                QuestionState.Initial -> Unit
                QuestionState.Pending -> {
                    uiHandlePending()
                }

                is QuestionState.ShowQuestions -> {

                    val currentQuestion = state.currentQuestion
                    val questions = state.question

                    uiHandleSuccess(
                        questions[currentQuestion].image!!,
                        currentQuestion,
                        questions.size
                    )

                    adapterList = QuestionAdapter(onItemClick = {
                        viewModel.onAnswerClick(it)
                    })

                    adapterList.customSubmitList(
                        questions[currentQuestion].answers,
                        questions[currentQuestion].correctAnswer
                    )

                    with(binding.questionList) {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = adapterList

                    }

                    binding.rightArrow.setOnClickListener {
                        viewModel.getNextQuestion()
                    }

                }

                is QuestionState.QuestionEnd -> {
                    binding.mainContainer.visibility = View.GONE

                    with(binding.fragmentWin) {
                        this.root.visibility = View.VISIBLE

                        points.text = state.point.toString()

                        goBack.setOnClickListener {
                            viewModel.goBackWithSaveResult(onSuccess = {
                                findNavController().popBackStack()
                            })
                        }

                        goRestart.setOnClickListener {
                            //todo restart
                        }

                    }

                }

                QuestionState.QuestionsCompleted -> {
                    questionCompleteHandle()
                }
            }

        })

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun questionCompleteHandle() {
        binding.progressPending.visibility = View.INVISIBLE
        binding.messageElement.root.visibility = View.VISIBLE

        binding.messageElement.goBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.messageElement.message.text =
            getString(R.string.you_ve_completed_every_possible_level)
    }


    private fun uiHandlePending() {
        binding.mainContainer.visibility = View.INVISIBLE
        binding.progressPending.visibility = View.VISIBLE
    }

    private fun uiHandleSuccess(image: String, questionIndex: Int, maxQuestion: Int) {
        binding.mainContainer.visibility = View.VISIBLE
        binding.progressPending.visibility = View.INVISIBLE

        val draw1 = binding.root.resources.getIdentifier(
            image,
            "drawable",
            binding.root.context.packageName
        )

        Glide.with(binding.root.context).load(draw1).into(
            binding.questionImg
        )

        val questionCountText = getString(R.string.question_count, questionIndex + 1, maxQuestion)
        binding.questionCount.text = questionCountText

        binding.progressBar.max = maxQuestion - 1
        binding.progressBar.progress = questionIndex

    }


}