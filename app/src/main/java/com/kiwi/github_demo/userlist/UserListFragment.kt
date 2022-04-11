package com.kiwi.github_demo.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kiwi.github_demo.R
import com.kiwi.github_demo.data.entites.User
import com.kiwi.github_demo.databinding.UserListFragmentBinding
import com.kiwi.github_demo.userlist.UserListFragmentDirections.Companion.actionUserListFragmentToUserDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.user_list_fragment), UserListContract.View {

    private val binding by viewBinding(UserListFragmentBinding::bind)

    @Inject
    lateinit var presenterFactory: UserListPresenter.Factory
    lateinit var presenter: UserListContract.Presenter

    private val adapter = UserListAdapter { user ->
        findNavController().navigate(actionUserListFragmentToUserDetailsFragment(user.login))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = presenterFactory.create(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    override fun addData(data: PagingData<User>) {
        lifecycleScope.launch {
            adapter.submitData(data)
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
    }
}
