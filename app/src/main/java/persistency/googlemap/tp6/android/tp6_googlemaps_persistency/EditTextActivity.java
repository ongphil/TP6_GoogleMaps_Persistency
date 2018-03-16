package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
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

public class EditTextActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, EditTextFragment.OnFragmentInteractionListener {

    EditTextFragment etf;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        edittext = (EditText) findViewById(R.id.editText);

        etf = EditTextFragment.newInstance(); // on crée un nouveau fragment contenant le EditText
        getSupportFragmentManager().beginTransaction().add(R.id.edittext_fragment, etf).commit(); // On l'ajoute dans le FrameLayout


        edittext.addTextChangedListener(new TextWatcher() { // Listener détectant si on effectue une action dans le EditText (pas celui du fragment)
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /// Si on écrit dans le EditText (pas celui du fragment)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /// Si le EditText (pas celui du fragment) a le focus dans l'activité
                /// Déterminer le focus permet d'éviter qu'on modifie indéfiniment les EditText puisque ça fait une boucle sans fin (L'un modifie l'autre qui modifie l'autre qui modifie l'autre ....)
                if(edittext.hasFocus())
                {
                    etf.setTextEditTextFragment(charSequence); // On modifie également le EditText du fragment
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        getMenuInflater().inflate(R.menu.edit_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calc) {
            Intent intent = new Intent(this, CalculatriceActivity.class);
            startActivity(intent);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    Méthode appelé par le fragment contenant l'EditText
    Permet de modifier l'EditText de l'activité
*/
    @Override
    public void setEditTextActivity(CharSequence s) {
        edittext.setText(s);
    }

}
