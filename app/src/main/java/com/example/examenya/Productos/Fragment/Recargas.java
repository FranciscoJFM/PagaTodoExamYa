package com.example.examenya.Productos.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.examenya.Model.Item;
import com.example.examenya.Productos.Adapter.CustomGridViewAdapter;
import com.example.examenya.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Recargas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Recargas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recargas extends Fragment {

    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    Map<String, String> sectionHeaderTitles = new HashMap<String, String>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Recargas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Recargas.
     */
    // TODO: Rename and change types and number of parameters
    public static Recargas newInstance(String param1, String param2) {
        Recargas fragment = new Recargas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  rootView = inflater.inflate(R.layout.fragment_recargas, container, false);


        Bitmap claroIcon = BitmapFactory.decodeResource( this.getResources(), R.drawable.ic_claro);
        Bitmap tuentiIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_tuenti);
        Bitmap entelIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_entel);

        gridArray.add(new Item(claroIcon,"Tiempo aire"));
        gridArray.add(new Item(claroIcon,"Megas"));
        gridArray.add(new Item(claroIcon,"Megas"));
        gridArray.add(new Item(tuentiIcon,"Megas"));
        gridArray.add(new Item(tuentiIcon,"Megas"));
        gridArray.add(new Item(tuentiIcon,"Megas"));
        gridArray.add(new Item(entelIcon,"Megas"));
        gridArray.add(new Item(entelIcon,"Megas"));
        gridArray.add(new Item(entelIcon,"Megas"));


        gridView = (GridView) rootView.findViewById(R.id.gridviewdos);
        customGridAdapter = new CustomGridViewAdapter(getActivity(), R.layout.activity_custom_grid_view_adapter, gridArray);

        gridView.setAdapter(customGridAdapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }
}
