package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditTextFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTextFragment extends Fragment {

    EditText et_fragment;

    private OnFragmentInteractionListener mListener;

    public EditTextFragment() {
        // Required empty public constructor
    }

    public static EditTextFragment newInstance() {
        EditTextFragment fragment = new EditTextFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);
        et_fragment = (EditText) view.findViewById(R.id.text_fragment);


        et_fragment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_fragment.hasFocus())
                {
                    mListener.setEditTextActivity(charSequence); // Appelle la méthode de l'activité pour modifier le text de son EditText
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
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
        void setEditTextActivity(CharSequence sequence);
    }

    public void setTextEditTextFragment(CharSequence s)
    {
        et_fragment.setText(s);
    }
}
