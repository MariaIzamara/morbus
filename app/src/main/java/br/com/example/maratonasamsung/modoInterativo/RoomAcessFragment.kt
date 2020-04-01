package br.com.example.maratonasamsung.modoInterativo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import br.com.example.maratonasamsung.R
import br.com.example.maratonasamsung.model.Requests.SalaRequest

import br.com.example.maratonasamsung.model.Responses.SalaResponse
import br.com.example.maratonasamsung.model.Responses.SessaoResponse
import br.com.example.maratonasamsung.service.Service
import kotlinx.android.synthetic.main.fragment_room_acess.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomAcessFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_acess, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.acessBtnContinuar).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.acessBtnContinuar -> {
                if(acessEditNomeSala.text.toString() == "" || acessEditSenha.text.toString() == "") {
                    var texto = "Preencha todos os campos obrigatórios"
                    val duracao = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, texto, duracao)
                    toast.show()
                }
                else if(acessEditNomeSala.text.toString() != "" && acessEditSenha.text.toString() != "")
                    acessarSala()
            }
        }
    }

    fun acessarSala(){
        Service.retrofit.acessarSala(
            nome = acessEditNomeSala.text.toString()
        ).enqueue(object : Callback<SalaResponse>{
            override fun onFailure(call: Call<SalaResponse>, t: Throwable) {
                Log.d("Deu ruim", t.toString())
            }
            override fun onResponse(call: Call<SalaResponse>, response: Response<SalaResponse>) {
                Log.d("Nice", response.toString())

                val sala = response.body()

                if(sala!!.status) {
                    if (sala!!.senha == acessEditSenha.text.toString())
                        sessao(sala!!.nome, sala!!.senha)
                    else {
                        var texto = "Senha inválida"
                        val duracao = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(context, texto, duracao)
                        toast.show()
                        acessEditSenha.setText("")
                    }
                }



    fun sessao(nome: String, senha: String) {
        Service.retrofit.sessao(
            sessao = SessaoRequest(
                nome_sala = nome,
                senha_sala = senha
            )
        ).enqueue(object : Callback<SessaoResponse>{
            override fun onFailure(call: Call<SessaoResponse>, t: Throwable) {
                Log.d("Deu ruim", t.toString())
            }
            override fun onResponse(call: Call<SessaoResponse>, response: Response<SessaoResponse>) {
                Log.d("Nice", response.toString())

                val sessao = response.body()
                val parametro = Bundle()
                parametro.putInt("id", sessao!!.id_sessao)
                navController!!.navigate(R.id.action_roomAcessFragment_to_roomAcessNameFragment, parametro)
            }
        })
    }
}
