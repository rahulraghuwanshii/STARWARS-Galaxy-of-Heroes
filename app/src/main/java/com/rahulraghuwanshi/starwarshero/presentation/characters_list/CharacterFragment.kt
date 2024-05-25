package com.rahulraghuwanshi.starwarshero.presentation.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.rahulraghuwanshi.starwarshero.databinding.FragmentCharacterListBinding
import com.rahulraghuwanshi.starwarshero.di.core.Injector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharacterFragment : Fragment() {

    @Inject
    lateinit var charactersViewModelFactory: CharactersViewModelFactory

    private lateinit var charactersViewModel: CharactersViewModel

    private lateinit var charactersAdapter: CharactersAdapter

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireContext().applicationContext as Injector).createCharacterListSubComponent()
            .inject(this)

        charactersViewModel = ViewModelProvider(
            this,
            charactersViewModelFactory
        )[CharactersViewModel::class.java]

        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        collectFlow()
    }

    private fun getData() {
        charactersViewModel.fetchCharactersList()
    }

    private fun setUp() {
        charactersAdapter = CharactersAdapter {
            val action =
                CharacterFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    characterDetails = it
                )
            findNavController().navigate(action)
        }
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvStarWarCharacter.layoutManager = layoutManager
        binding.rvStarWarCharacter.adapter = charactersAdapter
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                charactersViewModel.characterListFlow.collectLatest {
                    it?.let {
                        charactersAdapter.submitData(lifecycle, it)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                charactersAdapter.loadStateFlow.collectLatest {
                    if (charactersAdapter.itemCount > 0) {
                        binding.rvStarWarCharacter.visibility = View.VISIBLE
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                charactersAdapter.addLoadStateListener { loadState ->

                    if (loadState.refresh is LoadState.Loading ||
                        loadState.append is LoadState.Loading
                    ) {
                        // Show ProgressBar
                        binding.charactersProgressBar.visibility = View.VISIBLE
                    } else {
                        // Hide ProgressBar
                        binding.charactersProgressBar.visibility = View.GONE

                        // If we have an error, show a toast
                        val errorState = when {
                            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                            else -> null
                        }
                        errorState?.let {
                            Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG)
                                .show()
                        }
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