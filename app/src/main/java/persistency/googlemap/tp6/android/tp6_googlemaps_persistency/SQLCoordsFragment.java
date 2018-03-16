package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SQLCoordsFragment extends Fragment {
    TextView currentLatText;
    TextView currentLngText;
    TextView markerLatText;
    TextView markerLngText;

    private OnFragmentInteractionListener mListener;

    public SQLCoordsFragment() {
        // Required empty public constructor
    }

    public static SQLCoordsFragment newInstance() {
        SQLCoordsFragment fragment = new SQLCoordsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlcoords, container, false);
        currentLatText = (TextView) view.findViewById(R.id.currentSQLLatText);
        currentLngText = (TextView) view.findViewById(R.id.currentSQLLngText);
        markerLatText = (TextView) view.findViewById(R.id.markerSQLLatText);
        markerLngText = (TextView) view.findViewById(R.id.markerSQLLngText);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SharedCoordsFragment.OnFragmentInteractionListener) {
            mListener = (SQLCoordsFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setCurrentPosition(int lat, int lng){
        currentLatText.setText("lat : " /*+ String.valueOf(lat)*/);
        currentLngText.setText("lng : " + String.valueOf(lng));
    }

    public void setMarkerPosition(int lat, int lng){
        markerLatText.setText("lat : " + String.valueOf(lat));
        markerLngText.setText("lng : " + String.valueOf(lng));
    }
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
    }
}
