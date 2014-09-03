package com.ImageViewSvg;

/**
 * 
 * @author Pavel.B.Chernov (based on ideas of kushnarev)
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
    }

    /*
     * Walks through the objects tree
     * When meets ImageView - sets its width and height 
     */
    private void SetChildsWidthHeight(ViewGroup view, int width, int height) {
    	int i;
    	for (i = 0; i < view.getChildCount(); ++i) {
    		View child = view.getChildAt(i);
    		if (child instanceof ViewGroup) {
    			SetChildsWidthHeight((ViewGroup)child, width, height);
    		}
    		else if (child instanceof ImageView) {
    			ImageView img = (ImageView) child;
    			img.setLayoutParams(new TableRow.LayoutParams(width, height));
    		}
    	}
    }

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
    	super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
    		TableLayout table = (TableLayout) findViewById(R.id.TableView);
        	if (table == null) {
        		return;
            }

        	// Set uniform width and height for all ImageViews in table
        	SetChildsWidthHeight(table, table.getWidth() / 3, table.getHeight() / 2);
        }
	}
}