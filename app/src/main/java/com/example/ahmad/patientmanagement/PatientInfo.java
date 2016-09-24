package com.example.ahmad.patientmanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class PatientInfo extends Fragment {
    public PatientInfo() {
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
        View view = inflater.inflate(R.layout.fragment_patient_info, container, false);
        String firstName = StaffLogin.patientDetails.getFirstname();
        String lastname = StaffLogin.patientDetails.getSurname();

        TextView nameView = (TextView)view.findViewById(R.id.firstName);
        nameView.setText("First Name : " + firstName);

        TextView surnameView = (TextView)view.findViewById(R.id.lastName);
        surnameView.setText("Last Names : " + lastname);

        TextView dobView = (TextView)view.findViewById(R.id.dob);
        String id = StaffLogin.patientDetails.getDOB();
        int year = Integer.parseInt(id.substring(0,2));
        if(year > 16) year+=1900;
        else year+=2000;

        int month = Integer.parseInt(id.substring(2,4));
        int date = Integer.parseInt(id.substring(4,6));
        String m = month+"";
        if(month<10)m="0"+m;
        String dob = year + "-" + m + "-" + date;
        dobView.setText("Date of Birth : " + dob);

        TextView admittedView = (TextView)view.findViewById(R.id.dateAdmitted);
        String admitted = StaffLogin.patientDetails.getDateAdmitted();
        admittedView.setText("Date Admitted: " + admitted);

        TextView emergency = (TextView)view.findViewById(R.id.emergCon);
        String contact = StaffLogin.patientDetails.getEmergencyContact();
        emergency.setText("Contact : " + contact);

        if(!StaffLogin.patientDetails.getImageName().equalsIgnoreCase("default")){
            ImageView image = (ImageView)view.findViewById(R.id.image);
            Bitmap bmp = BitmapFactory.decodeFile("/sdcard/"+StaffLogin.patientDetails.getImageName());
            image.setImageBitmap(bmp);
        }


        return view;
    }
}
