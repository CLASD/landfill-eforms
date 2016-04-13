package sanitation.la.project.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sanitation.la.project.myapplication.data.EntryData;
import sanitation.la.project.myapplication.dummy.DummyContent;
import sanitation.la.project.myapplication.formClass.Instantaneous;
//import sanitation.la.project.myapplication.helpers.DatabaseHandler;
import sanitation.la.project.myapplication.helpers.DatabaseHandler;
import sanitation.la.project.myapplication.helpers.DbHelper;
import sanitation.la.project.myapplication.helpers.OnFragmentInteractionListener;
import sanitation.la.project.myapplication.helpers.lacDbEntry;
import sanitation.la.project.myapplication.ui.ExportFragment;
import sanitation.la.project.myapplication.ui.FormDetailFragment;
import sanitation.la.project.myapplication.ui.FormEntryFragment;
import sanitation.la.project.myapplication.ui.FormListFragment;
import sanitation.la.project.myapplication.ui.FormPickerFragment;
import sanitation.la.project.myapplication.ui.ItemFragment;
import sanitation.la.project.myapplication.ui.LocationTestFragment;
import sanitation.la.project.myapplication.ui.MenuFragment;
import sanitation.la.project.myapplication.ui.MessageDialogFragment;
import sanitation.la.project.myapplication.ui.Uidemofragment;

public class DrawerMain extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback , OnFragmentInteractionListener
{
    private boolean mTwoPane;
    private final String TAG = getClass().getSimpleName();

    private GoogleMap mMap;
    private FormEntryFragment entryFrag;
    private DbHelper mDbHelper;
    private DatabaseHandler db;

    private enum FORM_TYPE {INSTANTANEOUS, INTEGRATED, HOTSPOT}

    private FORM_TYPE formID = FORM_TYPE.INSTANTANEOUS;


    private int formType = 0;

    //very temp
    private ArrayList<EntryData> tempData;
    /*
            onCreate is called when the activity is started,
            initialize the view and other things our app will need
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_main);      //set the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);     //find a reference to the view created in xml
        setSupportActionBar(toolbar);
     //   mDbHelper = new DbHelper(getApplicationContext());
        db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting to SQLite...");
        // test input - can't access addInstantaneous from DatabaseHandler
      //  db.addInstantaneous( new Instantaneous(2,2,2,2, 3, 3, 2330., 2,  new Date() ) );
//        db.addInstantaneous(new Instantaneous("1", "1", "1", "18:46", "18:56", "2", "102.0",
// "35"));

        List<Instantaneous> instantaneous = db.getAllInstantaneous();

        Log.d(TAG, "Loaded " + instantaneous.size() + " entries from local db");

        for(Instantaneous i: instantaneous)
                Log.d(TAG, "Instantaneous data: " + i.toString());
        // printing
//        for(Instantaneous ins : instantaneous) {
//            String log = "ID: " + ins.getInstantaneousDataPK() + ", SitePK: " + ins.getSitePK() +
//                    ", EmployeePK: " + ins.getEmployeePK() + ", StartTime: " + ins.getStartTime()
//                    + ", FinishTime: " + ins.getFinishTime() + ", InstrumentPK: " + ins
//                    .getInstrumentPK() + ", MaxCH: " + ins.getMaxCH() + ", SiteSamplingPoint: " +
//                    ins.getSiteSamplingPointPK();
//            Log.d("Instantaneous: ", log);
//        }

        //saving this for later if we want to use it.
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //set up the side drawer menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ind the spot for our fragments, and place the first one in to be displayed
        if (findViewById(R.id.list_container) != null) {
           // FormListFragment fragment = new FormListFragment();
            entryFrag = new FormEntryFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, entryFrag)
                    .commit();
        }

    }

    /****************************************************************************************
     * ************************** Fragment listener methods *********************************************
     * ****************************************************************************************
     */

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onNewFormEntry(String arg) {
        Uidemofragment fragment = new Uidemofragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        Bundle b = new Bundle();
        b.putString("formArg", arg);
        b.putInt("FORMID", formType);
        fragment.setArguments(b);
        transaction.replace(R.id.list_container, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        Log.d(TAG, "Adding New form fragment");
    }

    private void addEntryToDb(EntryData e){
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

         // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(lacDbEntry.mEntry.COLUMN_NAME_ENTRY_ID, e.getId());
        values.put(lacDbEntry.mEntry.COLUMN_NAME_TITLE, e.getName());
        values.put(lacDbEntry.mEntry.COLUMN_NAME_CONTENT, e.getData().get(0).getData());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                lacDbEntry.mEntry.TABLE_NAME,
                lacDbEntry.mEntry.COLUMN_NAME_NULLABLE,
                values);

        Log.d(TAG, "New entry added.");
    }
    @Override
    public void onNewEntrySubmited(EntryData e){
        Log.d(TAG, "New entry submited: " + e.getName() + " " + e.getData().get(0));
         //   addEntryToDb(e);
//        if(tempData == null)
//            tempData = new ArrayList<EntryData>();
        if(db != null)
        db.addInstantaneous( new Instantaneous(db.getInstantaneousCount()+2,2,2,2, 3, 3, e.getData().get(0).getData(), e.getGrid(),  new Date() ) );

        //tempData.add(e);

        //it is instantaneous entry, valid and over 500 ppm
        if(formType == 0 && e.getDataSize() >=  1 && e.getData().get(0).getData() >= 500.){
            Log.d(TAG, "Hot spot detected");


            DialogFragment newFragment = MessageDialogFragment.newInstance(e);
            newFragment.show(getSupportFragmentManager(), "dialog");
        }
      //  tempData.addAll(e.getData());

//        if(entryFrag != null)
//        {
//           // entryFrag.addItem(e);
//
//            Log.d(TAG, "New entry added.");
//
//        }
//        else
        if(entryFrag == null)  entryFrag = new FormEntryFragment();

        //FormEntryFragment fragment = new FormEntryFragment();
//        Bundle b = new Bundle();
//        b.putDoubleArray("DATA", toDArray(tempData));
//        b.putInt("FORMID", 1);
//        entryFrag.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_container, entryFrag)
                .commit();
        Log.d(TAG, "Adding Form Entry fragment");
    }

