package com.svarks.movie.ui.moviecontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.svarks.movie.databinding.FragmentMovieDetailsBinding
import com.svarks.movie.util.AppConstant

/**
 * MovieDetailsFragment
 * Displaying the details of the particular movie
 */
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initUI()
    }

    /**
     * Binding the data from the MoviesFragment
     */
    private fun initUI() {
        binding.apply {
            tvByLine.text = arguments?.getString(AppConstant.BYLINE, "")
            tvByHeadline.text = arguments?.getString(AppConstant.HEADLINE, "")
            tvByShortSummary.text = arguments?.getString(AppConstant.SHORT_SUMMARY, "")
            tvByPublicationDate.text = arguments?.getString(AppConstant.PUBLICATION_DATE, "")
            tvByOpeningDate.text = arguments?.getString(AppConstant.OPENING_DATE, "")
        }
    }
}
