/**
 * 
 */
package info.tabswipe.adapter;

import com.example.integrated.Accelerometer;
import com.example.integrated.Pedometer;
import com.example.integrated.SensorList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author Neha
 *
 */
public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(android.support.v4.app.FragmentManager fm) {
		// TODO Auto-generated constructor stub
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch(index){
		case 0: return new SensorList();
		case 1: return new Accelerometer();
		case 2: return new Pedometer();
		}
		
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
