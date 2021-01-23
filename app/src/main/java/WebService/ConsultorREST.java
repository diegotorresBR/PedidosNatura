package WebService;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by diego on 06/11/15.
 */
public class ConsultorREST extends AsyncTask<Void, Void, ArrayList<Consultor>>{

    private static final String URL_WS = "http://191.252.56.222:8080/Consultor/consultor/";


    public ArrayList<Consultor> getListaConsultor() throws Exception {

        String[] resposta = new WebServiceClient().getWS(URL_WS + "buscarConsultorAtivo");
//       String[] resposta = new WebServiceCliente().get(URL_WS + "buscarTodos");

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Consultor> listaConsultor = new ArrayList<Consultor>();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                listaConsultor.add(gson.fromJson(array.get(i), Consultor.class));
                Log.i("rest GSON", array.get(i).toString());
            }
            return listaConsultor;
        } else {
            throw new Exception(resposta[1]);
        }
    }


    @Override
    protected ArrayList<Consultor> doInBackground(Void... params) {
        ArrayList<Consultor> lista = new ArrayList<Consultor>();
        try {

            lista = getListaConsultor();

        } catch (Exception e) {
            Log.e("Erro no AsyncTask", e.getMessage());
        }

        return lista;
    }
}
