package com.kiwi.github_demo.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kiwi.github_demo.R
import com.kiwi.github_demo.databinding.UserListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private val binding by viewBinding(UserListFragmentBinding::bind)

    @Inject
    lateinit var presenter: UserListPresenter

    private val adapter = UserListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()

        lifecycleScope.launch {
            presenter.dataFlow.collect(adapter::submitData)
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
    }
}
