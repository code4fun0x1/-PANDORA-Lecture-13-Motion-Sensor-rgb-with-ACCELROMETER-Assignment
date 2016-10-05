# -PANDORA-Lecture-13-Motion-Sensor-rgb-with-ACCELROMETER-Assignment



 @Override
            public void onSensorChanged(SensorEvent event) {

               Log.d(TAG, "onSensorChanged: "+max);
             //   f.add(event.values[0]);


                int x= (int) ((event.values[0]/max)*255);
                int y= (int) ((event.values[1]/max)*255);
                int z= (int) ((event.values[2]/max)*255)-125;
            //    Log.d(TAG, "onSensorChanged: "+x+"    "+y+"    "+b);
               r.setText(String.valueOf(x));
                g.setText(String.valueOf(y));
                b.setText(String.valueOf(z));
                main_activity.setBackgroundColor(Color.rgb(x,y,z));

            }
