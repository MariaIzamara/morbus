package br.com.example.maratonasamsung


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class RoomTypekFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_typek, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnCriar).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnAcessar).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnModoOffline).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnCriar -> navController!!.navigate(R.id.action_roomTypekFragment_to_roomCreateFragment)
            R.id.btnAcessar -> navController!!.navigate(R.id.action_roomTypekFragment_to_roomAcessFragment)
            R.id.btnModoOffline -> navController!!.navigate(R.id.action_roomCreateFragment_to_placeholder)
        }
    }

}
