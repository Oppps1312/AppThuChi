package com.example.sothuchiv4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sothuchiv4.databinding.FragmentThuChiBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThuChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThuChiFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    View view;
    Button btn1, btn2;
    FragmentThuChiBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThuChiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThuChiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThuChiFragment newInstance(String param1, String param2) {
        ThuChiFragment fragment = new ThuChiFragment();
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

        view = inflater.inflate(R.layout.fragment_thu_chi, container, false);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView2);
        replaceFragment(new TienChiFragment());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nhaptienthu:
                    replaceFragment(new TienThuFragment());

                    break;
                case R.id.nhaptienchi:
                    replaceFragment(new TienChiFragment());

            }
            return true;
        });
        return view;
    }



    public void replaceFragment(Fragment fragment){
        try {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.thuchilayout, fragment).commit();

        }
        catch (Exception e){

        }
    }
}