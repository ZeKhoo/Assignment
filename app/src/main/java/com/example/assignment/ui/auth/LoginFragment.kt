package com.example.assignment.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
//import androidx.lifecycle.Observer
import com.example.assignment.databinding.FragmentLoginBinding
import com.example.assignment.data.network.AuthApi
import com.example.assignment.data.network.Resource
import com.example.assignment.data.repository.AuthRepository
import com.example.assignment.ui.auth.base.BaseFragment
import com.example.assignment.ui.auth.home.HomeActivity

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonSignIn.enable(false)


        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(false)
            when(it){
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Fail", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.editTextTextPassword.addTextChangedListener{
            val staffid = binding.editTextTextStaffId.text.toString().trim()
            binding.buttonSignIn.enable(staffid.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonSignIn.setOnClickListener {
            val staffid = binding.editTextTextStaffId.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            binding.progressbar.visible(true)
            viewModel.login(staffid,password)
        }
    }



    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java ))


}