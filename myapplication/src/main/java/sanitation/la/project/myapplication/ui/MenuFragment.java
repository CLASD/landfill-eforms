package sanitation.la.project.myapplication.ui;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import sanitation.la.project.myapplication.adapters.GridViewAdapter;
import sanitation.la.project.myapplication.R;

/**
 * Created by saul on 10/3/15.
 */
public class MenuFragment extends Fragment implements OnItemClickListener {

    private static final int INITIAL_DELAY_MILLIS = 300;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_layout, container, false);


        GridView gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setNumColumns(3);
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new GridViewAdapter(getActivity().getApplicationContext()));
        swingBottomInAnimationAdapter.setAbsListView(gridView);

        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        gridView.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
        // if(v.getId()==0)
//
//        Fragment myFrag = new MainActivityFragment();
//
//        getFragmentManager().beginTransaction()
//                .replace(R.id.mainFrame, myFrag).commit();

            FormListFragment fragment = new FormListFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, fragment).addToBackStack("List")
                    .commit();


    }

}
