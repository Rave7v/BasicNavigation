package fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicnavigation.R
import com.example.basicnavigation.databinding.FragmentDestinationBinding
import com.example.basicnavigation.database.User
import com.example.basicnavigation.databinding.FragmentLeftBinding

class destinationFragment : Fragment() {

    private lateinit var binding: FragmentDestinationBinding
    private val destinationViewModel: DestinationViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDestinationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedUsername = arguments?.getString("username_arg")
        binding.tvReceivedArg.setText(receivedUsername)
        binding.rvUserEntries.layoutManager = LinearLayoutManager(view?.context)

        destinationViewModel.getUsers()

        destinationViewModel.savedUsers.observe(viewLifecycleOwner,{ usersList->
            if (!usersList.isNullOrEmpty()){
                val adapter = destinationAdapter(usersList)
                binding.rvUserEntries.adapter = adapter

                for (saveduser in usersList){
                    Log.d("obtainedusers","from fragment user: ${saveduser.username}")
                }
            }else{
                Log.d("obtainedusers","from fragment is null or empty")
            }
        })


    }
}