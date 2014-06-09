package com.upb.taxbilling.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.upb.taxbilling.R;
import com.upb.taxbilling.model.data.Bill;

/**
 * The fragment where the information about a User and Bill is export to a file
 * @author Kevin Aguilar
 */
public class ExportBill extends Fragment{
	
	/**
	 *Boolean attributes to check availabilities SD memory
	 *Button attribute to run the data export 
	 */
	
	boolean sdDisponible = false;
	boolean sdAccesoEscritura = false;
	Button Export;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_export_bill,
				container, false);
		
		Export = (Button)view.findViewById(R.id.button1);
		Export.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickExport(v);
			}
		});
		
		
	    return view;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	/**
	 * Method to export user data and bill data
	 * This method is called by the button Export
	 * @param v
	 */
	
	public void ClickExport(View v)
	{
		this.ExportDataRegister(this.UserData(),this.BillData());
	}
	
	/**
	 * Method to create the file and write user data and bill data
	 * This method receives as parameters an ArrayList of user data and an ArrayList of bill data
	 * @param ArrayUser
	 * @param ArrayBill
	 */
	
	public void ExportDataRegister(ArrayList<String> ArrayUser, ArrayList<String> ArrayBill){
		
		String estado = Environment.getExternalStorageState();
		
		/**
		 * Two "if" to check availabilities SD memory  
		 */
		
		if (estado.equals(Environment.MEDIA_MOUNTED))
		{
		    sdDisponible = true;
		    sdAccesoEscritura = true;
		}
		else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
		    sdDisponible = true;
		    sdAccesoEscritura = false;
		}
		else
		{
		    sdDisponible = false;
		    sdAccesoEscritura = false;
		}
		try
		{
		    File ruta_sd = Environment.getExternalStorageDirectory();
		 
		    File f = new File(ruta_sd.getAbsolutePath(), "Factura.txt");
		 
		    OutputStreamWriter fout =  new OutputStreamWriter(new FileOutputStream(f));
		
		fout.write("DiCaprio");
		fout.write("\n");
		for(int i=0; i < ArrayUser.size(); i++)
		{
		    	fout.write(ArrayUser.get(i));
		    	fout.write("\n");
		}
		Toast.makeText(getActivity(), "Exportando Datos de Usuario", Toast.LENGTH_SHORT).show();
		fout.write("\n");
		for(int i=0; i < ArrayBill.size(); i++)
		{
		    	fout.write(ArrayBill.get(i));
		    	fout.write("\n");
		}
		Toast.makeText(getActivity(), "Exportando Facturas", Toast.LENGTH_SHORT).show();
		fout.close();
		    
		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
		}		
	}
	
	/**
	 * This method returns an ArrayList of user data sorted  
	 * @return
	 */
	
	public ArrayList<String> UserData()
	{		
		RegisterFragment rf =  new RegisterFragment();
		ArrayList<String> ArrayUser = new ArrayList<String>();
		
		ArrayUser.add(rf.getDataTaxpayer().getNameLastname());
		ArrayUser.add(rf.getDataTaxpayer().getAddress());
		ArrayUser.add(Integer.toString(rf.getDataTaxpayer().getIdentityNumber()));
		ArrayUser.add(rf.getDataTaxpayer().getExpeditionPlace());
		ArrayUser.add(rf.getDataCompany().getEmployerBussinesName());
		ArrayUser.add(Integer.toString(rf.getDataCompany().getNitNumber()));
		ArrayUser.add(rf.getDataCompany().getAddress());
		
		return ArrayUser;
	}
	
	/**
	 * This method returns an ArrayList of bill data sorted
	 * @return
	 */
	
	public ArrayList<String> BillData()
	{
		Date now =  new Date();
		Bill bill1 = new Bill(1, 1, 1, now, 10.45, "asd123");
		Bill bill2 = new Bill(1, 2, 1, now, 10.45, "asd123");

		ArrayList<String> ArrayBill = new ArrayList<String>();
		ArrayList<Bill> ArrayBillData = new ArrayList<Bill>();
		ArrayBillData.add(bill1);
		ArrayBillData.add(bill2);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		for(int i=0; i < ArrayBillData.size() ; i++)
		{
			ArrayBill.add(Integer.toString(ArrayBillData.get(i).getNit())+"|"
		              +Integer.toString(ArrayBillData.get(i).getBillNumber())+"|"
		              +Long.toString(ArrayBillData.get(i).getAutorizationNumber())+"|"
		              +df.format(ArrayBillData.get(i).getEmissionDate())+"|"
				      +Double.toString(ArrayBillData.get(i).getAmount())+"|"
				      +ArrayBillData.get(i).getControlCode());
		}
		return ArrayBill;
	}
}
