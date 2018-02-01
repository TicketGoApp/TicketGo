package vn.ticketgo.taipv.ticketgo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/11/2018
 * Email: tai97nd@gmail.com
 */

public class DownloadTask extends AsyncTask<String, Integer, String> {
    private Context context;
    ProgressDialog progressDialog;

    public DownloadTask(Context context) {
        this.context = context;
    }

    /**
     * Set up a ProgressDialog
     */

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in progress...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
//        progressDialog.show();

    }

    /**
     *  Background task
     */
    @Override
    protected String doInBackground(String... params) {
        String path = params[0];
        int file_length;

        Log.i("Info: path", path);
        try {
            URL url = new URL(path);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            file_length = urlConnection.getContentLength();

            /**
             * Create a folder
             */
            File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");
            if (!new_folder.exists()) {
                boolean success;

                success=new_folder.mkdir();
                if (success) {
                    Log.i("Info", "Folder succesfully created");
                } else {
                    Log.i("Info", "Failed to create folder");
                }
            } else {
                Log.i("Info", "Folder already exists");
            }

            /**
             * Create an output file to store the image for download
             */
            File output_file = new File(new_folder, "ticket.jpg");

//            File output_file = new File(new_folder, String.valueOf(new Random().nextInt(1000))+"image.jpg");
            OutputStream outputStream = new FileOutputStream(output_file);

            InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
            byte [] data = new byte[1024];
            int total = 0;
            int count;
            while ((count = inputStream.read(data)) != -1) {
                total += count;

                outputStream.write(data, 0, count);
                int progress = 100 * total / file_length;
                publishProgress(progress);

                Log.i("Info", "Progress: " + Integer.toString(progress));
            }
            inputStream.close();
            outputStream.close();

            Log.i("Info", "file_length: " + Integer.toString(file_length));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Download complete.";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.hide();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//        File folder = new File("sdcard/ticket");
        File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");
//        File output_file = new File(folder, String.valueOf(new Random().nextInt(1000))+"image.jpg");
        File output_file = new File(new_folder, "ticket.jpg");
        String path = output_file.toString();

        SharedUtils.getInstance().putStringValue("pathImage",path);
        Log.i("Info", "Path: " + path);
    }
}