    @Override
    public ArrayList<EntryData> getData(){
        if(tempData == null)
            return new ArrayList<EntryData>();
        return tempData;
    }
    private double[] toDArray(ArrayList<Double> in) {
        double[] o = new double[in.size()];
        for(int i=0; i<in.size(); i++)
            o[i] = in.get(i);

        return o;

    }

    @Override
    public void onAddEntryClicked(int id){
            Log.d(TAG, "Add new entry triggered. Data: " + id);
            tempData = entryFrag.getData();
            showNewEntryFrag(id);

    }

    @Override
    public void onFragmentItemClicked(int i){
        Log.d(TAG, "Item Clicked: " + i);

    }

    @Override
    public String exportClicked(){
        Log.d(TAG, "Export Clicked: ");
        Date d = new Date();
        String name = "clasandata " + d.toString() + ".json";
        String path = "LandfillData";
        try {
            Gson gson = new Gson();
            String gstr = gson.toJson(tempData); //format collected data to Json

            File myFile = new File(Environment.getExternalStorageDirectory()+File.separator+path);
            myFile.mkdirs();
            myFile = new File(Environment.getExternalStorageDirectory()+File.separator+path+File.separator+name);

//            File myFile = new File("Removable"+File.separator+path + "USBdisk1" +File.separator+path);
//            myFile.mkdirs();
//            myFile = new File("Removable"+File.separator+path + "USBdisk1" +File.separator+path+File.separator+name);

           // Log.d(TAG, gstr);

            //write json data to a file at path/name (date)
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append(gstr);
            myOutWriter.close();
            fOut.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Log.d(TAG, "Json File Exported");
        return name;
    }

    @Override
    public void onFormPicked(int pos){
        if(pos == 0){
            //instananeous
            Log.d(TAG, "Instantaneous Form Picked");
            formID = FORM_TYPE.INSTANTANEOUS;


        } else if(pos == 1){
            // hotspot
            Log.d(TAG, "hotspot Form Picked");
            formID = FORM_TYPE.HOTSPOT;
        }
        else if(pos == 2){
            //integrated
            Log.d(TAG, "integrated Form Picked");
            formID = FORM_TYPE.INTEGRATED;
        }

        formType = pos;
        //open the FormEntryFragment (list of entries for a form type)
        showFromEntryFrag(pos);

    }


    public void openHotspotEntry(){
        Log.d(TAG, "Creating new hot spot entry");
        formID = FORM_TYPE.HOTSPOT;
        formType = 1;
        showNewHotspot();

    }

    /****************************************************************************************
     * ****************************************************************************************
     * ****************************************************************************************
     */
    
    
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
        getMenuInflater().inflate(R.menu.drawer_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, DrawerMain.class));
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //@// TODO: 1/21/16 needs to be cleaned up and generalized for adding the specified fragment 
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            showNewEntryFrag(0);

        } else if (id == R.id.nav_forms) {
            FormPickerFragment fragment = new FormPickerFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, fragment)
                    .commit();
            Log.d(TAG, "Adding Grid fragment");

        } else if (id == R.id.nav_new) {
            Uidemofragment fragment = new Uidemofragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, fragment)
                    .commit();
            Log.d(TAG, "Adding Grid fragment");
        } else if (id == R.id.nav_open) {
                showFromEntryFrag(0);

        } else if (id == R.id.nav_previous) {

            if (findViewById(R.id.list_container) != null) {
                FormListFragment fragment = new FormListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_container, fragment)
                        .commit();
            }

        } else if (id == R.id.nav_share) {
            Log.d(TAG, "Share/Update.");
//            if (findViewById(R.id.list_container) != null) {
//                MenuFragment mf = new MenuFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.list_container, mf)
//                        .commit();
//                Log.d(TAG, "Grid placed");
//            }

        } else if (id == R.id.nav_send) {
            Log.d(TAG, "Send/Export.");
            if (findViewById(R.id.list_container) != null) {
                ExportFragment f = new ExportFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_container, f)
                        .commit();
                Log.d(TAG, "Export placed");
            }

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_map) {
            Log.d(TAG, "Show map clicked.");
            if (findViewById(R.id.list_container) != null) {
                //SupportMapFragment mapFragment = new SupportMapFragment();
                //mapFragment.getMapAsync(this);

                LocationTestFragment mapFragment = new LocationTestFragment();

                //MapsFragment fragment = new MapsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_container, mapFragment)
                        .commit();
                Log.d(TAG, "Adding map");
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFromEntryFrag(int id){
        FormEntryFragment fragment = new FormEntryFragment();
        Bundle b = new Bundle();
       // b.putDoubleArray();
        b.putInt("FORMID", id);
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_container, fragment)
                .commit();
        Log.d(TAG, "Adding Form Entry fragment");

    }
    private void showNewEntryFrag(int id){
        Uidemofragment fragment = new Uidemofragment();
        Bundle b = new Bundle();
        b.putInt("FORMID", id);
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_container, fragment)
                .commit();
        Log.d(TAG, "Adding new Entry fragment");
    }

    private void showNewHotspot(){
        Uidemofragment fragment = new Uidemofragment();
        Bundle b = new Bundle();
        b.putInt("FORMID", 1);   //hot spot! always
        fragment.setArguments(b);
        if(tempData.size() > 0 ) {
            //pre populate
            EntryData e = tempData.get(tempData.size() - 1);
            b.putInt("GridID", e.getGrid());
            b.putDouble("ch4ppm", e.getData().get(0).getData());
            b.putString("IME", "temp123");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_container, fragment)
                .commit();
        Log.d(TAG, "Adding new Entry fragment");
    }

    // map stuff will be moved to its own fragment if/when we use it
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lopezCanyon = new LatLng(34.286911, -118.400914);
        LatLng schollCanyon = new LatLng(34.152666, -118.194597);
        LatLng bishopsCanyon = new LatLng(34.286905, -118.400631);
        LatLng sheldonArleta = new LatLng(34.227111, -118.406669);
        LatLng toyonCanyon = new LatLng(34.146090, -118.304545);
        LatLng gaffeyStreet = new LatLng(33.759605, -118.291736);

        ArrayList<LatLng> locations = new ArrayList<LatLng>();
        locations.add(lopezCanyon);
        locations.add(schollCanyon);
        locations.add(bishopsCanyon);
        locations.add(sheldonArleta);
        locations.add(toyonCanyon);
        locations.add(gaffeyStreet);


//        Bishops Canyon Landfill - land restoration complete
//        Gaffey Street Landfill - land restoration complete
//        Lopez Canyon Landfill - closure work complete
//        Sheldon-Arleta Landfill - closure work complete and land redevelopment in progress
//        Toyon Canyon Landfill -
        int i = 1;
        for (LatLng l : locations)
            mMap.addMarker(new MarkerOptions().position(l).title("Landfill " + i++));

        //    mMap.addMarker(new MarkerOptions().position(lopezCanyon).title("Lopez Canyon Landfill"));

        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(lopezCanyon));

        float zdiff = 4.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lopezCanyon, mMap.getMaxZoomLevel() - zdiff));
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }



    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(FormDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        FormDetailFragment fragment = new FormDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.form_detail_container, fragment)
                                .commit();
                    } else {

//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, FormDetailActivity.class);
//                        intent.putExtra(FormDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//
//                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
