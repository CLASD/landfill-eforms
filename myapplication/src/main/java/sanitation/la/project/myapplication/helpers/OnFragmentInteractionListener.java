package sanitation.la.project.myapplication.helpers;

import android.net.Uri;

import java.util.ArrayList;

import sanitation.la.project.myapplication.data.EntryData;

/**
 * Created by saul on 1/14/16.
 */
/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
    void onNewFormEntry(String arg);
    void onAddEntryClicked(int data);
    void onFragmentItemClicked(int i);
    void onNewEntrySubmited(EntryData e);
    ArrayList<EntryData> getData();
    String exportClicked();
    void onFormPicked(int pos);
}