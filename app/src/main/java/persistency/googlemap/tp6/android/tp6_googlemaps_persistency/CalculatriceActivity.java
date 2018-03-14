package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalculatriceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DisplayFragment.OnFragmentDisplayListener, KeyboardFragment.OnFragmentInteractionListener {


    private String str_operation = ""; // String contenant l'opération
    private String str_resultat = ""; // String contenant le résultat
    private EditText adresseip_serveur; // permet d'entrer l'adresse IP du PC pour ne pas avoir besoin de changer l'adresse dans le code à chaque fois
    private boolean operator_used = false; // permet de savoir si un opérateur a été utilisé, et ainsi ne pas pouvoir en utiliser un 2ème
    private boolean is_op1 = true; // permet de savoir si un opérateur a été utilisé, et ainsi traiter le 2ème nombre
    private String op1 = ""; // le string qui va se former avec de la concaténation pour obtenir le nombre final op1
    private String op2 = ""; // le string qui va se former avec de la concaténation pour obtenir le nombre final op2
    private char op = ' '; // le char qui va contenir l'opérateur

    private boolean is_new_operation = true; // permet de savoir s'il s'agit d'une nouvelle opération

    private String last_op = ""; // La dernière opération
    private String last_result = ""; // Le dernier résultat

    private Socket s;

    private DisplayFragment drf; // Fragment possédant les TextView opération et résultat
    private KeyboardFragment kf; // Fragment possédant les boutons de la calculatrice
    private boolean order_place = true; // Booléan pour savoir quel fragment est dans quel FrameLayout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        adresseip_serveur = (EditText)findViewById(R.id.iptext);

        if (savedInstanceState != null) { // Permet de récupérer les données lorsqu'on passe en mode paysage/portrait
            str_operation = savedInstanceState.getString("Str_operation");
            str_resultat = savedInstanceState.getString("Str_resultat");
            operator_used = savedInstanceState.getBoolean("Operator_used");
            is_op1 = savedInstanceState.getBoolean("Is_op1");
            op1 = savedInstanceState.getString("Op1");
            op2 = savedInstanceState.getString("Op2");
            op = savedInstanceState.getChar("Op");
            is_new_operation = savedInstanceState.getBoolean("Is_new_operation");
            last_op = savedInstanceState.getString("Last_op");
            last_result = savedInstanceState.getString("Last_result");
            adresseip_serveur.setText(savedInstanceState.getString("Adresseip_serveur"));
            //order_place = savedInstanceState.getBoolean("Order_place");
        }

        drf = DisplayFragment.newInstance(str_operation, str_resultat); // A la création du fragment, on affiche l'opération en cours (si elle existe)
        kf = KeyboardFragment.newInstance();


        getSupportFragmentManager().beginTransaction().add(R.id.display, drf).add(R.id.keyboard, kf).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculatrice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_calculatrice) {
            Intent intent = new Intent(this, CalculatriceActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_editText){
            Intent intent = new Intent(this, EditTextActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_Coordonnees){
            Intent intent = new Intent(this, CoordonneesActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
       Méthode appelé par le fragment des boutons grâce à l'interface
       Concatène les caractères de l'opération et envoies ce qu'il faut afficher au fragment des TextViews
   */
    @Override
    public void addNumber(String str_button) {

        switch (str_button) {
            case "0":
                begin_new_operation();
                add_number("0");
                break;
            case "1":
                begin_new_operation();
                add_number("1");
                break;
            case "2":
                begin_new_operation();
                add_number("2");
                break;
            case "3":
                begin_new_operation();
                add_number("3");
                break;
            case "4":
                begin_new_operation();
                add_number("4");
                break;
            case "5":
                begin_new_operation();
                add_number("5");
                break;
            case "6":
                begin_new_operation();
                add_number("6");
                break;
            case "7":
                begin_new_operation();
                add_number("7");
                break;
            case "8":
                begin_new_operation();
                add_number("8");
                break;
            case "9":
                begin_new_operation();
                add_number("9");
                break;
            case "+":
                add_operator('+');
                break;
            case "-":
                add_operator('-');
                break;
            case "*":
                add_operator('*');
                break;
            case "/":
                add_operator('/');
                break;
            case "C":
                reset_all_variables();
                break;
            case "=":
                launch_operation(); // On se connecte au serveur pour récupérer le résultat et on l'affiche


                if(order_place) // Détermine la place des fragments dans les framelayouts
                {
                    getSupportFragmentManager().beginTransaction().remove(drf).commitNow();
                    getSupportFragmentManager().beginTransaction().remove(kf).commitNow();
                    getSupportFragmentManager().beginTransaction().replace(R.id.keyboard, drf).commitNow();
                    getSupportFragmentManager().beginTransaction().replace(R.id.display, kf).commitNow();

                    drf.setOperationText(str_operation);
                    drf.setResultText(str_resultat);

                    order_place = false; // on met à jour l'ordre des fragments*/
                }
                else
                {
                    getSupportFragmentManager().beginTransaction().remove(drf).commitNow();
                    getSupportFragmentManager().beginTransaction().remove(kf).commitNow();
                    getSupportFragmentManager().beginTransaction().replace(R.id.display, drf).commitNow();
                    getSupportFragmentManager().beginTransaction().replace(R.id.keyboard, kf).commitNow();

                    drf.setOperationText(str_operation);
                    drf.setResultText(str_resultat);

                    order_place = true;
                }

                break;
        }
    }

    /*
        Permet de concaténer le chiffre associé au bouton, au string op1 ou op2 et ainsi former les nombres qui seront envoyés au serveur
    */
    public void add_number(String n)
    {
        if(is_op1) // s'il s'agit de op1
        {
            op1 = op1 + n;
            str_operation = str_operation + n;
        }
        else // sinon, cela signifie qu'on souhaite former op2
        {
            op2 = op2 + n;
            str_operation = str_operation + n;
        }

        drf.setOperationText(str_operation); // on met à jour le texte de l'opération
    }

    /*
        Permet d'ajouter un opérateur à l'opération et de signaler qu'un opérateur a déjà été utilisé pour ne pas pouvoir en utiliser un autre
    */
    public void add_operator(char o)
    {
        if(op1!= "")
        {
            if(!operator_used)
            {
                str_operation = str_operation + String.valueOf(o);
                drf.setOperationText(str_operation);
                operator_used = true;
                op =o;
                is_op1 = false;
            }
        }
    }

    /*
        Permet de restaurer toutes les variables (sera utilisé pour le bouton C)
    */
    public void reset_all_variables()
    {
        drf.setOperationText("");
        drf.setResultText("");
        operator_used = false;
        is_op1 = true;
        op1 = "";
        op2 = "";
        op = ' ';
        str_operation = "";
        str_resultat = "";
    }

    /*
        Permet de réinitialiser toutes les variables et d'indiquer qu'on commence une nouvelle opération
        Permet notamment de gérer le fait que toutes les variables seront rénitialisés si on appuie sur égal et qu'on appuie sur un nouveau bouton
    */
    public void begin_new_operation()
    {
        if(is_new_operation)
        {
            reset_all_variables();
            is_new_operation = false;
        }
    }

    /*
        Permet de se connecter au serveur pour récupérer le résultat
    */
    public void launch_operation()
    {
        if (op2.length() >= 1 && !is_new_operation) //  && adresseip_serveur.getText().toString() != "" Si op2 n'est pas vide, cela signifie qu'une opération est possible
        {
            is_new_operation = true; // indique que la prochaine fois qu'on appuie sur un bouton, les variables seront réinitialisées
            openSocketTask opensocketTask = new openSocketTask(); // On crée une tâche asynchrone pour se connecter au serveur
            opensocketTask.execute(); // On lance la tâche
        }
    }

    /*
        Tâche asynchrone pour se connecter au serveur, permet de ne pas ralentir l'interface
    */
    private class openSocketTask extends AsyncTask<Void, Void, Double> {

        @Override
        protected Double doInBackground(Void... voids) {
            Double resultat = 0.0;
            try
            {
                s = new Socket(adresseip_serveur.getText().toString(), 9876); // On crée une Socket client qui se connecte à CE PC et sur le même port que le Socket server
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); // Permet d'écrire des données
                dos.writeDouble(Double.parseDouble(op1)); // On écrit un premier Double dans le buffer OutputStream
                dos.writeChar(op); // On écrit l'opérateur sous forme de Char dans le buffer OutputStream
                dos.writeDouble(Double.parseDouble(op2)); // On écrit le deuxième Double dans le buffer OutputStream
                dos.flush(); // On vide le buffer

                DataInputStream dis = new DataInputStream(s.getInputStream()); // Permet de lire les données reçues du serveur
                resultat = dis.readDouble(); // Lis la prochaine donnée de type Double (ici le résultat retourné)

                dis.close(); // Ferme l'inputStream
                dos.close(); // Ferme l'outputStream

                s.close(); // Ferme la socket

                str_resultat = resultat.toString();

                last_op = str_operation;
                last_result = str_resultat;


            }catch(IOException e)
            {
                e.printStackTrace();
            }

            return resultat;

        }

        @Override
        protected void onPostExecute(Double resultat) {
            drf.setResultText(last_result); // Dis à l'interface de modifier le texte du résultat
        }
    }

    /*
        Permet de sauvegarder toutes les données dans le savedInstanceState pour pouvoir les récupérer ensuite dans le cas où on tourne l'écran et que l'activité est détruite
    */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("Str_operation", str_operation);
        savedInstanceState.putString("Str_resultat", str_resultat);
        savedInstanceState.putBoolean("Operator_used", operator_used);
        savedInstanceState.putBoolean("Is_op1", is_op1);
        savedInstanceState.putString("Op1", op1);
        savedInstanceState.putString("Op2", op2);
        savedInstanceState.putChar("Op", op);
        savedInstanceState.putBoolean("Is_new_operation", is_new_operation);
        savedInstanceState.putString("Last_op", last_op);
        savedInstanceState.putString("Last_result", last_result);
        savedInstanceState.putString("Adresseip_serveur", adresseip_serveur.getText().toString());
        savedInstanceState.putBoolean("Order_place", order_place);
    }
}
