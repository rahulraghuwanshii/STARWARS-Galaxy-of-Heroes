package com.rahulraghuwanshi.starwarshero.presentation.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.rahulraghuwanshi.starwarshero.databinding.FragmentCharacterDetailBinding
import com.rahulraghuwanshi.starwarshero.di.core.Injector
import com.rahulraghuwanshi.starwarshero.presentation.character_detail.movies.MoviesAdapter
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailFragment : Fragment() {


    @Inject
    lateinit var characterDetailViewModelFactory: CharacterDetailViewModelFactory

    private lateinit var viewModel: CharacterDetailViewModel

    private val moviesAdapter by lazy { MoviesAdapter() }

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs<CharacterDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        (requireContext().applicationContext as Injector).createCharacterDetailSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(
            this,
            characterDetailViewModelFactory
        )[CharacterDetailViewModel::class.java]

        getData()

        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        collectFlow()
    }

    private fun getData() {
        viewModel.getHomeWorldData(args.characterDetails.homeWorld)
        viewModel.getMoviesData(args.characterDetails.films)
    }

    private fun setUp() {
        val character = args.characterDetails

        binding.txtName.text = character.name
        binding.txtDetailName.text = character.name
        binding.txtSkinColor.text = character.skinColor.uppercase()
        binding.txtHairColor.text = character.hairColor.uppercase()
        binding.txtHeight.text = character.height
        binding.txtMass.text = character.mass
        binding.txtEyeColor.text = character.eyeColor.uppercase()
        binding.txtGender.text = character.gender.uppercase()
        binding.txtBirthYear.text = character.birthYear

        binding.imgBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectFlow() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.homeWorldFlow.collect {
                    when (it.status) {
                        RestClientResult.Status.SUCCESS -> {
                            binding.progressBarHomeWord.isVisible = false
                            binding.txtHomeWorld.isVisible = true
                            binding.txtHomeWorld.text = it.data?.name
                        }

                        RestClientResult.Status.ERROR -> {
                            binding.progressBarHomeWord.isVisible = false
                            binding.txtHomeWorld.isVisible = true
                            binding.txtHomeWorld.text = it.message
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }

                        RestClientResult.Status.LOADING -> {
                            binding.progressBarHomeWord.isVisible = true
                            binding.txtHomeWorld.isVisible = false
                        }

                        RestClientResult.Status.NONE -> {

                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movieDetailsFlow.collect {
                    when (it.status) {
                        RestClientResult.Status.SUCCESS -> {
                            binding.progressMovies.isVisible = false
                            moviesAdapter.submitList(it.data)
                            binding.rvMovies.adapter = moviesAdapter
                        }

                        RestClientResult.Status.ERROR -> {
                            binding.progressMovies.isVisible = false
                            binding.txtMoviesError.isVisible = true
                            binding.txtMoviesError.text = it.message
                        }

                        RestClientResult.Status.LOADING -> {
                            binding.progressMovies.isVisible = true
                        }

                        RestClientResult.Status.NONE -> {}
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}