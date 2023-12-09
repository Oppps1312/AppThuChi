package com.example.sothuchiv4;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class LichFragment extends Fragment implements CalendarAdapter.OnItemListener {

    TextView textViewDate;
    //DatePickerDialog.OnDateSetListener setListener;

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LichFragment newInstance(String param1, String param2) {
        LichFragment fragment = new LichFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich, container, false);
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        selectedDate = LocalDate.now();
        setMonthView();
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate localDate) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
            YearMonth yearMonth = YearMonth.from(localDate);
            int daysInMonth = yearMonth.lengthOfMonth();

            LocalDate firstOfMonth = localDate.withDayOfMonth(1);
            int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1;i<=42;i++){
            if(i<=dayOfWeek || i> daysInMonth+dayOfWeek){
                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = null;

            formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
            return date.format(formatter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ClickBtn(View v){
        switch (v.getId()){
            case R.id.minusBtn:
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
                break;

            case  R.id.plusBtn:
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
                break;
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = dayText + "/" + monthYearFromDate(selectedDate);
            Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show();
        }
    }
}
