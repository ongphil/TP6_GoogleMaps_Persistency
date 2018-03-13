package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KeyboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KeyboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeyboardFragment extends Fragment {
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, badd, bsous, bmult, bdiv, bC, begal;

    private OnFragmentInteractionListener mListener;


    public KeyboardFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static KeyboardFragment newInstance() {
        KeyboardFragment fragment = new KeyboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_keyboard, container, false); // Assimile le xml à ce fragment

        /// LISTENERS DES BOUTONS DE LA VUE
        // TODO : Vérifier l'utilité du cas Button car d'apres AS c'est rendondant
        b1 = (Button)view.findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b1.getText().toString()); // On appelle la méthode addNumber de l'activité grâce à l'interface
                }
            }
        });

        b2 = (Button)view.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b2.getText().toString());
                }
            }
        });

        b3 = (Button)view.findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b3.getText().toString());
                }
            }
        });

        b4 = (Button)view.findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b4.getText().toString());
                }
            }
        });

        b5 = (Button)view.findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b5.getText().toString());
                }
            }
        });

        b6 = (Button)view.findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b6.getText().toString());
                }
            }
        });

        b7 = (Button)view.findViewById(R.id.button7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b7.getText().toString());
                }
            }
        });

        b8 = (Button)view.findViewById(R.id.button8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b8.getText().toString());
                }
            }
        });

        b9 = (Button)view.findViewById(R.id.button9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(b9.getText().toString());
                }
            }
        });

        badd = (Button)view.findViewById(R.id.buttonadditionner);
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(badd.getText().toString());
                }
            }
        });

        bsous = (Button)view.findViewById(R.id.buttonsoustraire);
        bsous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(bsous.getText().toString());
                }
            }
        });

        bmult = (Button)view.findViewById(R.id.buttonmultiplier);
        bmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(bmult.getText().toString());
                }
            }
        });

        bdiv = (Button)view.findViewById(R.id.buttondiviser);
        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(bdiv.getText().toString());
                }
            }
        });

        bC = (Button)view.findViewById(R.id.buttonC);
        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(bC.getText().toString());
                }
            }
        });

        begal = (Button)view.findViewById(R.id.buttonegal);
        begal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.addNumber(begal.getText().toString());
                }
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context; // Permet la communication entre ce fragment et l'activité grâce à une interface
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
        // TODO: Update argument type and name
        void addNumber(String string);
    }


}
