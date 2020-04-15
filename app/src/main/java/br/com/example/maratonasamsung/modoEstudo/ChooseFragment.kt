package br.com.example.maratonasamsung.modoEstudo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.example.maratonasamsung.R
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.example.maratonasamsung.model.Responses.DoencasResponse
import br.com.example.maratonasamsung.service.Service
import kotlinx.android.synthetic.main.fragment_choose.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            doencas()
        }
  
    fun doencas(){
        Service.retrofit.doencas().enqueue(object : Callback<List<DoencasResponse>>{
            override fun onFailure(call: Call<List<DoencasResponse>>, t: Throwable) {
                Log.d("Deu ruim!!!",t.toString())
            }

            override fun onResponse(call: Call<List<DoencasResponse>>, response: Response<List<DoencasResponse>>) {
                Log.d("Sucesso", response.body().toString())
                var doenca: List<DoencasResponse> = response!!.body()!!
                recyclerDoencas.apply{
                    layoutManager = LinearLayoutManager(activity)
                    adapter = DoencaAdapter(doenca.filter { it.tipo == arguments!!.getString("agenteInfectante") })
                }
            }
        })
    }
}

