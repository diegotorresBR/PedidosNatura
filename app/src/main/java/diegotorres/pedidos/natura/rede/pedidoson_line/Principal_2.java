package diegotorres.pedidos.natura.rede.pedidoson_line;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import WebService.Consultor;
import WebService.ConsultorREST;
import WebService.Cupom;
import WebService.CupomRest;

/**
 * Created by diego on 09/10/15.
 */
public class Principal_2 extends AppCompatActivity{

    private WebView web;
    private View progresso, relative;
    private Toast toast;
    private long lastBackPressTime = 0;
    public String espaco_padrao = "https://m.rede.natura.net/espaco/diegotorres";
    public String espaco = "https://m.rede.natura.net/espaco/";
    public String consultor;
    private String cupom;
    public static Toolbar toolbar;
    private AlertDialog alerta;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relative = (RelativeLayout) findViewById(R.id.progresso);
        progresso = findViewById(R.id.avloadingIndicatorView);
        obter_consultor();
        getCupom();
        web = (WebView) findViewById(R.id.webView);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new ClienteWeb());
        ChecarCompatibilidade();
        web.loadUrl(espaco_padrao);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer id = item.getItemId();

        Log.i("menu", id.toString());
        Integer id2 = R.id.action_settings;
        Log.i("menu", id2.toString());

        if(id == R.id.action_settings){

            final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
            builder.setTitle("Meus Pedidos da Rede Natura");
            builder.setMessage(cupom);
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alerta = builder.create();
            alerta.show();

        }

        return super.onOptionsItemSelected(item);

    }


    private class ClienteWeb extends WebViewClient {

        /*@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }*/

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            web.setVisibility(View.GONE);
            relative.setVisibility(View.VISIBLE);
            progresso.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progresso.setVisibility(View.GONE);
            web.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            //Principal_2.toolbar.setTitle(view.getTitle());
            super.onPageFinished(view, url);
        }

    }

    private void ChecarCompatibilidade(){
        int versao = Build.VERSION.SDK_INT;

        if (versao >= 21){
            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack()){
            web.goBack();

        }else {

            if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
                toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_LONG);
                toast.show();
                this.lastBackPressTime = System.currentTimeMillis();
            } else {
                if (toast != null) {
                    toast.cancel();
                }
                super.onBackPressed();
            }
        }
    }

    public void obter_consultor(){


        try {
            ConsultorREST consultorREST = new ConsultorREST();
            ArrayList<Consultor> c = consultorREST.execute().get();

            this.consultor = c.get(0).getEspaco();
            this.espaco_padrao = espaco + this.consultor;
            Log.i("consultor", this.consultor);

        } catch (Exception e) {
            Log.e("erro", e.getMessage());
        }

    }

    public void getCupom() {

        try {
            CupomRest cupom = new CupomRest();
            ArrayList<Cupom> c = cupom.execute().get();

            this.cupom = c.get(0).getDescricao();
            Log.i("cupom", this.cupom);

        } catch (Exception e) {
            Log.e("erro", e.getMessage());
        }
    }
}
