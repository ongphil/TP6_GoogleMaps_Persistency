package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SharedCoordsFragment extends Fragment {
    TextView currentLatText;
    TextView currentLngText;
    TextView markerLatText;
    TextView markerLngText;

    private OnFragmentInteractionListener mListener;

    public SharedCoordsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shared_coords, container, false);
        currentLatText = (TextView) view.findViewById(R.id.currentSharedLatText);
        currentLngText = (TextView) view.findViewById(R.id.currentSharedLngText);
        markerLatText = (TextView) view.findViewById(R.id.markerSharedLatText);
        markerLngText = (TextView) view.findViewById(R.id.markerSharedLngText);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
        currentLatText.setText("Lat : " + String.valueOf(lat));
        currentLngText.setText("Lng : " + String.valueOf(lng));
    }

    public void setMarkerPosition(int lat, int lng){
        markerLatText.setText("Lat : " + String.valueOf(lat));
        markerLngText.setText("Lng : " + String.valueOf(lng));
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
