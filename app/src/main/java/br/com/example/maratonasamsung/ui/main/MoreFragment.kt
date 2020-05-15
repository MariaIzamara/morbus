package br.com.example.maratonasamsung.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import br.com.example.maratonasamsung.R
import br.com.example.maratonasamsung.tutoriaisRegras.TutorialActivity

/**
 * A simple [Fragment] subclass.
 */
class MoreFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnTutorial).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnRegras).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnCreditos).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnTutorial -> startActivity(Intent(activity, TutorialActivity::class.java))
            R.id.btnRegras -> {
                navController!!.navigate(R.id.action_moreFragment_to_rulesFragment)
            }
            R.id.btnCreditos -> {
                navController!!.navigate(R.id.action_moreFragment_to_creditsFragment)
            }
        }
    }
}
