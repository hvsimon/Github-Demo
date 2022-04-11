package com.kiwi.github_demo.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.kiwi.github_demo.R
import com.kiwi.github_demo.data.entites.UserDetails
import com.kiwi.github_demo.databinding.UserDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.user_details_fragment), UserDetailsContract.View {

    private val arg by navArgs<UserDetailsFragmentArgs>()
    private val binding by viewBinding(UserDetailsFragmentBinding::bind)

    @Inject
    lateinit var presenterFactory: UserDetailsPresenter.Factory
    lateinit var presenter: UserDetailsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = presenterFactory.create(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        presenter.loadUser(arg.userName)
    }

    override fun showUserDetails(userDetails: UserDetails) {
        binding.ivAvatar.load(userDetails.avatarUrl) {
            transformations(CircleCropTransformation())
        }
        binding.tvName.text = userDetails.name
        binding.tvBio.text = userDetails.bio
        binding.tvLogin.text = userDetails.login
        binding.tvBadge.isVisible = userDetails.siteAdmin
        binding.tvLocation.text = userDetails.location
        binding.tvLink.text = userDetails.blog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}
