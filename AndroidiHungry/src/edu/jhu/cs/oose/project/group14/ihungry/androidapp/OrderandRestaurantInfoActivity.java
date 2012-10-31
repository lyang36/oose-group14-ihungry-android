package edu.jhu.cs.oose.project.group14.ihungry.androidapp;

import com.example.androidihungry.R;
import com.example.androidihungry.R.layout;
import com.example.androidihungry.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.*;

/**
 * This activity shows the detailed information about the specific restaurant
 * and the menu is listed. Customer could also select food items on the menu.
 * 
 * 
 * @author SuNFloWer
 * 
 */
public class OrderandRestaurantInfoActivity extends Activity {

	static final private String[][] menu_info = {
			{ "i1001", "Chicken with Broccoli", "4.5" },
			{ "i1002", "Assorted Mixed Vegetable", "4.65" },
			{ "i1003", "Shrimp with Lobster Sauce", "4.95" },
			{ "i1004", "Chicken with Cashew Nuts", "5.05" },
			{ "i1005", "B-B-Q Spare Ribs", "5.25" },
			{ "i1006", "Skewered Beef", "4.5" },
			{ "i1007", "Wonton Soup", "1.5" },
			{ "i1008", "House Special Soup", "5.50" }

	};
	static final private String[] menu_info2 = { "Chicken with Broccoli",
			"Assorted Mixed Vegetable", "Shrimp with Lobster Sauce",
			"Chicken with Cashew Nuts", "B-B-Q Spare Ribs", "Skewered Beef",
			"Wonton Soup", "House Special Soup" };

	private TextView tv_rest_name;
	private TextView tv_rest_addr;

	private ListView m_ListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderand_restaurant_info);

		tv_rest_name = (TextView) findViewById(R.id.tv_restaurant);
		tv_rest_addr = (TextView) findViewById(R.id.tv_address);

		/* Get information passed from MyitemizedOverlay */
		Bundle bundle = this.getIntent().getExtras();
		// tv_rest_name.setText(
		// bundle.getCharSequence("Title") + "@\n"
		// + bundle.getInt("LatE6") + " : " + bundle.getInt("LonE6"));

		String rest_id = (String) bundle.getCharSequence("rest_id");
		String rest_name = (String) bundle.getCharSequence("rest_name");
		String rest_addr = (String) bundle.getCharSequence("rest_addr");
		tv_rest_name.setText(rest_name);
		tv_rest_addr.setText(rest_addr);

		m_ListView = (ListView) findViewById(R.id.list);
		m_ListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.list_item_simple, R.id.title, menu_info2));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_orderand_restaurant_info,
				menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// do something on back => Just finish
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
