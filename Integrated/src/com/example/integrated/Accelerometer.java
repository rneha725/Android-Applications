package com.example.integrated;

import com.example.integrated.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Accelerometer extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View rootView=inflater.inflate(R.layout.fragment_accelerometer,container,false);
		return rootView;
	}


}
