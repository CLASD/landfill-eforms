package sanitation.la.project.myapplication.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;


import sanitation.la.project.myapplication.DrawerMain;
import sanitation.la.project.myapplication.R;
import sanitation.la.project.myapplication.data.EntryData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public  class MessageDialogFragment extends DialogFragment {

    public static MessageDialogFragment newInstance(EntryData e) {
        MessageDialogFragment frag = new MessageDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Hot spot Detected! Grid ID: " + e.getGrid());
        args.putInt("GridID", e.getId());
        for(int i=0; i<e.getDataSize(); i++) {
            String t = e.getData().get(i).getLabel();
            double v = e.getData().get(i).getData();
            args.putDouble(t, v);
        }
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");

        return new AlertDialog.Builder(getActivity(), R.style.AnimationTheme )
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage("Create a linked Hotspot entry?")
                .setPositiveButton("Open now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Log.d("MEssage Dialoag", "Positive button clicked!");
                                ( (DrawerMain)getActivity()).openHotspotEntry();
                                // ((ItemListActivity)getActivity()).doPositiveClick();
                            }
                        }
                ).setNegativeButton("Later",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //  ((ItemListActivity) getActivity()).doNegativeClick();
                            }
                        }
                )
                .create();
    }


}




