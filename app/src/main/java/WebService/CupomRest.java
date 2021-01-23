package WebService;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by diego on 01/12/15.
 */
public class CupomRest extends AsyncTask<Void, Void, ArrayList<Cupom>> {

    private static final String URL_WS = "http://191.252.56.222:8080/Consultor/consultor/";


    public ArrayList<Cupom> getCupom() throws Exception {

        String[] resposta = new WebServiceClient().getWS(URL_WS + "buscarCupom");
//       String[] resposta = new WebServiceCliente().get(URL_WS + "buscarTodos");

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Cupom> listaCupom = new ArrayList<Cupom>();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                listaCupom.add(gson.fromJson(array.get(i), Cupom.class));
                Log.i("rest GSON", array.get(i).toString());
            }
            return listaCupom;
        } else {
            throw new Exception(resposta[1]);
        }
    }


    @Override
    protected ArrayList<Cupom> doInBackground(Void... params) {
        ArrayList<Cupom> lista = new ArrayList<Cupom>();
        try {

            lista = getCupom();

        } catch (Exception e) {
            Log.e("Erro no AsyncTask", e.getMessage());
        }

        return lista;
    }
}
