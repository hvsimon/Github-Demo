package com.kiwi.github_demo.userdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kiwi.github_demo.R
import com.kiwi.github_demo.databinding.UserDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.user_details_fragment) {

    private val arg by navArgs<UserDetailsFragmentArgs>()
    private val binding by viewBinding(UserDetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}
